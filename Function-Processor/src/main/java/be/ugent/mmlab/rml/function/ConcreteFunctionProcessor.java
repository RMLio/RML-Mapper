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

    private static ConcreteFunctionProcessor instance = null;
    private static FunctionHandler handler;

    protected ConcreteFunctionProcessor() {
        // Exists only to defeat instantiation.
    }

    public static ConcreteFunctionProcessor getInstance() {
        if (instance == null) {
            String basePath = "";
            try {
                String classJar = ConcreteFunctionProcessor.class.getResource("/be/ugent/mmlab/rml/function/ConcreteFunctionProcessor.class").toString();
                if (classJar.startsWith("jar:")) {
                    basePath = ConcreteFunctionProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/";
                } else {
                    basePath = (new File(ConcreteFunctionProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath())).getParent() + "/../../";
                }
            } catch (Exception e) {
                System.err.println("err: " + e.getMessage());
            }
            System.err.println("basePath: " + basePath);
            handler = new FunctionHandler(basePath + "resources/functions");
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
            System.err.println("The function " + function + " was not defined.");
            log.error("The function " + function + " was not defined.");
            //TODO: wmaroy:
            return "undefined";
        }
        return fn.execute(parameters).toString();
    }
}
