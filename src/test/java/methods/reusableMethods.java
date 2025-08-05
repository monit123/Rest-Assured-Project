package methods;
import io.restassured.path.json.JsonPath;
public class reusableMethods {
	public static String rawToJson(String value,String parameterName) {
    JsonPath js=new JsonPath(value);
    return js.getString(parameterName);
    }
}
