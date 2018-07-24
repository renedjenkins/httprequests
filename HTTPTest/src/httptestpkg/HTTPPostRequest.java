package httptestpkg;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HTTPPostRequest {

    private static HttpURLConnection con;

    public static void main(String[] args) throws MalformedURLException,
            ProtocolException, IOException {

        String url = "http://www.renejenkins.com/cgisecure/musicsearchresult.pl";
        //Pet Shop Boys Example
        //String urlParameters = "txtbxTitle=&txtbxArtist=pet+shop+boys&txtbxAlbum=&txtbxYear=&txtbxGenre=&txtbxBitRt=&btnSearchSubmit=Search";
        //Elvis Example
        String urlParameters = "txtbxTitle=&txtbxArtist=elvis&txtbxAlbum=&txtbxYear=&txtbxGenre=&txtbxBitRt=&btnSearchSubmit=Search";
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {
        	String userpass = "<omitted>" + ":" + "<omitted>";
        	String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Java client");
            //con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Type", "text/html");
            // note just because you request json you will only get back the type the host sends
            //con.setRequestProperty("Content-Type", "application/json");
            
            
            con.setRequestProperty ("Authorization", basicAuth);

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());

        } finally {
            
            con.disconnect();
        }
    }

}
