package org.shevek.math.fractions;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.converter.*;
import org.junit.jupiter.params.provider.*;

import static org.assertj.core.api.Assertions.*;
import static org.shevek.math.fractions.Fraction.*;

class FractionAddTest {

    @ParameterizedTest(name = "should return same result for integer fraction addition than when adding two integers")
    @CsvSource({"2, 3, 5", "4, 3, 7", "3, 0, 3"})
    void integerFractionRepresentationAdditionShouldYieldSameResultAsIntegerAddition(int a, int b, int c) {
        final Fraction aAsFraction = Fraction.forInteger(a);
        final Fraction bAsFraction = Fraction.forInteger(b);

        assertThat(aAsFraction.add(bAsFraction)).isEqualTo(Fraction.forInteger(c));
    }

    @Test
    @DisplayName("adding Integer to Fraction should result with same denominator")
    void addingIntegerToFractionShouldResultWithSameDenominator() {
        Fraction fraction = Fraction.forString("4/3");
        assertThat(fraction.add(1)).isEqualTo(fraction(7, 3));
    }

    @Test
    @DisplayName("should keep same denominator when adding two fractions with the same denominator")
    void addingTwoFractionsWithSameDenominatorShouldntChangeIt() {
        Fraction fraction = fraction(1, 5);
        assertThat(fraction.add(fraction(1, 5))).isEqualTo(fraction(2, 5));
    }

    @Test
    @DisplayName("adding zero to zero should result zero")
    void addingZeroToZeroShouldResultZero() {
        assertThat(zero().add(zero())).isEqualTo(zero());
    }

    @ParameterizedTest(name = "addition should handle negative fractions: {0} + {1} = {2}")
    @CsvSource({"0, -1/3, -1/3", "-1/3, -1/3, -2/3", "-1/3, 1/3, 0"})
    void additionShouldHandleNegativeFractions(String a, String b, String c) {
        assertThat(Fraction.forString(a).add(Fraction.forString(b))).isEqualTo(Fraction.forString(c));
    }

    @ParameterizedTest(name = "different denominators")
    @CsvSource({ "2/3, 3/5, 19/15", "1/3, 1/2, 5/6" })
    void differentDenominators(@ConvertWith(StringToFraction.class) Fraction a, Fraction b, Fraction c) {
        assertThat(a.add(b)).isEqualTo(c);
    }

    static class StringToFraction extends TypedArgumentConverter<String, Fraction> {

        protected StringToFraction() {
            super(String.class, Fraction.class);
        }

        @Override
        protected Fraction convert(String source) throws ArgumentConversionException {
            return Fraction.forString(source);
        }
    }
}
