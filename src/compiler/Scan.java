package compiler;

import java.util.ArrayList;

public class Scan {
	public int spx, c0 = 0;
	public ArrayList<Integer> pilex = new ArrayList<Integer>();
	public ArrayList<Integer> pcode = new ArrayList<Integer>();
	public ArrayList<String> dicot = new ArrayList<String>();
	public ArrayList<String> dicont = new ArrayList<String>();
	
	public void interpret(int inst) { // TODO check JIF, SUP, SUPE, INF, INFE, EG, DIFF, AND, OR, NOT, ADD, SUB, DIV, MULT, NEG, INC, DEC		make JSR, RSR, RDLN, WRT, WRTLN, STOP
		Instruction tmp = Instruction.valueOf(inst);
		
		switch(tmp) {
		case LDA: // chargement adresse
			spx++;
			pilex.add(spx, pcode.get(c0 + 1));
			c0 = c0 + 2;
			break;
		case LDV: // chargement valeur
			spx++;
			pilex.add(spx, pilex.get(pcode.get(c0 + 1)));
			c0 = c0 + 2;
			break;
		case LDC: // chargement constante
			spx++;
			pilex.add(spx, pcode.get(c0 + 1));
			c0 = c0 + 2;
			break;
		case JMP: // saut adresse
			c0 = pcode.get(c0 + 1);
			break;
		case JIF: // saut adresse si faux
			if(pilex.get(spx) == 1) c0 = pcode.get(c0 + 1);
			break;
		case JSR: // saut sous-routine
			break;
		case RSR: // retour sous-routine
			break;
		case SUP: // >
			if(pilex.get(spx) > pilex.get(spx - 1)) {
				spx++;
				pilex.add(spx, 1);
			}
			break;
		case SUPE: // >=
			if(pilex.get(spx) >= pilex.get(spx - 1)) {
				spx++;
				pilex.add(spx, 1);
			}
			break;
		case INF: // <
			if(pilex.get(spx) < pilex.get(spx - 1)) {
				spx++;
				pilex.add(spx, 1);
			}
			break;
		case INFE: // <=
			if(pilex.get(spx) <= pilex.get(spx - 1)) {
				spx++;
				pilex.add(spx, 1);
			}
			break;
		case EG: // =
			if(pilex.get(spx) == pilex.get(spx - 1)) {
				spx++;
				pilex.add(spx, 1);
			}
			break;
		case DIFF: // !=
			if(pilex.get(spx) != pilex.get(spx - 1)) {
				spx++;
				pilex.add(spx, 1);
			}
			break;
		case AND:
			if(pilex.get(spx) + pilex.get(spx - 1) == 2) {
				spx++;
				pilex.add(spx, 1);
			}
			break;
		case OR:
			if(pilex.get(spx) + pilex.get(spx - 1) == 1) {
				spx++;
				pilex.add(spx, 1);
			}
			break;
		case NOT:
			pilex.add(spx, - pilex.get(spx));
			break;
		case ADD: // +
			spx++;
			pilex.add(spx, pilex.get(spx - 2) + pilex.get(spx - 1));
			break;
		case SUB: // -
			spx++;
			pilex.add(spx, pilex.get(spx - 2) - pilex.get(spx - 1));
			break;
		case DIV: // /
			spx++;
			pilex.add(spx, pilex.get(spx - 2) / pilex.get(spx - 1));
			break;
		case MULT: // *
			spx++;
			pilex.add(spx, pilex.get(spx - 2) * pilex.get(spx - 1));
			break;
		case NEG:
			pilex.add(spx, - pilex.get(spx));
			break;
		case INC: // ++
			pilex.add(spx, pilex.get(spx) + 1);
			break;
		case DEC: // --
			pilex.add(spx, pilex.get(spx) - 1);
			break;
		case RD: // lecture
			spx++;
			pilex.add(spx, 0); // pilex[spx] = scan() ???
			c0++;
			break;
		case RDLN: // lecture retour
			break;
		case WRT: // ecriture
			break;
		case WRTLN: // ecriture retour
			break;
		case AFF: // affecter
			pilex.add(pilex.get(spx - 1), pilex.get(spx));
			spx = spx - 2;
			c0++;
			break;
		case STOP: // arret
			break;
		}
	}
	
	public void execut() {
		while(pcode.get(c0) != Instruction.STOP.getPos()) {
			interpret(pcode.get(c0));
		}
	}
	public Node scan(String line) { // TODO find if return type needed
		return scanUnit(line, 0);
	}

	public Node scanUnit(String line, int pos) { // TODO everything
		String unit = line.substring(pos, pos + 1);
		System.out.println(unit + " scanned");
		
		if(pos < (line.length() - 1)) {
			scanUnit(line, pos + 1);
		}
		
		return null;
	}
}
