package com.project.shimi.creative_theory_test.ui.rawmaterial;

import android.util.Log;

import com.project.shimi.creative_theory_test.R;
import com.project.shimi.creative_theory_test.model.RawMaterialItem;
import com.project.shimi.creative_theory_test.ui.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RawMaterialPresenter {

    private MainActivity activity;
    private RawMaterialFragment context;

    private StringBuilder stringBuilder;

    public RawMaterialPresenter(MainActivity activity,RawMaterialFragment fragment) {
        this.activity = activity;
        this.context = fragment;
        stringBuilder = new StringBuilder();
    }

    public void getRawMaterialFromJson() {
        try {
            try (BufferedReader jsonReader = new BufferedReader(new InputStreamReader(activity.getResources().openRawResource(R.raw.json_raw_material)))) {
                for (String line; (line = jsonReader.readLine()) != null; ) {
                    stringBuilder.append(line).append("\n");
                }
            }
            JSONTokener jsonTokener = new JSONTokener(stringBuilder.toString());
            JSONArray jsonArray = new JSONArray(jsonTokener);
            List<RawMaterialItem> rawMaterialItemList = new ArrayList<>();
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                RawMaterialItem rawMaterialItem = new RawMaterialItem();
                rawMaterialItem.setId(jsonObject.getString("id"));
                rawMaterialItem.setName(jsonObject.getString("name"));
                rawMaterialItem.setPicture(jsonObject.getString("picture"));
                rawMaterialItem.setDefaultPrice(jsonObject.getInt("default_price"));
                rawMaterialItemList.add(rawMaterialItem);
            }
            context.updateAdapter(rawMaterialItemList);
        } catch (FileNotFoundException e) {
            Log.e("error", "File not Found");
        } catch (IOException e) {
            Log.e("error", "IO Error");
        } catch (JSONException e) {
            Log.e("error", "Error While Parsing JSON");
        }
    }

}
