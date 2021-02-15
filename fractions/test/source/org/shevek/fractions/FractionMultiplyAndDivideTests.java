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
}
