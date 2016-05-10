package be.ugent.mmlab.rml.logicalsourcehandler.termmap.concrete;

import be.ugent.mmlab.rml.logicalsourcehandler.termmap.AbstractTermMapProcessor;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RML Processor
 *
 * @author andimou
 */
public class JSONPathTermMapProcessor extends AbstractTermMapProcessor {
    
    // Log
    private static final Logger log = 
            LoggerFactory.getLogger(
            JSONPathTermMapProcessor.class.getSimpleName());
    
    @Override
    public List<String> extractValueFromNode(Object node, String expression) {
        
        try {
            if(expression.contains(" ")){
                expression = ".[\'" + expression + "\']";
            }
            Object val = JsonPath.read(node, expression);
            List<String> list = new ArrayList<>();
            if (val instanceof JSONArray) {
                JSONArray arr = (JSONArray) val;
                return Arrays.asList(arr.toArray(new String[0]));
            }
            list.add((String) val.toString());
            return list;
        } catch (com.jayway.jsonpath.InvalidPathException ex) {
            log.debug("InvalidPathException " + ex + "for " + expression);
            return new ArrayList<>();
        } catch (Exception ex) {
            log.error("Exception: " + ex);
            return null;
        }
        
    }
    
    @Override
    public String cleansing(String value) {
        return value;
    }

}
