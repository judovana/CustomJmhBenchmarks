package org.judovana.impls;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class DataOutputStreamWorker {

    public enum BasicType {CHAR, SHORT, INT, STRING}


    private final int SIZE;
    private final ByteArrayOutputStream byteArrayOutputStream;
    private final File f;
    private final String outputString;
    private final FileOutputStream fileOutputStream;
    private final DataOutput bufferedFileStream, rawFileStream, byteArrayStream;
    private final BasicType basicType;

    public DataOutputStreamWorker(int size, BasicType type) {
        try {
            f = File.createTempFile("dos", "bench");
            this.SIZE = size;
            byteArrayOutputStream = new ByteArrayOutputStream(SIZE);
            fileOutputStream = new FileOutputStream(f);
            byteArrayStream = new DataOutputStream(byteArrayOutputStream);
            rawFileStream = new DataOutputStream(fileOutputStream);
            bufferedFileStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
            basicType = type;
            outputString = new String(new byte[SIZE]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void writeChars(DataOutput dataOutput) {
        try {
            for (int i = 0; i < SIZE; i += 2) {
                dataOutput.writeChar(i);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void writeShorts(DataOutput dataOutput) {
        try {
            for (int i = 0; i < SIZE; i += 2) {
                dataOutput.writeShort(i);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void writeInts(DataOutput dataOutput) {
        try {
            for (int i = 0; i < SIZE; i += 4) {
                dataOutput.writeInt(i);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void writeString(DataOutput dataOutput) {
        try {
            dataOutput.writeChars(outputString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void write(DataOutput dataOutput) {
        switch (basicType) {
            case CHAR:
                writeChars(dataOutput);
                break;
            case SHORT:
                writeShorts(dataOutput);
                break;
            case INT:
                writeInts(dataOutput);
                break;
            case STRING:
                writeString(dataOutput);
                break;
        }
    }

    public void dataOutputStreamOverByteArray() throws IOException {
        byteArrayOutputStream.reset();
        write(byteArrayStream);
        byteArrayOutputStream.flush();
    }

    public void dataOutputStreamOverRawFileStream() throws IOException {
        fileOutputStream.getChannel().position(0);
        write(rawFileStream);
        fileOutputStream.flush();
    }

    public void dataOutputStreamOverBufferedFileStream() throws IOException {
        fileOutputStream.getChannel().position(0);
        write(bufferedFileStream);
        fileOutputStream.flush();
    }
}