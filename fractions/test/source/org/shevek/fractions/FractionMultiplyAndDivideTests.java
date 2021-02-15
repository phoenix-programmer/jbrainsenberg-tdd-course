package org.shevek.fractions;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
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
}
