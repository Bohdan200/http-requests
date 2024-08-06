package corp.classes.Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.Connection;

import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;

public class HttpStatusImageDownloader {
    private final HttpStatusChecker checker;

    public HttpStatusImageDownloader() {
        this.checker = new HttpStatusChecker();
    }

    public void downloadStatusImage(int code) throws Exception {
        String imageUrl = checker.getStatusImage(code);

        File directory = new File("./downloaded");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = code + ".jpg";
        File file = new File(directory, fileName);

        Connection.Response response = Jsoup
                .connect(imageUrl)
                .ignoreContentType(true)
                .execute();

        try (InputStream in = response.bodyStream();
             FileOutputStream out = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
