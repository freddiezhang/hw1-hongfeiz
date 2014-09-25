import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import org.apache.uima.jcas.tcas.Annotation;

/**
 * An annotator that detects gene mention tags from input CAS using Stanford CoreNLP.
 */
public class GeneAnnotator extends JCasAnnotator_ImplBase {
  /**
   * ID of input and output type system
   */
  private String inId;
  /**
   * Text of input type system
   */
  private String inText;
  /**
   * Begin offset of a gene tag operated by stanford CoreNLP
   */
  private int inBegin;
  /**
   * End offset of a gene tag operated by stanford CoreNLP
   */
  private int inEnd;
  
  private String outText;
  /**
   * Begin offset of a gene tag
   */
  private int outBegin;
  /**
   * End offset of a gene tag
   */
  private int outEnd;
  
  /**
   * Call PosTagNamedEntityRecognizer to extract gene mention tags from input type system
   */
  public void process(JCas aJCas) {
    // get annotation indexes
    FSIterator<Annotation> it = aJCas.getAnnotationIndex(InputSentence.type).iterator();
    InputSentence inSentence = new InputSentence(aJCas);
    while(it.hasNext()){
      inSentence =(InputSentence) it.next();
    }
    inId = inSentence.getId();
    inText = inSentence.getText();
    
    try {
      PosTagNamedEntityRecognizer ner= new PosTagNamedEntityRecognizer();
      Map<Integer, Integer> map = ner.getGeneSpans(inText);
      Set set = map.entrySet();
      Iterator iter = set.iterator();    
      while(iter.hasNext()){   
        Map.Entry entry = (Map.Entry)iter.next();          
        inBegin = (Integer) entry.getKey();          
        inEnd = (Integer) entry.getValue(); 
        int current = 0;
        int numOfSpace = 0;
        outText = inText.substring(inBegin,inEnd);
        for(current = 0; current<inBegin; current++){
          if(inText.charAt(current)==' '){
            numOfSpace++;
          }
        }
        outBegin = inBegin - numOfSpace;
        outEnd = inEnd - numOfSpace - 1;
        OutputGene out = new OutputGene(aJCas);
        out.setId(inId);
        out.setGeneTag(outText);
        out.setBegin(outBegin);
        out.setEnd(outEnd);
        out.addToIndexes();
           
      }      
    } catch (ResourceInitializationException e) {
      e.printStackTrace();
    }
    
    

    
  }

}
