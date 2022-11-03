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
import pl.sabuk.mateusz.assignment.db.Shelf;

public class ShelfListAdapter extends RecyclerView.Adapter<ShelfListAdapter.MyViewHolder> {

    private Context context;
    private List<Shelf> shelfList;
    private OnShelfListener mOnShelfListener;
    public ShelfListAdapter(Context context, OnShelfListener onShelfListener) {
        this.context = context;
        this.mOnShelfListener = onShelfListener;
    }

    public Shelf getShelfListAt(int index){
        if (shelfList.size()>index && index >= 0){
            return shelfList.get(index);
        }
        return null;
    }

    public void setShelfList(List<Shelf> shelfList) {
        this.shelfList = shelfList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShelfListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shelf_recycler, parent, false);
        return new MyViewHolder(view, mOnShelfListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ShelfListAdapter.MyViewHolder holder, int position) {
        holder.shelfName.setText(this.shelfList.get(position).name);
        holder.numOfBooks.setText(String.valueOf(this.shelfList.get(position).numOfReadBooks) + " / "
                +String.valueOf(this.shelfList.get(position).numOfBooks));
    }

    @Override
    public int getItemCount() {
        return this.shelfList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView shelfName;
        TextView numOfBooks;
        OnShelfListener onShelfListener;

        public MyViewHolder(View view, OnShelfListener onShelfListener){
            super(view);
            shelfName = view.findViewById(R.id.recycler_shelf_name);
            numOfBooks = view.findViewById(R.id.recycler_num_of_books);
            this.onShelfListener = onShelfListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onShelfListener.onShelfClick(getAbsoluteAdapterPosition());
        }
    }

    public interface OnShelfListener{
        void onShelfClick(int position);
    }
}
