package compiler;

public class Scan {	
	public static int pos;
	
	public Scan() {
		super();
	}
	
	public LexicalUnit scan(String line, int pos) {
		return scanUnit(line, pos);
	}

	public LexicalUnit scanUnit(String line, int pos) { // TODO everything
		String unit = line.substring(pos, pos + 1);
		int action = 0;
		int start;
		int end = pos + 1;
		LexicalUnit tmp = null;
		
		// START SYMBOL
		if (pos == 0) {
			end = line.indexOf("-");
			unit = line.substring(0, end - 1);
			tmp = new LexicalUnit("IDNTER", action, AtomType.NTERM, unit);
			
			setPos(end);
			return tmp;
		}
		else {
			// ELTER
			if ((unit.equals("'"))) {
				start = pos + 1;
				end = line.indexOf("'", start);
				unit = line.substring(start, end);

				if (unit.contains("#")) {
					start = unit.indexOf("#");
					action = Integer.parseInt(line.substring(start + 2, end));
					unit = unit.substring(pos, start);
				}
				tmp = new LexicalUnit("ELTER", action, AtomType.NTERM, unit);
				
				setPos(end + 1);
				return tmp;
			}
			if (unit.equals("-")) {
				if (line.substring(pos, pos + 2).equals("->")) {
					unit = line.substring(pos, pos + 2);
					tmp = new LexicalUnit(unit, action, AtomType.TERM, unit);
					setPos(end + 2);
					return tmp;
				}
			}
			// TODO deal with useless characters ???
			
			unit = line.substring(pos, line.length());
			tmp = new LexicalUnit("IDNTER", action, AtomType.TERM, unit);
			
			setPos(line.length() - 1);
			return tmp;
		}
	}
	
	private void setPos(int pos2) {
		pos = pos2;
		Forest.setPos(pos);
	}

	public int getPos() {
		return pos;
	}
}
