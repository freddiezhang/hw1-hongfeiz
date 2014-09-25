import java.io.File;
import java.io.IOException;
import java.io.FileReader;   
import java.util.ArrayList;
import java.io.BufferedReader;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

/**
 * A simple collection reader that reads document from a directory by line. 
 * It also simply splits each line into two parts: Id and text.
 * <p>
 * This CAS Consumer takes one parameter:
 * <ul>
 * <li><code>InputDirectory</code> - path to directory containing files</li>
 * </ul>
 * 
 * 
 */
public class GeneCollectionReader extends CollectionReader_ImplBase {
  /**
   * Name of configuration parameter that must be set to the path of a directory containing input
   * files.
   */
  public static final String PARAM_INPUTDIR = "InputDirectory";

  private ArrayList<String> mSentences;
  
  private int mCurrentIndex;
  
  /**
   * Initialize the input file name
   */
  public void initialize() throws ResourceInitializationException {
    File directory = new File(
              (String)getConfigParameterValue(PARAM_INPUTDIR));
    mCurrentIndex = 0; 
    mSentences = new ArrayList<String>();
    File inputFile = new File(directory+"/hw1.in");
    BufferedReader reader = null;
    //Read line by line from input file into a list.
    try {
        reader = new BufferedReader(new FileReader(inputFile));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
            mSentences.add(tempString);
        }
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e1) {
            }
        }
    }    
  }
  

  /**
   * Read the next item from the sentence collection and populates a CAS
   */
  public void getNext(CAS aCAS) throws IOException, CollectionException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new CollectionException(e);
    } 
    // divide each line into two parts.
    String str = mSentences.get(mCurrentIndex++);
    String[] sentence = str.split(" ",2);
    InputSentence a = new InputSentence(jcas);
    // put IDs and texts in CAS
    a.setId(sentence[0]);
    a.setText(sentence[1]);
    a.addToIndexes();

  }

  /**
   * Called when the Collection Reader is no longer needed
   */
  public void close() throws IOException {

  }

  /**
   * Return an array of Progress objects, containing three fields
   * - the amount already completed, the total amount, and a entity.
   */
  public Progress[] getProgress() {
    return new Progress[] { new ProgressImpl(mCurrentIndex, mSentences.size(), Progress.ENTITIES) };
  }

  /**
   * Return whether or not there are any documents remaining to be read from the collection
   */
  public boolean hasNext() {
    return mCurrentIndex < mSentences.size();
  }
  
  /*
   * public int getNumberOfSentences() {
    return mSentences.size();
  }*/

}
