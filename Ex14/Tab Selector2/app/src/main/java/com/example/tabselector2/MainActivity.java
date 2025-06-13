package com.example.tabselector2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtSearch;
    ListView lv1, lv2, lv3;
    TabHost tab;
    ArrayList<Item> lst1, lst2, lst3;
    myarrayAdapter adapter1, adapter2, adapter3;
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
        addControl();
        addEvent();
    }

    private void addControl() {
        tab = findViewById(R.id.tabhost);
        tab.setup();
        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(R.drawable.ic_search));
        tab.addTab(tab1);

        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.ic_list));
        tab.addTab(tab2);

        TabHost.TabSpec tab3 = tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("", getResources().getDrawable(R.drawable.ic_favorite));
        tab.addTab(tab3);

        edtSearch = findViewById(R.id.edt_search);
        lv1 = findViewById(R.id.lv1);
        lv2 = findViewById(R.id.lv2);
        lv3 = findViewById(R.id.lv3);
        lst1 = new ArrayList<Item>();
        lst2 = new ArrayList<Item>();
        lst3 = new ArrayList<Item>();
        adapter1 = new myarrayAdapter(MainActivity.this, R.layout.listitem, lst1);
        adapter2 = new myarrayAdapter(MainActivity.this, R.layout.listitem, lst2);
        adapter3 = new myarrayAdapter(MainActivity.this, R.layout.listitem, lst3);
        lv1.setAdapter(adapter1);
        lv2.setAdapter(adapter2);
        lv3.setAdapter(adapter3);
        E
    }

    private void addEvent() {
        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equalsIgnoreCase("t1")){
                    lst1.clear();
                    lst1.add(new Item("52300",0 , "Em là ai tôi là ai"));
                    lst1.add(new Item("52600",1 , "Chén đắng"));
                    adapter1.notifyDataSetChanged();
                }
                if(tabId.equalsIgnoreCase("t2")){
                    lst2.clear();
                    lst2.add(new Item("57236",0 , "Gửi em ở cuối sông"));
                    lst2.add(new Item("51548",0 , "Say tình"));
                    adapter2.notifyDataSetChanged();
                }
                if(tabId.equalsIgnoreCase("t3")){
                    lst3.clear();
                    lst3.add(new Item("57689",1 , "Hát với dòng sông"));
                    lst3.add(new Item("58716",0 , "Say tình - remix"));
                    adapter3.notifyDataSetChanged();
                }
            }
        });
    }
}