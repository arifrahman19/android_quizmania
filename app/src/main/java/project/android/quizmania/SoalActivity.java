package project.android.quizmania;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class SoalActivity extends AppCompatActivity  {

    private TextView question,answer1,answer2,answer3,answer4,random;
    private Button buttonGet,nextBtn;
    private TextView textMapel;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        question = (TextView) findViewById(R.id.question);
        answer1 = (TextView) findViewById(R.id.answer1);
        answer2 = (TextView) findViewById(R.id.answer2);
        answer3 = (TextView) findViewById(R.id.answer3);
        answer4 = (TextView) findViewById(R.id.answer4);
        textMapel=(TextView)findViewById(R.id.textViewMapel);
        nextBtn= (Button)findViewById(R.id.nextBtn);

        Random r=new Random();
        int i=r.nextInt(4-1)+1;



        getData(i);

        nextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(SoalActivity.this, MainActivity.class));
            }
        });

    }

    private void getData(int i) {

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String id=String.valueOf(i);
        String url = AppConfig.DATA_URL+id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SoalActivity.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String soal="";
        String jawaban1="";
        String jawaban2="";
        String jawaban3="";
        String jawaban4="";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(AppConfig.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            soal = collegeData.getString(AppConfig.KEY_SOAL);
            jawaban1 = collegeData.getString(AppConfig.KEY_JAWABAN1);
            jawaban2 = collegeData.getString(AppConfig.KEY_JAWABAN2);
            jawaban3 = collegeData.getString(AppConfig.KEY_JAWABAN3);
            jawaban4 = collegeData.getString(AppConfig.KEY_JAWABAN4);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        question.setText(soal);
        answer1.setText(jawaban1);
        answer2.setText(jawaban2);
        answer3.setText(jawaban3);
        answer4.setText(jawaban4);

        Intent intent=getIntent();
        int mapel=intent.getIntExtra("tingkatSekolah", 0);
        textMapel.setText(String.valueOf(mapel));

    }


}
