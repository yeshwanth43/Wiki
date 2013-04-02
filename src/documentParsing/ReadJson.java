package documentParsing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {

	public ArrayList<String> arrayList = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	public ArrayList<String> readJsonFile() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(
					"parsed-contents/parsedContext.json"));
			JSONObject jsonObject = (JSONObject) obj;
			arrayList = (ArrayList<String>) jsonObject.get("SENTENCES");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return arrayList;
	}
}
