package org.judovana.benchs;

import org.judovana.impls.TestbyteArrayCopy;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(value= Scope.Benchmark)
public class byteArrayCopy {

    TestbyteArrayCopy.VariantRunner byteArrayCopySelfRunner;
    TestbyteArrayCopy.VariantRunner byteArrayCopyNewRunner;
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
