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

    private FunctionHandler handler = new FunctionHandler((new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath())).getParent() + "/resources/functions");
    private static final Logger log = LoggerFactory.getLogger(ConcreteFunctionProcessor.class);

    public ConcreteFunctionProcessor() {
    }

    public String processFunction(String function, Map<String, String> parameters) {
        FunctionModel fn = this.handler.get(function);
        if(fn == null) {
            System.err.println("The function " + function + " was not defined.");
            log.error("The function " + function + " was not defined.");
            return "undefined";
        } else {
            return fn.execute(parameters).toString();
        }
    }


}

    /**

    private static ConcreteFunctionProcessor instance = null;
    private FunctionHandler handler

<<<<<<< HEAD
    public ConcreteFunctionProcessor() {
        this.handler = new FunctionHandler(new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "/resources/functions");
=======
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
>>>>>>> aeeb3a84894fdc009058292ef6827acb0020b650
    }

    // Log
    private static final Logger log =
            LoggerFactory.getLogger(ConcreteFunctionProcessor.class);

    public String processFunction(
            String function, Map<String, String> parameters) {
        FunctionModel fn = handler.get(function);
        if (fn == null) {
            System.err.println("The function " + function + " was not defined.");
            log.error("The function " + function + " was not defined.");
            //TODO: wmaroy:
            return "undefined";
        }
        return fn.execute(parameters).toString();
    }
}

     **/