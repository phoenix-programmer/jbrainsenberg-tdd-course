package org.shevek.math.fractions;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.assertj.core.api.Assertions.*;
import static org.shevek.math.fractions.Fraction.fraction;

public class FractionCreationAndRepresentationTests {

    @Test
    @DisplayName("Fractions can't have 0 as denominator")
    void cantHaveZeroAsDenominator() {
        assertThatIllegalArgumentException().isThrownBy(() -> fraction(5, 0)).withMessage("0 isn't a valid denominator");
        assertThatIllegalArgumentException().isThrownBy(() -> Fraction.forString("3/0")).withMessage("0 isn't a valid denominator");
        assertThatIllegalArgumentException().isThrownBy(() -> Fraction.forString("0/0")).withMessage("0 isn't a valid denominator");
    }

    @ParameterizedTest(name = "string factory method should deal with integers: {0} = {1}")
    @CsvSource({"1, 1/1", "2, 2/1", "-1, -1/1"})
    void stringFactoryMethodShouldDealWithIntegers(String integerAsString, String expectedResult) {
        assertThat(Fraction.forString(integerAsString)).isEqualTo(Fraction.forString(expectedResult));
    }

    @ParameterizedTest(name = "string factory method should not accept invalid input: {0}")
    @CsvSource({"A", "1/2/3", "1/A"})
    void stringFactoryMethodShouldNotAcceptInvalidInput(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> Fraction.forString(input));
    }

    @ParameterizedTest(name = "should have denominator equals to one for integers: {0}")
    @CsvSource({"1", "2", "-3"})
    void integersWhenRepresentedAsFractionShouldHaveDenominatorEqualToOne(int integer) {
        final Fraction integerAsFraction = Fraction.forInteger(integer);
        assertThat(integerAsFraction).isEqualTo(fraction(integer, 1));
    }

    @ParameterizedTest(name = "should use improper notation when representing fraction as string: {0}")
    @CsvSource({"7/5", "11/4", "5/3"})
    void shouldUseImproperNotation(String fractionAsString) {
        Fraction fraction = Fraction.forString(fractionAsString);
        assertThat(fraction.toString()).isEqualTo(fractionAsString);
    }

    @Test
    @DisplayName("should use lowest terms when representing a Fraction as string")
    void shouldUseLowestTermsWhenExpressingAsString() {
        Fraction fraction = Fraction.forString("2/4");
        assertThat(fraction.toString()).isEqualTo("1/2");
    }

    @Test
    @DisplayName("fraction creation with negative numerator and denominator should result in a positive one")
    void fractionCreationWithNegativeNumeratorAndDenominatorShouldResultInAPositiveOne() {
        Fraction fraction = Fraction.forString("-1/-2");
        assertThat(fraction).isEqualTo(fraction(1, 2));
    }

    @Test
    @DisplayName("should accept zero as valid fraction")
    void shouldAcceptZeroAsValidFraction() {
        assertThatNoException().isThrownBy(() -> Fraction.forInteger(0));
    }

    @Test
    @DisplayName("creation of negative fractions from either numerator or denominator")
    void creationOfNegativeFractionsFromEitherNumeratorOrDenominator() {
        assertThat(fraction(-1, 3)).isEqualTo(fraction(1, -3));
    }

    @Test
    @DisplayName("negatingFractionsShouldBeAllowed")
    void negatingFractionsShouldBeAllowed() {
        assertThat(fraction(2, 5).negated()).isEqualTo(fraction(-2, 5));
        assertThat(fraction(-3, 4).negated()).isEqualTo(fraction(3, 4));
    }

    @Test
    @DisplayName("simplified fraction should be equal to the same non simplified fraction")
    void simplifiedFractionShouldBeEqualToTheSameNonSimplifiedFraction() {
        assertThat(fraction(1, 2)).isEqualTo(fraction(2, 4));
    }

    @Test
    @DisplayName("Unsimplifyable fraction should yield same fraction when tried to simplify")
    void unsimplifyableFractionShouldYieldSameFractionWhenTriedToSimplify() {
        final Fraction fraction = fraction(1, 3);
        assertThat(fraction.reduce()).isSameAs(fraction);
    }
}
