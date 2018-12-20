
package einlesen;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import exceptions.CSVLeseException;

/**
 * lieﬂt eine csv datei ein und speichert die daten in einer List<List<String>>.
 * die ‰uﬂereliste speichert die zeilen und die inneren die spalten in ihnen.
 *
 */
public class CSVReader {
	private static final char SEPERATOR1 = ',';
	private static final char SEPERATOR2 = ';';
	private static final char QUOTE = '"';

	/**
	 * L‰dt die angegebene datei.
	 * 
	 * @param path
	 *            der pfad zu der enzulesenden datei.
	 * @return den inhalt der datein in form von zwei verschachtelten listen.
	 * @throws IOException
	 *             wenn beim Laden der Datei ein fehler auftritt.
	 * @throws CSVLeseException
	 *             wenn beim Parsen der datei ein fehler auftritt.
	 */
	public static List<List<String>> loadCsv(String path) throws IOException, CSVLeseException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8.name()));

		List<List<String>> ret = new ArrayList<List<String>>();
		int i = 0;
		String zeile;
		while ((zeile = br.readLine()) != null) {
			ret.add(parseLine(zeile, i));
			i++;
		}
		return ret;
	}

	private static List<String> parseLine(String line, int z) throws CSVLeseException {
		boolean inQuotes = false;
		List<String> words = new LinkedList<String>();
		String word = "";
		for (int i = 0; i < line.length(); i++) {
			if (isEscapedQuote(line, i)) {
				i++;
				word = word + QUOTE;
			} else if (isQuote(line, i)) {
				inQuotes = !inQuotes;
			} else if (isSepereator(line, i) && !inQuotes) {
				word = word.trim();
				words.add(word);
				word = "";
			} else {
				word = word + line.charAt(i);
			}
		}

		if (!inQuotes) {
			word = word.trim();
			words.add(word);
		} else {
			throw new CSVLeseException("Keine schlieﬂenden \"" + QUOTE + "\" bei Zeile " + (z + 1));
		}

		return words;
	}

	private static boolean isSepereator(String line, int pos) {
		return line.charAt(pos) == SEPERATOR1 || line.charAt(pos) == SEPERATOR2;
	}

	private static boolean isQuote(String line, int pos) {
		return line.charAt(pos) == QUOTE;
	}

	private static boolean isEscapedQuote(String line, int pos) {
		return line.charAt(pos) == QUOTE && line.charAt(pos + 1) == QUOTE;
	}
}
