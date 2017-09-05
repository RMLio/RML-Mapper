package be.ugent.mmlab.rml.function;

import java.util.HashMap;
import java.util.Map;

public class ExecutionModel {

    private final String executes;
    private Map<String, Object> input;

    protected ExecutionModel() {
        this.executes = "";
        this.input = new HashMap<>();
    }

    public ExecutionModel(String executes) {
        this.executes = executes;
        this.input = new HashMap<>();
    }

    public void addInput(String key, Object value) {
        this.input.put(key, value);
    }

    public String getExecutes() {
        return executes;
    }

    public Map<String, Object> getInput() {
        return input;
    }
}
