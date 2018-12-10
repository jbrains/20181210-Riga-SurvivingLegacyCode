package ca.jbrains.trivia.test;

import com.adaptionsoft.games.uglytrivia.Game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class GenerateGoldenMaster {

    public static void main(String[] args) throws Exception {
        final Path goldenMasterOutputRoot = Paths.get("test", "data");
        Files.createDirectories(goldenMasterOutputRoot);
        new GenerateGoldenMaster().runGame(goldenMasterOutputRoot);
    }

    private void runGame(Path goldenMasterOutputRoot) throws IOException {
        final Path goldenMasterOutputPath = goldenMasterOutputRoot.resolve("game-762.txt");
        final FileOutputStream canvas = new FileOutputStream(goldenMasterOutputPath.toFile());
        System.setOut(new PrintStream(canvas));

        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random(762);

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
