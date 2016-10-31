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

    private FunctionHandler handler;

    public ConcreteFunctionProcessor() {
        this.handler = new FunctionHandler("../resources/functions");
    }

    // Log
    private static final Logger log =
            LoggerFactory.getLogger(ConcreteFunctionProcessor.class);

    public String processFunction(
            String function, Map<String, String> parameters) {
        FunctionModel fn = this.handler.get(function);
        if (fn == null) {
            log.error("The function " + function + " was not defined.");
        }
        return fn.execute(parameters).toString();
    }
}
