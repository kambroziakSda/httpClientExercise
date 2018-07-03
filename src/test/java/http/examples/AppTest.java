package http.examples;

import org.apache.http.Header;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testGetSdAcademy() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://sdacademy.pl");
        CloseableHttpResponse response = client.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println("Status: " + statusCode);
        String statusDescription = response.getStatusLine().getReasonPhrase();
        System.out.println(statusDescription);

        Header[] allHeaders = response.getAllHeaders();
        for (Header header : allHeaders) {
            System.out.println("Nagłówek: " + header.getName() + ", wartosc: " + header.getValue());
        }
        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();

        String responseAsString = basicResponseHandler.handleResponse(response);

        System.out.println(responseAsString);

    }

    @Test
    public void testGetSdAcademyByIp() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://185.11.130.82");

        CloseableHttpResponse response = httpClient.execute(httpGet);

        String responseAsString = new BasicResponseHandler().handleResponse(response);

        System.out.println(responseAsString);
    }

    @Test
    public void testDeleteSdAcademy() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete httpDelete = new HttpDelete("https://google.pl");

        CloseableHttpResponse response = httpClient.execute(httpDelete);

        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println(statusCode);

        String responseAsString = new BasicResponseHandler().handleResponse(response);

        System.out.println(responseAsString);
    }

    @Test
    public void testSendDataToSdAcademy() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://google.pl");

        httpPost.setEntity(EntityBuilder.create().setText("Jak sie macie?").build());

        CloseableHttpResponse response = httpClient.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println(statusCode);

        String responseAsString = new BasicResponseHandler().handleResponse(response);

        System.out.println(responseAsString);
    }

    @Test
    public void testJavaApi() throws IOException {
        URL url = new URL("https://sdacademy.pl");

        URLConnection urlConnection = url.openConnection();

        try (InputStream inputStream = urlConnection.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(new GZIPInputStream(inputStream));
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    @Test
    public void testGetPolandInfo() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://restcountries.eu/rest/v2/name/poland");
        CloseableHttpResponse response = client.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println("Status: " + statusCode);
        String statusDescription = response.getStatusLine().getReasonPhrase();
        System.out.println(statusDescription);

        String responseAsString = new BasicResponseHandler().handleResponse(response);

        System.out.println(responseAsString);

    }

    @Test
    public void testGsonParse() {

    }

    @Test
    public void testBuildSentence() throws IOException {


    }

    @Test
    public void buildJsonFromObject() {

    }

    @Test
    public void buildObjectFromJson() {

    }

    @Test
    public void sendStudentData() throws IOException {

    }

    @Test
    public void getStudentData() throws IOException {

    }

}
