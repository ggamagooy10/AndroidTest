package com.example.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;

import java.util.Locale;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    ImageView handImageView;
    ImageView setHandImageView;
    AnimationDrawable animationDrawable;
    TextToSpeech textToSpeech;

    TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int i) {
            if(i != TextToSpeech.ERROR){
                textToSpeech.setLanguage(Locale.KOREAN);
                textToSpeech.setPitch(1.0f);
                textToSpeech.setSpeechRate(1.0f);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handImageView = findViewById(R.id.hand_anim_image_view);
        setHandImageView=findViewById(R.id.set_hand_image_view);
        handImageView.setVisibility(View.GONE);
        setHandImageView.setVisibility(View.VISIBLE);



        animationDrawable= (AnimationDrawable) handImageView.getDrawable();

        textToSpeech = new TextToSpeech(getApplicationContext(), onInitListener);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void button_click(View view) {
        switch (view.getId()){
            case R.id.replay_button:
               setHandImageView.setVisibility(View.GONE);
               handImageView.setVisibility(View.VISIBLE);
               animationDrawable.start();
               textToSpeech.speak("가위바위보",TextToSpeech.QUEUE_FLUSH,null,null);


               break;

            default:
                animationDrawable.stop();
                handImageView.setVisibility(View.GONE);
                setHandImageView.setVisibility(View.VISIBLE);
                setHandImageView.setImageResource(R.drawable.gawe);

                break;

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        textToSpeech.shutdown();
    }
}
