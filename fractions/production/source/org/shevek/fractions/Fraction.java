package org.shevek.fractions;

public class Fraction {

    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("0 isn't a valid denominator");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction forInteger(int integer) {
        return new Fraction(integer, 1);
    }

    public static Fraction forString(String fractionAsString) {
        final String[] parts = fractionAsString.split("/");
        if (parts.length == 2) {
            return new Fraction(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } else if (parts.length == 1) {
            return Fraction.forInteger(Integer.parseInt(parts[0]));
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        int gcd = greatestCommonDenominator(numerator, denominator);
        return String.format("%d/%d", numerator / gcd, denominator / gcd);
    }

    private int greatestCommonDenominator(int numberA, int numberB) {
        return numberB == 0 ? numberA : greatestCommonDenominator(numberB, numberA % numberB);
    }

    public Fraction add(Fraction other) {
        return new Fraction(numerator + (other.numerator * (denominator / other.denominator)), denominator);
    }

    public Fraction add(int integer) {
        return add(Fraction.forInteger(integer));
    }
}
