package documentParsing;

import java.io.BufferedReader;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	public int no_Of_Polysyllables = 0;

	public boolean parse(String resourceLocation) throws IOException {

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
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		docProcessing();
		statistics();
		if (!parsedText.equals("Document not Parsed Successfully")) {
			return true;
		} else {
			return false;
		}
	}

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
			SyllableCount test = new SyllableCount();
			for (String s : tokens) {
				int syllable = test.CountSyllables(s);
				totalSyllableCount += syllable;
				no_Of_Characters += s.length();
				if (syllable >= 3) {
					no_Of_Polysyllables++;
				}
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
		try {
			writeToJson(sentences);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return no_Of_Sentences;
	}

	private void statistics() {
		System.out.println(no_Of_Sentences + "\t sentences");
		System.out.println(totalSyllableCount + "\t total syllable count");
		System.out.println(no_Of_Words + "\t words");
		System.out.println(no_Of_Characters + "\t characters");
		System.out.println(no_Of_Polysyllables + "\t polysyllables");
	}

	@SuppressWarnings("unchecked")
	private void writeToJson(String[] sentences) throws IOException {
		JSONObject jsonObj = new JSONObject();
		ReadabilityIndexes readability = new ReadabilityIndexes();
		double fleschKincaidReadabilityIndex = readability
				.fleschKincaidReadabilityIndex(no_Of_Words, no_Of_Sentences,
						totalSyllableCount);
		double automatedReadabilityIndex = readability
				.automatedReadabilityIndex(no_Of_Characters, no_Of_Words,
						no_Of_Sentences);
		double smogIndex = readability.smogIndex(no_Of_Polysyllables,
				no_Of_Sentences);
		double gunningFogIndex = readability.gunningFogIndex(no_Of_Words,
				no_Of_Sentences, no_Of_Polysyllables);
		JSONArray arrayList = new JSONArray();
		jsonObj.put("Flesch Kincaid Readability Index",
				fleschKincaidReadabilityIndex);
		jsonObj.put("Automated Readability Index", automatedReadabilityIndex);
		jsonObj.put("SMOG Index", smogIndex);
		jsonObj.put("Gunning Fog Index", gunningFogIndex);
		for (String sentence : sentences) {
			arrayList.add(sentence);
		}
		jsonObj.put("SENTENCES", arrayList);
		try {
			FileWriter fWriter = new FileWriter(new File(
					"parsed-contents/parsedContext.json"));
			fWriter.write(jsonObj.toJSONString());
			fWriter.flush();
			fWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out
				.println("Enter the path for the Document to test Synthesizer");
		String resourceLocation = br.readLine();
		DocParseV1 dpv1 = new DocParseV1();
		dpv1.parse(resourceLocation);

	}

}
