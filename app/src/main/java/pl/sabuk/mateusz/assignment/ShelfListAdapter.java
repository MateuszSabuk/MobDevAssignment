package pl.sabuk.mateusz.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.sabuk.mateusz.assignment.db.Shelf;

public class ShelfListAdapter extends RecyclerView.Adapter<ShelfListAdapter.MyViewHolder> {

    private Context context;
    private List<Shelf> shelfList;
    public ShelfListAdapter(Context context) {
        this.context = context;
    }


    public void setShelfList(List<Shelf> shelfList) {
        this.shelfList = shelfList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShelfListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shelf_recycler, parent, false);
        return new MyViewHolder(view);
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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView shelfName;
        TextView numOfBooks;
        public MyViewHolder(View view){
            super(view);
            shelfName = view.findViewById(R.id.shelf_name);
            numOfBooks = view.findViewById(R.id.num_of_books);

        }
    }
}
