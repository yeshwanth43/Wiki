package documentParsing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

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

	private String parsedText = "Document not Parsed Successfully";
	public int no_Of_Sentences = 0;
	public int totalSyllableCount = 0;
	public int no_Of_Words = 0;
	public int no_Of_Characters = 0;

	public boolean parse() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out
				.println("Enter the path for the Document to test Synthesizer");
		String resourceLocation = br.readLine();
		if (resourceLocation.isEmpty()) {
			resourceLocation = "assets/readability.pdf";
		}
		Parser parser = new AutoDetectParser();
		ContentHandler handler = new BodyContentHandler();
		Metadata meta = new Metadata();
		try {
			File file = new File(resourceLocation);
			InputStream stream = new FileInputStream(file);
			parser.parse(stream, handler, meta, new ParseContext());
			parsedText = handler.toString();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		docProcessing();
		if (!parsedText.equals("Document not Parsed Successfully")) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("resource")
	private int docProcessing() throws IOException {
		InputStream sentenceModel = new FileInputStream("conf/en-sent.bin");
		InputStream tokenModel = new FileInputStream("conf/en-token.bin");
		String sentences[] = {};
		String tokens[] = {};
		try {
			SentenceModel sModel = new SentenceModel(sentenceModel);
			TokenizerModel tModel = new TokenizerModel(tokenModel);
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(sModel);
			Tokenizer tokenizer = new TokenizerME(tModel);
			sentences = sentenceDetector.sentDetect(parsedText);
			tokens = tokenizer.tokenize(parsedText);
			no_Of_Words = tokens.length;
			no_Of_Sentences = sentences.length;
			FleschKincaidReadabilityTest test = new FleschKincaidReadabilityTest();
			for (String s : tokens) {
				totalSyllableCount += test.CountSyllables(s);
				no_Of_Characters += s.length();
			}
			statistics();
			int i = 0;
			File file = new File("parsed-contents/parsedcontent.txt");
			FileWriter fw;
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for (String s : sentences) {
				i++;
				System.out.println(s + "\t" + i);
				bw.write(s);
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sentenceModel != null) {
				try {
					sentenceModel.close();
				} catch (IOException e) {
				}
			}
		}
		return no_Of_Sentences;
	}

	private void statistics() {
		FleschKincaidReadabilityTest test = new FleschKincaidReadabilityTest();
		System.out.println(no_Of_Sentences + "\t sentences");
		System.out.println(no_Of_Words + "\t words");
		System.out.println(no_Of_Characters + "\t characters");
		System.out.println(test.calculateReadabilityIndex(no_Of_Words,
				no_Of_Sentences, totalSyllableCount) + "\t readability Index");
	}

	public static void main(String args[]) throws IOException {
		DocParseV1 dpv1 = new DocParseV1();
		dpv1.parse();

	}

}
