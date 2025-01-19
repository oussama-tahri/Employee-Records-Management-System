package com.employee.service;

import com.employee.model.Employee;
import com.employee.utils.JsonUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiClient {
    private final String baseUrl;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public <T> T get(String endpoint, Class<T> responseType) {
        try {
            URL url = new URL(baseUrl + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                String response = new String(inputStream.readAllBytes());
                return JsonUtils.fromJson(response, responseType);
            } else {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in GET request", e);
        }
    }

    public String get(String endpoint) {
        try {
            URL url = new URL(baseUrl + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    return response.toString();
                }
            } else {
                throw new RuntimeException("Failed with HTTP code: " + responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("API call failed: " + e.getMessage(), e);
        }
    }


    public void post(String endpoint, Object requestBody) {
        try {
            URL url = new URL(baseUrl + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(JsonUtils.toJson(requestBody).getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in POST request", e);
        }
    }

    public void put(String endpoint, Object requestBody) {
        try {
            URL url = new URL(baseUrl + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(JsonUtils.toJson(requestBody).getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in PUT request", e);
        }
    }

    public void delete(String endpoint) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_NO_CONTENT) {
            throw new IOException("DELETE request failed with response code: " + responseCode);
        }
    }

    public void downloadFile(String endpoint, String savePath) {
        try {
            URL url = new URL(baseUrl + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                try (InputStream inputStream = conn.getInputStream();
                     FileOutputStream fos = new FileOutputStream(savePath)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error downloading file", e);
        }
    }
}

