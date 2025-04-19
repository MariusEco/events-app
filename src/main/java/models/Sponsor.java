package models;

public class Sponsor {
    private String name;
    private double sponsoredAmount;

    public Sponsor(String name, double sponsoredAmount) {
        this.name = name;
        this.sponsoredAmount = sponsoredAmount;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " - Sponsored: " + sponsoredAmount + " RON";
    }
}
