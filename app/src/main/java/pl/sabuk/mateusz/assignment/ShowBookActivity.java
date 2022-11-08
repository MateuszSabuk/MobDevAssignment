package pl.sabuk.mateusz.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

        TextView titleText = (TextView) findViewById(R.id.bookTitleDisplay);
        titleText.setText(book.title);

        TextView authorText = (TextView) findViewById(R.id.bookAuthorDisplay);
        authorText.setText(book.author);

        TextView scoreText = (TextView) findViewById(R.id.bookScoreDisplay);
        String score = "Score: " + book.myScore.toString() + "/20";
        scoreText.setText(score);

        TextView readText = (TextView) findViewById(R.id.bookReadDisplay);
        String readBookText = "Book Read: " + book.isRead;
        readText.setText(readBookText);


        ImageView bookCoverView = (ImageView) findViewById(R.id.bookCoverImg);

        TextView ISBNTextDisplay = (TextView) findViewById(R.id.bookISBNDisplay);
        String ISBNtext = "ISBN Num: \n" + book.isbn;
        ISBNTextDisplay.setText(ISBNtext);



        TextView descriptionText = (TextView) findViewById(R.id.bookDescriptionDisplay);
        descriptionText.setText(book.description);

        Button backButton = (Button) findViewById(R.id.bookViewBackButton);
        backButton.setOnClickListener(view -> finish());

        TextView dateAddedText = (TextView) findViewById(R.id.dateAddedDisplay);
        String date = "Date Added: " + book.addTime;
        dateAddedText.setText(date);






    }
}