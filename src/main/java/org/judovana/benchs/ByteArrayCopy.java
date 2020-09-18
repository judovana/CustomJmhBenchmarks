package org.judovana.benchs;

import org.judovana.impls.TestByteArrayCopy;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(value= Scope.Benchmark)
public class ByteArrayCopy {

    TestByteArrayCopy.VariantRunner ByteArrayCopySelfRunner;
    TestByteArrayCopy.VariantRunner ByteArrayCopyNewRunner;
    Byte[] selfByteData;
    Byte[] newByteData;
    Byte[] selfByteRes;
    Byte[] newByteRes;


    @Setup
    public void initData() {
        ByteArrayCopySelfRunner = new TestByteArrayCopy.SelfCopy();
        ByteArrayCopyNewRunner = new TestByteArrayCopy.NewCopy();
        selfByteData = TestByteArrayCopy.init();
        newByteData = TestByteArrayCopy.init();
    }

    @TearDown
    public void verifyData() {
        TestByteArrayCopy.verify(selfByteData);
        TestByteArrayCopy.verify(newByteData);
        ByteArrayCopySelfRunner = null;
        ByteArrayCopyNewRunner = null;
        selfByteData = null;
        newByteData = null;
        selfByteRes = null;
        newByteRes = null;
    }


    @Benchmark
    public void ByteArrayCopySelf() {
        selfByteRes = ByteArrayCopySelfRunner.run(selfByteData);
    }

    @Benchmark
    public void ByteArrayCopyNew() {
        newByteRes = ByteArrayCopyNewRunner.run(newByteData);
    }


}
