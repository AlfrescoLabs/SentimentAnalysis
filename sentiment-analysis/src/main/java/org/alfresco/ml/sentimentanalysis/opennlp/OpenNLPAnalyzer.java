package org.alfresco.ml.sentimentanalysis.opennlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer;
import org.alfresco.ml.sentimentanalysis.model.Ranking.ANALYSIS_OUTCOME;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;

public class OpenNLPAnalyzer implements SentimentAnalyzer
{
    private DocumentCategorizerME categorizer;

    // TODO How to configure our model path?
    public static final String modelPath = 
               "src/main/resources/models/opennlp-sentiments.model";
    
    public OpenNLPAnalyzer() throws IOException 
    {
        FileInputStream input = new FileInputStream(new File(modelPath));
        DoccatModel model = new DoccatModel(input);
        categorizer = new DocumentCategorizerME(model);
    }

    @Override
    public ANALYSIS_OUTCOME analyzeLine(String line)
    {
        // Split on whitespace or some punctuation
        // TODO Use an OpenNLP tokenizer to do this for us
        String[] tokens = line.split("[\\s\\.,]+");

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
