package compiler;

public class Atom extends Node {
	public String cod;
	public int action;
	public AtomType aType;
	
	public Atom(String cod, int action, AtomType aType) {
		super();
		this.cod = cod;
		this.action = action;
		this.aType = aType;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCodeAtom(){
		this.codeAtom = CodeAtom.ATOM;
	}
	
	public void displayAtom(String space) {
		System.out.println("|" + space + cod + " " + aType);
		if (action > 0) System.out.println("|" + space + "#" + action);
	}
	
	public void displayAtomBis() {
		if (aType == AtomType.NTERM) System.out.print(cod);
		else {
			System.out.print("\'" + cod);
			if (action > 0) System.out.print("#" + action);
			System.out.print("\'");
		}
		
	}
}
