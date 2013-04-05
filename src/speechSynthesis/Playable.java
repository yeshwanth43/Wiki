package speechSynthesis;

import java.io.File;

public class Playable {
	private PlayableType type;
	private Object data = null;

	private String name;

	public static Playable createJSMLPlayable(String jsmlText) {
		return new Playable(PlayableType.JSML, jsmlText, jsmlText);
	}

	public static Playable createJSMLFilePlayable(File jsmlFile) {
		return new Playable(PlayableType.JSML_FILE, jsmlFile,
				jsmlFile.getName());
	}

	public static Playable createTextPlayable(String text) {
		return new Playable(PlayableType.TEXT, text, text);
	}

	public static Playable createTextFilePlayable(File textFile) {
		return new Playable(PlayableType.TEXT_FILE, textFile,
				textFile.getName());
	}

	public static Playable createURLPlayable(String url) {
		return new Playable(PlayableType.URL, url, url.toString());
	}

	private Playable(PlayableType type, Object data, String name) {
		this.type = type;
		this.data = data;
		this.name = name;
	}

	public PlayableType getType() {
		return this.type;
	}

	public File getFile() {
		if ((this.type == PlayableType.TEXT_FILE)
				|| (this.type == PlayableType.JSML_FILE)) {
			return (File) this.data;
		}
		return null;
	}

	public String getText() {
		if ((this.type == PlayableType.JSML)
				|| (this.type == PlayableType.TEXT)) {
			return (String) this.data;
		}
		return null;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		String typeName = "[" + this.type.toString() + "] ";
		return typeName + this.name;
	}
}
