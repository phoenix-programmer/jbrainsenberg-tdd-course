package org.shevek.fractions;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.assertj.core.api.Assertions.*;

class FractionTest {

    @ParameterizedTest(name = "should have denominator equals to one for integers - {1}")
    @CsvSource({"0", "1", "2", "-3"})
    void integersWhenRepresentedAsFractionShouldHaveDenominatorEqualToOne(int integer) {
        final Fraction integerAsFraction = Fraction.forInteger(integer);
        assertThat(integerAsFraction.toString()).isEqualTo(String.format("%d/1", integer));
    }

    @ParameterizedTest(name = "should return same result for integer fraction addition than when adding two integers")
    @CsvSource({"2, 3, 5", "4, 3, 7", "3, 0, 3", "0, 0, 0"})
    void integerFractionRepresentationAdditionShouldYieldSameResultAsIntegerAddition(int a, int b, int c) {
        final Fraction aAsFraction = Fraction.forInteger(a);
        final Fraction bAsFraction = Fraction.forInteger(b);

        assertThat(aAsFraction.add(bAsFraction).toString()).isEqualTo(String.format("%s/1", c));
    }

    @ParameterizedTest(name = "should use improper notation when representing fraction as string: {0}")
    @CsvSource({"7/5", "11/4", "5/3"})
    void shouldUseImproperNotation(String fractionAsString) {
        Fraction fraction = Fraction.forString(fractionAsString);

        assertThat(fraction.toString()).isEqualTo(fractionAsString);
    }

    @ParameterizedTest(name = "string factory method should deal with integers")
    @CsvSource({"1, 1/1", "2, 2/1"})
    void stringFactoryMethodShouldDealWithIntegers(String integerAsString, String expectedResult) {
        assertThat(Fraction.forString(integerAsString).toString()).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "string factory method should not accept invalid input")
    @CsvSource({"A", "1/2/3", "1/A"})
    void stringFactoryMethodShouldNotAcceptInvalidInput(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> Fraction.forString(input));
    }

    @Test
    @DisplayName("Fractions can't have 0 as denominator")
    void cantHaveZeroAsDenominator() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Fraction(5, 0)).withMessage("0 isn't a valid denominator");
        assertThatIllegalArgumentException().isThrownBy(() -> Fraction.forString("3/0")).withMessage("0 isn't a valid denominator");
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
        Fraction fraction = new Fraction(1, 5);
        assertThat(fraction.add(new Fraction(1, 5)).toString()).isEqualTo("2/5");
    }

    @Test
    @DisplayName("should use lowest terms when representing a Fraction as string")
    void shouldUseLowestTermsWhenExpressingAsString() {
        Fraction fraction = Fraction.forString("2/4");
        assertThat(fraction.toString()).isEqualTo("1/2");
    }

}
