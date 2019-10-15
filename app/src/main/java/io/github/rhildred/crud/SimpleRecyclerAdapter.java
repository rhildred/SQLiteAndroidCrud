package io.github.rhildred.crud;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
            for(int nId: SimpleRecyclerAdapter.this.to){
                this.aTexts.add( (TextView) view.findViewById(nId));
            }
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.resource,parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HashMap<String, String> map = this.data.get(position);
        for(int nPosition = 0; nPosition < this.from.length; nPosition++){
            String sKey = this.from[nPosition];
            ((MyViewHolder)holder).aTexts.get(nPosition).setText(map.get(sKey));
        }
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
