package be.ugent.mmlab.rml.grel;

/**
 * Created by andimou on 7/10/16.
 */
public class GrelVocabulary {
    public static String GREL_NAMESPACE = "http://semweb.mmlab.be/ns/grel#";

    public enum GrelTerm {

        // GREL CLASSES

        // GREL BOOLEAN PROPERTIES
        IS_SET("isSet"),
        BOOLEAN_MATCH("booleanMatch"),
        CONTAINS("contains"),

        // PARAMETERS PROPERTIES
        VALUE_PARAMETER("valueParameter"),
        SUBSTRING_PARAMETER("subStringParameter"),
        REGEX_PARAMETER("regexParameter"),

        // OUTPUT
        BOOLEAN_VALUE("booleanValue");

        private String displayName;

        private GrelTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }

    }
}
