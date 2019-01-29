package org.alfresco.ml.sentimentanalysis.opennlp;

import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer;
import org.alfresco.ml.sentimentanalysis.model.Ranking.ANALYSIS_OUTCOME;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;

public class OpenNLPAnalyzer implements SentimentAnalyzer
{

    @Override
    public ANALYSIS_OUTCOME analyzeLine(String line)
    {
        return null;
    }

}
