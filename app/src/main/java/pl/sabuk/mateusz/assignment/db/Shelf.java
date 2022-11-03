package pl.sabuk.mateusz.assignment.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Shelf {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @Ignore
    public int numOfBooks;
    @Ignore
    public int numOfReadBooks;
}
