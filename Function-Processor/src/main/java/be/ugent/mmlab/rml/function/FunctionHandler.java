package be.ugent.mmlab.rml.function;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Function Handler
 *
 * @author bjdmeest
 */
public class FunctionHandler {

    public String basePath;

    public Map<String, FunctionModel> functions = new HashMap();

    public FunctionHandler(String path) {
        this.basePath = path;

//        // DEBUG
//        Class cls = this.getClass(path + "/GrelFunctions.java", "GrelFunctions");
//        Class<?> params[] = new Class[1];
//        params[0] = String.class;
//        FunctionModel fn = null;
//        try {
//            String[] args = new String[1];
//            args[0] = "http://semweb.mmlab.be/ns/grel#valueParameter";
//            fn = new FunctionModel("http://semweb.mmlab.be/ns/grel#isSet", cls.getDeclaredMethod("isSet", params), args);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        this.put(fn);
        this.loadFromDisk();
    }

    private boolean loadFromDisk() {
        JSONParser parser = new JSONParser();
        JSONObject a = null;
        try {
            a = (JSONObject) parser.parse(new FileReader(this.basePath + "/metadata.json"));
        } catch (ParseException | FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONArray files = (JSONArray) a.get("files");
        for (int i = 0; i < files.size(); i++) {
            JSONObject fileObj = (JSONObject) files.get(i);
            Class cls = this.getClass(this.basePath + "/" + fileObj.getAsString("path"), fileObj.getAsString("name"));
            JSONArray fileFunctions = (JSONArray) fileObj.get("functions");
            for (int j = 0; j < fileFunctions.size(); j++) {
                JSONObject functionObj = (JSONObject) fileFunctions.get(j);
                JSONArray parameters = (JSONArray) functionObj.get("parameters");
                Class<?> params[] = new Class[parameters.size()];
                String[] args = new String[parameters.size()];
                for (int k = 0; k < parameters.size(); k++) {
                    JSONObject param = (JSONObject) parameters.get(k);
                    params[k] = this.getParamType(param.getAsString("type"));
                    args[k] = param.getAsString("url");
                }
                FunctionModel fn = null;
                try {
                    fn = new FunctionModel(functionObj.getAsString("url"), cls.getDeclaredMethod(functionObj.getAsString("name"), params), args);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                this.put(fn);
            }
        }
        return true;
    }

    private Class getParamType(String type) {
        switch (type) {
            case "xsd:string":
                return String.class;
            default:
                throw new Error("Couldn't derive type from " + type);
        }
    }

    private boolean put(FunctionModel fn) {
        this.functions.put(fn.getURI(), fn);
        return true;
    }

    public FunctionModel get(String URI) {
        FunctionModel res = null;
        if (this.functions.containsKey(URI)) {
            res = this.functions.get(URI);
        } else {
            // TODO download
        }

        return res;
    }

    private Class getClass(String path, String className) {
        // TODO let's not recompile every time
        File sourceFile = new File(path);
        Class<?> cls = null;

        // Compile source file.
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int res = compiler.run(null, null, null, sourceFile.getPath());

        if (res != 0) {
            return null;
        }

        // Load and instantiate compiled class.
        URLClassLoader classLoader = null;
        try {
            classLoader = URLClassLoader.newInstance(new URL[]{(new File(this.basePath)).toURI().toURL()});
            cls = Class.forName(className, true, classLoader);
        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cls;
    }

}
