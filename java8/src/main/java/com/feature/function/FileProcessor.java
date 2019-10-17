package com.feature.function;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface FileProcessor {

    String process(BufferedReader br) throws IOException;
}
