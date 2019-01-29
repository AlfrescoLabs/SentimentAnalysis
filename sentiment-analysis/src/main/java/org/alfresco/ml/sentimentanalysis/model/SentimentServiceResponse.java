package org.alfresco.ml.sentimentanalysis.model;

/**
 * Created by cleseach on 29/01/2019.
 */
public class SentimentServiceResponse 
{
    final Ranking ranking;

    public SentimentServiceResponse(Ranking ranking)
    {
        this.ranking = ranking;
    }
    public Ranking getRanking() 
    {
        return ranking;
    }
}
