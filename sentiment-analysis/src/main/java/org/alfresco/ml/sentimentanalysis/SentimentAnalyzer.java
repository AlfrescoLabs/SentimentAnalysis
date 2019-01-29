package org.alfresco.ml.sentimentanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public interface SentimentAnalyzer
{

    public Logger logger = Logger.getLogger(SentimentAnalyzer.class);

    public enum ANALYSYS_OUTCOME
    {
        POSITIVE, NEUTRAL, NEGATIVE
    }

    public ANALYSYS_OUTCOME analyzeLine(String line);

    default public Map<ANALYSYS_OUTCOME, Integer> analyzeBySentences(String text)
    {
        List<String> sentences = splitTextBySentences(text);
        Map<ANALYSYS_OUTCOME, Integer> result = new HashMap<>();
        result.put(ANALYSYS_OUTCOME.POSITIVE, 0);
        result.put(ANALYSYS_OUTCOME.NEGATIVE, 0);
        result.put(ANALYSYS_OUTCOME.NEUTRAL, 0);

        if (sentences == null || sentences.isEmpty())
        {
            return null;
        }

        for (String line : sentences)
        {
            ANALYSYS_OUTCOME outcome = analyzeLine(line);
            logger.debug("Sentence: " + line + " outcome: " + outcome.toString());
            result.put(outcome, result.get(outcome) + 1);
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
