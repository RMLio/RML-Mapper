package be.ugent.mmlab.rml.logicalsourcehandler.termmap.concrete;

import be.ugent.mmlab.rml.logicalsourcehandler.termmap.AbstractTermMapProcessor;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RML Processor
 *
 * @author andimou
 */
public class CSVTermMapProcessor extends AbstractTermMapProcessor {
    
    // Log
    private static final Logger log = LoggerFactory.getLogger(CSVTermMapProcessor.class);

    @Override
    public List<String> extractValueFromNode(Object node, String expression) {
        HashMap<String, String> row = (HashMap<String, String>) node;
        for(String key : row.keySet())
            key = new String(key.getBytes(), UTF_8);
        //call the right header in the row
        List<String> list = new ArrayList();
        if (row.containsKey(expression)){
            list.add(row.get(expression));
        }
        return list;
    }
    
    @Override
    public String cleansing(String value) {
        return value;
    }

}
