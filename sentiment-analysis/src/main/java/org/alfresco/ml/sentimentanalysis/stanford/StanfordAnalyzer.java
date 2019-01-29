package org.alfresco.ml.sentimentanalysis.stanford;

import java.util.Properties;

import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;

public class StanfordAnalyzer implements SentimentAnalyzer
{

    StanfordCoreNLP tokenizer;
    StanfordCoreNLP pipeline;

    public StanfordAnalyzer()
    {
        Properties pipelineProps = new Properties();
        Properties tokenizerProps = new Properties();
        pipelineProps.setProperty("annotators", "parse, sentiment");
        pipelineProps.setProperty("parse.binaryTrees", "true");
        pipelineProps.setProperty("enforceRequirements", "false");
        tokenizerProps.setProperty("annotators", "tokenize ssplit");
        tokenizer = new StanfordCoreNLP(tokenizerProps);
        pipeline = new StanfordCoreNLP(pipelineProps);
    }

    public ANALYSYS_OUTCOME analyzeLine(String line)
    {
        Annotation annotation = tokenizer.process(line);
        pipeline.annotate(annotation);
        String output = "";
        output = annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0).get(SentimentCoreAnnotations.SentimentClass.class);

        logger.debug(output);

        switch (output.trim())
        {
            case "Negative":
                return ANALYSYS_OUTCOME.NEGATIVE;
            case "Positive":
                return ANALYSYS_OUTCOME.POSITIVE;
            case "Neutral":
            case "Unsure":
            default:
                return ANALYSYS_OUTCOME.NEUTRAL;

        }
    }
}
