package be.ugent.mmlab.rml.function;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Function Processor
 *
 * @author bjdmeest
 */
public class ConcreteFunctionProcessor {

    private static ConcreteFunctionProcessor instance = null;

    private static FunctionHandler handler;

    protected ConcreteFunctionProcessor() {
        // Exists only to defeat instantiation.
    }

    public static ConcreteFunctionProcessor getInstance() {
        if (instance == null) {
            handler = new FunctionHandler("../resources/functions");
            instance = new ConcreteFunctionProcessor();
        }
        return instance;
    }

    // Log
    private static final Logger log =
            LoggerFactory.getLogger(ConcreteFunctionProcessor.class);

    public String processFunction(
            String function, Map<String, String> parameters) {
        FunctionModel fn = handler.get(function);
        if (fn == null) {
            log.error("The function " + function + " was not defined.");
        }
        return fn.execute(parameters).toString();
    }
}
