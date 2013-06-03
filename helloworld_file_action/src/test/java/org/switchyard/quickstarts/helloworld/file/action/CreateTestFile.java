package org.switchyard.quickstarts.helloworld.file.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class CreateTestFile {
    
    public static void main(String args[]) throws Exception {
        String inputDirectory = args[0];
        String fileName = args[1];
        String fileContents = args[2];
        File x = new File(inputDirectory + "/" + fileName);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(x));
            out.write(fileContents.toCharArray());
            out.close();
        } catch (Exception e) {
            System.out.println("Error while writing the file: " + inputDirectory + "/" + fileName);
            System.out.println(e.getMessage());
        }
    }
    
}