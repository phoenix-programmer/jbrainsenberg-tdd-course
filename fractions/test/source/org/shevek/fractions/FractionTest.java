package org.shevek.fractions;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.assertj.core.api.Assertions.*;

class FractionTest {

    @ParameterizedTest
    @CsvSource({"0", "1", "2", "-3"})
    @DisplayName("should have denominator equals to One for integers")
    void integersWhenRepresentedAsFractionShouldHaveDenominatorEqualToOne(int integer) {
        final Fraction integerAsFraction = Fraction.forInteger(integer);
        assertThat(integerAsFraction.toString()).isEqualTo(String.format("%d/1", integer));
    }

    @ParameterizedTest
    @CsvSource({"2, 3, 5", "4, 3, 7", "3, 0, 3", "0, 0, 0"})
    @DisplayName("should return same result for integer fraction addition than when adding two integers")
    void integerFractionRepresentationAdditionShouldYieldSameResultAsIntegerAddition(int a, int b, int c) {
        final Fraction aAsFraction = Fraction.forInteger(a);
        final Fraction bAsFraction = Fraction.forInteger(b);

        assertThat(aAsFraction.add(bAsFraction).toString()).isEqualTo(String.format("%s/1", c));
    }

    @ParameterizedTest
    @CsvSource({"1/2", "2/3", "5/3"})
    @DisplayName("string factory method should produce a printable fracton equals to its input")
    void stringFactoryMethodTest(String fractionAsString) {
        Fraction fraction = Fraction.forString(fractionAsString);

        assertThat(fraction.toString()).isEqualTo(fractionAsString);
    }

    @ParameterizedTest
    @CsvSource({"1, 1/1", "2, 2/1"})
    @DisplayName("string factory method should deal with integers")
    void stringFactoryMethodShouldDealWithIntegers(String integerAsString, String expectedResult) {
        assertThat(Fraction.forString(integerAsString).toString()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"A", "1/2/3", "1/A"})
    @DisplayName("string factory method should not accept invalid input")
    void stringFactoryMethodShouldNotAcceptInvalidInput(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> Fraction.forString(input));
    }

    @Test
    @DisplayName("can't have 0 as denominator")
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
    @DisplayName("shouldUseLowestTerms")
    void shouldUseLowestTermsWhenExpressingAsString() {
        Fraction fraction = Fraction.forString("2/4");
        assertThat(fraction.toString()).isEqualTo("1/2");
    }

    @Test
    @DisplayName("shouldUseLowestTermsWhenAdding")
    void shouldUseLowestTermsWhenAdding() {
        Fraction fraction = Fraction.forString("1/3");
        assertThat(fraction.add(Fraction.forString("8/3")).toString()).isEqualTo("3/1");
    }
}
