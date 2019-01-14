package einlesen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.HTMLLeseException;

/**
 * lie?t eine html datei ein und speichert die daten in einer instaz von
 * HTMLDaten.
 *
 */
public class HTMLParser {
	/*
	 * <tr>\s.*\s*(\d*)\s*<\/td>\s*.*>\s*(.*)\s*<\/td>\s*.*>\s*(.*)\s*<\/td>\s*.
	 * *>\s*(.*)\s*<\/td>\s*.*>\s*(.*)\s*<\/td>\s*.*>\s*(.*)\s*<\/td>\s*.*>\s*(.
	 * *)\s*<\/td>\s*.*>\s*(.*)
	 */
	private final static String regex = "<tr>\\s.*\\s*(\\d*)\\s*<\\/td>\\s*.*>\\s*(.*)\\s*<\\/td>\\s*.*>\\s*(.*)\\s*<\\/td>\\s*.*>\\s*(.*)\\s*<\\/td>\\s*.*>\\s*(.*)\\s*<\\/td>\\s*.*>\\s*(.*)\\s*<\\/td>\\s*.*>\\s*(.*)\\s*<\\/td>\\s*.*>\\s*(.*)";
	private static final int COLLUMS = 9;

	/**
	 * l?dt die angegebene datei und gibt ein Obejct von HTMLDaten zur?ck
	 * welches alle eingelesenen Informationen enth?llt.
	 *
	 * @param path
	 *            der pfad zu der einzulesenden datei
	 * @return ein HTMLDaten obejct mit allen eingelesenen Informationen.
	 * @throws IOException
	 *             wenn beim laden der datei ein fehler auftritt.
	 * @throws HTMLLeseException
	 *             wenn beim paresen der datei ein fehler auftritt.
	 */
	public static HTMLDaten loadHTML(String path) throws IOException, HTMLLeseException {
		Map<String, String[]> map = getMap(path);
		String name = getName(path);

		// for (String key : map.keySet()) {
		// System.out.print(String.format("%-10s", key.substring(0,
		// Math.min(key.length(), 10))) + ":" + "\t");
		// for (String value : map.get(key)) {
		// if (value == null) {
		// System.out.print("null");
		// } else {
		// System.out.print(
		// String.format("%-10s", value.substring(0, Math.min(value.length(),
		// 10))) + "|" + "\t");
		// }
		// }
		// System.out.println();
		// }
		return new HTMLDaten(name, map);
	}

	private static Map<String, String[]> getMap(String path) throws FileNotFoundException, HTMLLeseException {
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		Map<String, String[]> map = new HashMap<>();
		boolean empty = true;
		FileInputStream inputStream = null;
		Scanner sc = null;
		StringBuffer sb = new StringBuffer(5000);
		try {
			inputStream = new FileInputStream(path);

			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				sb.append(line);
				sb.append('\n');
			}

			final Matcher matcher = pattern.matcher(sb);

			while (matcher.find()) {
				empty = false;
				String[] row = new String[COLLUMS - 2];
				for (int i = 2; i < COLLUMS; i++) {
					String s = matcher.group(i);
					// System.out.println(s);
					row[i - 2] = s;
				}
				map.put(matcher.group(1), row);
			}
			if (empty == true) {
				noData();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new HTMLLeseException("Fehlerhafte HTML Datei (" + e.getMessage() + ")");
		}
		sc.close();

		return map;
	}

	private static void noData() throws HTMLLeseException {
		throw new HTMLLeseException("Fehlerhafte HTML Datei ( KEINE DATEN )");
	}

	static String getName(String path) throws HTMLLeseException, FileNotFoundException {
		StringBuffer sb = new StringBuffer(5000);
		String course_n = "";
		// Pattern course_name =
		// Pattern.compile("align..left.*colspan=.9.>\\s*(.*)<.th><.tr>"); old
		// version
		Pattern course_name = Pattern.compile("align..left.*colspan=.9.>\\s*Bachelor\\s*(.*)<.th><.tr>");
		Matcher course_name_matcher;

		FileInputStream inputStream = null;
		Scanner sc = null;
		inputStream = new FileInputStream(path);

		sc = new Scanner(inputStream, "UTF-8");
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			sb.append(line);
			sb.append('\n');
		}
		course_name_matcher = course_name.matcher(sb);
		while (course_name_matcher.find()) {
			course_n = course_name_matcher.group(1);
			// System.out.println(course_n);
		}
		sc.close();
		return course_n;
	}

	// static boolean empty_ = true;
	//
	// public static void main(String[] args) {
	//
	// final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
	// String string = null;
	//
	// FileInputStream inputStream = null;
	// Scanner sc = null;
	// try {
	// inputStream = new FileInputStream("FHAachen.html");
	//
	// sc = new Scanner(inputStream, "UTF-8");
	// while (sc.hasNextLine()) {
	// String line = sc.nextLine();
	// sb.append(line);
	// sb.append('\n');
	// }
	//
	// final Matcher matcher = pattern.matcher(sb);
	//
	// while (matcher.find()) {
	// // System.out.println("Full match: " + matcher.group(0));
	// if (empty_)
	// empty_ = false;
	// for (int i = 1; i <= matcher.groupCount(); i++) {
	// if (i == 1)
	// System.out.println();
	// System.out.println("Group " + i + ": " + matcher.group(i));
	// }
	//
	// }
	//
	// } catch (Exception e) {
	// System.out.println("EXCEPTION");
	// e.printStackTrace();
	// }
	//
	// if (empty_)
	// System.out.println("NO Entrys found!");
	//
	// }
}
