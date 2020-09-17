package org.judovana.impls;

import java.io.IOException;
import java.util.Arrays;


public class TestbyteArrayCopy {

    private static final int iterations = 1000;
    private static final int n = 2_000_000;

    public static void main(String[] args) throws IOException {
        System.out.println("java.version=" + System.getProperty("java.version"));

        long s1 = System.currentTimeMillis();
        byte[] data = init();
        VariantRunner vr = new SelfCopy();
        long s2 = System.currentTimeMillis();;
        byte[] result = run(vr, data);
        long e1 = System.currentTimeMillis();
        verify(result);
        long e2 = System.currentTimeMillis();
        System.out.println(vr.getTitle() + " repeated " + iterations + " times took: " + (e1-s2) + "ms");
        System.out.println("entire snippet took: " + (e2 - s1) + "ms");


        s1 = System.currentTimeMillis();
        data = init();
        vr = new NewCopy();
        s2 = System.currentTimeMillis();;
        result = run(vr, data);
        e1 = System.currentTimeMillis();
        verify(result);
        e2 = System.currentTimeMillis();
        System.out.println(vr.getTitle() + " repeated " + iterations + " times took: " + (e1-s2) + "ms");
        System.out.println("entire snippet took: " + (e2 - s1) + "ms");
    }

    public static void verify(byte[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != ((byte) (i % Byte.MAX_VALUE))) {
                throw new IllegalStateException();
            }
        }
    }

    public static byte[] init() {
        byte[] array = new byte[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = (byte) (i % Byte.MAX_VALUE);
        }
        return array;
    }

    public interface VariantRunner {
        public byte[] run(byte[] array);
        public String getTitle();
    }

    public static class SelfCopy implements  VariantRunner {

        @Override
        public byte[] run(byte[] array) {
            return  Arrays.copyOf(array, array.length);
        }

        @Override
        public String getTitle() {
            return "Arrays.copyOf(byte[], int)";
        }
    }

    public static class NewCopy implements  VariantRunner {

        @Override
        public byte[] run(byte[] array) {
            byte[] array2 = new byte[array.length];
            System.arraycopy(array, 0, array2, 0, array.length);
            return array2;
        }

        @Override
        public String getTitle() {
            return "Arrays.copyOf(byte[], int, byte[], int, int)";
        }
    }


    private static byte[] run(VariantRunner vr, byte[] array) {
        byte[] array2 = null;
        for (int iteration = 0; iteration < iterations; iteration++) {
            array2 = vr.run(array);
        }
        return array2;

    }

}