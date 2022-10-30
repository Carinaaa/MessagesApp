package room.myapplication;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "MESSAGES_TABLE")
public class MessagesTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "text")
    private String text;

//getters and setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
