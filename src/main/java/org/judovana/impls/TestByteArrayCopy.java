package org.judovana.impls;

import java.io.IOException;
import java.util.Arrays;

/**
 * https://bugzilla.redhat.com/show_bug.cgi?id=1862163
 * Noticable difference between jdk8  and jdk 11 (jdk11 is aprox 6x slower)
 */

public class TestByteArrayCopy {

    private static final int iterations = 1000;
    private static final int n = 2_000_000;

    public static void main(String[] args) throws IOException {
        System.out.println("java.version=" + System.getProperty("java.version"));

        long s1 = System.currentTimeMillis();
        Byte[] data = init();
        VariantRunner vr = new SelfCopy();
        long s2 = System.currentTimeMillis();;
        Byte[] result = run(vr, data);
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

    public static void verify(Byte[] array) {
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals(Byte.valueOf((byte) (i % Byte.MAX_VALUE)))) {
                throw new IllegalStateException();
            }
        }
    }

    public static Byte[] init() {
        Byte[] array = new Byte[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = Byte.valueOf((byte) (i % Byte.MAX_VALUE));
        }
        return array;
    }

    public interface VariantRunner {
        public Byte[] run(Byte[] array);
        public String getTitle();
    }

    public static class SelfCopy implements  VariantRunner {

        @Override
        public Byte[] run(Byte[] array) {
            return  Arrays.copyOf(array, array.length);
        }

        @Override
        public String getTitle() {
            return "Arrays.copyOf(Byte[], int)";
        }
    }

    public static class NewCopy implements  VariantRunner {

        @Override
        public Byte[] run(Byte[] array) {
            Byte[] array2 = new Byte[array.length];
            System.arraycopy(array, 0, array2, 0, array.length);
            return array2;
        }

        @Override
        public String getTitle() {
            return "Arrays.copyOf(Byte[], int, Byte[], int, int)";
        }
    }


    private static Byte[] run(VariantRunner vr, Byte[] array) {
        Byte[] array2 = null;
        for (int iteration = 0; iteration < iterations; iteration++) {
            array2 = vr.run(array);
        }
        return array2;

    }

}