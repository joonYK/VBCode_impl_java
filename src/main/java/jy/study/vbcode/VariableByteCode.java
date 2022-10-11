package jy.study.vbcode;

import java.nio.ByteBuffer;
import java.util.List;

public class VariableByteCode {

    public static byte[] encodeNumber(int n) {
        int size = n / 128 + 1;
        byte[] bytes = new byte[size];

        int i = size - 1;
        while (true) {
            bytes[i] = (byte) (n % 128);
            if (n < 128)
                break;
            n = n / 128;
            i--;
        }

        bytes[size - 1] += 128;
        return bytes;
    }

    public static byte[] encode(List<Integer> numbers) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES * numbers.size());
        for (Integer number : numbers) {
            byte[] encodedBytes = encodeNumber(number);
            byteBuffer.put(encodedBytes);
        }

        byte[] byteStream = new byte[byteBuffer.position()];
        byteBuffer.flip();
        byteBuffer.get(byteStream);

        return byteStream;
    }

    public static List<Integer> decode(byte[] byteStream) {
        return null;
    }
}
