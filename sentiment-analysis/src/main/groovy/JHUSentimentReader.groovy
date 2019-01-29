// Generates OpenNLP DocumentSample objects from the 
// JHU sentiment datasets
import opennlp.tools.doccat.DocumentSample
import opennlp.tools.util.ObjectStream

public class JHUSentimentReader implements ObjectStream<DocumentSample> {
   private File folder
   public JHUSentimentReader(File folder) {
      this.folder = folder
      reset()
   }

   public void reset() {
      // TODO
   }

   public DocumentSample read() {
      // TODO
      return new DocumentSample("Positive","good")
   }
}
