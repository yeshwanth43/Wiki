package documentParsing;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;

public class PartOfSpeech {
	public static void main(String []args){
		InputStream modelIn = null;

		try {
		  modelIn = new FileInputStream("conf/en-pos-maxent.bin");
		  POSModel model = new POSModel(modelIn);
		  POSTaggerME tagger = new POSTaggerME(model);
		  String [] tags=tagger.tag(args);
		  for(String s: tags){
			  System.out.println(s+"");
		  }
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
	}

}


