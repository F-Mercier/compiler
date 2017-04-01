package compiler;

public class Star extends Node {
	public Node node;
	
	public Star(Node node) {
		super();
		this.node = node;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCodeAtom(){
		this.codeAtom = CodeAtom.STAR;
	}
}
