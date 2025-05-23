package com.example.bookapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BookAPI {

    public static String searchBook(String query) {
        try {
            String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=" + query.replace(" ", "+");
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            InputStream responseStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);

            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray items = json.getAsJsonArray("items");

            if (items == null || items.size() == 0) {
                return "No results found.";
            }

            JsonObject volumeInfo = items.get(0).getAsJsonObject().getAsJsonObject("volumeInfo");

            String title = volumeInfo.get("title").getAsString();
            String authors = volumeInfo.has("authors") ? volumeInfo.getAsJsonArray("authors").toString() : "Unknown";
            String description = volumeInfo.has("description") ? volumeInfo.get("description").getAsString() : "No description";

            return "Title: " + title + "\nAuthors: " + authors + "\n\n" + description;

        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
