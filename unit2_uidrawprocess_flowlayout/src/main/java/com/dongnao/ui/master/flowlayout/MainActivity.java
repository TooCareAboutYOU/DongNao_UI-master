package com.dongnao.ui.master.flowlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FlowLayoutView mFlowLayoutView;

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlowLayoutView= (FlowLayoutView) findViewById(R.id.flowlayout);
        mFlowLayoutView.setOnItemClickListener(new FlowLayoutView.OnItemClickListener() {
            @Override
            public void onItemListener(View view, int position) {
                Toast.makeText(MainActivity.this, "点击了第" + position+"个", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_addView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FlowLayoutView.LayoutParams layoutParams=new FlowLayoutView.LayoutParams(FlowLayoutView.LayoutParams.MATCH_PARENT,FlowLayoutView.LayoutParams.MATCH_PARENT);
//                ImageView img=new ImageView(MainActivity.this);
//                img.setImageResource(R.drawable.img_four);
//                img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                mFlowLayoutView.addView(img,layoutParams);
            }
        });

    }
}
