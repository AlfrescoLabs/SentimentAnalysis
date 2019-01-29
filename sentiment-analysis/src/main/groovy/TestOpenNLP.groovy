#!/usr/bin/env groovy

@Grab('org.apache.opennlp:opennlp-tools:1.9.1')
import opennlp.tools.doccat.DoccatModel
import opennlp.tools.doccat.DocumentCategorizerME

// Which model to use?
File modelFile = new File("sentiment.model")
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

double[] outcomes
DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model)

outcomes = myCategorizer.categorize("I love this book, it's the best")
println "Good -> ${myCategorizer.getBestCategory(outcomes)}"
outcomes = myCategorizer.categorize("This is horrible, it is a waste")
println "Bad -> ${myCategorizer.getBestCategory(outcomes)}"
