package main;

import java.io.IOException;

import daten.Benutzer;
import exceptions.CSVLeseException;
import exceptions.HTMLLeseException;

public class TestMain {

	public static void main(String[] args) throws IOException, CSVLeseException, HTMLLeseException {
		System.out.println(new Benutzer("FH Aachen.html", "studiengaenge.csv"));
	}
}
