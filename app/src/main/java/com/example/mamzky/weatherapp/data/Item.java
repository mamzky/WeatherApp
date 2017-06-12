package com.example.mamzky.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Reezky on 6/11/2017.
 */

public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition   = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }
}
