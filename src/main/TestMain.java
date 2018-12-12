package main;

import java.io.IOException;

import daten.Benutzer;
import einlesen.CSVReader;
import einlesen.HTMLParser;
import exceptions.CSVFormattierungsException;
import exceptions.CSVLeseException;

public class TestMain {

	public static void main(String[] args) throws IOException, CSVFormattierungsException, CSVLeseException {
		System.out.println(new Benutzer(CSVReader.loadCsv("CSV Datei - B. Sc. Informatik.csv"),
				HTMLParser.loadHTML("Fh Aachen.html")));
		System.out.println();
	}
}
