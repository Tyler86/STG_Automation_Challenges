package Common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class UrlChecker {


    public int checkUrl(String link) throws IOException {
       // link = link.replaceAll(" ", "%20");
        URL url = new URL(link);
        HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
        httpURLConnect.setConnectTimeout(3000);
        httpURLConnect.connect();
        return httpURLConnect.getResponseCode();
    }

    public int checkUrls(List<String> links) throws IOException {
        int numberOfBrokenImages = 0;
        for(int j=0; j<links.size();j++){
            if(checkUrl(links.get(j))!=200){
                numberOfBrokenImages++;
            }
        }
        return numberOfBrokenImages;
    }

}
