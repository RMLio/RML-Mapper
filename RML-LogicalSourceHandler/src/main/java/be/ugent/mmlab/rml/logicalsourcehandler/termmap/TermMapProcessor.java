package be.ugent.mmlab.rml.logicalsourcehandler.termmap;

import be.ugent.mmlab.rml.model.RDFTerm.TermMap;
import be.ugent.mmlab.rml.model.RDFTerm.TermType;
import be.ugent.mmlab.rml.vocabularies.QLVocabulary;
import java.util.List;
import org.openrdf.model.Value;

/**
 * RML Processor
 *
 * @author andimou
 */
public interface TermMapProcessor {
    
    /**
     * Resolve an expression and extract a single string value from a node
     * @param node current object
     * @param expression reference to value
     * @return extracted value
     */
    public List<String> extractValueFromNode(Object node, String expression);
    
    /**
     *
     * @param map
     * @param expression
     * @param template
     * @param replacement
     * @return
     */
    public String processTemplate(TermMap map, String expression, String template, String replacement);
    
    public List<String> templateHandler(String template, Object node, 
            QLVocabulary.QLTerm referenceFormulation, TermType termType);
    
    
    /**
     *
     * @param map
     * @param node
     * @return
     */
    public List<String> processTermMap(TermMap map, Object node);
    
    /**
     *
     * @param value
     * @param valueList
     * @param termMap
     * @return
     */
    public List<Value> applyTermType(
            String value, List<Value> valueList, TermMap termMap);
    
    public String cleansing(String value);
    
}
