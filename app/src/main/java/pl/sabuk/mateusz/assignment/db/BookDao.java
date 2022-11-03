package pl.sabuk.mateusz.assignment.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM Book")
    List<Book> getAllBooks();

    @Insert
    void insertBook(Book... book);

    @Delete
    void delete(Book book);

}
