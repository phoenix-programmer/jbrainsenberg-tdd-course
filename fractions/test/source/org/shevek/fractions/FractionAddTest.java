package org.shevek.fractions;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.assertj.core.api.Assertions.*;

class FractionAddTest {

    @ParameterizedTest(name = "should return same result for integer fraction addition than when adding two integers")
    @CsvSource({"2, 3, 5", "4, 3, 7", "3, 0, 3"})
    void integerFractionRepresentationAdditionShouldYieldSameResultAsIntegerAddition(int a, int b, int c) {
        final Fraction aAsFraction = Fraction.forInteger(a);
        final Fraction bAsFraction = Fraction.forInteger(b);

        assertThat(aAsFraction.add(bAsFraction).toString()).isEqualTo(String.format("%s/1", c));
    }

    @Test
    @DisplayName("adding Integer to Fraction should result with same denominator")
    void addingIntegerToFractionShouldResultWithSameDenominator() {
        Fraction fraction = Fraction.forString("4/3");
        assertThat(fraction.add(1).toString()).isEqualTo("7/3");
    }

    @Test
    @DisplayName("should keep same denominator when adding two fractions with the same denominator")
    void addingTwoFractionsWithSameDenominatorShouldntChangeIt() {
        Fraction fraction = Fraction.of(1, 5);
        assertThat(fraction.add(Fraction.of(1, 5)).toString()).isEqualTo("2/5");
    }

    @Test
    @DisplayName("adding zero to zero should result zero")
    void addingZeroToZeroShouldResultZero() {
        assertThat(Fraction.zero().add(Fraction.zero()).toString()).isEqualTo("0");
    }

    @ParameterizedTest(name = "addition should handle negative fractions")
    @CsvSource({"0, -1/3, -1/3", "-1/3, -1/3, -2/3", "-1/3, 1/3, 0"})
    void additionShouldHandleNegativeFractions(String a, String b, String c) {
        assertThat(Fraction.forString(a).add(Fraction.forString(b)).toString()).isEqualTo(c);
    }

}
