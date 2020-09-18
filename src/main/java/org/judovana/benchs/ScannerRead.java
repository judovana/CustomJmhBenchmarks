package org.judovana.benchs;

import org.judovana.impls.TestByteArrayCopy;
import org.judovana.impls.TestScannerRead;
import org.judovana.impls.TestScannerReadConstants;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@State(value= Scope.Benchmark)
public class ScannerRead {


    ByteArrayInputStream[] sourcesS;
    ByteArrayInputStream[] sourcesM;
    ByteArrayInputStream[] sourcesL;

    @Setup
    public void initData() throws IOException {
        sourcesS = TestScannerRead.getByteArrayInputStreams(TestScannerReadConstants.getShortData());
        sourcesM = TestScannerRead.getByteArrayInputStreams(TestScannerReadConstants.getMediumData());
        sourcesL = TestScannerRead.getByteArrayInputStreams(TestScannerReadConstants.getLongData());
    }

    @TearDown
    public void cleanData() {
        sourcesS = null;
        sourcesM = null;
        sourcesL = null;
    }


    @Benchmark
    public void smallInputs() {
        TestScannerRead.scanByteArrays(sourcesS);
    }

    @Benchmark
    public void mediumInputs() {
        TestScannerRead.scanByteArrays(sourcesM);
    }

    @Benchmark
    public void longInputs() {
        TestScannerRead.scanByteArrays(sourcesL);
    }


}
