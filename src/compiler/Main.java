package compiler;

public class Main {

	public static void main(String[] args) {
		Forest aForest = new Forest();
		Scan aScan;

		aForest.generateForest();
		
		aForest.showForest();
		aForest.showForestBis();

		aForest.verifAnalyze();
	}

}
