package pl.sabuk.mateusz.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import pl.sabuk.mateusz.assignment.fragments.HistoryFragment;
import pl.sabuk.mateusz.assignment.fragments.ScannerFragment;
import pl.sabuk.mateusz.assignment.fragments.ShelvesFragment;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    ShelvesFragment shelvesFragment = new ShelvesFragment();
    ScannerFragment scannerFragment = new ScannerFragment();
    HistoryFragment historyFragment = new HistoryFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,shelvesFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.shelves:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,shelvesFragment).commit();
                        return true;
                    case R.id.scanner:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, scannerFragment).commit();
                        return true;
                    case R.id.history:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,historyFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }


}