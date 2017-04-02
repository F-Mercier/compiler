package compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Forest {
	public ArrayList<Node> forest = new ArrayList<Node>(5);
	public String space = "   ";
	public Stack<Node> stack = new Stack<Node>();
	public Scan aScan = new Scan();
	public PCode pCode = new PCode();
	public static int pos;
	
	public void generateForest() {
		Node S = new Conc(
				new Star(
						new Conc(
								new Conc(
										new Conc(
												new Atom("N", 0, AtomType.NTERM),
												new Atom("->", 0, AtomType.TERM)),
										new Atom("E", 0, AtomType.NTERM)),
								new Atom(",", 1, AtomType.TERM))),
				new Atom(";", 0, AtomType.TERM));
		
		S.setName("S");
		
		forest.add(S);
	
		Node N = new Atom("IDNTER", 2, AtomType.TERM);
		
		N.setName("N");
		
		forest.add(N);
		
		Node E = new Conc(
				new Atom("T", 0, AtomType.NTERM),
				new Star(
						new Conc(
								new Atom("+", 0, AtomType.TERM),
								new Atom("T", 3, AtomType.NTERM))));
		
		E.setName("E");
		
		forest.add(E);
		
		Node T = new Conc(
				new Atom("F", 0, AtomType.NTERM),
				new Star(
						new Conc(
								new Atom(".", 0, AtomType.TERM),
								new Atom("F", 4, AtomType.NTERM))));
		
		T.setName("T");
		
		forest.add(T);
		
		Node F = new Union(
				new Union(
						new Union(
								new Union(
										new Atom("IDNTER", 5, AtomType.TERM),
										new Atom("ELTER", 5, AtomType.TERM)),
								new Conc(
										new Atom("(", 0, AtomType.TERM),
										new Conc(
												new Atom("E", 0, AtomType.NTERM),
												new Atom(")", 0, AtomType.TERM)))),
						new Conc(
								new Atom("[", 0, AtomType.TERM),
								new Conc(
										new Atom("E", 0, AtomType.NTERM),
										new Atom("]", 6, AtomType.TERM)))),
				new Conc(
						new Atom("(|", 0, AtomType.TERM),
						new Conc(
								new Atom("E", 0, AtomType.NTERM),
								new Atom("|)", 7, AtomType.TERM))));
		
		F.setName("F");
		
		forest.add(F);
	}
	
	public void showForest() {
		System.out.println("==============\n||  FOREST  ||\n==============");
		for(int i = 0 ; i < 5 ; i++) {
			System.out.println("=====" + forest.get(i).name + "=====");
			showTree(forest.get(i), space);
		}
	}
	
	public void showForestBis() {
		System.out.println("==============\n||  FOREST  ||\n==============");
		for(int i = 0 ; i < 5 ; i++) {
			System.out.print(forest.get(i).name + " -> ");
			showTreeBis(forest.get(i));
			if (i < 4) System.out.println(",");
			else System.out.println(";");
		}
	}
	
	public void showTree(Node n, String space) {		
		if (n instanceof Conc) ((Conc) n).setCodeAtom();
		if (n instanceof Union) ((Union) n).setCodeAtom();
		if (n instanceof Star) ((Star) n).setCodeAtom();
		if (n instanceof Un) ((Un) n).setCodeAtom();
		if (n instanceof Atom) ((Atom) n).setCodeAtom();
		
		switch(n.codeAtom) {
		case CONC: 
			System.out.println("|" + space + "CONC");
			showTree(((Conc) n).left, space + "|   ");
			System.out.println("|" + space + "|    ");
			showTree(((Conc) n).right, space + "|   ");
			break;
		case UNION:
			System.out.println("|" + space + "UNION");
			showTree(((Union) n).left, space + "|   ");
			showTree(((Union) n).right, space + "|   ");
			break;
		case STAR:
			System.out.println("|" + space + "STAR");
			showTree(((Star) n).node, space + "|   ");
			break;
		case UN:
			System.out.println("|" + space + "UN");
			showTree(((Un) n).node, space + "|   ");
			break;
		case ATOM:
			System.out.println("|" + space + "ATOM");
			((Atom) n).displayAtom(space);
			break;
		}
	}
	
	public void showTreeBis(Node n) {
		if (n instanceof Conc) ((Conc) n).setCodeAtom();
		if (n instanceof Union) ((Union) n).setCodeAtom();
		if (n instanceof Star) ((Star) n).setCodeAtom();
		if (n instanceof Un) ((Un) n).setCodeAtom();
		if (n instanceof Atom) ((Atom) n).setCodeAtom();
		
		switch(n.codeAtom) {
		case CONC: 
			showTreeBis(((Conc) n).left);
			System.out.print(" . ");
			showTreeBis(((Conc) n).right);
			break;
		case UNION:
			showTreeBis(((Union) n).left);
			System.out.print(" + ");
			showTreeBis(((Union) n).right);
			break;
		case STAR:
			System.out.print("[");
			showTreeBis(((Star) n).node);
			System.out.print("]");
			break;
		case UN:
			showTreeBis(((Un) n).node);
			break;
		case ATOM:
			((Atom) n).displayAtomBis();
			break;
		}
	}
	
	public void verifAnalyze() {
		System.out.println("===============\n||  ANALYSE  ||\n===============");
		if (analyze(forest.get(0))) System.out.println("Analyse OK");
		else System.out.println("!Analyse pas OK");
	}
	
	public boolean analyze(Node n) { // TODO fix the ATOM/TERM case
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new FileReader("src/g0"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean result = true;
		switch(n.codeAtom) {
		case CONC: 
			if (analyze(((Conc) n).left)) result = analyze(((Conc) n).right);
			else {
				result = false;
				System.out.println("!Analyse Conc left");
			}
			break;
		case UNION:
			if (analyze(((Union) n).left)) result = analyze(((Union) n).right);
			else {
				result = false;
				System.out.println("!Analyse Union left");
			}
			break;
		case STAR:
			result = analyze(((Star) n).node);
			break;
		case UN:
			result = analyze(((Un) n).node);
			break;
		case ATOM:
			switch(((Atom) n).aType) {
			case TERM:
				if (((Atom) n).cod.equals(aScan.scan(line, aScan.getPos()).cod)) {
					result = true;
					if (((Atom) n).action != 0) {
						gZeroAction(((Atom) n).cod, ((Atom) n).action, ((Atom) n).aType);
					}
					pos = aScan.getPos();
				}
				else {
					result = false;
					System.out.println("!Analyse Atom TERM " + ((Atom) n).cod);
					System.out.println(((Atom) n).cod + " != " + aScan.scan(line, aScan.getPos()).cod);
					System.out.println("SCAN : " + aScan.scan(line, aScan.getPos()).cod + aScan.scan(line, aScan.getPos()).action + aScan.scan(line, aScan.getPos()).aType);
					System.out.println("ATOM : " + ((Atom) n).cod + ((Atom) n).action + ((Atom) n).aType);
					System.out.println(pos);
					System.out.println(line.charAt(pos));
					System.out.println(line);
				}
				break;
			case NTERM:
				if (analyze(forest.get(findNode(((Atom) n).cod)))) {
					if (((Atom) n).action != 0) {
						gZeroAction(((Atom) n).cod, ((Atom) n).action, ((Atom) n).aType);
					}
					result = true;
				}
				else {
					result = false;
					System.out.println("!Analyse Atom NTERM " + ((Atom) n).cod);
				}
				break;
			}
			break;
		default:
			result = true;
		}
		return result;
	}
	
	public int findNode(String cod) {
		int pos = 0;
		for (Node n : forest) {
			if (n.name.equals(cod)) pos = forest.indexOf(n);
		}
		return pos;
	}
	
	public void gZeroAction(String cod, int action, AtomType aType) { // TODO make search in dictionaries, replace cod by search
		Node n1, n2 = null;
		
		switch(action) {
		case 1:
			n1 = stack.pop();
			n2 = stack.pop();
			forest.add(n1);
			break;
		case 2:
			stack.push(new Atom(cod, action, aType)); // Recherche(DICONT) ???
			break;
		case 3:
			n1 = stack.pop();
			n2 = stack.pop();
			stack.push(new Union(n1, n2));
			break;
		case 4:
			n1 = stack.pop();
			n2 = stack.pop();
			stack.push(new Conc(n1, n2));
			break;
		case 5:
			if (aType == AtomType.TERM) {
				stack.push(new Atom(cod, action, AtomType.TERM)); // Recherche(DICOT) ???
			}
			else {
				stack.push(new Atom(cod, action, AtomType.NTERM)); // Recherche(DICONT) ???
			}
			break;
		case 6:
			n1 = stack.pop();
			stack.push(new Star(n1));
			break;
		case 7:
			n1 = stack.pop();
			stack.push(new Un(n1));
			break;
		}
	}
	
	public static void setPos(int pos2) {
		pos = pos2;
	}
}
