package corp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import corp.classes.HttpURLConnection.HttpImageStatusCli;

public class HttpImageStatusCliTest {
    @Test
    void testAskStatusValidCode() {
        HttpImageStatusCli cli = new HttpImageStatusCli();
        String input = "200\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        cli.askStatus();

        String output = out.toString();
        assertTrue(output.contains("Image downloaded successfully."));
    }

    @Test
    void testAskStatusInvalidCode() {
        HttpImageStatusCli cli = new HttpImageStatusCli();
        String input = "10000\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        cli.askStatus();

        String output = out.toString();
        assertTrue(output.contains("There is no image for HTTP status 10000"));
    }

    @Test
    void testAskStatusInvalidInput() {
        HttpImageStatusCli cli = new HttpImageStatusCli();
        String input = "invalid\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        cli.askStatus();

        String output = out.toString();
        assertTrue(output.contains("Please enter a valid number."));
    }
}
