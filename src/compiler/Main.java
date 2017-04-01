package compiler;

public class Main {

	public static void main(String[] args) {
		Forest aForest = new Forest();

		aForest.generateForest();
		
		aForest.showForest();
		
		System.out.println(aForest.findNode("S"));
		System.out.println(aForest.findNode("N"));
		System.out.println(aForest.findNode("E"));
		System.out.println(aForest.findNode("T"));
		System.out.println(aForest.findNode("F"));
		
		if (aForest.analyze(aForest.forest.get(4))) System.out.println("Analyse OK");
		else System.out.println("Analyse pas OK");
	}

}
