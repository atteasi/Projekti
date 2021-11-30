package fi.asikainen.kalori;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class SwipeRight extends AppCompatActivity {

    float xStart;
    float xEnd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.right_screen);
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                xStart = touchEvent.getX();
                break;
            case MotionEvent.ACTION_UP:
                xEnd = touchEvent.getX();
                if(xStart < xEnd){
                    Intent i = new Intent(SwipeRight.this, MainActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}
