

/* First created by JCasGen Wed Sep 24 00:51:00 EDT 2014 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Sep 24 11:31:29 EDT 2014
 * XML source: /home/freddie/11791/hw1-hongfeiz/src/main/resources/types.xml
 * @generated */
public class OutputGene extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(OutputGene.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected OutputGene() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public OutputGene(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public OutputGene(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public OutputGene(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets Id of each gene tag
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (OutputGene_Type.featOkTst && ((OutputGene_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "OutputGene");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OutputGene_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets Id of each gene tag 
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (OutputGene_Type.featOkTst && ((OutputGene_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "OutputGene");
    jcasType.ll_cas.ll_setStringValue(addr, ((OutputGene_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: geneTag

  /** getter for geneTag - gets Text of each gene tag
   * @generated
   * @return value of the feature 
   */
  public String getGeneTag() {
    if (OutputGene_Type.featOkTst && ((OutputGene_Type)jcasType).casFeat_geneTag == null)
      jcasType.jcas.throwFeatMissing("geneTag", "OutputGene");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OutputGene_Type)jcasType).casFeatCode_geneTag);}
    
  /** setter for geneTag - sets Text of each gene tag 
   * @generated
   * @param v value to set into the feature 
   */
  public void setGeneTag(String v) {
    if (OutputGene_Type.featOkTst && ((OutputGene_Type)jcasType).casFeat_geneTag == null)
      jcasType.jcas.throwFeatMissing("geneTag", "OutputGene");
    jcasType.ll_cas.ll_setStringValue(addr, ((OutputGene_Type)jcasType).casFeatCode_geneTag, v);}    
  }

    