package pl.sabuk.mateusz.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Collections;
import java.util.List;

import pl.sabuk.mateusz.assignment.adapters.AddShelfAdapter;
import pl.sabuk.mateusz.assignment.adapters.ShelfBooksListAdapter;
import pl.sabuk.mateusz.assignment.db.AppDatabase;
import pl.sabuk.mateusz.assignment.db.Book;
import pl.sabuk.mateusz.assignment.db.Shelf;

public class AddShelfActivity extends AppCompatActivity {
    private AddShelfAdapter addShelfAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shelf);
        Button addShelfButton = (Button) this.findViewById(R.id.newShelfButton);
        addShelfButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.newShelfEditText)).getText().toString();
                if(name == null || name.isEmpty() || name.trim().isEmpty()) {
                    finish();
                    return;
                }
                System.out.println(name);
                name = name.replace("\n", " ").replace("\r", "");
                System.out.println(name);

                Shelf shelf = new Shelf();
                shelf.name = name;

                AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
                List<Integer> selectedBooks = addShelfAdapter.getSelectedBooksIds(v);
                List<Book> bookList = db.bookDao().getAllBooks();
                long shelfId = db.shelfDao().insertShelf(shelf)[0];
                for (int i = 0; i < bookList.size(); i++){
                    if (selectedBooks.contains(bookList.get(i).id)){
                        bookList.get(i).addToShelves(new int[] {(int)shelfId});
                        db.bookDao().updateBook(bookList.get(i));
                    }
                }


                finish();
            }
        });
        initRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initRecyclerView() {
        RecyclerView shelvesView = this.findViewById(R.id.add_books_recycler_view);
        shelvesView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration( this, DividerItemDecoration.VERTICAL);
        shelvesView.addItemDecoration((dividerItemDecoration));

        addShelfAdapter = new AddShelfAdapter( this);
        shelvesView.setAdapter(addShelfAdapter);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        // Get all books from database
        List<Book> bookList = db.bookDao().getAllBooks();

        for (int i = bookList.size()-1; i >= 0 ; i--) {
            bookList.get(i).isRead = false;
        }

        // Reverse so new are on top on the bottom
        Collections.reverse(bookList);
        addShelfAdapter.setShelfList(bookList);
    }
}