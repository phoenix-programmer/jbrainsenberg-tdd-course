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

    @ParameterizedTest
    @CsvSource({"2, 3, 5", "4, 3, 7", "3, 0, 3"})
    void integerFractionRepresentationAdditionShouldYieldSameResultAsIntegerAddition(int a, int b, int c) {
        final Fraction aAsFraction = Fraction.forInteger(a);
        final Fraction bAsFraction = Fraction.forInteger(b);

        assertThat(aAsFraction.add(bAsFraction).toString()).isEqualTo(String.format("%s/1", c));
    }

    @ParameterizedTest
    @CsvSource({"1/2", "2/3", "5/3"})
    void stringFactoryMethodTest(String fractionAsString) {
        Fraction fraction = Fraction.forString(fractionAsString);

        assertThat(fraction.toString()).isEqualTo(fractionAsString);
    }

}
