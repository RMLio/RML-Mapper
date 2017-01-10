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
            e.printStackTrace(); // maybe this? :p
        }
        return null;
    }

    public String getURI() {
        return URI;
    }

    private Object[] getParameters(Map<String, String> parameters) {
        Object[] args = new Object[this.parameters.length];
        Class[] paramTypes = this.method.getParameterTypes();
        for (int i = 0; i < this.parameters.length; i++) {
            if(parameters.get(this.parameters[i]) != null) {
                args[i] = parseParameter(parameters.get(this.parameters[i]), paramTypes[i]);
            } else {
                args[i] = null;
            }
        }
        return args;
    }

    private Object parseParameter(String parameter, Class type) {
        switch (type.getName()) {
            case "java.lang.String":
                return parameter;
            case "int":
                return Integer.parseInt(parameter);
            case "double":
                return Double.parseDouble(parameter);
            default:
                throw new Error("Couldn't derive " + type.getName() + " from " + parameter);
        }
    }
}
