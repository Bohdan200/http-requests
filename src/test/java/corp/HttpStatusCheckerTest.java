package corp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import corp.classes.HttpURLConnection.HttpStatusChecker;

public class HttpStatusCheckerTest {

    @Test
    void testGetStatusImageValidCode() throws Exception {
        HttpStatusChecker checker = new HttpStatusChecker();
        String imageUrl = checker.getStatusImage(200);

        assertEquals("https://http.dog/200.jpg", imageUrl);
    }

    @Test
    void testGetStatusImageInvalidCode() {
        HttpStatusChecker checker = new HttpStatusChecker();
        Exception exception = assertThrows(Exception.class, () -> {
            checker.getStatusImage(10000);
        });

        assertEquals("Image not found for status code: 10000", exception.getMessage());
    }
}
