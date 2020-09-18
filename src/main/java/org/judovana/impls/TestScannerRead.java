package org.judovana.impls;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * https://bugzilla.redhat.com/show_bug.cgi?id=1862929
 * noticable regression in 8u262 and between 8 and 11 or up
 */
public class TestScannerRead {

    public static void main(String[] args) throws IOException {
        System.out.println("java.version=" + System.getProperty("java.version"));
        List<byte[]> sources = TestScannerReadConstants.getOrigData();
        //List<byte[]> sources = TestScannerReadConstants.getShortData();
        //List<byte[]> sources = TestScannerReadConstants.getMediumData();
        //List<byte[]> sources = TestScannerReadConstants.getLongData();
        long totalElapsed = 0L;
        double d = 0.0;
        for (byte[] osource : sources) {
            ByteArrayInputStream source = getByteArrayInputStream(osource);
            long start = System.currentTimeMillis();
            d += scanByteArray(source);
            long end = System.currentTimeMillis();
            long elapsed = (end - start);
            totalElapsed += elapsed;
        }
        System.out.println("reading with Scanner took: "+totalElapsed +"ms");
        System.out.println("useless line to ensure everything is read "+d);
}

    private static ByteArrayInputStream getByteArrayInputStream(byte[] osource) {
        return new ByteArrayInputStream(osource);
    }

    public static ByteArrayInputStream[] getByteArrayInputStreams(List<byte[]> sources) {
        ByteArrayInputStream[] r = new ByteArrayInputStream[sources.size()];
        for(int i = 0; i < sources.size(); i++){
            r[i] = getByteArrayInputStream(sources.get(i));
        }
        return r;
    }

    private static double scanByteArray(ByteArrayInputStream source) {
        double d = 0.0;
        try (Scanner scanner = new Scanner(source, StandardCharsets.UTF_8.name())) {
            while (scanner.hasNext()) {
                d += scanner.nextDouble();
            }
        }
        return d;
    }

    public static void scanByteArrays(ByteArrayInputStream... sources) {
        for(ByteArrayInputStream source: sources){
            scanByteArray(source);
        }
    }
}