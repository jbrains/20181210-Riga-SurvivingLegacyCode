package ca.jbrains.trivia.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class CheckGoldenMaster {

    private PrintStream productionStdout;

    @Before
    public void rememberStdout() throws Exception {
        productionStdout = System.out;
    }

    @After
    public void replaceStdout() throws Exception {
        System.setOut(productionStdout);
    }

    @Test
    public void checkGoldenMaster() throws Exception {
        final ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        new GenerateGoldenMaster().runGame(762, canvas);
        final String actual = canvas.toString(StandardCharsets.UTF_8.name());

        final Path goldenMasterOutputRoot = GenerateGoldenMaster.rootPath();
        final byte[] goldenMasterOutput = Files.readAllBytes(GenerateGoldenMaster.outputPathForGame(goldenMasterOutputRoot, 762));
        final String expected = new String(goldenMasterOutput, StandardCharsets.UTF_8);

        Assert.assertEquals(expected, actual);
    }

}
