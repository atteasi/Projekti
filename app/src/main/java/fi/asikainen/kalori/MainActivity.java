package fi.asikainen.kalori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    float xStart;
    float xEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                xStart = touchEvent.getX();
                break;
            case MotionEvent.ACTION_UP:
                xEnd = touchEvent.getX();
                if(xStart < xEnd){
                    Intent i = new Intent(MainActivity.this, SwipeLeft.class);
                    startActivity(i);
                }else if(xStart > xEnd){
                    Intent i = new Intent(MainActivity.this, SwipeRight.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}