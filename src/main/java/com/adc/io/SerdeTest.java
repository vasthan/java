package com.adc.io;

import com.adc.model.Apple;

import java.io.*;

public class SerdeTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        var out = new ObjectOutputStream(new FileOutputStream("apple.dat"));
        out.writeInt(1);
        out.writeObject(new Apple("red", 10));
        out.writeObject(new Apple("green", 5));

        var in = new ObjectInputStream(new FileInputStream("apple.dat"));
        System.out.println(in.readInt());
        System.out.println(in.readObject());
        System.out.println(in.readObject());
    }
}
