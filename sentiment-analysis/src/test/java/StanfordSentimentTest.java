import static org.junit.Assert.assertEquals;

import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer;
import org.alfresco.ml.sentimentanalysis.model.Ranking.ANALYSIS_OUTCOME;
import org.alfresco.ml.sentimentanalysis.stanford.StanfordAnalyzer;
import org.junit.Test;


public class StanfordSentimentTest
{
    SentimentAnalyzer a = new StanfordAnalyzer();
    @Test
    public void testPositive()
    {
        assertEquals(ANALYSIS_OUTCOME.POSITIVE,a.analyzeLine("I really love hackathons"));
    }
    @Test
    public void testNegative()
    {
        assertEquals(ANALYSIS_OUTCOME.NEGATIVE, a.analyzeLine("I hate hackathons"));
    }
    @Test
    public void testNeutral()
    {
        assertEquals(ANALYSIS_OUTCOME.NEUTRAL, a.analyzeLine("its a hackathon"));
    }
}
