package main;

import java.io.IOException;

import einlesen.Factory;
import exceptions.CSVLeseException;
import exceptions.HTMLLeseException;

public class TestMain {
	public static final String HTML_PATH = "FH Aachen.html";
	public static final String CSV_PATH = "studiengaenge.csv";

	public static void main(String[] args) throws IOException, CSVLeseException, HTMLLeseException {
		System.out.println(Factory.loadBenutzer());
		System.out.println();

	}
}
