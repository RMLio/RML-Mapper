package be.ugent.mmlab.rml.function;


import java.io.File;
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
        this.handler = new FunctionHandler(new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "/resources/functions");
    }

    // Log
    private static final Logger log =
            LoggerFactory.getLogger(ConcreteFunctionProcessor.class);

    public String processFunction(
            String function, Map<String, String> parameters) {
        FunctionModel fn = this.handler.get(function);
        if (fn == null) {
            System.err.println("The function " + function + " was not defined.");
            log.error("The function " + function + " was not defined.");
            //TODO: wmaroy:
            return "undefined";
        }
        return fn.execute(parameters).toString();
    }
}
