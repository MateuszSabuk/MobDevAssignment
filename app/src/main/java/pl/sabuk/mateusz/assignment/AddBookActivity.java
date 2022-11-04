package pl.sabuk.mateusz.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import pl.sabuk.mateusz.assignment.db.AppDatabase;
import pl.sabuk.mateusz.assignment.db.Book;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Bundle extra = getIntent().getExtras();

        TextView isbnText = (TextView) findViewById(R.id.bookISBMInput);

        if (extra != null) {
            String ISBNNum = extra.getString("ISBN");
            isbnText.setText(ISBNNum);
        }else  isbnText.setText("0000");


        EditText bookTitle = findViewById(R.id.bookNameInput);
        EditText bookAuthor = findViewById(R.id.bookAuthorInput);

        SeekBar scoreInput = (SeekBar) findViewById(R.id.bookScoreInput);
        TextView scoreText = findViewById(R.id.bookScoreValue);
        scoreText.setText("0");
        scoreInput.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String val = String.valueOf(i);
                scoreText.setText(val);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        Spinner genreInput = (Spinner) findViewById(R.id.bookGenreInput);
        ArrayAdapter<CharSequence> GenAdaptor = ArrayAdapter.createFromResource(this,
                R.array.genreTypes, android.R.layout.simple_spinner_item);
        GenAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreInput.setAdapter(GenAdaptor);

        RadioButton tets = (RadioButton) findViewById(R.id.bookIsRead);
        Boolean isReadInput = false;
        if (tets.isActivated()) isReadInput = true;





        //Onclick function to show how to add books
        Button addBookButton = (Button) this.findViewById(R.id.newBook);
        Boolean finalIsReadInput = isReadInput;
        addBookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Book book = new Book();

                book.isbn = Integer.getInteger(isbnText.getText().toString());
                book.title = bookTitle.getText().toString();
                book.author = bookAuthor.getText().toString();
                book.myScore = Double.valueOf(scoreText.getText().toString());
                book.genre = genreInput.getSelectedItem().toString();
                book.isRead = finalIsReadInput;

                book.addToShelves(new int[] {2,45,9,5}); // what?
                // Adding book to the database
                AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
                db.bookDao().insertBook(book);
                finish();
            }
        });
    }

}