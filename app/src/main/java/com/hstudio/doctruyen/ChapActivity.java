package com.hstudio.doctruyen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hstudio.doctruyen.async.LoadChap;
import com.hstudio.doctruyen.object.Chap;

/**
 * Created by phhien on 6/16/2016.
 */
public class ChapActivity extends AppCompatActivity {

    private TextView title;
    private TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chap);

        title = (TextView) findViewById(R.id.title);
        data = (TextView) findViewById(R.id.data);

        String link = getIntent().getStringExtra("LINK");
        new LoadChap(this).execute(link);
    }

    public void setData(Chap chap) {
        title.setText(chap.getTitle());
        data.setText(chap.getData());
    }
}
