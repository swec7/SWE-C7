package einlesen;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class HTMLParser_neu {

	static StringBuffer sb = new StringBuffer(5000);

	final static String regex = "<tr>\\s.*\\s*(\\d*)\\s*<\\/td>\\s*.*>\\s*(.*)\\s*<\\/td>\\s*.*>\\s*(.*)\\s*<\\/td>\\s*.*>\\s*(.*)\\s*<\\/td>\\s*.*>\\s*(.*)\\s*<\\/td>\\s*.*>\\s*(.*)\\s*<\\/td>\\s*.*>\\s*(.*)\\s*<\\/td>\\s*.*>\\s*(.*)";
	
	static boolean empty_ = true;

	
	
	
	
	public static void main(String[] args) {
		
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		String string = null;
		
		

		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream("FH Aachen 3.html");

			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				sb.append(line);
				sb.append('\n');
			}
		
		final Matcher matcher = pattern.matcher(sb);

		while (matcher.find()) {
			//System.out.println("Full match: " + matcher.group(0));
			if(empty_)empty_ = false;
			for (int i = 1; i <= matcher.groupCount(); i++) {
				if(i == 1)System.out.println();
				System.out.println("Group " + i + ": " + matcher.group(i));
			}
			

	}

	}catch (Exception e) {
		System.out.println("EXCEPTION");
	}
	
		if(empty_)System.out.println("NO Entrys found!");
		
	}
}
