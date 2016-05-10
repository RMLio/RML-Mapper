package be.ugent.mmlab.rml.logicalsourcehandler.termmap.concrete;

import be.ugent.mmlab.rml.logicalsourcehandler.termmap.AbstractTermMapProcessor;
import be.ugent.mmlab.rml.xml.XOMBuilder;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPathException;
import jlibs.xml.DefaultNamespaceContext;
import jlibs.xml.sax.dog.NodeItem;
import jlibs.xml.sax.dog.XMLDog;
import jlibs.xml.sax.dog.expr.Expression;
import jlibs.xml.sax.dog.expr.InstantEvaluationListener;
import jlibs.xml.sax.dog.sniff.Event;
import nu.xom.Node;
import nu.xom.XPathContext;
import org.jaxen.saxpath.SAXPathException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

/**
 * RML Processor
 *
 * @author andimou
 */
public class XPathTermMapProcessor extends AbstractTermMapProcessor {
    
    // Log
    static final Logger log = LoggerFactory.getLogger(
            XPathTermMapProcessor.class.getSimpleName());
    
    public XPathContext nsContext = new XPathContext();
    private DefaultNamespaceContext dnc = new DefaultNamespaceContext();
    
    public XPathTermMapProcessor(){ }
    
    public XPathTermMapProcessor(DefaultNamespaceContext dnc){
        this.dnc = dnc;
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
        final List<String> list = new ArrayList<>();
        dnc.declarePrefix("sparql", "http://www.w3.org/2005/sparql-results#");
        XMLDog dog = new XMLDog(dnc);
        try {
            dog.addXPath("/*/" + expression);
        } catch (SAXPathException ex) {
            log.error("SAXPathException " + ex);
        }
        StringBufferInputStream input = 
                new StringBufferInputStream(node.toXML().toString());
        
        InputSource source = new InputSource(input);
        Event event = dog.createEvent();
        event.setXMLBuilder(new XOMBuilder());
        event.setListener(new InstantEvaluationListener() {
            //When an XPath expression matches
            @Override
            public void onNodeHit(
                    Expression expression, NodeItem nodeItem) {
                Node node = (Node) nodeItem.xml;    
                //if(nodeItem instanceOf Attribute)
                list.add(node.getValue().toString());
            }

            @Override
            public void finishedNodeSet(Expression expression) {
            }

            @Override
            public void onResult(Expression expression, Object result) {
            }
        });
        try {
            dog.sniff(event, source);
        } catch (XPathException ex) {
            log.error("XPathException " + ex);
        }

        /*//checks if the node has children, then cleans up new lines and extra spaces
               if (!(n instanceof Attribute) && n.getChildCount() > 1) {
                    list.add(n.getValue().trim().replaceAll("[\\t\\n\\r]", " ").replaceAll(" +", " ").replaceAll("\\( ", "\\(").replaceAll(" \\)", "\\)").replaceAll(" :", ":").replaceAll(" ,", ","));
                } else {
                    list.add(n.getValue().toString());
                }*/

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
