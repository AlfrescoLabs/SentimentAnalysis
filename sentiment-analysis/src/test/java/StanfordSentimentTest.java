import static org.junit.Assert.*;

import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer;
import org.alfresco.ml.sentimentanalysis.stanford.StanfordAnalyzer;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class StanfordSentimentTest {
	SentimentAnalyzer a = new StanfordAnalyzer();
	String[] outputs = new String[] {"Negative", "Neutral", "Positive"};

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@FileParameters("src/test/resources/testdata.csv")
	public void test(int sentiment, String data) {
		String result = a.analyze(data);
		String expectedResult = outputs[sentiment];
		if (!result.contains(expectedResult))
		{
			fail(result + " should be " + expectedResult);
		}
		System.out.println("Success");
	}

}
