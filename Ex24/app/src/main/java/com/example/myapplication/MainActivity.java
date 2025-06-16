package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView lv;
    private TextView txtDate;
    private ProgressBar progressBar;
    private ArrayList<Tygia> list;
    private MyArrayAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv = findViewById(R.id.lv);
        txtDate = findViewById(R.id.txt_date);
        setDate();
        list = new ArrayList<>();
        adapter = new MyArrayAdapter(this, R.layout.layout_listview, list);
        lv.setAdapter(adapter);
        new TyGiaTask().execute();
    }

    private void setDate() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtDate.setText("Hôm nay: "+ simpleDateFormat.format(currentTime));
    }

    private class TyGiaTask extends AsyncTask<Void, Void, ArrayList<Tygia>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapter.clear();
        }

        @Override
        protected ArrayList<Tygia> doInBackground(Void... params) {
            ArrayList<Tygia> result = new ArrayList<>();
            HttpURLConnection connection = null;
            try {
                URL url = new URL("https://dongabank.com.vn/exchange/export");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible)");
                connection.setRequestProperty("Accept", "*/*");

                try (InputStream is = connection.getInputStream();
                     InputStreamReader isr = new InputStreamReader(is);
                     BufferedReader br = new BufferedReader(isr)) {
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        builder.append(line);
                    }
                    String json = builder.toString().replace("(", "").replace(")", "");
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        Tygia tygia = new Tygia();
                        tygia.setType(item.optString("type", ""));

                        if (item.has("imageurl") && !item.isNull("imageurl")) {
                            tygia.setImageurl(item.getString("imageurl"));
                            HttpURLConnection imageConnection = null;
                            try {
                                URL imageUrl = new URL(tygia.getImageurl());
                                imageConnection = (HttpURLConnection) imageUrl.openConnection();
                                imageConnection.setRequestMethod("GET");
                                imageConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible)");
                                imageConnection.setRequestProperty("Accept", "*/*");
                                Bitmap bitmap = BitmapFactory.decodeStream(imageConnection.getInputStream());
                                tygia.setBitmap(bitmap);
                            } catch (Exception e) {
                                Log.e(TAG, "Error downloading image for " + tygia.getType(), e);
                            } finally {
                                if (imageConnection != null) {
                                    imageConnection.disconnect();
                                }
                            }
                        }

                        tygia.setBuycash(item.optString("buycash", ""));
                        tygia.setBuyck(item.optString("buyck", ""));
                        tygia.setSellcash(item.optString("sellcash", ""));
                        tygia.setSellck(item.optString("sellck", ""));
                        result.add(tygia);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Error fetching exchange rates", e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Tygia> result) {
            super.onPostExecute(result);
            list.clear();
            list.addAll(result);
            adapter.notifyDataSetChanged();
        }
    }
}