package org.judovana.benchs;

import org.judovana.impls.DataOutputStreamWorker;
import org.openjdk.jmh.annotations.*;

import java.io.*;
import java.util.concurrent.TimeUnit;

//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@Measurement(iterations = 6, time = 1)
@Warmup(iterations = 2, time = 2)
@State(Scope.Benchmark)
public class DataOutputStreamOps {


    private DataOutputStreamWorker shortChar;
    private DataOutputStreamWorker shortInt;
    private DataOutputStreamWorker shortShort;
    private DataOutputStreamWorker shortString;

    private DataOutputStreamWorker longChar;
    private DataOutputStreamWorker longInt;
    private DataOutputStreamWorker longShort;
    private DataOutputStreamWorker longString;

    @Setup
    public void setup() {
        int shortOne = 1024;
        int longOne = 1048576;
        shortChar = new DataOutputStreamWorker(shortOne, DataOutputStreamWorker.BasicType.CHAR);
        shortInt = new DataOutputStreamWorker(shortOne, DataOutputStreamWorker.BasicType.INT);
        shortShort = new DataOutputStreamWorker(shortOne, DataOutputStreamWorker.BasicType.SHORT);
        shortString = new DataOutputStreamWorker(shortOne, DataOutputStreamWorker.BasicType.STRING);
        longChar = new DataOutputStreamWorker(longOne, DataOutputStreamWorker.BasicType.CHAR);
        longInt = new DataOutputStreamWorker(longOne, DataOutputStreamWorker.BasicType.INT);
        longShort = new DataOutputStreamWorker(longOne, DataOutputStreamWorker.BasicType.SHORT);
        longString = new DataOutputStreamWorker(longOne, DataOutputStreamWorker.BasicType.STRING);
    }

    @Benchmark
    public void shortCharDataOutputStreamOverByteArray() throws IOException {
        shortChar.dataOutputStreamOverByteArray();
    }

    @Benchmark
    public void shortCharDataOutputStreamOverRawFileStream() throws IOException {
        shortChar.dataOutputStreamOverRawFileStream();
    }

    @Benchmark
    public void shortCharDataOutputStreamOverBufferedFileStream() throws IOException {
        shortChar.dataOutputStreamOverBufferedFileStream();
    }

    @Benchmark
    public void shortIntDataOutputStreamOverByteArray() throws IOException {
        shortInt.dataOutputStreamOverByteArray();
    }

    @Benchmark
    public void shortIntDataOutputStreamOverRawFileStream() throws IOException {
        shortInt.dataOutputStreamOverRawFileStream();
    }

    @Benchmark
    public void shortIntDataOutputStreamOverBufferedFileStream() throws IOException {
        shortInt.dataOutputStreamOverBufferedFileStream();
    }

    @Benchmark
    public void shortShortDataOutputStreamOverByteArray() throws IOException {
        shortShort.dataOutputStreamOverByteArray();
    }

    @Benchmark
    public void shortShortDataOutputStreamOverRawFileStream() throws IOException {
        shortShort.dataOutputStreamOverRawFileStream();
    }

    @Benchmark
    public void shortShortDataOutputStreamOverBufferedFileStream() throws IOException {
        shortShort.dataOutputStreamOverBufferedFileStream();
    }

    @Benchmark
    public void shortStringDataOutputStreamOverByteArray() throws IOException {
        shortString.dataOutputStreamOverByteArray();
    }

    @Benchmark
    public void shortStringDataOutputStreamOverRawFileStream() throws IOException {
        shortString.dataOutputStreamOverRawFileStream();
    }

    @Benchmark
    public void shortStringDataOutputStreamOverBufferedFileStream() throws IOException {
        shortString.dataOutputStreamOverBufferedFileStream();
    }


    @Benchmark
    public void longCharDataOutputStreamOverByteArray() throws IOException {
        longChar.dataOutputStreamOverByteArray();
    }

    @Benchmark
    public void longCharDataOutputStreamOverRawFileStream() throws IOException {
        longChar.dataOutputStreamOverRawFileStream();
    }

    @Benchmark
    public void longCharDataOutputStreamOverBufferedFileStream() throws IOException {
        longChar.dataOutputStreamOverBufferedFileStream();
    }

    @Benchmark
    public void longIntDataOutputStreamOverByteArray() throws IOException {
        longInt.dataOutputStreamOverByteArray();
    }

    @Benchmark
    public void longIntDataOutputStreamOverRawFileStream() throws IOException {
        longInt.dataOutputStreamOverRawFileStream();
    }

    @Benchmark
    public void longIntDataOutputStreamOverBufferedFileStream() throws IOException {
        longInt.dataOutputStreamOverBufferedFileStream();
    }

    @Benchmark
    public void longShortDataOutputStreamOverByteArray() throws IOException {
        longShort.dataOutputStreamOverByteArray();
    }

    @Benchmark
    public void longShortDataOutputStreamOverRawFileStream() throws IOException {
        longShort.dataOutputStreamOverRawFileStream();
    }

    @Benchmark
    public void longShortDataOutputStreamOverBufferedFileStream() throws IOException {
        longShort.dataOutputStreamOverBufferedFileStream();
    }

    @Benchmark
    public void longStringDataOutputStreamOverByteArray() throws IOException {
        longString.dataOutputStreamOverByteArray();
    }

    @Benchmark
    public void longStringDataOutputStreamOverRawFileStream() throws IOException {
        longString.dataOutputStreamOverRawFileStream();
    }

    @Benchmark
    public void longStringDataOutputStreamOverBufferedFileStream() throws IOException {
        longString.dataOutputStreamOverBufferedFileStream();
    }
}
