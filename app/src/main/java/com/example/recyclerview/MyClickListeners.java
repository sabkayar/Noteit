package com.example.recyclerview;

import android.view.View;

import androidx.recyclerview.widget.ItemTouchHelper;

public interface MyClickListeners {
    void onItemClick(NoteModel model);
    void onLongItemClick(NoteModel model);


}
