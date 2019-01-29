package org.alfresco.ml.sentimentanalysis.model;

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

    public Ranking(int positive, int negative, int neutral) {
        this.positive = positive;
        this.negative = negative;
        this.neutral = neutral;
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
