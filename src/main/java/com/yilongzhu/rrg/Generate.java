package com.yilongzhu.rrg;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.lang.StringBuffer;

import java.util.Map;
import java.util.Random;

public class Generate {
    private static String base = "https://api.yelp.com/v3/businesses/search?categories=restaurants&open_now=true&limit=50";
    private static String key = "";

    public static Business generateBusiness(Map<String, String> parameters) {
        try {
            String json = getJsonFromYelp(parameters);
            ObjectMapper mapper = new ObjectMapper();
            Search search = mapper.readValue(json, Search.class);
            Random rand = new Random();

            return search.getBusinesses()[rand.nextInt(Math.min(50, search.getTotal()))];
        } catch (Exception e) {
            System.out.println("Exception in generateBusiness(): " + e.getMessage());
            return null;
        }
    }

    private static String getJsonFromYelp(Map<String, String> parameters) throws MalformedURLException, IOException  {
        String url = buildURL(parameters);
        
        URL yelpURL = new URL(url);
        HttpURLConnection yelpCon = (HttpURLConnection) yelpURL.openConnection();
        yelpCon.setRequestMethod("GET");
        yelpCon.setRequestProperty("Authorization", "Bearer " + key);
        
        String line;
        StringBuffer json = new StringBuffer();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(yelpCon.getInputStream()))) {
            while ((line=in.readLine()) != null) {
                json.append(line);
            }
        } catch (Exception e) {
            System.out.println("Exception in getJsonFromYelp(): " + e.getMessage());
        }

        yelpCon.disconnect();

        return json.toString();
    }

    private static String buildURL(Map<String, String> parameters) {
        StringBuffer sb = new StringBuffer(base);
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            sb.append("&")
            .append(entry.getKey())
            .append("=")
            .append(entry.getValue());
        }

        return sb.toString();
    }
}