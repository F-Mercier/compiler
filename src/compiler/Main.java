package compiler;

public class Main {

	public static void main(String[] args) {
		Forest aForest = new Forest();

		aForest.generateForest();
		
		aForest.showForest();

		aForest.verifAnalyze();
		
		aForest.scan("test");
	}

}
