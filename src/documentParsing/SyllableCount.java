package documentParsing;

public class SyllableCount {

	/**
	 * @author Yeshwanth & Rahul
	 * @param args
	 */

	public int CountSyllables(String word) {
		char[] vowels = new char[] { 'a', 'e', 'i', 'o', 'u', 'y' };
		int sysllableCount = 0;
		boolean lastWasVowel = false;

		for (int i = 0; i < word.length(); i++) {
			char wc = word.charAt(i);
			boolean foundVowel = false;
			for (char v : vowels) {
				// don't count diphthongs
				if (v == wc && lastWasVowel) {
					foundVowel = true;
					lastWasVowel = true;
					break;
				} else if (v == wc && !lastWasVowel) {
					sysllableCount++;
					foundVowel = true;
					lastWasVowel = true;
					break;
				}

			}
			if (i < word.length() - 1)
				if (wc == 'e'
						&& (word.charAt(i + 1) == ' ' || word.charAt(i + 1) == '.')) {
					sysllableCount--;
				}

			if (i == word.length() - 1 && wc == 'e')
				sysllableCount--;

			// if full cycle and no vowel found, set lastWasVowel to false;
			if (!foundVowel)
				lastWasVowel = false;
		}
		return sysllableCount;
	}

}