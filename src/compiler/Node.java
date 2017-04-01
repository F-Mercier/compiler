package compiler;

public abstract class Node {
	public CodeAtom codeAtom;
	public String name;
	
	public abstract void setName(String name);
}
