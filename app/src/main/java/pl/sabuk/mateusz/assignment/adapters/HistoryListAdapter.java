package pl.sabuk.mateusz.assignment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.sabuk.mateusz.assignment.R;
import pl.sabuk.mateusz.assignment.db.Book;
import pl.sabuk.mateusz.assignment.db.Shelf;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder> {

    private Context context;
    private List<Book> bookList;
    public HistoryListAdapter(Context context) {
        this.context = context;
    }


    public void setShelfList(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryListAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_recycler, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListAdapter.HistoryViewHolder holder, int position) {
        holder.bookTitle.setText("" + this.bookList.get(position).title);
        holder.addTime.setText("Added: " + String.valueOf(this.bookList.get(position).addTime));
    }

    @Override
    public int getItemCount() {
        return this.bookList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        TextView bookTitle;
        TextView addTime;
        public HistoryViewHolder(View view){
            super(view);
            bookTitle = view.findViewById(R.id.recycler_book_title);
            addTime = view.findViewById(R.id.recycler_add_time);

        }
    }
}
