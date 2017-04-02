package compiler;

import java.util.ArrayList;

public class PCode {
	public int spx, c0 = 0;
	public ArrayList<Integer> pilex = new ArrayList<Integer>();
	public ArrayList<Integer> pcode = new ArrayList<Integer>();
	public ArrayList<String> dicot = new ArrayList<String>();
	public ArrayList<String> dicont = new ArrayList<String>();
	
	public void interpret(int inst) { // TODO check implement LDA, LDV, LDC, JMP, RD, AFF
		// TODO check algo and implement JIF, SUP, SUPE, INF, INFE, EG, DIFF, AND, OR, NOT, ADD, SUB, DIV, MULT, NEG, INC, DEC, RDLN, WRT, WRTLN, STOP
		// TODO make JSR, RSR if needed
		Instruction tmp = Instruction.valueOf(inst);
		
		switch(tmp) {
		// LOADING
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
		// JUMP
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
		// RELATION OPERATOR
		case SUP: // >
			spx++;
			if(pilex.get(spx) > pilex.get(spx - 1)) {
				pilex.add(spx, 1);
			}
			else {
				pilex.add(spx, 0);
			}
			c0++;
			break;
		case SUPE: // >=
			spx++;
			if(pilex.get(spx) >= pilex.get(spx - 1)) {
				pilex.add(spx, 1);
			}
			else {
				pilex.add(spx, 0);
			}
			c0++;
			break;
		case INF: // <
			spx++;
			if(pilex.get(spx) < pilex.get(spx - 1)) {
				pilex.add(spx, 1);
			}
			else {
				pilex.add(spx, 0);
			}
			c0++;
			break;
		case INFE: // <=
			spx++;
			if(pilex.get(spx) <= pilex.get(spx - 1)) {
				pilex.add(spx, 1);
			}
			else {
				pilex.add(spx, 0);
			}
			c0++;
			break;
		case EG: // =
			spx++;
			if(pilex.get(spx) == pilex.get(spx - 1)) {
				pilex.add(spx, 1);
			}
			else {
				pilex.add(spx, 0);
			}
			c0++;
			break;
		case DIFF: // !=
			spx++;
			if(pilex.get(spx) != pilex.get(spx - 1)) {
				pilex.add(spx, 1);
			}
			else {
				pilex.add(spx, 0);
			}
			c0++;
			break;
		// LOGIC OPERATOR
		case AND:
			spx++;
			if(pilex.get(spx) + pilex.get(spx - 1) == 2) {
				pilex.add(spx, 1);
			}
			else {
				pilex.add(spx, 0);
			}
			c0++;
			break;
		case OR:
			spx++;
			if(pilex.get(spx) + pilex.get(spx - 1) >= 1) {
				pilex.add(spx, 1);
			}
			else {
				pilex.add(spx, 0);
			}
			c0++;
			break;
		case NOT:
			spx++;
			if(pilex.get(spx) == 0) {
				pilex.add(spx, 1);
			}
			else {
				pilex.add(spx, 0);
			}
			c0++;
			break;
		// ARITHMETIC OPERATOR
		case ADD: // +
			spx++;
			pilex.add(spx, pilex.get(spx - 2) + pilex.get(spx - 1));
			c0++;
			break;
		case SUB: // -
			spx++;
			pilex.add(spx, pilex.get(spx - 2) - pilex.get(spx - 1));
			c0++;
			break;
		case DIV: // /
			spx++;
			pilex.add(spx, pilex.get(spx - 2) / pilex.get(spx - 1));
			c0++;
			break;
		case MULT: // *
			spx++;
			pilex.add(spx, pilex.get(spx - 2) * pilex.get(spx - 1));
			c0++;
			break;
		case NEG:
			pilex.add(spx, - pilex.get(spx));
			c0++;
			break;
		case INC: // ++
			pilex.add(spx, pilex.get(spx) + 1);
			c0++;
			break;
		case DEC: // --
			pilex.add(spx, pilex.get(spx) - 1);
			c0++;
			break;
		// INPUT-OUTPUT
		case RD: // lecture
			spx++;
			pilex.add(spx, 0); // pilex[spx] = scan() ???
			c0++;
			break;
		case RDLN: // lecture retour
			spx++;
			pilex.add(spx, 0); // pilex[spx] = scan() + retour chariot ???
			c0++;
			break;
		case WRT: // ecriture
			System.out.print(pilex.get(spx));
			c0++;
			break;
		case WRTLN: // ecriture retour
			System.out.println(pilex.get(spx));
			c0++;
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
}
