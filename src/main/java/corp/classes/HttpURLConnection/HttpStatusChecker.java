package corp.classes.HttpURLConnection;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;

public class HttpStatusChecker {
    public String getStatusImage(int code) throws Exception {
        String imageUrl = "https://http.cat/" + code + ".jpg";
        if (isImageAvailable(imageUrl)) {
            return imageUrl;
        } else {
            throw new Exception("Image not found for status code: " + code);
        }
    }

    private boolean isImageAvailable(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        int responseCode = connection.getResponseCode();

        return responseCode == 200;
    }
}
