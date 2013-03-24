package readabilityIndex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author yeshwanth
 * @param args
 */

public class FleschKincaidReadabilityTest {

	public double calculateReadability(String input) {
		System.out.println("input:\n" + input);
		return 0;
	}

	public static void main(String[] args) throws FileNotFoundException {
		File test = new File("test.txt");
		FileReader fr = new FileReader(test);
		BufferedReader br = new BufferedReader(fr);
		String text = null;
		StringBuffer lineText = new StringBuffer();
		try {
			while ((text = br.readLine()) != null) {
				lineText.append(text + "\n");
			}
			text = lineText.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FleschKincaidReadabilityTest index = new FleschKincaidReadabilityTest();
		System.out.println(index.calculateReadability(text));
	}
}
