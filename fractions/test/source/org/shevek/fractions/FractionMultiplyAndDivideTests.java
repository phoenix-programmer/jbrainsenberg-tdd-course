package org.shevek.fractions;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.shevek.fractions.Fraction.zero;

public class FractionMultiplyAndDivideTests {

    @Test
    @DisplayName("zero multiplied by any fraction should be zero")
    void zeroMultipliedByAnyFractionShouldBeZero() {
        assertThat(zero().multiply(Fraction.of(2, 5))).isEqualTo(zero());
    }

    @Test
    @DisplayName("any fraction multiplied by 0 should yield 0")
    void anyFractionMultipliedByZeroShouldBeZero() {
        assertThat(Fraction.of(2, 3).multiply(0)).isEqualTo(zero());
    }

    @Test
    @DisplayName("fractionMultiplicationIsStraightForward")
    void fractionMultiplicationIsStraightForward() {
        assertThat(Fraction.of(2,5).multiply(Fraction.of(1, 3))).isEqualTo(Fraction.of(2, 15));
    }

    @Test
    @DisplayName("Division by zero is not allowed")
    void divisionByZeroIsNotAllowed() {
        assertThatIllegalArgumentException().isThrownBy(() -> Fraction.of(2, 3).divide(zero()));
    }

    @Test
    @DisplayName("zeroDividedByAnyFractionShouldYieldZero")
    void zeroDividedByAnyFractionShouldYieldZero() {
        assertThat(zero().divide(Fraction.of(5, 8))).isSameAs(zero());
    }

    @Test
    @DisplayName("division by one should yield same fraction")
    void divisionByOneShouldYieldSameFraction() {
        final Fraction fraction = Fraction.of(2, 5);
        assertThat(fraction.divide(1)).isSameAs(fraction);
    }

    @Test
    @DisplayName("division by integer should yield appropiate result")
    void divisionByIntegerShouldYieldAppropiateResult() {
        assertThat(Fraction.of(2, 3).divide(2)).isEqualTo(Fraction.of(2, 6));
    }
}
