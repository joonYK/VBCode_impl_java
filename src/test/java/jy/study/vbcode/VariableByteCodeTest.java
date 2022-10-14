package jy.study.vbcode;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VariableByteCodeTest {

    @Test
    void encodeNumber() {
        //given
        int n = 5;

        //when
        byte[] bytes = VariableByteCode.encodeNumber(n);

        //then
        assertEquals(1, bytes.length);
        assertEquals("10000101", Integer.toBinaryString(bytes[0] & 0xff));
    }

    @Test
    void encode() {
        //given
        List<Integer> numbers = List.of(5, 130);

        //when
        byte[] bytes = VariableByteCode.encode(numbers);

        //then
        assertEquals(3, bytes.length);
        assertEquals("10000101", Integer.toBinaryString(bytes[0] & 0xff));
        assertEquals("1", Integer.toBinaryString(bytes[1] & 0xff));
        assertEquals("10000010", Integer.toBinaryString(bytes[2] & 0xff));
    }

    @Test
    void decode() {
        //given
        byte[] bytes = {(byte) Integer.parseInt("10000101", 2),
                        (byte) Integer.parseInt("00000001", 2),
                        (byte) Integer.parseInt("10000010", 2)};

        //when
        List<Integer> numbers = VariableByteCode.decode(bytes);

        //then
        assertEquals(2, numbers.size());
        assertEquals(5, numbers.get(0));
        assertEquals(130, numbers.get(1));
    }
}