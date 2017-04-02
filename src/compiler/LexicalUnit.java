package compiler;

public class LexicalUnit {
	public String cod;
	public int action;
	public AtomType aType;
	public String chain;

	public LexicalUnit(String cod, int action, AtomType aType, String chain) {
		super();
		this.cod = cod;
		this.action = action;
		this.aType = aType;
		this.chain = chain;
	}
}
