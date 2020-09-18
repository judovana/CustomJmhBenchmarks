package org.judovana.impls;

import static org.judovana.impls.TestScannerReadConstants.FILE_SIZES;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestScannerReadConstants {


    public static final long L19829 = 19829L;
    public static final long L19851 = 19851L;
    public static final long L24217 = 24217L;
    public static final long L24384 = 24384L;
    public static final long L48157 = 48157L;
    public static final long L48161 = 48161L;
    public static final long L93665 = 193665L;

    public static final long L195060 = 195060L;
    public static final long L196608 = 196608L;
    public static final long L192443 = 192443L;
    public static final long L192515 = 192515L;
    public static final long L385441 = 385441L;
    public static final long L384906 = 384906L;

    public static final long L1499300 = 1499300L;
    public static final long L1499600 = 1499600L;
    public static final long L1537381 = 1537381L;
    public static final long L1540039 = 1540039L;

    public static long[] shortLenghts = new long[]{L19829, L19851, L24217, L24384, L48157, L48161, L93665};
    public static long[] mediumLenghts = new long[]{L195060, L196608, L192443, L192515, L385441, L384906};
    public static long[] longLenghts = new long[]{L1499300, L1499600, L1537381, L1540039};


    private static long testedLength = 167066533L/10L; //tentimes less then in original case to have results in some reasonable time

    public static List<byte[]> getShortData() throws IOException {
        return getData(shortLenghts);
    }

    public static List<byte[]> getMediumData() throws IOException {
        return getData(mediumLenghts);
    }

    public static List<byte[]> getLongData() throws IOException {
        return getData(longLenghts);
    }

    private static List<byte[]> getData(long... lengths) throws IOException {
        List<byte[]> result = new ArrayList<>();
        long counter = 0;
        while (counter< testedLength) {
            for (long l : lengths) {
                result.addAll(toData(l));
                counter += l;
            }
        }
        return result;
    }



    static List<byte[]> getOrigData() throws IOException {
        return toData(getOrigLenhgts());
    }

    private static List<byte[]> toData(long... sizes) throws IOException {
        List<byte[]> result = new ArrayList<>(sizes.length);
        for (int f = 0; f < sizes.length; ++f) {
            long byteCount = sizes[f];
            long n = byteCount / 8;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            for (int i = 0; i < n; ++i) {
                String s = String.valueOf(1.0);
                bos.write(s.getBytes(StandardCharsets.UTF_8));
                bos.write('\n');
            }
            result.add(bos.toByteArray());
        }
        return result;
    }

    //total of 167066533 bytes
    //leadig to 2.0714783E7 Doubles (or d 2.0883223E7??!?)
    private static long[] ORIG_LENHGTS = {L24384, L24217, L24384, L24217, L195060, L93665, L196608, L196608, L1499600,
            L1499300, L24384, L24217, L48161, L48157, L24384, L24217, L24384, L24217, L195060, L93665, L196608,
            L196608, L1499600, L1499300, L24384, L24217, L48161, L48157, L24384, L24217, L195060, L93665, L196608,
            L196608, L1499600, L1499300, L24384, L24217, L24384, L24217, L195060, L93665, L196608, L196608, L1499600,
            L1499300, L24384, L24217, L48161, L48157, L24384, L24217, L195060, L93665, L196608, L196608, L1499600,
            L1499300, L24384, L24217, L48161, L48157, L24384, L24217, L24384, L24217,
            L24384, L24217, L195060, L93665,
            L196608, L196608, L1499600, L1499300, L24384, L24217, L48161, L48157, L24384, L24217, L24384, L24217,
            L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217, L24384, L24217, L24384, L24217,
            L24384, L24217, L24384, L24217, L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217,
            L48161, L48157, L24384, L24217, L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217,
            L48161, L48157, L24384, L24217, L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217,
            L48161, L48157, L24384, L24217, L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217,
            L48161, L48157, L24384, L24217, L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217,
            L24384, L24217, L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217, L24384, L24217,
            L24384, L24217, L24384, L24217, L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217,
            L24384, L24217, L24384, L24217, L24384, L24217, L24384, L24217, L195060, L93665, L196608, L196608,
            L1499600, L1499300, L24384, L24217, L48161, L48157, L24384, L24217, L195060, L93665, L196608, L196608,
            L1499600, L1499300, L24384, L24217, L48161, L48157, L24384, L24217, L195060, L93665, L196608, L196608,
            L1499600, L1499300, L24384, L24217, L24384, L24217, L195060, L93665, L196608, L196608, L1499600, L1499300,
            L24384, L24217, L24384, L24217, L48161, L48157, L24384, L24217, L195060, L93665, L196608, L196608,
            L1499600, L1499300, L24384, L24217, L48161, L48157, L24384, L24217, L24384, L24217, L195060, L93665,
            L196608, L196608, L1499600, L1499300, L24384, L24217, L48161, L48157, L24384, L24217, L195060, L93665,
            L196608, L196608, L1499600, L1499300, L24384, L24217, L24384, L24217, L24384, L24217,
            L24384, L24217,
            L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217, L48161, L48157, L24384, L24217,
            L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217, L48161, L48157, L24384, L24217,
            L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217, L48161, L48157, L24384, L24217,
            L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217, L48161, L48157, L24384, L24217,
            L195060, L93665, L196608, L196608, L1499600, L1499300, L24384, L24217, L24384, L24217, L195060, L93665,
            L196608, L196608, L24384, L24217, L1499600, L1499300, L24384, L24217, L24384, L24217, L195060, L93665,
            L196608, L196608, L1499600, L1499300, L24384, L24217, L24384, L24217, L24384, L24217, L19829, L19851,
            L19829, L19851, L19829, L19851, L19829, L19851, L19829, L19851, L24384, L24217, L195060, L93665, L196608,
            L196608, L24384, L24217, L24384, L24217, L195060, L93665, L196608, L24384, L24217, L196608,
            L24384, L24217,
            L195060, L93665, L196608, L24384, L24217, L196608, L24384, L24217, L195060, L93665, L196608, L24384,
            L196608, L24217, L24384, L24217, L195060, L93665, L196608, L24384, L24217, L196608, L24384, L24217,
            L24384,
            L24217, L24384, L24217, L24384, L24217, L24384, L24217, L24384, L24217,
            L24384, L24217, L24384, L24217,
            L24384, L24217, L24384, L24217, L24384, L24217, L24384, L24217,
            L24384, L24217, L24384, L24217, L24384,
            L24217, L24384, L24217, L48161, L48157, L24384, L24217, L24384, L24217,
            L24384, L24217, L24384, L24217,
            L24384, L24217, L24384, L24217, L24384, L24217, L24384, L24217, L1537381, L1537381, L1537381, L196608,
            L196608, L196608, L196608, L1537381, L1537381, L1537381, L1540039, L1540039, L1540039, L196608, L196608,
            L196608, L1540039, L1540039, L196608, L1537381, L1537381, L1540039, L1537381, L1540039, L1540039, L1537381,
            L1537381, L1537381, L1540039, L196608, L196608, L1540039, L1540039, L1540039, L192443, L385441, L1540039,
            L384906, L192515, L192443, L385441, L384906, L192515, L192443, L196608, L196608, L385441, L384906, L192515,
            L192443, L385441, L384906, L192515, L192443, L385441, L384906, L192515, L192443, L385441, L384906, L192515,
            L192443, L196608, L196608, L196608, L196608, L196608, L196608, L196608, L196608, L196608, L196608,};
    static long[] FILE_SIZES = getOrigLenhgts();

    private static long[] getOrigLenhgts() {
        Arrays.sort(ORIG_LENHGTS);
        return ORIG_LENHGTS;
    }
}
