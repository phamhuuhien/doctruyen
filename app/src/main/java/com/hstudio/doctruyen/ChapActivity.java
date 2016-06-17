package com.hstudio.doctruyen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hstudio.doctruyen.async.LoadChap;
import com.hstudio.doctruyen.object.Chap;

/**
 * Created by phhien on 6/16/2016.
 */
public class ChapActivity extends AppCompatActivity {

    private TextView title;
    private TextView data;
    private Button previous, next;
    private Chap mChap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chap);

        title = (TextView) findViewById(R.id.title);
        data = (TextView) findViewById(R.id.data);
        previous = (Button) findViewById(R.id.previous);
        next = (Button) findViewById(R.id.next);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadChap(ChapActivity.this).execute(mChap.getPrevious());
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadChap(ChapActivity.this).execute(mChap.getNext());
            }
        });

        String link = getIntent().getStringExtra("LINK");
        new LoadChap(this).execute(link);
    }

    public void setData(Chap chap) {
        mChap = chap;
        title.setText(chap.getTitle());
        data.setText(Html.fromHtml(chap.getData()));
        if("javascript:void(0)".equals(chap.getPrevious())) {
            previous.setEnabled(false);
        } else {
            previous.setEnabled(true);
        }

        if("chap.getPrevious()".equals(chap.getNext())) {
            next.setEnabled(false);
        } else {
            next.setEnabled(true);
        }
    }
}
