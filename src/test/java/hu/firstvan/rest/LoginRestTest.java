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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LoginRestTest {
    private HttpClient httpClient;
    private String username;
    private String tokenString;

    @Before
    public void setUp() throws Exception {
        httpClient = HttpClientBuilder.create().build();
    }

    @Test
    public void testLogin() throws IOException {
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
        String input = getStringFromInputStream(inputStream);

        JSONObject token = new JSONObject(input);

        username = token.getString("username");
        tokenString = token.getString("token");

        assertEquals("firstvan", username);
        assertNotEquals("", tokenString);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
