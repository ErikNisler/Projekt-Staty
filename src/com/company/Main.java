package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner userEntry = new Scanner(System.in);
        CountryList countriesListOver20 = new CountryList();
        ArrayList<Country> countriesOver20 = new ArrayList<>();
        ArrayList<String> countriesUnder20 = new ArrayList<>();
        CountryList countryList = new CountryList();

        /**Načtení ze souboru*/
        countryList.loadFromFile("vat-eu.txt");

        /**Vypište seznam všech států a u každého uveďte základní sazbu daně z přidané hodnoty ve formátu:*/
        System.out.println("Všechny státy:");
        for (int i = 0; i < countryList.sizeOfList(); i++) {
            Country country = countryList.getCountry(i);
            System.out.println(country.getDecription());
        }
        System.out.println();

        /**Vypište ve stejném formátu pouze státy, které mají základní sazbu daně z přidané hodnoty vyšší než 20 % a přitom nepoužívají speciální sazbu daně.*/
        System.out.println("Státy se základní sazbou DPH > 20 % a nepoužívají speciální sazbu daně.");
        for (int i = 0; i < countryList.sizeOfList(); i++) {
            Country country = countryList.getCountry(i);
            if ((country.getRegularVat() > 20) && (!country.isUsingSpecialVat())) {
                countriesOver20.add(country);
                System.out.println(country.getDecription());
            } else {
                countriesUnder20.add(country.getSymbol());
            }
        }
        /**Pod výpis doplň řádek s rovnítky pro oddělení a poté seznam zkratek států, které ve výpisu nefigurují.*/
        System.out.println();
        System.out.println("==================");
        System.out.println("Sazba VAT 20 % a nižší nebo používají speciální sazbu: ");
        /**Vypsání bez hranatých závorek*/
        System.out.println(countriesUnder20.toString().replace("[", "").replace("]", ""));


        System.out.println();
        /**Výpis seřaďte podle výše základní sazby DPH/VAT sestupně (nejprve státy s nejvyšší sazbou).*/
        System.out.println();
        System.out.println("SEŘAZENO:");
        Collections.sort(countriesOver20, Collections.reverseOrder());
        //Přidání států > 20 % do CountryListu (kvůli zápisu do souboru)
        for (Country c : countriesOver20) {
            countriesListOver20.addCountry(c);
            System.out.println(c.getDecription());
        }


        /**Výsledný výpis kromě zapište i do souboru s názvem vat-over-20.txt, který uložíte do stejné složky, ve které byl vstupní soubor.*/
        countriesListOver20.writeIntoFile("vat-over-20.txt");
        System.out.println("====================");
        /**Doplňte možnost, aby uživatel z klávesnice zadal výši sazby DPH/VAT, podle které se má filtrovat.
         Pokud uživatel zmáčkne pouze Enter, jako výchozí hodnota se použije sazba 20 %.
         Upravte název výstupního souboru tak, aby reflektoval zadanou sazbu daně.*/
        System.out.println("Zadej výši sazby: ");
        String input = userEntry.nextLine();

        CountryList specificVats = new CountryList();
        for (int i = 0; i < countryList.sizeOfList(); i++) {
            Country country = countryList.getCountry(i);
            if (input.isEmpty()) {
                if (country.getRegularVat() == 20.0) {
                    System.out.println(country.getDecription());
                    specificVats.addCountry(country);
                    specificVats.writeIntoFile("vat-20.txt");
                }
            } else if (Double.valueOf(input) == country.getRegularVat()) {
                System.out.println(country.getDecription());
                specificVats.addCountry(country);
                specificVats.writeIntoFile("vat-" + input + ".txt");

            }
        }
    }
}
