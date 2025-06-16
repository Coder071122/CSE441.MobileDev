package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ListView lv;
    ArrayList<List> list;
    MyArrayAdapter adapter;
    String nodeName;
    String title = "";
    String link = "";
    String description = "";
    String des = "";
    String urlStr = "";
    Bitmap mIcon_val = null;
    String URL = "https://vnexpress.net/rss/tin-moi-nhat.rss";

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
        list = new ArrayList<>();
        adapter = new MyArrayAdapter(this, R.layout.layout_listview, list);
        lv.setAdapter(adapter);
        LoadExampleTask task = new LoadExampleTask();
        task.execute();
    }

    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<List>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list.clear();
        }

        @Override
        protected ArrayList<List> doInBackground(Void... voids) {
            list = new ArrayList<>();
            try {
                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                XmlPullParser parser = fc.newPullParser();
                XMLParser myparser = new XMLParser();
                String xml = myparser.getXmlFromUrl(URL);

                parser.setInput(new StringReader(xml));
                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            nodeName = parser.getName();

                            if (nodeName.equals("item")) {
                                // Reset dữ liệu mỗi item
                                title = "";
                                link = "";
                                description = "";
                                des = "";
                                urlStr = "";
                                mIcon_val = null;
                            }

                            if (nodeName.equals("title")) {
                                title = parser.nextText();
                            } else if (nodeName.equals("link")) {
                                link = parser.nextText();
                            } else if (nodeName.equals("description")) {
                                description = parser.nextText();

                                try {
                                    int start = description.indexOf("src=");
                                    int end = description.indexOf("></a");

                                    if (start != -1 && end != -1) {
                                        urlStr = description.substring(start + 5, end - 2);
                                        URL newurl = new URL(urlStr);
                                        mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                                    }

                                    int desStart = description.indexOf("</br>");
                                    if (desStart != -1 && desStart + 5 < description.length()) {
                                        des = description.substring(desStart + 5).trim();
                                    } else {
                                        des = description;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    urlStr = "";
                                    des = description;
                                }
                            }
                            break;

                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("item")) {
                                list.add(new List(mIcon_val, des, link, title));
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<List> lists) {
            super.onPostExecute(lists);
            adapter.clear();
            adapter.addAll(lists);
        }
    }
}
