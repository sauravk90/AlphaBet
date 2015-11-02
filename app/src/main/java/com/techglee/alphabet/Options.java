package com.techglee.alphabet;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Options extends Activity implements View.OnClickListener {

    private Button setplayer_Button;
    private TextView player_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///// Set window fullscreen and remove title bar, and force portrit orientation //////
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //////////////////////end/////////////////////////

        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        setplayer_Button = (Button)findViewById(R.id.name_setbutton);
        player_view = (TextView)findViewById(R.id.name_view);
        setplayer_Button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v==setplayer_Button){
            //save in shared preference
        }
    }
}
