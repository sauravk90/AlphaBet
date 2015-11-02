package com.techglee.alphabet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class Score extends Activity implements View.OnClickListener {

    private TextView playerNameView;
    private TextView playerScoreView;
    private static final String LEADERBOARD_URL = "http://www.lordofgym.com/AlphaBet/leaderboard.php";
    ArrayList<HashMap<String, String>> scorelist;
    private static final String TAG_1 = "player_name";
    private static final String TAG_2 = "score ";
    JSONArray scoreArray = null;
    private static final String TAG_RESULTS="result"; //from php code
    String myJSON;
    private ListView scoreListView;
    private Button local_Button;
    private Button global_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///// Set window fullscreen and remove title bar, and force portrit orientation //////
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //////////////////////end/////////////////////////
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);
        local_Button = (Button)findViewById(R.id.local_button);
        global_Button = (Button)findViewById(R.id.global_button);
        local_Button.setOnClickListener(this);
        global_Button.setOnClickListener(this);
           }

    @Override
    public void onClick(View v) {
        if(v==global_Button){
            displayLeaderboard(); //Show global leaderboard
        }
        else if(v==local_Button){
           //Show local leaderboard
        }
    }

    private void displayLeaderboard() {

        class GetUsers extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                try {
                    playerNameView = (TextView) findViewById(R.id.playerNameView);
                  //  playerScoreView = (TextView) findViewById(R.id.playerScoreView);
                    loading = ProgressDialog.show(Score.this, "Please Wait", null, true, true);
                }catch (Exception ex){
                    Log.d("Saurav",ex.getLocalizedMessage());
                }
            }

            @Override
            protected String doInBackground(String... params) {
                Log.d("Saurav", "inside doin background of all user list -->");
                ArrayList<String> User_Details = new ArrayList<String>();
               // String s = params[0];
                BufferedReader bufferedReader = null;
                String result = null;
                try {
                    URL url = new URL(LEADERBOARD_URL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                    Log.d("Saurav", result);
                    return result;
                } catch (Exception e) {
                    Log.d("Saurav",e.getLocalizedMessage());
                    return null;
                }
            }

          //  protected void onPostExecute(String s) {
          @Override
                protected void onPostExecute(String s) {
                loading.dismiss();
                myJSON=s;
                showDetails(myJSON);
            }
        }
        GetUsers u = new GetUsers();
        u.execute();
    }

    protected void showDetails(String jsonData){
        try {
            JSONObject jsonObj = new JSONObject(jsonData);
            scoreArray = jsonObj.getJSONArray(TAG_RESULTS);
            scorelist = new ArrayList<HashMap<String,String>>();

            for(int i=0;i<scoreArray.length();i++){
                Log.d("Saurav","array is "+scoreArray);
                Log.d("Saurav","value of i now is "+i);
                Log.d("Saurav","length of array "+scoreArray.length());
                JSONObject c = scoreArray.getJSONObject(i);
                String playername_fetched = c.getString(TAG_1);
                String score_fetched = c.getString(TAG_2);
                HashMap<String,String> persons = new HashMap<String,String>();
                persons.put(TAG_1,playername_fetched);
                persons.put(TAG_2,score_fetched);

                scorelist.add(persons);

                scoreListView = (ListView)findViewById(R.id.scoreListView);
                ListAdapter adapter = new SimpleAdapter(Score.this, scorelist,
                        R.layout.scorelistlayout,
                        new String[] { TAG_1, TAG_2 }, new int[] {
                        R.id.playerNameView, R.id.playerScoreView});

                scoreListView.setAdapter(adapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




}


