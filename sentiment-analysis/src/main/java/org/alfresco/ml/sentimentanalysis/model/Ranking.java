package org.alfresco.ml.sentimentanalysis.model;

import org.alfresco.ml.sentimentanalysis.model.Ranking.ANALYSIS_OUTCOME;

/**
 * Created by cleseach on 29/01/2019.
 */
public class Ranking {
    // Number of positive sentences
    int positive;

    // Number of negative sentences
    int negative;

    // Number of neutral sentences
    int neutral;

    public enum ANALYSIS_OUTCOME
    {
        POSITIVE, NEUTRAL, NEGATIVE
    }

    public Ranking(int positive, int negative, int neutral) {
        this.positive = positive;
        this.negative = negative;
        this.neutral = neutral;
    }

    public Ranking() {
        this.positive = 0;
        this.negative = 0;
        this.neutral = 0;
    }
    
    public void increaseOutcome(ANALYSIS_OUTCOME type)
    {
    	switch(type)
    	{
    		case POSITIVE:
    			increasePositive();
    			break;
    		case NEGATIVE:
    			increaseNegative();
    			break;
    		case NEUTRAL:
    			increaseNeutral();
    			break;
    	}
    }
    
    public void increasePositive() {
    	positive++;
    }
    
    public void increaseNegative() {
    	negative++;
    }
    
    public void increaseNeutral() {
    	neutral++;
    }

    public int getPositive() {
        return positive;
    }

    public int getNegative() {
        return negative;
    }

    public int getNeutral() {
        return neutral;
    }
}
