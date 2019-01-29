package org.alfresco.ml.sentimentanalysis;

import org.alfresco.ml.sentimentanalysis.stanford.StanfordAnalyzer;

public class App {

	public static void main(String... args) throws Exception {
		SentimentAnalyzer sa = new StanfordAnalyzer();
		System.out.println(sa.analyze("You have no idea about nothing, you're stupid!"));
	}

}
