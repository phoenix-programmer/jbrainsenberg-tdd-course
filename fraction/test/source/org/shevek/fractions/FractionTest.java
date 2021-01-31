package org.shevek.fractions;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.assertj.core.api.Assertions.*;

class FractionTest {

    @ParameterizedTest
    @CsvSource({"0", "1", "2", "-3"})
    void integersWhenRepresentedAsFractionShouldHaveDenominatorEqualToOne(int integer) {
        final Fraction integerAsFraction = Fraction.forInteger(integer);
        assertThat(integerAsFraction.toString()).isEqualTo(String.format("%d/1", integer));
    }

}
