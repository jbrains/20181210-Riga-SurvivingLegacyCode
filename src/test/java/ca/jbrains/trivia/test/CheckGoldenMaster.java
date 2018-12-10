package ca.jbrains.trivia.test;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckGoldenMaster {
    @Test
    public void checkGoldenMaster() throws Exception {
        final ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        new GenerateGoldenMaster().runGame(762, canvas);
        final String actual = canvas.toString(StandardCharsets.UTF_8.name());

        final Path goldenMasterOutputRoot = Paths.get("test", "data");
        final byte[] goldenMasterOutput = Files.readAllBytes(goldenMasterOutputRoot.resolve("game-762.txt"));
        final String expected = new String(goldenMasterOutput, StandardCharsets.UTF_8);

        Assert.assertEquals(expected, actual);
    }
}
