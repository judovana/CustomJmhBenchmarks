/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

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
