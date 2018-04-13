package be.ugent.mmlab.rml.grel;

import be.ugent.mmlab.rml.model.TriplesMap;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GREL Processor
 * 
 *
 * @author andimou
 */
public class ConcreteGrelProcessor {
    
    // Log
    private static final Logger log = 
            LoggerFactory.getLogger(ConcreteGrelProcessor.class);

    public String processFunction(
            String function, TriplesMap functionTriplesMap, Object node, Map<String,String> parameters) {
        String result = null;

        switch (function){
            case "http://semweb.mmlab.be/ns/grel#isSet":
                result = isSet(parameters);
                break;
            case "http://semweb.mmlab.be/ns/grel#contains":
                result = contains(parameters);
                break;
            case "http://semweb.mmlab.be/ns/grel#booleanMatch":
                result = booleanMatch(parameters);
                break;
            default:
                log.error("The function " + function + " was not defined.");
        }
        return result;
    }

    public String isSet(Map<String, String> parameters){
        String result = null;
        String field = parameters.get(GrelVocabulary.GREL_NAMESPACE + GrelVocabulary.GrelTerm.VALUE_PARAMETER);
            if(field.isEmpty())
                result = "false";
            else
                result = "true";
        return result;
    }

    public String contains(Map<String,String> parameters){
        String string1 = parameters.get(GrelVocabulary.GrelTerm.VALUE_PARAMETER);
        String string2 = parameters.get(GrelVocabulary.GrelTerm.SUBSTRING_PARAMETER);
        if (string1.contains(string2))
            return  "true";
        else
            return "false";
    }

    public String booleanMatch(Map<String,String> parameters){
        String string1 = parameters.get(GrelVocabulary.GrelTerm.VALUE_PARAMETER);
        String string2 = parameters.get(GrelVocabulary.GrelTerm.REGEX_PARAMETER);
        if (string1.matches(string2))
            return  "true";
        else
            return "false";
    }
}
