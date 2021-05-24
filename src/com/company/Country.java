package com.company;

public class Country implements Comparable<Country>{
    private String symbol;
    private String name;
    private int regularVat;
    private double reducedVat;
    private boolean usingSpecialVat;

    public Country(String symbol, String name, int regularVat, double reducedVat, boolean usingSpecialVat) {
        this.symbol = symbol;
        this.name = name;
        this.regularVat = regularVat;
        this.reducedVat = reducedVat;
        this.usingSpecialVat = usingSpecialVat;
    }

    public static Country parseText(String record) throws ArrayIndexOutOfBoundsException{
        String[] items = record.split("\t");

        if (items.length != 5) { throw new ArrayIndexOutOfBoundsException("Mimo rozsah!"); }

        String symbol = items[0];
        String name = items[1];
        int regularVat = Integer.parseInt(items[2]);
        double reducedVat = Double.parseDouble(items[3]);
        boolean usingSpecialVat = Boolean.parseBoolean(items[4]);

        return new Country(symbol, name, regularVat, reducedVat, usingSpecialVat);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRegularVat() {
        return regularVat;
    }

    public void setRegularVat(int regularVat) {
        this.regularVat = regularVat;
    }

    public double getReducedVat() {
        return reducedVat;
    }

    public void setReducedVat(double reducedVat) {
        this.reducedVat = reducedVat;
    }

    public boolean isUsingSpecialVat() {
        return usingSpecialVat;
    }

    public void setUsingSpecialVat(boolean usingSpecialVat) {
        this.usingSpecialVat = usingSpecialVat;
    }

    public String getDecription(){
        return getName()+" ("+getSymbol()+"): "+getRegularVat()+" %";
    }

    @Override
    public int compareTo(Country second) {
        return Integer.compare(this.getRegularVat(), second.getRegularVat());
    }

    public String fileFormat(){
        return getName() + "\t"
                +getSymbol() + "\t"
                +getRegularVat() + "\t"
                +getRegularVat() + "\t"
                +isUsingSpecialVat();
    }

}
