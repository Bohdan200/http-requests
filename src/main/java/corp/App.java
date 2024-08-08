package corp;

import corp.classes.HttpURLConnection.HttpImageStatusCli;
//import corp.classes.Jsoup.HttpImageStatusCli;
//import corp.classes.HttpClient.HttpImageStatusCli;

public class App {
    public static void main(String[] args) {
        HttpImageStatusCli cli = new HttpImageStatusCli();
        cli.askStatus();
    }
}