package compiler;

public enum Instruction {
	LDA(1), LDV(2), LDC(3), JMP(4), JIF(5), JSR(6), RSR(7), SUP(8), SUPE(9), INF(10), INFE(11), EG(12), DIFF(13), AND(14), OR(15), NOT(16), ADD(17), SUB(18), DIV(19), MULT(20), NEG(21), INC(22), DEC(23), RD(24), RDLN(25), WRT(26), WRTLN(27), AFF(28), STOP(29);
	int pos;
	
	Instruction(int pos) {
		this.pos = pos;
	}
	
	public int getPos() {
		return pos;
	}
	
	public static Instruction valueOf(int action) {
		for(Instruction inst : Instruction.values()) {
			if (inst.pos == action) return inst;
		}
		return null;
	}
}
