package be.ugent.mmlab.rml.function;


import functions.DBpediaFunctions;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wmaroy on 28.04.17.
 *
 * So much hardcoded my eyes hurt
 */
public class DBpediaFunctionProcessor {


    private static DBpediaFunctionProcessor instance = null;
    private static final Logger log = LoggerFactory.getLogger(DBpediaFunctionProcessor.class);
    private SimpleValueFactory vf = SimpleValueFactory.getInstance();

    private final IRI stringDatatype = vf.createIRI("http://www.w3.org/2001/XMLSchema#string");
    private final IRI dateDatatype = vf.createIRI("http://www.w3.org/2001/XMLSchema#date");

    private DBpediaFunctionProcessor() {
        // singleton object
    }

    /**
     * Returns the instance of this class
     * Instantiate an instance if instance is null
     * @return
     */
    public static DBpediaFunctionProcessor getInstance() {
        if(instance == null) {
            instance = new DBpediaFunctionProcessor();
        }
        return instance;
    }

    /**
     * Delegates given function to its processing function
     * @param function
     * @param parameters
     * @return
     */
    public ArrayList<Value> processFunction(String function, Map<String, String> parameters, IRI datatype) {

        log.debug("Executing " + function);
        log.debug("Parameters :" + parameters);

        switch (function) {
            case "http://dbpedia.org/function/simplePropertyFunction":
                return processSimplePropertyFunction(parameters, datatype);

            case "http://dbpedia.org/function/latFunction":
                return processLatFunction(parameters);

            case "http://dbpedia.org/function/lonFunction":
                return processLonFunction(parameters);

            case "http://dbpedia.org/function/startDateFunction":
                return processStartDateFunction(parameters);

            case "http://dbpedia.org/function/endDateFunction":
                return processEndDateFunction(parameters);

            case "http://dbpedia.org/function/equals":
                return processEqualsFunction(parameters);

            case "http://dbpedia.org/function/isSet":
                return processIsSetFunction(parameters);

            case "http://dbpedia.org/function/contains":
                return processContainsFunction(parameters);

            case "http://dbpedia.org/function/extractDate":
                return processExtractDateFunction(parameters);

            case "http://dbpedia.org/function/extractEntity":
                return processExtractEntity(parameters);

            case "http://dbpedia.org/function/extractString":
                return processExtractStringFunction(parameters);
        }

        return new ArrayList<>();

    }

    /**
     *
     * @param parameters
     * @return
     */
    private ArrayList<Value> processSimplePropertyFunction(Map<String, String> parameters, IRI datatype) {



        String property = parameters.get("http://dbpedia.org/function/propertyParameter");
        String factor = parameters.get("http://dbpedia.org/function/factorParameter");
        String transform = parameters.get("http://dbpedia.org/function/transformParameter");
        String select = parameters.get("http://dbpedia.org/function/selectParameter");
        String prefix = parameters.get("http://dbpedia.org/function/prefixParameter");
        String suffix = parameters.get("http://dbpedia.org/function/suffixParameter");
        String unit = parameters.get("http://dbpedia.org/function/unitParameter");
        String ontology = parameters.get("http://dbpedia.org/function/ontologyPropertyParameter");

        ArrayList<String> result = DBpediaFunctions.simplePropertyFunction(property, factor, transform, select, prefix, suffix, unit, ontology);
        log.debug("Result: " + result);

        return transformToLiteralValues(result, datatype); // the type will be assigned in the EF :)
    }

    /**
     *
     * @param parameters
     * @return
     */
    private ArrayList<Value> processLatFunction(Map<String, String> parameters) {

        String coord = parameters.get("http://dbpedia.org/function/coordParameter");
        String lat = parameters.get("http://dbpedia.org/function/latParameter");
        String latDegrees = parameters.get("http://dbpedia.org/function/latDegreesParameter");
        String latMinutes = parameters.get("http://dbpedia.org/function/latMinutesParameter");
        String latSeconds = parameters.get("http://dbpedia.org/function/latSecondsParameter");
        String latDirection = parameters.get("http://dbpedia.org/function/latDirectionParameter");

        ArrayList<String> result = DBpediaFunctions.latFunction(coord, lat, latDegrees, latMinutes, latSeconds, latDirection);
        log.debug("Result: " + result);

        return transformToLiteralValues(result, null); // the type will be assigned in the EF :)
    }


    /**
     *
     * @param parameters
     * @return
     */
    private ArrayList<Value> processLonFunction(Map<String, String> parameters) {

        String coord = parameters.get("http://dbpedia.org/function/coordParameter");
        String lon = parameters.get("http://dbpedia.org/function/lonParameter");
        String lonDegrees = parameters.get("http://dbpedia.org/function/lonDegreesParameter");
        String lonMinutes = parameters.get("http://dbpedia.org/function/lonMinutesParameter");
        String lonSeconds = parameters.get("http://dbpedia.org/function/lonSecondsParameter");
        String lonDirection = parameters.get("http://dbpedia.org/function/lonDirectionParameter");

        ArrayList<String> result = DBpediaFunctions.latFunction(coord, lon, lonDegrees, lonMinutes, lonSeconds, lonDirection);
        log.debug("Result: " + result);

        return transformToLiteralValues(result, null); // the type will be assigned in the EF :)
    }

