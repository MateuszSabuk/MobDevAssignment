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
import pl.sabuk.mateusz.assignment.adapters.HistoryListAdapter;
import pl.sabuk.mateusz.assignment.adapters.ShelfListAdapter;
import pl.sabuk.mateusz.assignment.db.AppDatabase;
import pl.sabuk.mateusz.assignment.db.Book;
import pl.sabuk.mateusz.assignment.db.Shelf;

public class HistoryFragment extends Fragment implements HistoryListAdapter.OnBookListener {
    private HistoryListAdapter historyListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateRecyclerView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initRecyclerView();
        updateRecyclerView();
    }

    public void updateRecyclerView(){
        AppDatabase db = AppDatabase.getDbInstance(getActivity().getApplicationContext());
        // Get all books
        List<Book> bookList = db.bookDao().getAllBooks();
        Collections.reverse(bookList);
        historyListAdapter.setShelfList(bookList);
    }

    private void initRecyclerView() {
        RecyclerView historyRecyclerView = getView().findViewById(R.id.history_recycler_view);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration( getContext(), DividerItemDecoration.VERTICAL);
        historyRecyclerView.addItemDecoration((dividerItemDecoration));

        historyListAdapter = new HistoryListAdapter(getContext(), this);
        historyRecyclerView.setAdapter(historyListAdapter);
    }

    @Override
    public void onBookClick(int position) {
        Intent intent = new Intent(getActivity(), ShowShelfActivity.class);
        int id = this.historyListAdapter.getBookListAt(position).id;
        intent.putExtra("id", id);
        startActivity(intent);
    }
}