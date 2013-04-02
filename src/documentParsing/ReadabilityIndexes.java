package documentParsing;

public class ReadabilityIndexes {

	/**
	 * @author yeshwanth
	 * @param args
	 */

	public double automatedReadabilityIndex(int no_Of_characters,
			int no_Of_words, int no_Of_Sentences) {
		double index = ((((4.71) * (no_Of_characters / no_Of_words)) + ((0.5) * (no_Of_words / no_Of_Sentences))) - (21.43));
		return index;
	}

	public double smogIndex(int no_Of_Polysyllables, int no_Of_Sentences) {
		double index = (((1.0430) * (Math
				.sqrt(((no_Of_Polysyllables * 30) / no_Of_Sentences)))) + 3.1291);
		return index;
	}

	public double gunningFogIndex(int no_Of_Words, int no_Of_Sentences,
			int no_Of_Polysyllables) {
		double index = (((0.4) * (no_Of_Words / no_Of_Sentences)) + ((100) * (no_Of_Polysyllables / no_Of_Words)));
		return index;
	}

	public double fleschKincaidReadabilityIndex(int totalWords,
			int totalSentences, int totalSyllableCount) {
		double readabilityIndex = 0;
		readabilityIndex = (((0.39) * (totalWords / totalSentences)) + ((11.8) * (totalSyllableCount / totalWords)));
		return readabilityIndex;
	}
}
