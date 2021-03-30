    package com.example.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.ViewHolder> {

    ArrayList<NoteModel> arrayList;
    Context context;
    DbManager dbManager;
    public String [] mColors = {"#6200EE","#03DAC5","#FFDE03"};
    MyClickListeners myClickListeners;

   

    public MyRecyclerView(Context context, ArrayList<NoteModel> arrayList,MyClickListeners myClickListeners) {
        this.arrayList = arrayList;
        this.context = context;
        this.myClickListeners = myClickListeners;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.noteView.setText(arrayList.get(position).getNote());
//        //holder.noteView.setBackgroundColor(Color.parseColor(mColors[position % mColors.length]));
//        holder.noteView.setTextColor(Color.parseColor(mColors[position % mColors.length]));
//        //dbManager = new DbManager(context);
//        holder.noteView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onItemClick(position);
//                DbManager dbManager = new DbManager(context);
//                dbManager.delete(position);
//                arrayList.remove(position);
//                notifyItemRemoved(position);
//            }
//        });
        holder.bind(arrayList.get(position),myClickListeners);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteView;
        ImageButton imageButton;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noteView = itemView.findViewById(R.id.text_view);
            imageButton = itemView.findViewById(R.id.imageButton);


        }
        public void bind(final NoteModel model,final MyClickListeners myClickListeners) {
            noteView.setText(model.getNote());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    myClickListeners.onLongItemClick(model);

                    return true;
                }
            });






        }


//        @Override
//        public void onClick(View view) {
//           showPopupMenu(view,getAdapterPosition());
//        }
    }
//    private void showPopupMenu(View view,int position) {
//        // inflate menu
//        PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
//        popupMenu.inflate(R.menu.main_menu);
//        popupMenu.setOnMenuItemClickListener(this);
//        popupMenu.show();
//
//    }
//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.delete:
//                return true;
//            case R.id.update:
//                return true;
//
//            default:
//                return false;
//
//        }
//
//    }



}
