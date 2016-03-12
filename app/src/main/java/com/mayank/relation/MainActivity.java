package com.mayank.relation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends Activity {
    ListView list;
    String[] name = {
            "Mr. Cool Father",
            "Mrs. All Round Mom",
            "Mr. Younger Brother",
            "Ms. Crazy Sister",
            "Mr. Mighty GrandPa",
            "Mrs. Lovely GrandMa"
    } ;
    String[] relation = {
            "Father",
            "Mother",
            "Brother",
            "Sister",
            "GrandFather",
            "GrandMother"
    } ;
    Integer[] imageId = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CustomList adapter = new CustomList(MainActivity.this, name, relation, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent g = new Intent(MainActivity.this,MapsActivity.class);
                g.putExtra("KEY",String.valueOf(position));
                startActivity(g);
            }
        });
    }

}
