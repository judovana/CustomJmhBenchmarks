package org.judovana;


import org.judovana.benchs.ByteArrayCopy;
import org.judovana.benchs.ScannerRead;
import org.judovana.benchs.byteArrayCopy;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class CJMH {



    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(byteArrayCopy.class.getSimpleName())
                .include(ByteArrayCopy.class.getSimpleName())
                .include(ScannerRead.class.getSimpleName())
                .forks(1)
                .warmupForks(1)
                .warmupIterations(1)
                .measurementIterations(1)
                .build();

        new Runner(opt).run();
    }




}
