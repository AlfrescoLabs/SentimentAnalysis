package org.alfresco.ml.sentimentanalysis;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.alfresco.ml.sentimentanalysis.model.Ranking.ANALYSIS_OUTCOME;
import org.alfresco.ml.sentimentanalysis.opennlp.OpenNLPAnalyzer;
import org.junit.Before;
import org.junit.Test;


public class SentimentAnalyzerTest
{
    OpenNLPAnalyzer analyzer;
    @Before
    public void prep() throws IOException
    {
        analyzer = new OpenNLPAnalyzer();
    }
    @Test
    public void testPositive()
    {
        assertEquals(ANALYSIS_OUTCOME.POSITIVE, analyzer.analyzeLine("I love hackathons"));
    }
    @Test
    public void testNegative()
    {
        assertEquals(ANALYSIS_OUTCOME.NEGATIVE, analyzer.analyzeLine("I hate hackathons"));
    }
}
