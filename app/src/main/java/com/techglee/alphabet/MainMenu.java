package com.techglee.alphabet;

        import android.app.Activity;
        import android.content.Intent;
        import android.content.pm.ActivityInfo;
        import android.os.Bundle;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.Button;

public class MainMenu extends Activity implements View.OnClickListener {

    private Button play_button;
    private Button score_button;
    private Button options_button;
    private Button exit_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///// Set window fullscreen and remove title bar, and force portrit orientation //////
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //////////////////////end/////////////////////////

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        play_button = (Button)findViewById(R.id.play_view);
        score_button = (Button)findViewById(R.id.score_view);
        options_button = (Button)findViewById(R.id.options_view);
        exit_button = (Button)findViewById(R.id.exit_view);
        play_button.setOnClickListener(this);
        score_button.setOnClickListener(this);
        options_button.setOnClickListener(this);
        exit_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==play_button){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            //start activity Level
        }
        else if(v==score_button){
            Intent intent = new Intent(this,Score.class);
            startActivity(intent);
            //start activity leaderboard
        }
        else if(v==options_button){
            //start activity options
            Intent intent = new Intent(this,Options.class);
            startActivity(intent);
        }
        else if(v==exit_button){
            //Exit application
            System.exit(0);

            }
    }


}
