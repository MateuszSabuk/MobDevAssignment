package pl.sabuk.mateusz.assignment.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
public class Book {


    // TODO fix time warning
    public Book(){
        this.addTime = (new SimpleDateFormat("yyyy.MM.dd HH:mm")).format(new Timestamp(System.currentTimeMillis()));
    }


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "isbn")
    public String isbn;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "is_read")
    public boolean isRead;

    @ColumnInfo(name = "genre")
    public String genre;

    @ColumnInfo(name = "my_score")
    public Double myScore;

    @ColumnInfo(name = "add_time")
    public String addTime = "";

    @ColumnInfo(name = "description")
    public String description = "";

}

