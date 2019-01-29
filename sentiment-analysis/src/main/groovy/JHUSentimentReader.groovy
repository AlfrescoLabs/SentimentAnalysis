// Generates OpenNLP DocumentSample objects from the 
// JHU sentiment datasets
import opennlp.tools.doccat.DocumentSample
import opennlp.tools.util.ObjectStream

public class JHUSentimentReader implements ObjectStream<DocumentSample> {
   public static final String POSITIVE = "Positive"
   public static final String NEGATIVE = "Negative"

   private File folder
   private List<Tuple2> reviews
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
            if (file.getName().startsWith("posi"))
               reviews.add([file,POSITIVE] as Tuple2)
            if (file.getName().startsWith("nega"))
               reviews.add([file,NEGATIVE] as Tuple2)
         }
      }
   }

   public DocumentSample read() {
      if (samples.size() == 0) {
         if (reviews.size() == 0) return null

         Tuple details = reviews.remove(reviews.size()-1)
         File file = details[0]
         String sentiment = details[1]
         file.eachLine { line ->
            def tokens = []
            line.split().each { token ->
               if (token.startsWith("#")) return
               int split = token.indexOf(":")
               String text = token.substring(0, split).tr("_"," ")
               int count = token.substring(split+1) as Integer
               text.split().each { t ->
                  count.each { tokens.add(t) }
               }
            }
            samples.add(new DocumentSample(sentiment,tokens as String[]))
         }
      }

      if (samples.size() > 0) {
         return samples.remove(samples.size()-1)
      } else {
         return null
      }
   }
}
