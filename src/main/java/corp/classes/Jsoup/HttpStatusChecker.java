package corp.classes.Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
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
        Connection.Response response = Jsoup.connect(imageUrl)
                .ignoreContentType(true)
                .execute();

        return response.statusCode() == 200;
    }
}
