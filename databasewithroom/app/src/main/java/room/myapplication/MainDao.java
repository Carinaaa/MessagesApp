package room.myapplication;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDao {

    // Insert query
    @Insert(onConflict = REPLACE)
    void insert(MessagesTable mainData);

    //Delete query
    @Delete
    void delete(MessagesTable mainData);

    // Delete all query
    @Delete
    void reset(List<MessagesTable> mainData);

    //Update query
    @Query("UPDATE MESSAGES_TABLE SET text = :sText WHERE ID = :sID")
    void update(int sID, String sText);

    // Get all
    @Query("SELECT * FROM MESSAGES_TABLE")
    List<MessagesTable> getAll();
}
