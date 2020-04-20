package com.example.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Locale;
import java.util.Queue;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView handImageView;
    ImageView setHandImageView;
    ImageButton gaweButton;
    ImageButton baweButton;
    ImageButton boButton;
    ImageButton replayButton;
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

        gaweButton = findViewById(R.id.gawe_button);
        baweButton = findViewById(R.id.bawe_button);
        boButton = findViewById(R.id.bo_button);
        replayButton = findViewById(R.id.replay_button);

       // handImageView.setVisibility(View.GONE);
        //setHandImageView.setVisibility(View.VISIBLE);



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
               voicePlay("가위바위보");
               replayButton.setEnabled(false);
                gaweButton.setEnabled(true);
                baweButton.setEnabled(true);
                boButton.setEnabled(true);

               break;
            case R.id.gawe_button:
            case R.id.bawe_button:
            case R.id.bo_button:
                replayButton.setEnabled(true);
                gaweButton.setEnabled(false);
                baweButton.setEnabled(false);
                boButton.setEnabled(false);
                animationDrawable.stop();
                handImageView.setVisibility(View.GONE);
                setHandImageView.setVisibility(View.VISIBLE);
                int getComHand = new Random().nextInt(3)+1;
                switch (getComHand){
                    case 1:
                        setHandImageView.setImageResource(R.drawable.gawe);
                        if(view.getId()== R.id.gawe_button){
                            voicePlay("비겼어요. 다시 시작하세요");
                        }else if(view.getId()==R.id.bawe_button){
                            voicePlay("당신이 이겼어요");
                        }else{
                            voicePlay("제가 이겼어요. ");
                        }
                        break;
                    case 2:
                        setHandImageView.setImageResource(R.drawable.bawe);
                        if(view.getId()== R.id.gawe_button){
                            voicePlay("제가 이겼어요. ");

                        }else if(view.getId()==R.id.bawe_button){
                            voicePlay("비겼어요. 다시 시작하세요");
                        }else{
                            voicePlay("당신이 이겼어요");

                        }
                        break;
                    case 3:
                        setHandImageView.setImageResource(R.drawable.bo);
                        if(view.getId()== R.id.gawe_button){

                            voicePlay("당신이 이겼어요");
                        }else if(view.getId()==R.id.bawe_button){
                            voicePlay("제가 이겼어요. ");
                        }else{

                            voicePlay("비겼어요. 다시 시작하세요");
                        }
                        break;
                }
            break;

            default:

                setHandImageView.setImageResource(R.drawable.gawe);

                break;

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        textToSpeech.shutdown();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void voicePlay(String voiceText){
        textToSpeech.speak(voiceText,TextToSpeech.QUEUE_FLUSH,null,null);
    }
}
