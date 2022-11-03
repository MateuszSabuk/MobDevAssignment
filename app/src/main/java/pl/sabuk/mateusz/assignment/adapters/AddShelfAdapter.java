package pl.sabuk.mateusz.assignment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.sabuk.mateusz.assignment.R;
import pl.sabuk.mateusz.assignment.db.Book;
import pl.sabuk.mateusz.assignment.db.Shelf;

public class AddShelfAdapter extends RecyclerView.Adapter<AddShelfAdapter.AddShelfViewHolder> {

    private Context context;
    private List<Book> bookList;
    public AddShelfAdapter(Context context) {
        this.context = context;
    }

    public void setShelfList(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    public List<Book> getBookList(){
        return bookList;
    }

    @NonNull
    @Override
    public AddShelfAdapter.AddShelfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shelf_books_recycler, parent, false);
        return new AddShelfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddShelfAdapter.AddShelfViewHolder holder, int position) {
        holder.bookTitle.setText(this.bookList.get(position).title);
        holder.recyclerReadCheckBox.setChecked(this.bookList.get(position).isRead);
    }

    @Override
    public int getItemCount() {
        return this.bookList.size();
    }

    public class AddShelfViewHolder extends RecyclerView.ViewHolder{
        TextView bookTitle;
        CheckBox recyclerReadCheckBox;
        public AddShelfViewHolder(View view){
            super(view);
            bookTitle = view.findViewById(R.id.recycler_book_on_shelf_title);
            recyclerReadCheckBox = view.findViewById(R.id.recycler_read_checkbox);
        }
    }
}
