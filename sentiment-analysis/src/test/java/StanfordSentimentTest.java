import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer;
import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer.ANALYSYS_OUTCOME;
import org.alfresco.ml.sentimentanalysis.stanford.StanfordAnalyzer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class StanfordSentimentTest
{
    SentimentAnalyzer a = new StanfordAnalyzer();
    ANALYSYS_OUTCOME[] outputs = new ANALYSYS_OUTCOME[] { ANALYSYS_OUTCOME.NEGATIVE, ANALYSYS_OUTCOME.NEUTRAL, ANALYSYS_OUTCOME.POSITIVE };

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    @FileParameters("src/test/resources/testdata.csv")
    public void test(int sentiment, String data)
    {
        Map<ANALYSYS_OUTCOME, Integer> result = a.analyzeBySentences(data);
        ANALYSYS_OUTCOME expectedResult = outputs[sentiment];
        assertTrue(result.get(expectedResult) > 0);
    }

}
