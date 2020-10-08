package org.chaoscoders.extensions.numbersystemconverter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberSysConverterTest {

    @Test
    void hexLiteralToDecValue() {
        //given
        //when
        // input '1'
        char result1 = NumberSysConverter.hexLiteralToDecValue('1');
        //then
        assertEquals(1, result1);
    }
}