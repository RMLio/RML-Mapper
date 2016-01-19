package be.ugent.mmlab.rml.logicalsourcehandler.termmap.concrete;

import be.ugent.mmlab.rml.logicalsourcehandler.termmap.AbstractTermMapProcessor;
import java.util.ArrayList;
import java.util.List;
import jodd.jerry.Jerry;
import jodd.lagarto.dom.Node;
import jodd.lagarto.dom.NodeSelector;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RML Processor
 *
 * @author andimou
 */
public class CSS3TermMapProcessor extends AbstractTermMapProcessor {
    
    // Log
    private static final Logger log = LoggerFactory.getLogger(CSS3TermMapProcessor.class);
    
    @Override
    public List<String> extractValueFromNode(Object node, String expression) {
        Jerry doc = Jerry.jerry(node.toString());
        List<String> list = new ArrayList();
        String replacement = null;
        
        if(expression.equals("*")){
            list.add(node.toString());
            return list;
        }
        
        /*if (expression.equals("#")) {
            list.add(Integer.toString(enumerator++));
            return list;
        }*/
        
        Node doc2 = doc.get(0);
        NodeSelector nodeSelector = new NodeSelector(doc2);
        List<Node> selectedNodes ; //= nodeSelector.select(expression);
        if (expression.contains("href"))
            selectedNodes = nodeSelector.select("a");
        else
            selectedNodes = nodeSelector.select(expression);

        for (Node snode : selectedNodes) {
            if (expression.contains("href")) {
                list.add(StringEscapeUtils.unescapeHtml(
                        snode.getAttribute("href").toString().replaceAll(expression, replacement).trim().replaceAll("[\\t\\n\\r\\s]{2,}", " ")));
            } else {
                String value = StringEscapeUtils.unescapeHtml(snode.getTextContent().replaceAll("[\\t\\n\\r\\s]{2,}", " ").trim());
                if(value != null & !value.equals(""))
                    list.add(StringEscapeUtils.unescapeHtml(value));
            }
        }
        /*for (Node snode : selectedNodes) {
            if (snode.getInnerHtml().toString() != null && !snode.getInnerHtml().toString().trim().replaceAll("[\\t\\n\\r\\s]{2,}", " ").equals("")) {
                list.add(snode.getInnerHtml().toString().trim().replaceAll("[\\t\\n\\r\\s]{2,}", " "));
            }
        }*/
        return list;
    }
    
    @Override
    public String cleansing(String value) {
        try {
            Jerry doc = Jerry.jerry(value);
            Node node = doc.get(0);
            value = node.getTextContent().trim().replaceAll("[\\t\\n\\r\\s]", " ");
        } finally {
            return value;
        }
    }

}
