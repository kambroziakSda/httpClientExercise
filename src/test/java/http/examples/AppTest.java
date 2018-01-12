package http.examples;

import com.google.gson.*;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testGetSdAcademy() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://sdacademy.pl");
        httpGet.addHeader("Accept-Encoding", "");

        HttpResponse response = client.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println("Kod odpowiedzi: " + statusCode);

        String reasonPhrase = response.getStatusLine().getReasonPhrase();

        System.out.println("Description: " + reasonPhrase);

        for (Header h : response.getAllHeaders()) {
            System.out.println("Header name: " + h.getName() + " value: " + h.getValue());
        }

        HttpEntity entity = response.getEntity();

        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();

        String pageAsString = basicResponseHandler.handleEntity(entity);

        System.out.println(pageAsString);
    }

    @Test
    public void testGetSdAcademyByIp() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://185.11.130.82");
        httpGet.addHeader(new BasicHeader("Host", "sdacademy.pl"));

        HttpResponse response = client.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println("Kod odpowiedzi: " + statusCode);

        String reasonPhrase = response.getStatusLine().getReasonPhrase();

        System.out.println("Description: " + reasonPhrase);

        for (Header h : response.getAllHeaders()) {
            System.out.println("Header name: " + h.getName() + " value: " + h.getValue());
        }

        HttpEntity entity = response.getEntity();

        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();

        String pageAsString = basicResponseHandler.handleEntity(entity);

        System.out.println(pageAsString);


    }

    @Test
    public void testDeleteSdAcademy() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpDelete httpDelete = new HttpDelete("http://sdacademy.pl");

        HttpResponse response = client.execute(httpDelete);

        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println("Kod odpowiedzi: " + statusCode);

        String reasonPhrase = response.getStatusLine().getReasonPhrase();

        System.out.println("Description: " + reasonPhrase);

        for (Header h : response.getAllHeaders()) {
            System.out.println("Header name: " + h.getName() + " value: " + h.getValue());
        }

        HttpEntity entity = response.getEntity();

        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();

        String pageAsString = basicResponseHandler.handleEntity(entity);

        //System.out.println(pageAsString);
    }

    @Test
    public void testSendDataToSdAcademy() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://wp.pl");

        httpPost.setEntity(EntityBuilder.create().setText("Jak sie macie").build());

        HttpResponse response = client.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println("Kod odpowiedzi: " + statusCode);

        String reasonPhrase = response.getStatusLine().getReasonPhrase();

        System.out.println("Description: " + reasonPhrase);

        for (Header h : response.getAllHeaders()) {
            System.out.println("Header name: " + h.getName() + " value: " + h.getValue());
        }

        HttpEntity entity = response.getEntity();

        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();

        String pageAsString = basicResponseHandler.handleEntity(entity);

        System.out.println(pageAsString);
    }

    @Test
    public void testJavaApi() throws IOException {
        URL url = new URL("http://sdacademy.pl/");
        URLConnection yc = url.openConnection();
        Map<String, List<String>> headerFields = yc.getHeaderFields();
        System.out.println(headerFields);
        //yc.setRequestProperty("Accept-Encoding", "gzip,deflate");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                new GZIPInputStream(yc.getInputStream())));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();

    }

    @Test
    public void testGetPolandInfo() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://restcountries.eu/rest/v2/name/poland");


        HttpResponse response = client.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println("Kod odpowiedzi: " + statusCode);

        String reasonPhrase = response.getStatusLine().getReasonPhrase();

        System.out.println("Description: " + reasonPhrase);

        for (Header h : response.getAllHeaders()) {
            System.out.println("Header name: " + h.getName() + " value: " + h.getValue());
        }

        HttpEntity entity = response.getEntity();

        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();

        String pageAsString = basicResponseHandler.handleEntity(entity);

        System.out.println(pageAsString);
    }

    @Test
    public void testGsonParse() {
        String json = "{\"firstname\":\"K\",\"lastname\":\"A\"}";

        JsonParser jsonParser = new JsonParser();

        JsonElement parse = jsonParser.parse(json);

        JsonObject jsonObject = parse.getAsJsonObject();
        JsonElement jsonLastname = jsonObject.get("lastname");
        String lastname = jsonLastname.getAsString();

        System.out.println("Last name: " + lastname);
    }

    @Test
    public void testBuildSentence() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://restcountries.eu/rest/v2/name/poland");

        HttpResponse execute = client.execute(httpGet);

        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();

        String pageAsString = basicResponseHandler.handleEntity(execute.getEntity());

        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(pageAsString);

        JsonArray arrayOfObjects = parse.getAsJsonArray();

        JsonObject infoObject = arrayOfObjects.get(0).getAsJsonObject();

        int population = infoObject.get("population").getAsInt();

        StringBuilder resultBuilder = new StringBuilder("Polska ma: ")
                .append(population)
                .append(" ludnosci, graniczy z:");

        JsonArray borders = infoObject.get("borders").getAsJsonArray();

        for (JsonElement jsonElement : borders) {
            resultBuilder.append(jsonElement.getAsString()).append(", ");
        }
        resultBuilder
                .delete(resultBuilder.length() - 2, resultBuilder.length())
                .append("a po włosku to: ");

        String it = infoObject.get("translations").getAsJsonObject().get("it").getAsString();

        String result = resultBuilder.append(it).toString();

        System.out.println(result);
    }

    @Test
    public void buildJsonFromObject() {
        Gson gson = new Gson();
        Student student = new Student();
        student.setAge(18);
        student.setFirstName("K");
        student.setLastName("A");
        String studentInJson = gson.toJson(student);

        System.out.println(studentInJson);
    }

    @Test
    public void buildObjectFromJson() {
        Gson gson = new Gson();
        Student student = new Student();
        student.setAge(18);
        student.setFirstName("K");
        student.setLastName("A");
        String studentInJson = gson.toJson(student);
        System.out.println("Student in json: "+studentInJson);

        Student studentFromJson = new Gson().fromJson(studentInJson, Student.class);


        //Assert.assertEquals("K", studentFromJson.getFirstname());
        //Assert.assertEquals("A", studentFromJson.getLastname());
        Assert.assertEquals(18, (int) studentFromJson.getAge());

        System.out.println("Student object: "+studentFromJson);
    }

    @Test
    public void sendStudentData() throws IOException {
        Gson gson = new Gson();
        Student student = new Student();
        student.setAge(18);
        student.setFirstName("K");
        student.setLastName("A");
        String studentInJson = gson.toJson(student);

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://protected-cove-60658.herokuapp.com/students");

        httpPost.setEntity(EntityBuilder.create().setText(studentInJson).build());
        httpPost.addHeader("content-type","application/json");


        HttpResponse execute = httpClient.execute(httpPost);

        System.out.println("Status odpowiedzi: "+execute.getStatusLine().getStatusCode());

        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
        String s = basicResponseHandler.handleResponse(execute);

        System.out.println("Odp: "+s);
    }

    @Test
    public void getStudentData() throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet("https://protected-cove-60658.herokuapp.com/students");

        HttpResponse execute = httpClient.execute(httpGet);

        System.out.println("Status odpowiedzi: "+execute.getStatusLine().getStatusCode());

        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
        String s = basicResponseHandler.handleResponse(execute);

        System.out.println("Odp: "+s);
    }

}
