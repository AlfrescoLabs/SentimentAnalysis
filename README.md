# SentimentAnalysis
This project was created as part of Alfresco #DevCon2019 and aims to create a
micro service that is able to analyise the sentiment of a content. As part of
this project we will compare the different NLP frameworks and try to improve on
the accuracy of the prediction.

### Tasks  ###
* Project setup.
* Data preparation.
* NLP Sentiment Analysis interface.
* StandfordNLP implementation.
* OpenNLP implementation.
* SpringBoot setup to expose Rest-API endpoints
* Unit test.

### Stretch Tasks ###
* Integrate the service into Alfresco, create a rule that invokes the Sentiment Analysis API.
* Integrate with APS to perform a process based on the Sentiment result.

## Setup ##
Change into the sentiment-analysis folder and perform the following:
### Building The Application ###
```mvn clean install```
### Running The Application ###
```java -jar target/sentiment-analysis-1.0.0.ja```

## REST API ##

**Get Sentiment Analysis ranking by sentence**

Request

```bash
$ curl -X POST \
  http://localhost:8080/analyze/text \
  -H 'Content-Type: text/plain' \
  -d Text coming from Alfresco Content
```

Response

```json
{
    "ranking": {
        "positive": 3,
        "negative": 4,
        "neutral": 3
    }
}
```
