package com.dongnao.ui.master.flowlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FlowLayoutView mFlowLayoutView;

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
    }
}
