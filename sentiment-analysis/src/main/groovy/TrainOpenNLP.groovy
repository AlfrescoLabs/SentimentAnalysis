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

// Setup our parameters
TrainingParameters params = new TrainingParameters()
params.put(TrainingParameters.CUTOFF_PARAM, 2)
params.put(TrainingParameters.ITERATIONS_PARAM, 30)
DoccatFactory factory = new DoccatFactory()


// Build the model
ObjectStream<DocumentSample> samples = new JHUSentimentReader(folder)
println samples
DoccatModel model = DocumentCategorizerME.train("en", samples, params, factory)

// TODO Save
