package jy.study.vbcode;

import java.util.List;

public class VariableByteCode {

    public static byte[] encodeNumber(int n) {
        int size = n / 128 + 1;
        byte[] bytes = new byte[size];

        int i = size - 1;
        do {
            bytes[i] = (byte) (n % 128);
            n = n / 128;
            i--;
        } while (n >= 128);

        bytes[size - 1] += 128;
        return bytes;
    }

    public static byte[] encode(List<Integer> numbers) {
        return null;
    }

    public static List<Integer> decode(byte[] byteStream) {
        return null;
    }
}
