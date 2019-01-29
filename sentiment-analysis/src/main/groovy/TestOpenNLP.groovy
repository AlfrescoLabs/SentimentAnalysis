#!/usr/bin/env groovy

@Grab('org.apache.opennlp:opennlp-tools:1.9.1')
import opennlp.tools.doccat.DoccatModel
import opennlp.tools.doccat.DocumentCategorizerME

// Which model to use?
File modelFile = new File("opennlp-sentiments.model")
if (args.length > 0) modelFile = new File(args[0])
if (! modelFile.exists()) {
   println "Error - model not found in ${modelFile}"
   return
}

// Load
DoccatModel model
modelFile.withInputStream { is -> model = new DoccatModel(is) }

// Test it
println "\nTrying..."
def tests = [
  ["Positive","I love this book, it's the best"],
  ["Negative","This is horrible, it is a waste"]
]

DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model)
tests.each { test ->
   def tokens = test[1].split()
   def outcomes = myCategorizer.scoreMap(tokens)
   def best = myCategorizer.getBestCategory(myCategorizer.categorize(tokens))
   println "${test[0]} -> ${outcomes} = ${best}"
}
