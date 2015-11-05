package net.helpgod.mysqlitetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDBAdapter dbAdapter;

    ArrayList<SampleDto> result;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = (TextView) findViewById(R.id.tv_result);

        // DB Adapter
        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }

    public void mOnClick(View v){

        switch (v.getId()){
            case R.id.in_btn:
                dbAdapter.insertData();

                break;
            case R.id.out_btn:

                result = dbAdapter.getSelectAll();

                String str = "";
                for (int i = 0; i < result.size(); i++){
                    str = str + result.get(i).getId() + " " +  result.get(i).getKEY() + " " + result.get(i).getVALUE_01() + " " + result.get(i).getVALUE_02() + " " + result.get(i).getVALUE_03() + " " + result.get(i).getVALUE_04() + " " + result.get(i).getVALUE_05() + "\n";
                }

                tv_result.setText(str);

                break;
        }

    }
}
