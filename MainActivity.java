package com.example.ash.bctrees;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TreesAdapter treeAdapter;
        final ArrayList<Tree> treeArrayList= new ArrayList<Tree>();
    GridView gridView=findViewById(R.id.tree_grid);
    try{
        JSONObject trees= new JSONObject(loadJsonFromAsset("trees"));
        JSONArray treeArray= trees.getJSONArray("trees");


        if(treeArray==null){
            Log.v("potato", "cannot init tree array");

        }else{
        for(int i= 0; i<treeArray.length();i++){
            JSONObject tree= treeArray.getJSONObject(i);

            Log.v("potato", "got Tree: "+tree.getString("common_name"));
            Tree singleTree= new Tree();
            singleTree.commonName=tree.getString("common_name");
            singleTree.scientificName= tree.getString("common_name");
            singleTree.largeImage=getApplicationContext().getResources()
                    .getIdentifier(tree.getString("image")+"_big","drawable",
                            getApplicationContext().getPackageName());
            singleTree.smallImage=getApplicationContext().getResources()
                    .getIdentifier(tree.getString("image")+"_small","drawable",
                            getApplicationContext().getPackageName());

            treeArrayList.add(singleTree);


        }}
    }catch (JSONException e){
        e.printStackTrace();

    }

    Log.v("potato", "Size of tree arraylist is: "+ treeArrayList.size());
    treeAdapter=new TreesAdapter(treeArrayList, getApplicationContext());
        gridView.setAdapter(treeAdapter);
gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"I am " +treeArrayList.get(position).scientificName, Toast.LENGTH_LONG).show();
    }
});

    }
    public String loadJsonFromAsset(String filename){
     String json="";

       try {
           InputStream is = getAssets().open(filename +".json");
           int size =is.available();
           byte[] buffer=new byte[size];
           is.read(buffer);
           is.close();
           json= new String(buffer, "UTF-8");
       }catch (IOException e){e.printStackTrace();
       return  null;}return json;
    }
}
