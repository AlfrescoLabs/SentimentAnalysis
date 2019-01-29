package org.alfresco.ml.sentimentanalysis.opennlp;

import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer;
import org.alfresco.ml.sentimentanalysis.model.Ranking.ANALYSIS_OUTCOME;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;

public class OpenNLPAnalyzer implements SentimentAnalyzer
{
    private DocumentCategorizerME categorizer;

    // TODO How to configure our model path?
    // TODO When to load the model?

    @Override
    public ANALYSIS_OUTCOME analyzeLine(String line)
    {
        // Split on whitespace or some punctuation
        String[] tokens = line.split("[\\w\\.,]");

        // Categorise
        double[] outcomes = categorizer.categorize(tokens);

        // What is that?
        String category = categorizer.getBestCategory(outcomes);

        // Map to our results
        if (category.equals("positive"))
           return ANALYSIS_OUTCOME.POSITIVE;
        if (category.equals("negative"))
           return ANALYSIS_OUTCOME.NEGATIVE;
        return ANALYSIS_OUTCOME.NEUTRAL;
    }

}
