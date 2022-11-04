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
    public int isbn;

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

    @ColumnInfo(name = "shelf_ids")
    protected String shelfIds = "{}";


    public boolean isInShelf(int shelfId){
        String[] ids = this.shelfIds.substring(1, this.shelfIds.length() - 1).split(",");
        for(int i = 0; i < ids.length;i++){
            if (ids[i] == "") continue;
            if (Integer.parseInt(ids[i]) == shelfId){
                return true;
            }
        }
        return false;
    }

    public int[] getShelves(){
        String[] idsStrings = (this.shelfIds.substring(1, this.shelfIds.length() - 1)).split(",");
        if (idsStrings.length == 1) if (idsStrings[0] == "") return new int [0];
        int[] ids = new int[idsStrings.length];
        for(int i = 0; i < ids.length;i++){
            if (idsStrings[i] == "") continue;
            ids[i] = Integer.parseInt(idsStrings[i]);
        }
        return ids;
    }

    public void addToShelves(int[] shelfIds){
        String idsString = this.shelfIds.substring(1, this.shelfIds.length() - 1);
        String[] idsStrings = idsString.split(",");
        int[] ids = new int[idsStrings.length];
        for(int i = 0; i < ids.length;i++){
            if (idsStrings[i] == "") continue;
            ids[i] = Integer.parseInt(idsStrings[i]);
        }
        for (int i = 0; i < shelfIds.length; i++) {
            boolean addId = true;
            // Check all already assigned shelves
            for(int j = 0; j < ids.length; j++) {
                if (shelfIds[i] == ids[j]) {
                    addId = false;
                    break;
                }
            }
            // If not in the shelf yet
            if (addId) idsString+=","+shelfIds[i];
        }
        if (idsString.charAt(0) == ','){
            idsString = idsString.substring(1);
        }
        this.shelfIds = "{" + idsString + "}";
    }

    // TODO: Implement this
    public void removeFromShelves(int[] shelfIds){
        String[] idsStrings = this.shelfIds.substring(1, this.shelfIds.length() - 1).split(",");
        // TODO: Probably with a list would be easier glhf
    }
}

