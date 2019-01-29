#!/usr/bin/env groovy

@Grab('org.apache.opennlp:opennlp-tools:1.9.1')
import opennlp.tools.doccat.DoccatModel
import opennlp.tools.doccat.DocumentCategorizerME
import opennlp.tools.doccat.DocumentSample
import opennlp.tools.doccat.DoccatFactory
import opennlp.tools.util.ObjectStream
import opennlp.tools.util.TrainingParameters

// Which folder to process?
File folder = new File("processed_acl")
if (args.length > 0) folder = new File(args[0])
if (! folder.exists()) {
   println "Error - training data not found in ${folder}"
   println "Please download and unpack"
   println "  http://www.cs.jhu.edu/~mdredze/datasets/sentiment/processed_acl.tar.gz"
}
println "Building training data from ${folder}"

// Where to save to
File output = new File("sentiment.model")
if (args.length > 1) output = new File(args[1])


// Setup our parameters
TrainingParameters params = new TrainingParameters()
params.put(TrainingParameters.CUTOFF_PARAM, 2)
params.put(TrainingParameters.ITERATIONS_PARAM, 30)
DoccatFactory factory = new DoccatFactory()


// Build the model
ObjectStream<DocumentSample> samples = new JHUSentimentReader(folder)
DoccatModel model = DocumentCategorizerME.train("en", samples, params, factory)


// Test it
println "\nTrying..."

double[] outcomes
DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model)

outcomes = myCategorizer.categorize("I love this book, it's the best")
println "Good -> ${myCategorizer.getBestCategory(outcomes)}"
outcomes = myCategorizer.categorize("This is terrible, don't buy")
println "Bad -> ${myCategorizer.getBestCategory(outcomes)}"


// Save
output.withOutputStream { os -> model.serialize(os) }
println "Model written to ${output}"
