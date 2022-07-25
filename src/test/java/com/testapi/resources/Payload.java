package com.testapi.resources;

import java.util.HashMap;

public class Payload {

    public static HashMap<String, String> createUpdateUserPayload(String name, String role) {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("job", role);
        return data;
    }

    public static HashMap<String, String> createInvalidUserPayload(String name) {
        HashMap<String, String> data = new HashMap<>();
        data.put("",name);
        return data;
    }

    public static HashMap<String, Object> createMoviePayload(int id, String name, String genre, int rating) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("name", name);
        data.put("genre", genre);
        data.put("rating", rating);
        return data;
    }
}
