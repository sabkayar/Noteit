package com.example.recyclerview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainFragment extends Fragment implements MyClickListeners {
    RecyclerView recyclerView;
    ArrayList<NoteModel> arrayList;
    DbManager dbManager;
    MyRecyclerView adopter;
    NoteModel model;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main, container, false);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.recycle_view);
        dbManager = new DbManager(getActivity());
        model = new NoteModel();
        recyclerView.setHasFixedSize(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        try {
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        displayNotes();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment();

            }
        });
        return view;

    }

    public void addFragment(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        AddNotes addNotes = new AddNotes();
        transaction.add(R.id.frag_set,addNotes);
        transaction.commit();
    }
    public void displayNotes(){
        arrayList = new ArrayList<>(dbManager.getNotes());
        adopter = new MyRecyclerView(getContext(),arrayList,this);
        recyclerView.setAdapter(adopter);
        adopter.notifyDataSetChanged();
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }



        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Log.d("xyz----", "Inside onSwipe()");
          int position = (int) adopter.getItemId(viewHolder.getAdapterPosition());
          switch (direction){
              case ItemTouchHelper.LEFT:
                  Log.d("xyz----", "ItemTouchHelper.LEFT "+position+1);

                  dbManager.delete(model);
               //   arrayList.remove(position-1);
                  adopter.notifyItemRemoved(viewHolder.getAdapterPosition());
                  displayNotes();
                  break;
              case ItemTouchHelper.RIGHT:
                  Log.d("xyz----", "ItemTouchHelper.RIGHT");

                  break;
          }
        }
    };

    @Override
    public void onItemClick(NoteModel model) {

    }

    @Override
    public void onLongItemClick(NoteModel model) {
    }



}