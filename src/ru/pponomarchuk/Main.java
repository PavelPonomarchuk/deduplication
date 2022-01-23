package ru.pponomarchuk;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    /*
    I wanted just to implement deduplication algorithm,
    so I've done it completely in Main class
     */

    public static void main(String[] args) {
	    //fillTestFile(100000000);//use to create a big file

        Path path = Paths.get("/home/pavel/IdeaProjects/deduplication/src/ru/pponomarchuk/source.txt");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(str -> {
                if (!contains(str))
                    addLineToResult(str);
            });
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static boolean contains(String str){
        boolean fileContainsLine = false;

        try {
            File file = new File("/home/pavel/IdeaProjects/deduplication/src/ru/pponomarchuk/result.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String currentStr = reader.readLine();

            while (currentStr != null) {
                if (currentStr.equals(str)) {
                    fileContainsLine = true;
                    break;
                }
                currentStr = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return fileContainsLine;
    }

    private static void addLineToResult(String str){
        String path = "/home/pavel/IdeaProjects/deduplication/src/ru/pponomarchuk/result.txt";
        try {
            FileWriter writer = new FileWriter(path, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(str + "\n");
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void fillTestFile(int numberOfStrings) {
        String path = "/home/pavel/IdeaProjects/deduplication/src/ru/pponomarchuk/source.txt";
        String baseStr = "some quite long string *******************************************";

        try {
            FileWriter writer = new FileWriter(path, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);

            for (int i = 0; i < numberOfStrings; i++) {
                String currentStr = baseStr + "\n";
                bufferWriter.write(currentStr);
            }
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
