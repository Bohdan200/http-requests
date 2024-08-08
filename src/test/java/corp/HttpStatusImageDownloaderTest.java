package corp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import corp.classes.HttpURLConnection.HttpStatusImageDownloader;

public class HttpStatusImageDownloaderTest {
    @Test
    void testDownloadStatusImageValidCode() throws Exception {
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
        downloader.downloadStatusImage(200);

        File downloadedFile = new File("./images/HttpURLConnection/200.jpg");

        assertTrue(downloadedFile.exists());
        assertTrue(downloadedFile.delete());
    }

    @Test
    void testDownloadStatusImageInvalidCode() {
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
        Exception exception = assertThrows(Exception.class, () -> {
            downloader.downloadStatusImage(10000);
        });

        assertEquals("Image not found for status code: 10000", exception.getMessage());
    }
}
