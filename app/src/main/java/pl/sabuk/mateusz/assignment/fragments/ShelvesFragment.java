package pl.sabuk.mateusz.assignment.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Collections;
import java.util.List;

import pl.sabuk.mateusz.assignment.AddShelfActivity;
import pl.sabuk.mateusz.assignment.R;
import pl.sabuk.mateusz.assignment.ShowShelfActivity;
import pl.sabuk.mateusz.assignment.adapters.ShelfListAdapter;
import pl.sabuk.mateusz.assignment.db.AppDatabase;
import pl.sabuk.mateusz.assignment.db.Book;
import pl.sabuk.mateusz.assignment.db.Shelf;

public class ShelvesFragment extends Fragment implements ShelfListAdapter.OnShelfListener {
    private ShelfListAdapter shelfListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shelves, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateRecyclerView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Button addShelfButton = (Button) getView().findViewById(R.id.newShelf);
        addShelfButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddShelfActivity.class);
                startActivity(intent);
            }
        });
        initRecyclerView();
        updateRecyclerView();
    }

    public void updateRecyclerView(){
        AppDatabase db = AppDatabase.getDbInstance(getActivity().getApplicationContext());
        // Get all shelves from database
        List<Shelf> shelfList = db.shelfDao().getAllShelves();
        List<Book> bookList = db.bookDao().getAllBooks();
        // Populate shelves with books

        ////////////////////////////////////////
        for (int j = bookList.size()-1; j >= 0 ; j--) {
            boolean isOnShelf = false;
            for (int i = shelfList.size()-1; i > 0 ; i--) {
                if (shelfList.get(i).hasBook(bookList.get(j).id)){
                    isOnShelf = true;
                }
            }
            if (!isOnShelf && shelfList.get(0).id == 1) {
                shelfList.get(0).addBook(bookList.get(j).id);
            }
        }
        ///////////////////////////////////////
        // If no unlisted books then don't show the "unlisted" shelf
        if (shelfList.get(0).id == 1 && shelfList.get(0).getBooks().size() == 0) {
            shelfList.remove(0);
        }
        // Reverse so new are on top and unlisted books on the bottom
        Collections.reverse(shelfList);
        shelfListAdapter.setShelfList(shelfList);
    }

    private void initRecyclerView() {
        RecyclerView shelvesView = getView().findViewById(R.id.shelvesView);
        shelvesView.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration( getContext(), DividerItemDecoration.VERTICAL);
        shelvesView.addItemDecoration((dividerItemDecoration));

        shelfListAdapter = new ShelfListAdapter(getContext(), this);
        shelvesView.setAdapter(shelfListAdapter);
    }

    @Override
    public void onShelfClick(int position) {
        Intent intent = new Intent(getActivity(), ShowShelfActivity.class);
        int id = this.shelfListAdapter.getShelfListAt(position).id;
        intent.putExtra("id", id);
        startActivity(intent);
    }
}