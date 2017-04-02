package compiler;

public class Scan {	
	public Scan() {
		super();
	}
	
	public LexicalUnit scan(String line) {
		System.out.println(line);
		return scanUnit(line, 0);
	}

	public LexicalUnit scanUnit(String line, int pos) { // TODO everything
		String unit = line.substring(pos, pos + 1);
		System.out.println(unit + " scanned");
		
		if(pos < (line.length() - 1)) {
			scanUnit(line, pos + 1);
		}
		
		/*
		 * IF VARIABLE ALORS COD = ELTER
		 * IF NOM AVANT LA FLECHE ALORS IDNTER
		 * IF FLECHE ALORS IDNTER
		 * PLEIN AUTRES CAS
		 */
		
		return new LexicalUnit(unit, 0, AtomType.NTERM, unit);
	}
}
