package main;

import java.io.IOException;

import einlesen.Factory;
import exceptions.CSVFormattierungsException;
import exceptions.CSVLeseException;

public class TestMain {

	public static void main(String[] args) throws IOException, CSVFormattierungsException, CSVLeseException {
		System.out.println(Factory.loadBenutzer());
		System.out.println();
	}
}
