package io.github.rhildred.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ListView itemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsListView = (ListView) findViewById(R.id.itemsListView);

        PlayerDB db = new PlayerDB(this);
        // create a List of Map<String, ?> objects
        ArrayList<HashMap<String, String>> data = db.getPlayers();

        // create the resource, from, and to variables
        int resource = R.layout.listview_item;
        String[] from = {"name", "wins", "losses", "ties"};
        int[] to = {R.id.nameTextView, R.id.winsTextView, R.id.lossesTextView, R.id.tiesTextView};

        // create and set the adapter
        SimpleAdapter adapter =
                new SimpleAdapter(this, data, resource, from, to);
        itemsListView.setAdapter(adapter);

    }
}
