package hu.firstvan.rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class WorklogSummaryTest {
    private HttpClient httpClient;
    private String username;
    private String tokenString;

    @Before
    public void setUp() throws Exception {
        httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost( "http://localhost:8888/jira-integration/rest/restlogin");

        JSONObject object = new JSONObject();
        try {
            object.put("username", "firstvan");
            object.put("password", "Pid34Kut");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        String message = object.toString();
        request.setEntity(new StringEntity(message, "UTF8"));
        request.setHeader("Content-type", "application/json");

        HttpResponse response = httpClient.execute(request);

        InputStream inputStream = response.getEntity().getContent();
        String input = LoginRestTest.getStringFromInputStream(inputStream);

        JSONObject token = new JSONObject(input);

        username = token.getString("username");
        tokenString = token.getString("token");
    }

    @Test
    public void testWorklogSummary() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8888/jira-integration/rest/worklogsummary/firstvan/2018-05-09");
        request.setHeader("x-username", username);
        request.setHeader("x-token", tokenString);
        HttpResponse response = httpClient.execute(request);

        InputStream inputStream = response.getEntity().getContent();
        String input = LoginRestTest.getStringFromInputStream(inputStream);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("0h 30m", input);
    }
}
