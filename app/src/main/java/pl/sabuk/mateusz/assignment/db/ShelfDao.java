package pl.sabuk.mateusz.assignment.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShelfDao {

    @Query("SELECT * FROM Shelf")
    List<Shelf> getAllShelves();

    @Query("SELECT * FROM Shelf WHERE id = :id")
    List<Shelf> getShelfById(int id);

    @Insert
    long [] insertShelf(Shelf... shelf);

    @Delete
    void delete(Shelf shelf);

}
