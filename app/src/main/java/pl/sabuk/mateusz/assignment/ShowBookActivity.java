package pl.sabuk.mateusz.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import pl.sabuk.mateusz.assignment.db.AppDatabase;
import pl.sabuk.mateusz.assignment.db.Book;
import pl.sabuk.mateusz.assignment.db.Shelf;

public class ShowBookActivity extends AppCompatActivity {

    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);

        Intent intent = getIntent();
        bookId = intent.getIntExtra("id", 0);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Book book = db.bookDao().getBookById(bookId);

        //Shelf book = db.shelfDao().getShelfById(bookId);

    }
}