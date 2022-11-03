package pl.sabuk.mateusz.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pl.sabuk.mateusz.assignment.db.AppDatabase;
import pl.sabuk.mateusz.assignment.db.Shelf;

public class AddShelfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shelf);
        Button addShelfButton = (Button) this.findViewById(R.id.newShelfButton);
        addShelfButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.newShelfEditText)).getText().toString();
                // TODO: Sanitize more
                if(name.length()==0) {
                    finish();
                }
                //TODO: Checkboxes for the books
                Shelf shelf = new Shelf();
                shelf.name = name;

                AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
                db.shelfDao().insertShelf(shelf);

                finish();
            }
        });
    }
}