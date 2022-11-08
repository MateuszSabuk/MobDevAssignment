package pl.sabuk.mateusz.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Bundle extra = getIntent().getExtras();

        TextView isbnText = (TextView) findViewById(R.id.bookISBMInput);
        EditText bookTitle = findViewById(R.id.bookNameInput);
        EditText bookAuthor = findViewById(R.id.bookAuthorInput);
        EditText bookDescription = findViewById(R.id.bookDescriptionInput);

        if (extra != null) {
            String numISBNAPI = extra.getString("ISBN");
            isbnText.setText(numISBNAPI);
            String titleAPI = extra.getString("Title");
            bookTitle.setText(titleAPI);
            String authorAPI = extra.getString("Author");
            bookAuthor.setText(authorAPI);
            String descriptionAPI = extra.getString("Description");
            bookDescription.setText(descriptionAPI);

        }else  isbnText.setText("0");



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

        RadioButton isReadRadioBtn = (RadioButton) findViewById(R.id.bookIsRead);
        boolean isReadInput = isReadRadioBtn.isActivated();


        //Onclick function to show how to add books
        Button addBookButton = (Button) this.findViewById(R.id.newBook);
        Boolean finalIsReadInput = isReadInput;
        addBookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Book book = new Book();


                book.isbn = isbnText.getText().toString();
                book.title = bookTitle.getText().toString();
                book.author = bookAuthor.getText().toString();
                book.myScore = Double.valueOf(scoreText.getText().toString());
                book.genre = genreInput.getSelectedItem().toString();
                book.isRead = finalIsReadInput;
                book.description = bookDescription.getText().toString() ;

                // Adding book to the database
                AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
                db.bookDao().insertBook(book);
                finish();
            }
        });
    }

}