package com.osypenko;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        File file = new File("logs/");
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            assert listFiles != null;
            for (File listFile : listFiles) {
                System.err.println(listFile);
            }
        }

        try (FileReader fileLog = new FileReader("logs/log-file.log")) {
            while (fileLog.ready()) {
                char read = (char) fileLog.read();
                System.out.print(read);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
