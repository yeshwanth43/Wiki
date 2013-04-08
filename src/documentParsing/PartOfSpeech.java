package documentParsing;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class PartOfSpeech {

	public HashMap<String, ArrayList<String>> textTagger(String input) {

		InputStream modelIn = null;
		InputStream sentenceModel = null;
		String sentences[] = {};
		try {
			modelIn = new FileInputStream("conf/en-pos-maxent.bin");
			sentenceModel = new FileInputStream("conf/en-sent.bin");
			SentenceModel sModel = new SentenceModel(sentenceModel);
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(sModel);
			sentences = sentenceDetector.sentDetect(input);
			POSModel model = new POSModel(modelIn);
			POSTaggerME tagger = new POSTaggerME(model);
			System.out.println(sentences[0]);
			String[] tags = tagger.tag(sentences);
			for (String s : tags) {
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (sentenceModel != null) {
					try {
						sentenceModel.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return null;
	}

	public static void main(String[] args) {
		PartOfSpeech pos = new PartOfSpeech();
		pos.textTagger("How does technology aid the battle against modern day slavery? Tune in to the live-stream from the Google DC office Tuesday, April 9, 2.30pm - 4.00pm EDT, where Polaris Project, Liberty Asia and La Strada International will join us to announce an exciting new initiative, followed by panel discussions exploring how technology can disrupt human trafficking. ");
	}

}
