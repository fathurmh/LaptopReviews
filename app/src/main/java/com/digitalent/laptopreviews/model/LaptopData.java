package com.digitalent.laptopreviews.model;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.androidadvance.topsnackbar.TSnackbar;
import com.digitalent.laptopreviews.MainActivity;
import com.digitalent.laptopreviews.R;
import com.digitalent.laptopreviews.fragment.HomeFragment;
import com.digitalent.laptopreviews.utils.Constants;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class LaptopData extends AsyncTask<Void, Void, Void> {
    private ProgressDialog dialog;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    @SuppressLint("StaticFieldLeak")
    private MainActivity activity;
    private HomeFragment homeFragment;
    private ArrayList<Laptop> listLaptop;
    private int selectedMenu;

    public LaptopData(Context context, HomeFragment homeFragment, ArrayList<Laptop> listLaptop, int selectedMenu) {
        this.context = context;
        this.activity = (MainActivity) context;
        this.homeFragment = homeFragment;
        this.listLaptop = listLaptop;
        this.selectedMenu = selectedMenu;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        JSONObject jsonObject = getDataFromWeb();
        try {
            if (jsonObject != null) {
                if(jsonObject.length() > 0) {
                    JSONArray array = jsonObject.getJSONArray("records");
                    int lenArray = array.length();
                    if(lenArray > 0) {
                        int jIndex = 0;
                        for(; jIndex < lenArray; jIndex++) {
                            Laptop model = new Laptop();
                            JSONObject innerObject = array.getJSONObject(jIndex);
                            int id = innerObject.getInt(Laptop.KEY_ID);
                            String name = innerObject.getString(Laptop.KEY_NAME);
                            String description = innerObject.getString(Laptop.KEY_DESCRIPTION);
                            String image = innerObject.getString(Laptop.KEY_IMAGE);
                            String price = innerObject.getString(Laptop.KEY_PRICE);
                            String cpu = innerObject.getString(Laptop.KEY_CPU);
                            String gpu = innerObject.getString(Laptop.KEY_GPU);
                            String display = innerObject.getString(Laptop.KEY_DISPLAY);
                            String storage = innerObject.getString(Laptop.KEY_STORAGE);
                            String ram = innerObject.getString(Laptop.KEY_RAM);
                            String battery = innerObject.getString(Laptop.KEY_BATTERY);
                            String dimension = innerObject.getString(Laptop.KEY_DIMENSIONS);
                            String weight = innerObject.getString(Laptop.KEY_WEIGHT);
                            String source = innerObject.getString(Laptop.KEY_SOURCE);
                            model.setId(id);
                            model.setName(name);
                            model.setDescription(description);
                            model.setImage(image);
                            model.setPrice(price);
                            model.setCpu(cpu);
                            model.setGpu(gpu);
                            model.setDisplay(display);
                            model.setStorage(storage);
                            model.setRam(ram);
                            model.setBattery(battery);
                            model.setDimensions(dimension);
                            model.setWeight(weight);
                            model.setSource(source);
                            listLaptop.add(model);
                        }
                    }
                }
            }
        } catch (JSONException je) {
            Log.i(Constants.TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(context);
        dialog.setTitle("Please wait...");
        dialog.setMessage("I am getting your data...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();

        if(listLaptop.size() > 0) {
            homeFragment.setMode(selectedMenu);
        } else {
            TSnackbar.make(activity.findViewById(R.id.fragment_container),"No Data Found.",TSnackbar.LENGTH_LONG).show();
        }
    }

    private static JSONObject getDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(Constants.API_URL)
                    .build();
            Response response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(Constants.TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
}
