package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

//    {"name":{"mainName":"Club sandwich",
//    "alsoKnownAs":["Clubhouse sandwich"]},
//    "placeOfOrigin":"United States",
//    "description":"A club sandwich, also called a clubhouse sandwich,
//    is a sandwich of bread (occasionally toasted), sliced cooked poultry,
//    fried bacon, lettuce, tomato, and mayonnaise. It is often cut into quarters or
//    halves and held together by cocktail sticks. Modern versions frequently have two layers which
//    are separated by an additional slice of bread.",
//    "image":"https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Club_sandwich.png/800px-Club_sandwich.png",
//    "ingredients":["Toasted bread","Turkey or chicken","Bacon","Lettuce","Tomato","Mayonnaise"]}

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject jsonObj = new JSONObject(json);
            sandwich.setMainName(jsonObj.getString("mainName"));

            List<JSONObject> listObjs = parseJsonData(jsonObj,"alsoKnownAs");




        } catch (JSONException e) {
            e.printStackTrace();
        }




        return sandwich;
    }


    public static List<JSONObject> parseJsonData(JSONObject obj, String pattern)throws JSONException {

        List<JSONObject> listObjs = new ArrayList<JSONObject>();
        JSONArray geodata = obj.getJSONArray (pattern);
        for (int i = 0; i < geodata.length(); ++i) {
            final JSONObject site = geodata.getJSONObject(i);
            listObjs.add(site);
        }
        return listObjs;
    }
}
