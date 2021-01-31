package org.shevek.fractions;

public class Fraction {

    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction forInteger(int integer) {
        return new Fraction(integer, 1);
    }

    public static Fraction forString(String fractionAsString) {
        final String[] parts = fractionAsString.split("/");
        return new Fraction(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    @Override
    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }

    public Fraction add(Fraction other) {
        return Fraction.forInteger(numerator + other.numerator);
    }
}
