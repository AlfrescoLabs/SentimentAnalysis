// Generates OpenNLP DocumentSample objects from the 
// JHU sentiment datasets
import opennlp.tools.doccat.DocumentSample
import opennlp.tools.util.ObjectStream

public class JHUSentimentReader implements ObjectStream<DocumentSample> {
   private File folder
   private List<File> reviews
   private List<DocumentSample> samples

   public JHUSentimentReader(File folder) {
      this.folder = folder
      reset()
      println "There are ${reviews.size()} review files to process"
   }

   public void reset() {
      reviews = []
      samples = []
      folder.eachDir { dir ->
         dir.eachFileMatch(~/.*.review/) { file ->
            reviews.add(file)
         }
      }
   }

   public DocumentSample read() {
      // Load and process a new file if we're out of data
      if (samples.size() == 0) {
         if (reviews.size() == 0) return null

         // Loads reviews and turns into OpenNLP documents
         // Stored with one line per review
         //   token:count token:count token:count ... #label#:label
         // Doesn't store raw reviews, stores partly processed tokens
         // Token can be a single word, or compounds like "completely_corrupt"
         // TODO Take advantage of these paired word tokens / relations
         File file = reviews.remove(reviews.size()-1)
         file.eachLine { line ->
            def tokens = []
            String sentiment = null
            line.split().each { token ->
               int split = token.indexOf(":")
               String text = token.substring(0, split).tr("_"," ")
               if (text.equals("#label#")) {
                  sentiment = token.substring(split+1)
                  return
               }

               // To keep weightings, repeat tokens by count
               int count = token.substring(split+1) as Integer
               text.split().each { t ->
                  count.each { tokens.add(t) }
               }
            }
            samples.add(new DocumentSample(sentiment,tokens as String[]))
         }
      }

      // Pop off the last review and return
      if (samples.size() > 0) {
         return samples.remove(samples.size()-1)
      } else {
         return null
      }
   }
}
