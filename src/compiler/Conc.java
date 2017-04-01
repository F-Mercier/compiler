package compiler;

public class Conc extends Node {
	public Node left;
	public Node right;
	
	public Conc(Node left, Node right) {
		super();
		this.left = left;
		this.right = right;
		setCodeAtom();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCodeAtom(){
		this.codeAtom = CodeAtom.CONC;
	}
}
