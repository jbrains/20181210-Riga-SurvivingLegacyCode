package ca.jbrains.trivia.test;

import com.adaptionsoft.games.uglytrivia.Game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class GenerateGoldenMaster {

    public static void main(String[] args) throws Exception {
        final Path goldenMasterOutputRoot = rootPath();
        Files.createDirectories(goldenMasterOutputRoot);

        new GenerateGoldenMaster().runGame(762,
                new FileOutputStream(outputPathForGame(rootPath(), 762).toFile()));
    }

    public static Path rootPath() {
        return Paths.get("test", "data");
    }

    public static Path outputPathForGame(Path goldenMasterOutputRoot, final int gameNumber) {
        return goldenMasterOutputRoot.resolve(String.format("game-%s.txt", gameNumber));
    }

    public void runGame(final int gameNumber, final OutputStream canvas) throws IOException {
        System.setOut(new PrintStream(canvas));

        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random(gameNumber);

        boolean notAWinner;
        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);

        canvas.flush();
        canvas.close();
    }
}
