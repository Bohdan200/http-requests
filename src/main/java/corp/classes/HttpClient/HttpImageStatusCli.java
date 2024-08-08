package corp.classes.HttpClient;

import java.util.Scanner;

public class HttpImageStatusCli {
    private final HttpStatusImageDownloader downloader;

    public HttpImageStatusCli() {
        this.downloader = new HttpStatusImageDownloader();
    }

    public void askStatus() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter HTTP status code (or type 'exit' to quit and to see the downloaded images in directory \"download\"):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program.");
                break;
            }

            try {
                int statusCode = Integer.parseInt(input);
                try {
                    downloader.downloadStatusImage(statusCode);
                    System.out.println("Image downloaded successfully.");
                } catch (Exception e) {
                    System.out.println("There is no image for HTTP status " + statusCode);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
