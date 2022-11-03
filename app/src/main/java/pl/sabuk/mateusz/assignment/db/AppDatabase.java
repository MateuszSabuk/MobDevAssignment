package pl.sabuk.mateusz.assignment.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import pl.sabuk.mateusz.assignment.R;

@Database(entities = {Shelf.class, Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ShelfDao shelfDao();
    public abstract BookDao bookDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MLL_DB")
                    .allowMainThreadQueries().build();
            if(INSTANCE.shelfDao().getAllShelves().size() == 0) {
                Shelf shelf = new Shelf();
                shelf.id = 1;
                shelf.name = context.getString(R.string.unlisted_books);
                INSTANCE.shelfDao().insertShelf(shelf);
            }
        }
        return INSTANCE;
    }
}
