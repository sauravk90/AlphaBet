package com.techglee.alphabet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GameOver extends Activity implements View.OnClickListener {

    private Button quit_Button;
    private Button hiscore_Button;
    private Button restart_Button;
    private static final String INSERT_URL = "http://www.lordofgym.com/AlphaBet/addscore.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///// Set window fullscreen and remove title bar, and force portrit orientation //////
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //////////////////////end/////////////////////////

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        String score = getIntent().getStringExtra("score").toString();
        String name = "Bunty";
        quit_Button =(Button)findViewById(R.id.quit_button);
        hiscore_Button =(Button)findViewById(R.id.score_button);
        restart_Button =(Button)findViewById(R.id.restart_button);
        quit_Button.setOnClickListener(this);
        hiscore_Button.setOnClickListener(this);
        restart_Button.setOnClickListener(this);
        if(! score.equalsIgnoreCase("0")) {
            addScore(score, name);
        }
    }

    public void addScore(String score, String name){
        String urlSuffix = "?name="+name+"&score="+score;
        class AddScore extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GameOver.this, "Inserting your score",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(INSERT_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;

                }catch(Exception e){
                    return null;
                }
            }
        }

        AddScore ru = new AddScore();
        ru.execute(urlSuffix);



    }


    @Override
    public void onClick(View v) {
        if(v==quit_Button){
            System.exit(0);
        }
        else if(v==hiscore_Button){
            Intent intent = new Intent(v.getContext(),Score.class);
            startActivity(intent);
        }
        else if(v==restart_Button){
            Intent intent = new Intent(v.getContext(),MainActivity.class);
            startActivity(intent);
        }


    }


}
