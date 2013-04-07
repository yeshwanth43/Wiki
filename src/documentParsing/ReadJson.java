package documentParsing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.stream.JsonReader;

public class ReadJson {

	public ArrayList<String> arrayList = new ArrayList<String>();
	public String[] indexes = new String[4];

	public ArrayList<String> readJsonFile() {

		try {
			JsonReader reader = new JsonReader(new FileReader(
					"parsed-contents/parsedContext.json"));

			reader.beginObject();

			while (reader.hasNext()) {

				String name = reader.nextName();

				if (name.equals("SENTENCES")) {
					reader.beginArray();
					while (reader.hasNext()) {
						arrayList.add(reader.nextString().replaceAll("/\n+/g",
								""));
					}
					reader.endArray();
				} else if (name.equals("Gunning Fog Index")) {
					indexes[0] = reader.nextString();
				} else if (name.equals("Automated Readability Index")) {
					indexes[1] = reader.nextString();
				} else if (name.equals("Flesch Kincaid Readability Index")) {
					indexes[2] = reader.nextString();
				} else if (name.equals("SMOG Index")) {
					indexes[3] = reader.nextString();
				}
			}
			reader.endObject();
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return arrayList;
	}
}
