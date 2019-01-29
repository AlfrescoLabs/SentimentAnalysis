package org.alfresco.ml.sentimentanalysis.services;

/**
 * Created by cleseach on 29/01/2019.
 */

import org.alfresco.ml.sentimentanalysis.SentimentAnalyzer;
import org.alfresco.ml.sentimentanalysis.model.Ranking;
import org.alfresco.ml.sentimentanalysis.model.SentimentServiceResponse;
import org.alfresco.ml.sentimentanalysis.stanford.StanfordAnalyzer;
import org.springframework.web.bind.annotation.*;

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
    public SentimentServiceResponse acceptData(InputStream dataStream) {
        // TODO Call Service
        Ranking ranking = new Ranking(3, 4, 3);
        return new SentimentServiceResponse(ranking);
    }
}
