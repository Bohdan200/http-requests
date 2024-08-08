package corp.classes.HttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class HttpStatusChecker {
    public String getStatusImage(int code) throws Exception {
        String imageUrl = "https://httpgoats.com/" + code + ".jpg";
        if (isImageAvailable(imageUrl)) {
            return imageUrl;
        } else {
            throw new Exception("Image not found for status code: " + code);
        }
    }

    private boolean isImageAvailable(String imageUrl) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpHead request = new HttpHead(imageUrl);
            HttpResponse response = httpClient.execute(request);
            return response.getStatusLine().getStatusCode() == 200;
        }
    }
}
