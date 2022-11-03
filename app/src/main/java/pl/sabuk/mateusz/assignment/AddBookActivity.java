package pl.sabuk.mateusz.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import pl.sabuk.mateusz.assignment.db.AppDatabase;
import pl.sabuk.mateusz.assignment.db.Book;
import pl.sabuk.mateusz.assignment.db.Shelf;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);


        //Onclick function to show how to add books
        Button addBookButton = (Button) this.findViewById(R.id.newBook);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Book book = new Book();

                // TODO: Check and sanitize inputs, you ll figure it out
                // Those two are just examples
                book.title = "What";
                book.addToShelves(new int[] {2,45,3,5});

                // Adding book to the database
                AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
                db.bookDao().insertBook(book);
                finish();
            }
        });
    }

}