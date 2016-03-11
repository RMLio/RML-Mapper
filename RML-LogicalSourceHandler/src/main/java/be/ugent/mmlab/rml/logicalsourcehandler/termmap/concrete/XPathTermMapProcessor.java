package be.ugent.mmlab.rml.logicalsourcehandler.termmap.concrete;

import be.ugent.mmlab.rml.logicalsourcehandler.termmap.AbstractTermMapProcessor;
import java.util.ArrayList;
import java.util.List;
import nu.xom.Attribute;
import nu.xom.Node;
import nu.xom.Nodes;
import nu.xom.XPathContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RML Processor
 *
 * @author andimou
 */
public class XPathTermMapProcessor extends AbstractTermMapProcessor {
    
    // Log
    static final Logger log = LoggerFactory.getLogger(XPathTermMapProcessor.class);
    
    public XPathContext nsContext = new XPathContext();
    
    public XPathTermMapProcessor(){ }
    
    public XPathTermMapProcessor(XPathContext nsContext){
        this.nsContext = nsContext;
    }
    
    /**
     * Process a XPath expression against an XML node
     *
     * @param node
     * @param expression
     * @return value that matches expression
     */
    private List<String> extractValueFromNode(Node node, String expression) {
        //DefaultNamespaceContext dnc = get_namespaces();
        List<String> list = new ArrayList<>();

        //if there's nothing to uniquelly identify, use # - temporary solution - challenge
            /*if(expression.equals("#")){
         list.add(Integer.toString(enumerator++));
         return list;
         }*/
        Nodes nodes = node.query(expression, nsContext);

        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);

            //checks if the node has a value or children
            if (!n.getValue().isEmpty() || (n.getChildCount() != 0)) 
            //MVS's for extracting elements and not the string
            /*if (!(n instanceof Attribute) && n.getChild(0) instanceof Element) {
             list.add(n.toXML());
             } 
             else {
             list.add(n.getValue());
             }*/ //checks if the node has children, then cleans up new lines and extra spaces
            {
                if (!(n instanceof Attribute) && n.getChildCount() > 1) {
                    list.add(n.getValue().trim().replaceAll("[\\t\\n\\r]", " ").replaceAll(" +", " ").replaceAll("\\( ", "\\(").replaceAll(" \\)", "\\)").replaceAll(" :", ":").replaceAll(" ,", ","));
                } else {
                    list.add(n.getValue().toString());
                }
            }

        }

        return list;
    }


    @Override
    public List<String> extractValueFromNode(Object node, String expression) {
        return extractValueFromNode((Node) node, expression);
    }
    
    @Override
    public String cleansing(String value) {
        return value;
    }
    
}
