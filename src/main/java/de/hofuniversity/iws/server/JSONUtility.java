package de.hofuniversity.iws.server;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
public class JSONUtility {
    
	  public static JSONObject convert_string_to_json(String body) throws JSONException {

	  		JSONObject jsonObj = new JSONObject(body);
			return jsonObj;
	  }
}
