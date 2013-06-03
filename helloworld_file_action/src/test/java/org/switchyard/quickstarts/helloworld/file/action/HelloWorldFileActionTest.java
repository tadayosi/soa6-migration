package org.switchyard.quickstarts.helloworld.file.action;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.camel.model.RouteScanner;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(
        config = SwitchYardTestCaseConfig.SWITCHYARD_XML,
        mixins = CDIMixIn.class,
        scanners = RouteScanner.class)
public class HelloWorldFileActionTest {
    
    private static final String INPUT_DIR = "target/input";
    private static final String OUTPUT_DIR = "target/output";
    private static final String FILE_NAME = "MyInput.dat";
    private static final String FILE_CONTENTS = "Hello World In A File";
    
    @Before
    public void setUp() {
        new File(INPUT_DIR, FILE_NAME).delete();
        new File(OUTPUT_DIR, FILE_NAME).delete();
    }
    
    @Test
    public void runTest() throws Exception {
        // TODO add test store
        File inputFile = new File(INPUT_DIR, FILE_NAME);
        FileUtils.writeStringToFile(inputFile, FILE_CONTENTS);
        Thread.sleep(500);
        
        File outputFile = new File(OUTPUT_DIR, FILE_NAME);
        assertThat(inputFile.exists(), is(false));
        assertThat(outputFile.exists(), is(true));
        assertThat(FileUtils.readFileToString(outputFile), is(equalTo(FILE_CONTENTS)));
    }
}
