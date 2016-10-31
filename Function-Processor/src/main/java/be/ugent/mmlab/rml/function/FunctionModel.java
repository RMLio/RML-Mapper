package be.ugent.mmlab.rml.function;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Function Model
 *
 * @author bjdmeest
 */
public class FunctionModel {

    private final String[] parameters;
    private String URI;
    private Method method;

    public FunctionModel(String URI, Method m, String[] parameters) {
        this.URI = URI;
        this.method = m;
        this.parameters = parameters;
    }

    public Object execute(Map<String, String> args) {
        Object[] parameters = this.getParameters(args);
        try {
            return this.method.invoke(null, parameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // Nothing to do?
        }
        return null;
    }

    public String getURI() {
        return URI;
    }

    private Object[] getParameters(Map<String, String> parameters) {
        Object[] args = new Object[this.parameters.length];
        for (int i = 0; i < this.parameters.length; i++) {
            args[i] = parameters.get(this.parameters[i]);
        }
        return args;
    }
}
