package com.example.switchyard.soa6_migration_helloworld_file_action;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.camel.model.RouteScanner;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.hornetq.HornetQMixIn;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = {
        CDIMixIn.class, HornetQMixIn.class }, scanners = RouteScanner.class)
public class HelloWorldFileActionTest {

    private static final String TARGET_DIR = "target/";
    private static final String INPUT_DIR = "input/";
    private static final String OUTPUT_DIR = "output/";
    private static final String INPUT_FILE_NAME = "test.txt";
    private static final String OUTPUT_FILE_NAME = "results.log";
    private static final String fileContents = "Hello World File Action";

    @Before
    public void setUp() {
        new File(TARGET_DIR + INPUT_DIR + INPUT_FILE_NAME).delete();
        new File(TARGET_DIR + OUTPUT_DIR + OUTPUT_FILE_NAME).delete();
    }

    @Test
    public void sendFile() throws Exception {
        File directory = new File(TARGET_DIR, INPUT_DIR);
        File file1 = new File(directory, INPUT_FILE_NAME);
        directory.mkdir();
        file1.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(file1));
        out.write(fileContents.toCharArray());
        out.close();
        Thread.sleep(5000);

        assertTrue(new File(TARGET_DIR+ OUTPUT_DIR + OUTPUT_FILE_NAME).exists());
        assertEquals(fileContents, FileUtils.readFileToString(new File(TARGET_DIR+ OUTPUT_DIR + OUTPUT_FILE_NAME)));

    }
}
