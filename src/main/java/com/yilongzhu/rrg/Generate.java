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
    public static Business generateBusiness() {
        try {
            String json = getJsonFromYelp();
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

    private static String getJsonFromYelp() throws MalformedURLException, IOException  {
        String apiURL = "https://api.yelp.com/v3/businesses/search?latitude=33.795867650000005&longitude=-84.32778960627682&radius=10000&categories=restaurants&open_now=true";
        String apiKey = "";
        
        URL yelpURL = new URL(apiURL);
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
}