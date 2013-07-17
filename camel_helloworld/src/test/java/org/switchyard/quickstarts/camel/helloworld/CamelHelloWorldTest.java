package org.switchyard.quickstarts.camel.helloworld;

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
public class CamelHelloWorldTest {
    
    private static final String INPUT_DIR = "target/input";
    private static final String FILE_NAME = "MyInput.dat";
    private static final String FILE_CONTENTS = "Hello World In A File";
    
    @Before
    public void setUp() {
        new File(INPUT_DIR, FILE_NAME).delete();
    }
    
    @Test
    public void runTest() throws Exception {
        File inputFile = new File(INPUT_DIR, FILE_NAME);
        FileUtils.writeStringToFile(inputFile, FILE_CONTENTS);
        Thread.sleep(1000);
        assertThat(inputFile.exists(), is(false));
    }
}