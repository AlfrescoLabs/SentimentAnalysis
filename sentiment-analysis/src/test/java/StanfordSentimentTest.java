import static org.junit.Assert.assertEquals;

import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer;
import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer.ANALYSYS_OUTCOME;
import org.alfresco.ml.sentimentanalysis.stanford.StanfordAnalyzer;
import org.junit.Test;


public class StanfordSentimentTest
{
    SentimentAnalyzer a = new StanfordAnalyzer();
    ANALYSYS_OUTCOME[] outputs = new ANALYSYS_OUTCOME[] { 
            ANALYSYS_OUTCOME.NEGATIVE, 
            ANALYSYS_OUTCOME.NEUTRAL, 
            ANALYSYS_OUTCOME.POSITIVE };

    @Test
    public void testPositive()
    {
        assertEquals(ANALYSYS_OUTCOME.POSITIVE,a.analyzeLine("I really love hackathons"));
    }
    @Test
    public void testNegative()
    {
        assertEquals(ANALYSYS_OUTCOME.NEGATIVE, a.analyzeLine("I hate hackathons"));
    }
    @Test
    public void testNeutral()
    {
        assertEquals(ANALYSYS_OUTCOME.NEUTRAL, a.analyzeLine("its a hackathon"));
    }
}
