package documentParsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class DocParseV1 {

	/**
	 * @author yeshwanth
	 * @param args
	 * @throws IOException
	 */

	public String parse() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out
				.println("Enter the path for the Document to test Synthesizer");
		String resourceLocation = br.readLine();
		String parsedText = "Empty string means Document not Parsed Successfully";
		Parser parser = new AutoDetectParser();
		ContentHandler handler = new BodyContentHandler();
		Metadata meta = new Metadata();
		try {
			File file = new File(resourceLocation);
			InputStream stream = new FileInputStream(file);
			parser.parse(stream, handler, meta, new ParseContext());
			System.out.println("Title: " + meta.get("title"));
			System.out.println("Author: " + meta.get("Author"));
			System.out.println(handler.toString() + "\nContent: ");
			parsedText=handler.toString();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return parsedText;
	}

}
