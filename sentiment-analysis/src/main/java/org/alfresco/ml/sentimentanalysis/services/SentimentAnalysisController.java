package org.alfresco.ml.sentimentanalysis.services;

/**
 * Created by cleseach on 29/01/2019.
 */

import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer;
import org.alfresco.ml.sentimentanalysis.model.Ranking;
import org.alfresco.ml.sentimentanalysis.model.SentimentServiceResponse;
import org.alfresco.ml.sentimentanalysis.stanford.StanfordAnalyzer;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;


@RestController
@RequestMapping("/analyze")
public class SentimentAnalysisController {

    @GetMapping(value = "/{text}", produces = "application/json")
    public SentimentServiceResponse analyze(@PathVariable String text) {
        SentimentAnalyzer sa = new StanfordAnalyzer();
        Ranking ranking = sa.analyzeBySentences(text);
        return new SentimentServiceResponse(ranking);
    }

    @PostMapping(value = "/text", produces = "application/json")
    public SentimentServiceResponse analyzeText(@RequestBody String text) {
        SentimentAnalyzer sa = new StanfordAnalyzer();
        Ranking ranking = sa.analyzeBySentences(text);
        return new SentimentServiceResponse(ranking);
    }

    @PostMapping(value = "/binary", produces = "application/json")
    public SentimentServiceResponse acceptData(InputStream dataStream) throws IOException {
        SentimentAnalyzer sa = new StanfordAnalyzer();
    	String text = IOUtils.toString(dataStream, "UTF-8");
        Ranking ranking = sa.analyzeBySentences(text);
        return new SentimentServiceResponse(ranking);
    }
}
