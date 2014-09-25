import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.jcas.tcas.Annotation;
/**
 * A simple CAS consumer that writes recognized gene tags in to a file. UTF-8 encoding is used.
 * <p>
 * This CAS Consumer takes one parameter:
 * <ul>
 * <li><code>OutputDirectory</code> - path to directory into which output files will be written</li>
 * </ul>
 * 
 * 
 */


public class GeneCasConsumer extends CasConsumer_ImplBase {
  /**
   * Name of configuration parameter that must be set to the path of a directory into which the
   * output files will be written.
   */
  public static final String PARAM_OUTPUTDIR = "OutputDirectory";

  private File mOutputDir;
  
  private File outFile;
  
  /**
   * Called when the CAS Consumer is first created
   */
  public void initialize() throws ResourceInitializationException {
    mOutputDir = new File(((String) getConfigParameterValue(PARAM_OUTPUTDIR)).trim());
    try {
      if (!mOutputDir.exists()) {
        mOutputDir.mkdirs();
      }
      outFile = new File(mOutputDir + "/hw1-hongfeiz.out");   
      if (outFile.exists()) {
        outFile.delete();
      }
      else {
        outFile.createNewFile();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
   
  }
  /**
   * Obtain gene mention tagging results from output type system and write into file
   */
  public void processCas(CAS aCAS) throws ResourceProcessException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }
    FSIterator<Annotation> it = jcas.getAnnotationIndex(OutputGene.type).iterator();
    OutputGene outputGene = new OutputGene(jcas);
    while (it.hasNext()) {
      outputGene = (OutputGene) it.next();      
      try {
        String id = outputGene.getId();
        String gene = outputGene.getGeneTag();
        int begin = outputGene.getBegin();
        int end = outputGene.getEnd();
        
        String outputsentence = id + "|" + begin + " " + end + "|" + gene + "\n";
        FileOutputStream outStream = new FileOutputStream(outFile,true);
        outStream.write(outputsentence.getBytes("UTF-8"));
        outStream.close();
       
      }catch (IOException e) {
        throw new ResourceProcessException(e);
      }
    }
  
  }

}
