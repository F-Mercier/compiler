package compiler;

public class Union extends Node {
	public Node left;
	public Node right;
	
	public Union(Node left, Node right) {
		super();
		this.left = left;
		this.right = right;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCodeAtom(){
		this.codeAtom = CodeAtom.UNION;
	}
}
