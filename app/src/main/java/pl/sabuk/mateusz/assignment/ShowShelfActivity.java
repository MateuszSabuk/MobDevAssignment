package pl.sabuk.mateusz.assignment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import pl.sabuk.mateusz.assignment.adapters.HistoryListAdapter;
import pl.sabuk.mateusz.assignment.adapters.ShelfBooksListAdapter;
import pl.sabuk.mateusz.assignment.adapters.ShelfListAdapter;
import pl.sabuk.mateusz.assignment.db.AppDatabase;
import pl.sabuk.mateusz.assignment.db.Book;
import pl.sabuk.mateusz.assignment.db.Shelf;

public class ShowShelfActivity extends AppCompatActivity implements ShelfBooksListAdapter.OnBookListener {
    private ShelfBooksListAdapter shelfBooksListAdapter;
    private int shelfId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shelf);
        Intent intent = getIntent();
        shelfId = intent.getIntExtra("id", 0);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Shelf shelf = db.shelfDao().getShelfById(shelfId).get(0);

        TextView shelfName = (TextView) this.findViewById(R.id.show_shelf_name);
        shelfName.setText(shelf.name);

        initRecyclerView();
        updateRecyclerView();
    }
    @Override
    public void onResume() {
        super.onResume();
        updateRecyclerView();
    }


    public void updateRecyclerView(){
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        // Get all books from database
        List<Book> bookList = db.bookDao().getAllBooks();

        if (shelfId == 1) {
            for (int i = bookList.size()-1; i >= 0 ; i--) {
                if(bookList.get(i).getShelves().length > 0){
                    bookList.remove(i);
                }
            }
        } else {
            for (int i = bookList.size()-1; i >= 0 ; i--) {
                if(!bookList.get(i).isInShelf(shelfId)){
                    bookList.remove(i);
                }
            }
        }


        // Reverse so new are on top on the bottom
        Collections.reverse(bookList);
        shelfBooksListAdapter.setShelfList(bookList);
    }

    private void initRecyclerView() {
        RecyclerView shelvesView = this.findViewById(R.id.show_shelf_recycler_view);
        shelvesView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration( this, DividerItemDecoration.VERTICAL);
        shelvesView.addItemDecoration((dividerItemDecoration));

        shelfBooksListAdapter = new ShelfBooksListAdapter( this, this);
        shelvesView.setAdapter(shelfBooksListAdapter);
    }

    @Override
    public void onBookClick(int position) {
        Intent intent = new Intent(this, ShowBookActivity.class);
        int id = this.shelfBooksListAdapter.getBookListAt(position).id;
        intent.putExtra("id", id);
        startActivity(intent);
    }
}