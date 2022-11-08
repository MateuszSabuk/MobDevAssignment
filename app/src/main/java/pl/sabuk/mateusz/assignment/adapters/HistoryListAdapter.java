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
    private OnBookListener mOnBookListener;
    public HistoryListAdapter(Context context, OnBookListener onBookListener) {
        this.context = context;
        this.mOnBookListener = onBookListener;
    }


    public Book getBookListAt(int index){
        if (bookList.size()>index && index >= 0){
            return bookList.get(index);
        }
        return null;
    }

    public void setShelfList(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryListAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_recycler, parent, false);
        return new HistoryViewHolder(view, mOnBookListener);
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

    public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView bookTitle;
        TextView addTime;
        OnBookListener onBookListener;

        public HistoryViewHolder(View view, OnBookListener onBookListener){
            super(view);
            bookTitle = view.findViewById(R.id.recycler_book_title);
            addTime = view.findViewById(R.id.recycler_add_time);
            this.onBookListener = onBookListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBookListener.onBookClick(getAbsoluteAdapterPosition());
        }
    }

    public interface OnBookListener{
        void onBookClick(int position);
    }
}
