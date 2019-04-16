package com.yilongzhu.rrg;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.lang.StringBuffer;

import java.util.Random;

public class Generate {
    private static String base = "https://api.yelp.com/v3/businesses/search?categories=restaurants&open_now=true";

    public static Business generateBusiness(double latitude, double longitude, int radius) {
        try {
            String json = getJsonFromYelp(latitude, longitude, radius);
            ObjectMapper mapper = new ObjectMapper();
            Search search = mapper.readValue(json, Search.class);
            // System.out.println(search.toString());
            // for (Business b : search.getBusinesses())
            //     System.out.println(mapper.writeValueAsString(b));
            
            Random rand = new Random();

            return search.getBusinesses()[rand.nextInt(20)];
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

    private static String getJsonFromYelp(double latitude, double longitude, int radius) throws MalformedURLException, IOException  {
        String url = buildURL(latitude, longitude, radius);
        String apiKey = "";
        
        URL yelpURL = new URL(url);
        HttpURLConnection yelpCon = (HttpURLConnection) yelpURL.openConnection();
        yelpCon.setRequestMethod("GET");
        yelpCon.setRequestProperty("Authorization", "Bearer " + apiKey);
        
        String line;
        StringBuffer json = new StringBuffer();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(yelpCon.getInputStream()))) {
            while ((line=in.readLine()) != null) {
                json.append(line);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        yelpCon.disconnect();

        return json.toString();
    }

    private static String buildURL(double latitude, double longitude, int radius) {
        StringBuffer sb = new StringBuffer(base);
        sb.append("&latitude=").append(latitude)
        .append("&longitude=").append(longitude)
        .append("&radius=").append(radius);

        return sb.toString();
    }
}