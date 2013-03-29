package documentParsing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class SampleTika {

	/**
	 * @author yeshwanth
	 * @param args
	 */

	public String clean() {
		String extractedText = new String();
		Tika tika = new Tika();
		Parser parser = new AutoDetectParser();
		ContentHandler handler = new BodyContentHandler();
		Metadata meta = new Metadata();
		try {
			File input = new File("assets/uml2.pdf");
			InputStream stream = new FileInputStream(input);

			parser.parse(stream, handler, meta, new ParseContext());
			System.out.println("Title: " + meta.get("title"));
			System.out.println("Author: " + meta.get("Author"));
			System.out.println(handler.toString() + "\nContent: ");
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
		try {
			extractedText = tika.parseToString(new File("assets/swsn.pdf"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return extractedText;
	}

	public static void main(String args[]) {
		SampleTika st = new SampleTika();
		st.clean();

	}

}
