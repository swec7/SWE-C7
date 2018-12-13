package main;

import java.io.IOException;

import daten.Benutzer;
import exceptions.CSVLeseException;
import exceptions.HTMLLeseException;

public class TestMain {
	public static final String HTML_PATH = "FHAachen.html";
	public static final String CSV_PATH = "studiengaenge.csv";

	public static void main(String[] args) throws IOException, CSVLeseException, HTMLLeseException {
		System.out.println(new Benutzer(HTML_PATH, CSV_PATH));
	}
}
