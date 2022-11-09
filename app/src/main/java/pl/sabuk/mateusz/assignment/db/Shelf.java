package pl.sabuk.mateusz.assignment.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class Shelf {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "book_ids")
    protected String bookIds = "{}";



    public boolean hasBook(int bookId){
        String[] ids = this.bookIds.substring(1, this.bookIds.length() - 1).split(",");
        for(int i = 0; i < ids.length;i++){
            if (ids[i] == "" || ids[i].isEmpty() || ids[i].length() == 0) continue;
            if (Integer.parseInt(ids[i]) == bookId){
                return true;
            }
        }
        return false;
    }

    public List<Integer> getBooks(){
        List<Integer> bookList = new ArrayList<>();
        String[] idsStrings = (this.bookIds.substring(1, this.bookIds.length() - 1)).split(",");
        if (idsStrings.length == 1) if (idsStrings[0] == "") return bookList;

        for(int i = 0; i < idsStrings.length;i++){
            if (idsStrings[i] == "" || idsStrings[i].isEmpty() || idsStrings[i].length() == 0) continue;
            bookList.add(Integer.parseInt(idsStrings[i]));
        }
        return bookList;
    }

    public void addBook(int bookId){

        if (this.hasBook(bookId)) return;
        String val = String.valueOf(bookId);
        if (this.bookIds != "{}") val = "," + val;
        this.bookIds = this.bookIds.substring(0, this.bookIds.length() - 1) + val + "}";
    }

    public void removeBook(int bookId){
        String[] idStrings = this.bookIds.substring(1, this.bookIds.length() - 1).split(",");
        List<Integer> ids = new ArrayList<>();

        for(int i = 0; i < idStrings.length;i++){
            if (idStrings[i] == "" || idStrings[i].isEmpty() || idStrings[i].length() == 0) continue;
            ids.add(Integer.parseInt(idStrings[i]));
        }
        // Deletion of duplicates just in case
        ids = new ArrayList<>( new HashSet<>(ids));
        ids.remove((Integer) bookId);

        String str;
        if (ids.size()==0){
            this.bookIds = "{}";
            return;
        }
        str = ids.get(0).toString();

        for ( int i = 1; i < ids.size();i++) {
            str += "," + ids.get(i);
        }
        if (str.charAt(0) == ','){
            str = str.substring(1,str.length());
        }

        this.bookIds = "{" + str + "}";
    }
}
