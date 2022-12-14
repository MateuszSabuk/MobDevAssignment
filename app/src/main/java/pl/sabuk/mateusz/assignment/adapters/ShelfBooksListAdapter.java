package pl.sabuk.mateusz.assignment.adapters;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CheckBox;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.List;

        import pl.sabuk.mateusz.assignment.R;
        import pl.sabuk.mateusz.assignment.db.AppDatabase;
        import pl.sabuk.mateusz.assignment.db.Book;
        import pl.sabuk.mateusz.assignment.db.Shelf;

public class ShelfBooksListAdapter extends RecyclerView.Adapter<ShelfBooksListAdapter.ShelfBooksViewHolder> {

    private Context context;
    private List<Book> bookList;
    private OnBookListener mOnBookListener;
    private OnHoldListener mOnHoldListener;
    public ShelfBooksListAdapter(Context context, OnBookListener onBookListener, OnHoldListener onHoldListener) {
        this.context = context;
        this.mOnBookListener = onBookListener;
        this.mOnHoldListener = onHoldListener;
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
    public ShelfBooksListAdapter.ShelfBooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shelf_books_recycler, parent, false);
        return new ShelfBooksViewHolder(view, mOnBookListener, mOnHoldListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ShelfBooksListAdapter.ShelfBooksViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bookTitle.setText(this.bookList.get(position).title);
        holder.recyclerReadCheckBox.setChecked(this.bookList.get(position).isRead);

        holder.recyclerReadCheckBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = v.getContext();
                int duration = Toast.LENGTH_SHORT;

                CharSequence text = "Not read";
                if (holder.recyclerReadCheckBox.isChecked()){
                    text = "Read!";
                }

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                AppDatabase db = AppDatabase.getDbInstance(v.getContext());
                Book book = bookList.get(position);
                book.isRead = holder.recyclerReadCheckBox.isChecked();
                db.bookDao().updateBook(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.bookList.size();
    }

    public class ShelfBooksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView bookTitle;
        CheckBox recyclerReadCheckBox;
        OnBookListener onBookListener;
        OnHoldListener onHoldListener;

        public ShelfBooksViewHolder(View view, OnBookListener onBookListener, OnHoldListener onHoldListener){
            super(view);
            bookTitle = view.findViewById(R.id.recycler_book_on_shelf_title);
            recyclerReadCheckBox = view.findViewById(R.id.recycler_read_checkbox);
            this.onBookListener = onBookListener;
            this.onHoldListener = onHoldListener;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBookListener.onBookClick(getAbsoluteAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            return onHoldListener.onBookHold(getAbsoluteAdapterPosition());
        }
    }

    public interface OnBookListener{
        void onBookClick(int position);
    }

    public interface OnHoldListener {
        boolean onBookHold(int position);
    }
}
