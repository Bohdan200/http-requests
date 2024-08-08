package corp.classes.HttpURLConnection;

import java.net.HttpURLConnection;
import java.net.URL;

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

        File directory = new File("./images/HttpURLConnection");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = code + ".jpg";
        File file = new File(directory, fileName);

        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();


        try (InputStream in = connection.getInputStream();
             FileOutputStream out = new FileOutputStream(file)) {
            byte[] buffer = in.readAllBytes();

            for (byte b : buffer) {
                out.write(b);
            }
        }
    }
}
