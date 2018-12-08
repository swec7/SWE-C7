package daten;

import java.io.IOException;

public class TestMain {

	public static void main(String[] args) throws IOException, CSVFormattierungsException, CSVLeseException {
		System.out.println(CsvExtractor.loadCsv("C:/Users/lukas/Downloads/CSV Datei - B. Sc. Informatik.csv"));
	}
}
