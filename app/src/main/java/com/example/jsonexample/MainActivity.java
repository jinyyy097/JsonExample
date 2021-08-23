package com.example.jsonexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jsonexample.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private JSONObject jsonObject;
    private ActivityMainBinding binding;
    private final String packagename = "com.example.uiexample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        intent = getIntent();

        if(intent.getStringExtra("json") != null)
        {
            try {

                String s = intent.getStringExtra("json");
                System.out.println(s);
                jsonObject = new JSONObject(s);
                binding.nameview.setText(jsonObject.getString("BIZPLC_NM"));
                binding.Latedit.setText(jsonObject.getString("REFINE_WGS84_LAT"));
                binding.LngEdit.setText(jsonObject.getString("REFINE_WGS84_LOGT"));
                binding.addressview.setText(jsonObject.getString("REFINE_LOTNO_ADDR"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ComponentName cn = new ComponentName(packagename,packagename+".MainActivity");

                Intent intent2 = new Intent(Intent.ACTION_MAIN);

                intent2.addCategory(Intent.CATEGORY_LAUNCHER);

                intent2.setComponent(cn);

                try {
                    intent2.putExtra("modifydata",jsonObject.getString("BIZPLC_NM")+","+binding.Latedit.getText()+","+binding.LngEdit.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setResult(Activity.RESULT_OK,intent2);

                finish();
            }
        });

    }
}