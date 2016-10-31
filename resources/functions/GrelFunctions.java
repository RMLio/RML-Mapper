import java.util.Map;

/**
 * GREL Functions
 *
 * @author andimou
 */
public class GrelFunctions {

    public static boolean isSet(String valueParameter) {
        return !valueParameter.isEmpty();
    }

    public static boolean contains(String valueParameter, String subStringParameter) {
        return valueParameter.contains(subStringParameter);
    }

    public static boolean booleanMatch(String valueParameter, String regexParameter) {
        return valueParameter.matches(regexParameter);
    }
}
