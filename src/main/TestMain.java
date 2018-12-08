package main;

import java.io.IOException;

import daten.Benutzer;
import einlesen.CSVExtractor;
import exceptions.CSVFormattierungsException;
import exceptions.CSVLeseException;

public class TestMain {

	public static void main(String[] args) throws IOException, CSVFormattierungsException, CSVLeseException {
		System.out.println(
				new Benutzer(CSVExtractor.loadCsv("C:/Users/lukas/Downloads/CSV Datei - B. Sc. Informatik.csv")));
	}
}
