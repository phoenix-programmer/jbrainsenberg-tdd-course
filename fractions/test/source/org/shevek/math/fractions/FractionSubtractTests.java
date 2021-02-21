package org.shevek.math.fractions;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shevek.math.fractions.Fraction.*;

public class FractionSubtractTests {

    @Test
    @DisplayName("Zero substract zero should be zero")
    void zeroSubstractZeroShouldBeZero() {
        assertThat(zero().subtract(zero())).isSameAs(zero());
    }

    @Test
    @DisplayName("should allow subtract integer to a fraction")
    void shouldAllowSubtractIntegerToFraction() {
        assertThat(fraction(2, 5).subtract(1)).isEqualTo(fraction(-3, 5));
    }

    @Test
    @DisplayName("subtractShouldReturnZeroWhenAppropiate")
    void subtractShouldReturnZeroWhenAppropiate() {
        final Fraction fraction = fraction(2, 5);
        assertThat(fraction.subtract(fraction)).isSameAs(zero());

    }
}