    /**
     *
     * @param parameters
     * @return
     */
    private ArrayList<Value> processStartDateFunction(Map<String, String> parameters) {

        String startDate = parameters.get("http://dbpedia.org/function/startDatePropertyParameter");
        String startDateOntology = parameters.get("http://dbpedia.org/function/startDateOntologyParameter");

        ArrayList<String> results = new ArrayList<>();
        results.add(DBpediaFunctions.startDateFunction(startDate, startDateOntology));
        log.debug("Result: " + results);

        return transformToLiteralValues(results, null);

    }

    /**
     *
     * @param parameters
     * @return
     */
    private ArrayList<Value> processEndDateFunction(Map<String, String> parameters) {

        String endDate = parameters.get("http://dbpedia.org/function/endDatePropertyParameter");
        String endDateOntology = parameters.get("http://dbpedia.org/function/endDateOntologyParameter");

        ArrayList<String> results = new ArrayList<>();
        results.add(DBpediaFunctions.endDateFunction(endDate, endDateOntology));
        log.debug("Result: " + results);

        return transformToLiteralValues(results, null);
    }

    /**
     *
     * @param parameters
     * @return
     */
    private ArrayList<Value> processEqualsFunction(Map<String, String> parameters) {

        String value = parameters.get("http://dbpedia.org/function/equals/valueParameter");
        String property = parameters.get("http://dbpedia.org/function/equals/propertyParameter");

        ArrayList<Boolean> results = new ArrayList<>();
        results.add(DBpediaFunctions.equals(property, value));
        log.debug("Result: " + results);

        return transformToBooleanValues(results);

    }

    /**
     *
     * @param parameters
     * @return
     */
    private ArrayList<Value> processIsSetFunction(Map<String, String> parameters) {

        String property = parameters.get("http://dbpedia.org/function/isSet/propertyParameter");

        ArrayList<Boolean> results = new ArrayList<>();
        if(parameters.size() == 0) {
            results.add(false);
        } else {
            results.add(true);
        }
        //results.add(DBpediaFunctions.isSet(property));
        log.debug("Result: " + results);

        return transformToBooleanValues(results);
    }

    /**
     *
     * @param parameters
     * @return
     */
    private ArrayList<Value> processContainsFunction(Map<String, String> parameters) {

        String property = parameters.get("http://dbpedia.org/function/contains/propertyParameter");
        String value = parameters.get("http://dbpedia.org/function/contains/valueParameter");

        ArrayList<Boolean> results = new ArrayList<>();
        results.add(DBpediaFunctions.contains(property, value));
        log.debug("Result: " + results);

        return transformToBooleanValues(results);
    }

    /**
     *
     * @param parameters
     * @return
     */
    private ArrayList<Value> processExtractDateFunction(Map<String, String> parameters) {

        String property = parameters.get("http://dbpedia.org/parameter/property");
        String dateType = parameters.get("http://dbpedia.org/parameter/datedatatype");

        ArrayList<String> results = new ArrayList<>();
        results.add(DBpediaFunctions.extractDate(property, dateType));
        log.debug("Result: " + results);

        return transformToLiteralValues(results, dateDatatype);
    }

    /**
     *
     * @param parameters
     * @return
     */
    private ArrayList<Value> processExtractStringFunction(Map<String, String> parameters) {

        String property = parameters.get("http://dbpedia.org/parameter/property");

        ArrayList<String> results = new ArrayList<>();
        results.add(DBpediaFunctions.extractString(property));
        log.debug("Result: " + results);

        return transformToLiteralValues(results, stringDatatype);
    }

    /**
     *
     * @param parameters
     * @return
     */
    private ArrayList<Value> processExtractEntity(Map<String, String> parameters) {

        String property = parameters.get("http://dbpedia.org/parameter/property");

        ArrayList<String> results = DBpediaFunctions.extractEntity(property);
        log.debug("Result: " + results);

        return transformToLiteralValues(results, null);
    }


    private ArrayList<Value> transformToBooleanValues(ArrayList<Boolean> booleans) {
        ArrayList<Value> values = new ArrayList<>();
        if(booleans != null) {
            for (Boolean bool : booleans) {
                if(bool != null) {
                    values.add(vf.createLiteral(bool)); // the type will be assigned in the EF :)
                }
            }
        }

        return values;
    }

    /**
     * Transforms strings into Literal Values
     * @param literals
     * @return
     */
    private ArrayList<Value> transformToLiteralValues(ArrayList<String> literals, IRI datatype) {
        ArrayList<Value> values = new ArrayList<>();
        if(literals != null) {
            for (String literal : literals) {
                if(literal != null) {
                    if (datatype == null) {
                        values.add(vf.createLiteral(literal)); // the type will be assigned in the EF :)
                    } else {
                        values.add(vf.createLiteral(literal, datatype));
                    }
                }
            }
        }

        return values;
    }


}
