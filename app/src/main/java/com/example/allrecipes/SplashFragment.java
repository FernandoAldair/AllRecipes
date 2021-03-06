package com.example.allrecipes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.allrecipes.databinding.FragmentInsertarRecetaBinding;
import com.example.allrecipes.databinding.FragmentSplashBinding;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class SplashFragment extends Fragment {

    FragmentSplashBinding binding;

    Executor executor = Executors.newSingleThreadExecutor();

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentSplashBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        // Mutablelivedata se utiliza para que observe y si hay algun cambio se ejecute el cambio automaticamente
        MutableLiveData<Boolean> finishedLoading = new MutableLiveData<>();

        // Nos redirige al fragment de login
        finishedLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                navController.navigate(R.id.action_splashFragment_to_loginFragment);
            }
        });

        // hace una cuenta atras
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    finishedLoading.postValue(true);
                }catch (InterruptedException exception){
                    exception.printStackTrace();
                }
            }
        });




    }
}