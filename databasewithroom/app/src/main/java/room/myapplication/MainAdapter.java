package room.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<MessagesTable> dataList;
    private Activity context;
    private RoomDB database;

    //constructor
    public MainAdapter(Activity context, List<MessagesTable> dataList)
    {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //init view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // init message data
        MessagesTable data = dataList.get(position);
        // init data
        database = RoomDB.getInstance(context);
        //set text on tet view
        holder.textView.setText(data.getText());

        holder.btEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                    // init message data
                    MessagesTable d = dataList.get(holder.getAdapterPosition());
                    // Get id
                    int sId = d.getID();
                    // Get text
                    String sText = d.getText();

                    // Create dialog
                    Dialog dialog = new Dialog(context);
                    // Set content view
                    dialog.setContentView(R.layout.dialog_update);
                    // init width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //init height
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                //Set layout
                dialog.getWindow().setLayout(width,height);
                //Show dialog
                dialog.show();

                // init and assign variable
                final EditText editText = dialog.findViewById(R.id.edit_text);
                Button btUpdate = dialog.findViewById(R.id.bt_update);

                //Set text on edit text
                editText.setText(sText);

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //dismiss dialog
                        dialog.dismiss();
                        //Get update text from edit text
                        String uText = editText.getText().toString().trim();
                        //Update text in database
                        database.mainDao().update(sId,uText);
                        //notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // init
                MessagesTable d = dataList.get(holder.getAdapterPosition());
                //delete text from db
                database.mainDao().delete(d);
                //notify when data is deleted
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // Initialize variable
        TextView textView;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assign variable
            textView = itemView.findViewById(R.id.text_view);
            btEdit =itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}
