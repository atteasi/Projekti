package fi.asikainen.kalori;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * @Author Atte Asikainen
 */
/**
 * This activity makes sure the Fragment Container displays the desired fragment with help from the BottomNavigationView
 *
 */

public class MainActivity extends AppCompatActivity {
    /**
     * The onCreate method that gets rid of the title bar and sets up the BottomNavigationView
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Gets rid of the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        //Sets up the BottomNavigationView
        BottomNavigationView bottomNavigator = findViewById(R.id.bottom_navigation);
        bottomNavigator.setOnNavigationItemSelectedListener(listener);
        bottomNavigator.setSelectedItemId(R.id.nav_home);
        //Makes the selection be HomeFragment on startup
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    //Setting up the method where you can switch between fragments with the BottomNavigationView
    private BottomNavigationView.OnNavigationItemSelectedListener listener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                /**
                 * The method that handles the switching of fragments when selecting different items on the BottomNavigationView
                 */
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selection = null;
                    // Assigns a value to the selection- variable based on the selection of item in the BottomNavigationView
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selection = new HomeFragment();
                            break;

                        case R.id.nav_graphs:
                            selection = new GraphFragment();
                            break;

                        case R.id.nav_mood:
                            selection = new BMIFragment();
                            break;

                        case R.id.nav_kalori_minus:
                            selection = new KaloriMinusFragment();
                            break;

                        case R.id.nav_kalori_plus:
                            selection = new KaloriPlusFragment();
                            break;
                    }
                    // Switches the fragment shown based on user selection
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selection).commit();

                    return true;
                }
            };
}