package com.techglee.alphabet;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.util.DisplayMetrics;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Level3 extends Activity implements View.OnClickListener {

    //public String input = "SOM";
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button startB;
    public TextView text,scoreText,timerText;
    private static long NUMBER_MILLIS = 20000;
    private static final String MILLISECONDS_FORMAT = "%d";
    private int secondsLeft = 0;
    public String input;
    public int len;
    private TextView inputText;
    private TextView userText;
    private TextView test1;
    private TextView test2;
    //Button [] buttons = new Button[count];
    private Button but1;
    private Button but2;
    private Button but3;
    private Button but4;
    private Button but5;
    private Button but6;
    ArrayList numbers = new ArrayList();
    Random rand=new Random();
    Random randWord=new Random();
    int[] randNo = new int[1725];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///// Set window fullscreen and remove title bar, and force portrit orientation //////
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //////////////////////end/////////////////////////
        String timeLeft = getIntent().getStringExtra("timeLeft").toString();
        NUMBER_MILLIS = Long.parseLong(timeLeft)*1000;
        ArrayList<String> arrayWord= new ArrayList<String>();
        //String wordList[] = new String[30];
        String s1 ="EXTRME";
        String s2 ="AMAZIG";
        String s3 ="WONRFUL";
        arrayWord.add(s1);
        arrayWord.add(s2);
        arrayWord.add(s3);
        int randomInt = randWord.nextInt(3);
        String wordToDisplay = arrayWord.get(randomInt);
        input = wordToDisplay;
        int count = input.length();
        Button [] buttons = new Button[count];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreText = (TextView) this.findViewById(R.id.scoreView);
        timerText = (TextView) this.findViewById(R.id.timerView);
        inputText = (TextView)findViewById(R.id.inputText);
        userText = (TextView)findViewById(R.id.userText);
        test1 = (TextView)findViewById(R.id.textView);
        test2 = (TextView)findViewById(R.id.textView2);
        countDownTimer = new MyCountDownTimer(NUMBER_MILLIS, 1);
        countDownTimer.start();

        inputText.setText(input);
        userText.setText("");

        int MaxH=1725;
        int MinH=0;
        int MaxW=970;
        int MinW=0;

        for (int i = 0; i < count; i++) {
            buttons[i] = new Button(this);
            RelativeLayout ll = (RelativeLayout)findViewById(R.id.maincontainer);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            //Random
            //buttons[i].setY((int)(Math.random() * ( MaxH - MinH)));
            //buttons[i].setX( (int)(Math.random() * ( MaxW - MinW )));

            // for (int l=MinH ; l< MaxH;l++) {
            int ygenerated = rand.nextInt(1725) + 1;
            if(i==0)
            {
                randNo[0] = ygenerated;
                numbers.add(randNo[0]);
                buttons[i].setY((int)(Math.random() * ( MaxH - MinH)));
            }
            else
            {
                while(numbers.contains(new Integer(ygenerated)))
                {
                    ygenerated = rand.nextInt(1725) + 1;
                }
                randNo[i] = ygenerated;
                numbers.add(randNo[i]);
                buttons[i].setY((int)(Math.random() * ( MaxH - MinH)));
            }



            buttons[i].setX( (int)(Math.random() * ( MaxW - MinW )));

            String temp = Character.toString(input.charAt(i));
            buttons[i].setText(temp);
            buttons[i].setOnClickListener(this);
            buttons[i].setId(i);
            test1.setText(Integer.toString(buttons[i].getId()));

            ll.addView(buttons[i],lp);

            ViewGroup.LayoutParams params=buttons[i].getLayoutParams();
            params.width=110;
            params.height=120;
            buttons[i].setLayoutParams(params);
        }

        if(count==1){
            but1 = (Button)findViewById(buttons[0].getId());
        }
        else if(count==2){
            but1 = (Button)findViewById(buttons[0].getId());
            but2 = (Button)findViewById(buttons[1].getId());
        }
        else if(count==3){
            but1 = (Button)findViewById(buttons[0].getId());
            but2 = (Button)findViewById(buttons[1].getId());
            but3 = (Button)findViewById(buttons[2].getId());
        }
        else if(count==4){
            but1 = (Button)findViewById(buttons[0].getId());
            but2 = (Button)findViewById(buttons[1].getId());
            but3 = (Button)findViewById(buttons[2].getId());
            but4 = (Button)findViewById(buttons[3].getId());
        }
        else if(count==5){
            but1 = (Button)findViewById(buttons[0].getId());
            but2 = (Button)findViewById(buttons[1].getId());
            but3 = (Button)findViewById(buttons[2].getId());
            but4 = (Button)findViewById(buttons[3].getId());
            but5 = (Button)findViewById(buttons[4].getId());
        }
        else if(count==6){
            but1 = (Button)findViewById(buttons[0].getId());
            but2 = (Button)findViewById(buttons[1].getId());
            but3 = (Button)findViewById(buttons[2].getId());
            but4 = (Button)findViewById(buttons[3].getId());
            but5 = (Button)findViewById(buttons[4].getId());
            but6 = (Button)findViewById(buttons[5].getId());
        }
    }

    ///////////////////////////////////////
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        updateSizeInfo();
    }
    private void updateSizeInfo() {
        RelativeLayout rl_cards_details_card_area = (RelativeLayout) findViewById(R.id.maincontainer);
        int w = rl_cards_details_card_area.getWidth();
        int h = rl_cards_details_card_area.getHeight();
        Log.v("Saurav", "get height --> " +w+"-"+h);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        displaymetrics = this.getBaseContext().getResources().getDisplayMetrics();
        double dpwidth = (w/displaymetrics.density)+0.5;
        Log.v("Saurav","dpwidth is --> "+dpwidth);
        double dpheight = (h/displaymetrics.density)+0.5;
        Log.v("Saurav","dpheight is --> "+dpheight);
        Log.v("Saurav","pxhieght===>"+pxToDp(h));
    }
    //////////////////////////////////////
    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = this.getBaseContext().getResources().getDisplayMetrics();
        //getContext().getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }
    @Override
    public void onClick(View v) {
        if(v==but1 ){
            test2.setText("button 1 was clicked");
            userText.setText(userText.getText().toString() + Character.toString(input.charAt(0)));
            checkCompletion(v);
        }

        else if(v==but2){
            test2.setText("button 2 was clicked");
            userText.setText(userText.getText().toString() + Character.toString(input.charAt(1)));
            checkCompletion(v);
        }

        else if(v==but3){
            test2.setText("button 3 was clicked");
            userText.setText(userText.getText().toString() + Character.toString(input.charAt(2)));
            checkCompletion(v);
        }

        else if(v==but4){
            test2.setText("button 4 was clicked");
            userText.setText(userText.getText().toString() + Character.toString(input.charAt(3)));
            checkCompletion(v);
        }
        else if(v==but5){
            test2.setText("button 5 was clicked");
            userText.setText(userText.getText().toString() + Character.toString(input.charAt(4)));
            checkCompletion(v);
        }
        else if(v==but6){
            test2.setText("button 6 was clicked");
            userText.setText(userText.getText().toString() + Character.toString(input.charAt(5)));
            checkCompletion(v);
        }
    }

    public void checkCompletion(View v){
        if (input.equalsIgnoreCase(userText.getText().toString())) {
            //go to game over activity

            //Intent intent = new Intent(v.getContext(),Score.class);
            //startActivity(intent);


            Intent intent = new Intent(v.getContext(),GameOver.class);
            intent.putExtra("score",scoreText.getText().toString());
            startActivity(intent);

            userText.setText("Your final score is :" + scoreText.getText().toString());


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Class defined for Countdown Timer
    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            scoreText.setText("Time's up!");
            // text2.setText(Long.toString(millisUntilFinished / 1000));

        }


        @Override
        public void onTick(long millisUntilFinished) {
            // text.setText("" + millisUntilFinished / 1000);
            if (Math.round((float) millisUntilFinished / 1000.0f) != secondsLeft) {
                secondsLeft = Math.round((float) millisUntilFinished / 1000.0f);
                // scoreText.setText(String.valueOf(String.format(MILLISECONDS_FORMAT, millisUntilFinished % 10)));
                scoreText.setText(String.valueOf(secondsLeft));
            }
            long roundMillis = secondsLeft * 1000;
            if (roundMillis == NUMBER_MILLIS) {
                timerText.setText(secondsLeft
                        + "." + String.format(MILLISECONDS_FORMAT, 0));
                //text2.setText(Long.toString(millisUntilFinished/1000));
            } else {
                timerText.setText(secondsLeft
                        + "." + String.format(MILLISECONDS_FORMAT, millisUntilFinished % 10));

            }

        }

    }



}
