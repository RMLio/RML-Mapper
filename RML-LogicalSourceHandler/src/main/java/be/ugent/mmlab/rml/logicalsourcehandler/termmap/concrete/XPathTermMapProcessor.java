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

    
    //TODO: Deprecated, remove
    /*public DefaultNamespaceContext get_namespaces() {
        //TODO:Get the namespaces from xml file
        DefaultNamespaceContext dnc = new DefaultNamespaceContext();

        this.nsContext.addNamespace("xsd", Namespaces.URI_XSD);
        dnc.declarePrefix("xsd", Namespaces.URI_XSD);
        this.nsContext.addNamespace("gml", "http://www.opengis.net/gml");
        dnc.declarePrefix("gml", "http://www.opengis.net/gml");
        this.nsContext.addNamespace("agiv", "http://www.agiv.be/agiv");
        dnc.declarePrefix("agiv", "http://www.agiv.be/agiv");

        this.nsContext.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        dnc.declarePrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        this.nsContext.addNamespace("simcore", "http://www.lbl.gov/namespaces/Sim/SimModelCore");
        dnc.declarePrefix("simcore", "http://www.lbl.gov/namespaces/Sim/SimModelCore");
        this.nsContext.addNamespace("simres", "http://www.lbl.gov/namespaces/Sim/ResourcesGeneral");
        dnc.declarePrefix("simres", "http://www.lbl.gov/namespaces/Sim/ResourcesGeneral");
        this.nsContext.addNamespace("simgeom", "http://www.lbl.gov/namespaces/Sim/ResourcesGeometry");
        dnc.declarePrefix("simgeom", "http://www.lbl.gov/namespaces/Sim/ResourcesGeometry");
        this.nsContext.addNamespace("simbldg", "http://www.lbl.gov/namespaces/Sim/BuildingModel");
        dnc.declarePrefix("simbldg", "http://www.lbl.gov/namespaces/Sim/BuildingModel");
        this.nsContext.addNamespace("simmep", "http://www.lbl.gov/namespaces/Sim/MepModel");
        dnc.declarePrefix("simmep", "http://www.lbl.gov/namespaces/Sim/MepModel");
        this.nsContext.addNamespace("simmodel", "http://www.lbl.gov/namespaces/Sim/Model");
        dnc.declarePrefix("simmodel", "http://www.lbl.gov/namespaces/Sim/Model");

        this.nsContext.addNamespace("mml", "http://www.w3.org/1998/Math/MathML");
        dnc.declarePrefix("mml", "http://www.w3.org/1998/Math/MathML");
        this.nsContext.addNamespace("xlink", "http://www.w3.org/1999/xlink");
        dnc.declarePrefix("xlink", "http://www.w3.org/1999/xlink");
        this.nsContext.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        dnc.declarePrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        this.nsContext.addNamespace("tp", "http://www.plazi.org/taxpub");
        dnc.declarePrefix("tp", "http://www.plazi.org/taxpub");
        this.nsContext.addNamespace("sparql", "http://www.w3.org/2005/sparql-results#");
        dnc.declarePrefix("sparql", "http://www.w3.org/2005/sparql-results#");

        return dnc;
    }*/
    
    @Override
    public String cleansing(String value) {
        return value;
    }
    
}
