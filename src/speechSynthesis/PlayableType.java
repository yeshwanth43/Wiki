package speechSynthesis;

public class PlayableType {
	private String typeName;
	public static final PlayableType TEXT = new PlayableType("text");

	public static final PlayableType TEXT_FILE = new PlayableType("text file");

	public static final PlayableType JSML_FILE = new PlayableType("JSML file");

	public static final PlayableType JSML = new PlayableType("JSML");

	public static final PlayableType URL = new PlayableType("URL");

	private PlayableType(String typeName) {
		this.typeName = typeName;
	}

	public String toString() {
		return this.typeName;
	}
}