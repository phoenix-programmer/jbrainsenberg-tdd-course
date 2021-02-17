package org.shevek.fractions;

import java.util.*;

import static java.lang.Math.abs;

public class Fraction {

    private static final String ZERO_AS_STRING = "0";
    private static final Fraction ZERO = new Fraction(0, 1);
    private final int numerator;
    private final int denominator;

    private Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("0 isn't a valid denominator");
        }
        if (numerator < 0 && denominator < 0) {
            this.numerator = abs(numerator);
        } else {
            this.numerator = numerator * (denominator < 0 ? -1 : 1);
        }
        this.denominator = abs(denominator);
    }

    public static Fraction zero() {
        return ZERO;
    }

    public static Fraction fraction(int numerator, int denominator) {
        if (numerator == 0) {
            return ZERO;
        }
        return new Fraction(numerator, denominator);
    }

    public static Fraction forInteger(int integer) {
        if (integer == 0) {
            return ZERO;
        }
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

    public Fraction negated() {
        return Fraction.fraction(-1 * numerator, denominator);
    }

    public Fraction add(Fraction other) {
        if (this == ZERO) {
            return other;
        } else if (other == ZERO) {
            return this;
        }
        return fraction(numerator * other.denominator + other.numerator * denominator, denominator * other.denominator);
    }

    public Fraction add(int integer) {
        return add(Fraction.forInteger(integer));
    }

    public Fraction subtract(Fraction fraction) {
        return add(fraction.negated());
    }

    public Fraction subtract(int number) {
        return subtract(Fraction.forInteger(number));
    }

    public Fraction multiply(Fraction fraction) {
        return Fraction.fraction(numerator * fraction.numerator, denominator * fraction.denominator);
    }

    public Fraction multiply(int number) {
        return multiply(Fraction.forInteger(number));
    }

    public Fraction divide(Fraction fraction) {
        if (fraction == ZERO) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return Fraction.fraction(numerator * fraction.denominator, denominator * fraction.numerator);
    }

    public Fraction divide(int number) {
        if (number == 1) {
            return this;
        }
        return divide(Fraction.forInteger(number));
    }

    public Fraction simplify() {
        if (this == ZERO) {
            return ZERO;
        }
        int gcd = abs(greatestCommonDivisor(numerator, denominator));
        if (gcd == 1) {
            return this;
        }
        return fraction(numerator / gcd, denominator / gcd);
    }

    @Override
    public String toString() {
        if (numerator == 0) {
            return ZERO_AS_STRING;
        }
        Fraction fraction = simplify();
        return String.format("%d/%d", fraction.numerator, fraction.denominator);
    }

    private int greatestCommonDivisor(int numberA, int numberB) {
        while (numberB != 0) {
            int temp = numberB;
            numberB = numberA % numberB;
            numberA = temp;
        }
        return abs(numberA);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Fraction fraction = ((Fraction) o).simplify();
        Fraction thisSimplified = simplify();
        return thisSimplified.numerator == fraction.numerator && thisSimplified.denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        final Fraction simplified = simplify();
        return Objects.hash(simplified.numerator, simplified.denominator);
    }
}
