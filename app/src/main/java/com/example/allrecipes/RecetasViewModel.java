package com.example.allrecipes;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class RecetasViewModel extends AndroidViewModel {

    MutableLiveData<Uri> imagenSelecc = new MutableLiveData<>();


    public RecetasViewModel(@NonNull Application application) {
        super(application);
    }

    void establecerImagenSelecc (Uri uri){
        imagenSelecc.setValue(uri);
    }
}
