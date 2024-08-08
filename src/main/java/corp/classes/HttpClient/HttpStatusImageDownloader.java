package corp.classes.HttpClient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;

public class HttpStatusImageDownloader {

    private final HttpStatusChecker checker;

    public HttpStatusImageDownloader() {
        this.checker = new HttpStatusChecker();
    }

    public void downloadStatusImage(int code) throws Exception {
        String imageUrl = checker.getStatusImage(code);

        File directory = new File("./images/HttpClient");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = code + ".jpg";
        File file = new File(directory, fileName);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(new HttpGet(imageUrl));
             InputStream inputStream = response.getEntity().getContent();
             BufferedInputStream in = new BufferedInputStream(inputStream);
             FileOutputStream out = new FileOutputStream(file)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
