package io.github.rhildred.crud;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private MainActivity mainActivity;
    private ArrayList<HashMap<String, String>> data;
    private int resource;
    private String[] from;
    private int[] to;

    public SimpleRecyclerAdapter(MainActivity mainActivity, ArrayList<HashMap<String, String>> data, int resource, String[] from, int[] to) {
        this.mainActivity = mainActivity;
        this.data = data;
        this.resource = resource;
        this.from = from;
        this.to = to;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ArrayList<TextView> aTexts;
        public TextView popText;

        public MyViewHolder(View view) {
            super(view);
            this.aTexts = new ArrayList<TextView>();
            countryText = (TextView) view.findViewById(R.id.countryName);
            popText = (TextView) view.findViewById(R.id.pop);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
