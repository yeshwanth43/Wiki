package documentParsing;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Partofspeech {

	public String textTagger(String input) {

		InputStream modelIn = null;
		InputStream sentenceModel = null;
		String tokens[] = {};
		String taggedString = new String();
		try {
			InputStream tokenModel = new FileInputStream("conf/en-token.bin");
			TokenizerModel tModel = new TokenizerModel(tokenModel);
			Tokenizer tokenizer = new TokenizerME(tModel);
			tokens = tokenizer.tokenize(input);
			modelIn = new FileInputStream("conf/en-pos-maxent.bin");
			POSModel model = new POSModel(modelIn);
			POSTaggerME tagger = new POSTaggerME(model);
			String[] tags = tagger.tag(tokens);
			POSSample sample = new POSSample(tokens, tags);
			taggedString = sample.toString();
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

		return taggedString;
	}

	public static void main(String[] args) {
		Partofspeech pos = new Partofspeech();
		System.out
				.println(pos
						.textTagger("Conversation between boy & girl after 3 months of breakup"));
	}

}
