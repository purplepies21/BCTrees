package com.example.ash.bctrees;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class TreesAdapter extends BaseAdapter {
    private ArrayList<Tree> treeArrayList;
    private Context context;

    public TreesAdapter(ArrayList trees, Context c){
        treeArrayList=trees;
        context= c;

    }
    @Override
    public int getCount() {
        return treeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if(convertView==null){

            imageView=new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(500,500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }else{
            imageView=(ImageView)convertView;

        }
        imageView.setImageResource(treeArrayList.get(position).smallImage);

        return imageView;
    }
}
