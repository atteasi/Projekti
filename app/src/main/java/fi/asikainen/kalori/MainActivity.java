package fi.asikainen.kalori;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigator = findViewById(R.id.bottom_navigation);
        bottomNavigator.setOnNavigationItemSelectedListener(listener);
        bottomNavigator.setSelectedItemId(R.id.nav_home);

        bottomNavigator.setSelectedItemId(R.id.nav_home);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selection = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selection = new HomeFragment();
                            break;

                        case R.id.nav_graphs:
                            selection = new GraphFragment();
                            break;

                        case R.id.nav_mood:
                            selection = new MoodFragment();
                            break;

                        case R.id.nav_kalori_minus:
                            selection = new KaloriMinusFragment();
                            break;

                        case R.id.nav_kalori_plus:
                            selection = new KaloriPlusFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selection).commit();

                    return true;
                }
            };
}