package compiler;

public class Un extends Node {
	public Node node;
	
	public Un(Node node) {
		super();
		this.node = node;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCodeAtom(){
		this.codeAtom = CodeAtom.UN;
	}
}
