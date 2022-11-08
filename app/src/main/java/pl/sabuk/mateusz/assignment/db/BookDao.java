package pl.sabuk.mateusz.assignment.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM Book")
    List<Book> getAllBooks();

    @Query("SELECT * FROM Book WHERE id = :id")
    Book getBookById(int id);

    @Insert
    void insertBook(Book... book);


    @Update
    void updateBook(Book... book);


    @Delete
    void delete(Book book);

}
