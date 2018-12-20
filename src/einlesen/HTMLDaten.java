package einlesen;

import java.util.Map;

/**
 * speichert alle daten die aus einer html-datei ausgelesen werden.
 * (geschreibene module und name des studiengangs).
 *
 */
public class HTMLDaten {
	private String studiengang;
	private Map<String, String[]> map;

	public HTMLDaten(String studiengang, Map<String, String[]> map) {
		super();
		this.studiengang = studiengang;
		this.map = map;
	}

	public String getStudiengang() {
		return "Informatik";
	}

	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}

	public Map<String, String[]> getMap() {
		return map;
	}

	public void setMap(Map<String, String[]> map) {
		this.map = map;
	}
}