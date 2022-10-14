package jy.study.vbcode;

import java.nio.ByteBuffer;
import java.util.ArrayList;
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
        List<Integer> numbers = new ArrayList<>();
        int n = 0;

        for (int i = 0; i < byteStream.length; i++) {
            int b = byteStream[i] & 0xff;
            if (b < 128) {
                n = 128 * n + b;
            } else {
                n = 128 * n + (b - 128);
                numbers.add(n);
                n = 0;
            }
        }

        return numbers;
    }
}
