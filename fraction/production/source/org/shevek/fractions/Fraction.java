package org.shevek.fractions;

public class Fraction {

    private final int numerator;

    public static Fraction forInteger(int integer) {
        return new Fraction(integer);
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", numerator, 1);
    }

    public Fraction add(Fraction other) {
        return Fraction.forInteger(numerator + other.numerator);
    }
}
