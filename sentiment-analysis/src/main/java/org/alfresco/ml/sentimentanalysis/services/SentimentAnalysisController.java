package org.alfresco.ml.sentimentanalysis.services;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cleseach on 29/01/2019.
 */

import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer;
import org.alfresco.ml.sentimentanalysis.model.Ranking;
import org.alfresco.ml.sentimentanalysis.model.SentimentServiceResponse;
import org.alfresco.ml.sentimentanalysis.opennlp.OpenNLPAnalyzer;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyze")
public class SentimentAnalysisController
{
    @GetMapping(value = "/{text}", produces = "application/json")
    public SentimentServiceResponse analyzeOpenNLP(@PathVariable String text) throws IOException
    {
        SentimentAnalyzer sa = new OpenNLPAnalyzer();
        Ranking ranking = sa.analyzeBySentences(text);
        
        return new SentimentServiceResponse(ranking);
    }

    @PostMapping(value = "/text", produces = "application/json")
    public SentimentServiceResponse analyzeTextOpenNLP(@RequestBody String text) throws IOException
    {
        SentimentAnalyzer sa = new OpenNLPAnalyzer();
        Ranking ranking = sa.analyzeBySentences(text);
        return new SentimentServiceResponse(ranking);
    }

    @PostMapping(value = "/binary", produces = "application/json")
    public SentimentServiceResponse acceptData(InputStream dataStream) throws IOException
    {
        SentimentAnalyzer sa = new OpenNLPAnalyzer();
        String text = IOUtils.toString(dataStream, "UTF-8");
        Ranking ranking = sa.analyzeBySentences(text);
        return new SentimentServiceResponse(ranking);
        
    }
}
