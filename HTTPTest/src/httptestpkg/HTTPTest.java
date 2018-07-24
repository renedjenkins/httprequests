package httptestpkg;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HTTPTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// want to test this: http://www.renejenkins.com/cgisecure/musicsearchresult.pl?txtbxTitle=&txtbxArtist=pet+shop+boys&txtbxAlbum=&txtbxYear=&txtbxGenre=&txtbxBitRt=&btnSearchSubmit=Search
		URL url = null;
		try {
			url = new URL("http://www.renejenkins.com/cgi-bin/index.pl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        URLConnection urlConnection = null;
		try {
			urlConnection = url.openConnection();
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

		    System.out.println("Request method is " + httpCon.getRequestMethod());
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Map<String, List<String>> headers = urlConnection.getHeaderFields();
        Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
        for (Map.Entry<String, List<String>> entry : entrySet) {
            String headerName = entry.getKey();
            System.out.println("Header Name:" + headerName);
            List<String> headerValues = entry.getValue();
            for (String value : headerValues) {
                System.out.print("Header value:" + value);
            }
            System.out.println();
        }
		
		
	}

}
