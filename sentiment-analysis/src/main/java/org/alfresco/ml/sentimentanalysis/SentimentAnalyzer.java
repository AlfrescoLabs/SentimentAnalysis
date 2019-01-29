package org.alfresco.ml.sentimentanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public abstract class SentimentAnalyzer
{

    public enum ANALYSYS_OUTCOME
    {
        POSITIVE, NEUTRAL, NEGATIVE
    }

    protected abstract ANALYSYS_OUTCOME analyzeLine(String line);

    public Map<ANALYSYS_OUTCOME, Integer> analyzeBySentences(String text)
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
            System.out.println("Sentence: " + line + " outcome: " + outcome.toString());
            result.put(outcome, result.get(outcome) + 1);
        }

        return result;
    }

    protected List<String> splitTextBySentences(String text)
    {
        List<String> result = new ArrayList<String>();
        if (StringUtils.isBlank(text))
        {
            return result;
        }

        result.addAll(Arrays.asList(StringUtils.split(text, ".")));

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
