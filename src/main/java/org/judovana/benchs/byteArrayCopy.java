package org.judovana.benchs;

import org.judovana.impls.TestbyteArrayCopy;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

@State(value= Scope.Benchmark)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(3)
public class byteArrayCopy {

    TestbyteArrayCopy.SelfCopy byteArrayCopySelfRunner;
    TestbyteArrayCopy.NewCopy byteArrayCopyNewRunner;
    byte[] selfbyteData;
    byte[] newbyteData;
    byte[] selfbyteRes;
    byte[] newbyteRes;

    @Setup
    public void initData() {
        byteArrayCopySelfRunner = new TestbyteArrayCopy.SelfCopy();
        byteArrayCopyNewRunner = new TestbyteArrayCopy.NewCopy();
        selfbyteData = TestbyteArrayCopy.init();
        newbyteData = TestbyteArrayCopy.init();
    }

    @TearDown
    public void verifyData() {
        TestbyteArrayCopy.verify(selfbyteData);
        TestbyteArrayCopy.verify(newbyteData);
        byteArrayCopySelfRunner = null;
        byteArrayCopyNewRunner = null;
        selfbyteData = null;
        newbyteData = null;
        selfbyteRes = null;
        newbyteRes = null;
    }

    @Benchmark
    public void byteArrayCopySelf() {
        selfbyteRes = byteArrayCopySelfRunner.run(selfbyteData);
    }

    @Benchmark
    public void byteArrayCopyNew() {
        newbyteRes = byteArrayCopyNewRunner.run(newbyteData);
    }

}
