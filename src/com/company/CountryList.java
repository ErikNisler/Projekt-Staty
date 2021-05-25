package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CountryList {
    ArrayList<Country> listOfCountries = new ArrayList<>();

    public void loadFromFile(String file) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("vat-eu.txt")))) {
            while (scanner.hasNextLine()) {
                listOfCountries.add(Country.parseText(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Soubor nebyl nalezen! " + e.getLocalizedMessage());
        }
    }

    public void writeIntoFile(String file) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (Country c : listOfCountries) {
                writer.println(c.fileFormat());
            }
        } catch (IOException e) {
            System.err.println("Chyba při zápisu souboru " + e.getLocalizedMessage());
        }
    }

    public int sizeOfList() {
        return listOfCountries.size();
    }

    public Country getCountry(int position) {
        return listOfCountries.get(position);
    }

    public void addCountry(Country country) {
        listOfCountries.add(country);
    }

    //Method that will get you countries with specific vat you write in
   /** public void getSpecificVat(double input) {
        CountryList specificVats = new CountryList();
        for (int i = 0; i < listOfCountries.size(); i++) {
            Country country = listOfCountries.get(i);
            if (input == country.getRegularVat()) {
                System.out.println(country.getDecription());
                specificVats.addCountry(country);
                specificVats.writeIntoFile("vat-" + input + ".txt");
            } /*else {
                if (country.getRegularVat() == 20) {
                    System.out.println(country.getDecription());
                    specificVats.addCountry(country);
                    specificVats.writeIntoFile("vat-20.txt");
                }
            }*/
        }
