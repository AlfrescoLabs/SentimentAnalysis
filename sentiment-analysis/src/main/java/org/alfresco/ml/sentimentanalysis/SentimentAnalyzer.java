package org.alfresco.ml.sentimentanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.ml.sentimentanalysis.model.Ranking;
import org.alfresco.ml.sentimentanalysis.model.Ranking.ANALYSIS_OUTCOME;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public interface SentimentAnalyzer
{

    public Logger logger = Logger.getLogger(SentimentAnalyzer.class);

    public ANALYSIS_OUTCOME analyzeLine(String line);

    default public Ranking analyzeBySentences(String text)
    {
        List<String> sentences = splitTextBySentences(text);
        Ranking result = new Ranking();

        if (sentences == null || sentences.isEmpty())
        {
            return null;
        }

        for (String line : sentences)
        {
            ANALYSIS_OUTCOME outcome = analyzeLine(line);
            logger.debug("Sentence: " + line + " outcome: " + outcome.toString());
            result.increaseOutcome(outcome);
        }

        return result;
    }

    default public List<String> splitTextBySentences(String text)
    {
        List<String> result = new ArrayList<String>();
        if (StringUtils.isBlank(text))
        {
            return result;
        }

        result.addAll(Arrays.asList(StringUtils.split(text, "!|\\.|\\?")));

        List<String> stringsToRemove = new ArrayList<String>();
        for (String s : result)
        {
            if (StringUtils.isBlank(s))
            {
                stringsToRemove.add(s);
            }
        }
        result.removeAll(stringsToRemove);
        return result;
    }
}
