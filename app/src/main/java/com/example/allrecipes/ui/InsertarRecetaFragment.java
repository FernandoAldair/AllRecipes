package com.example.allrecipes.ui;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.allrecipes.databinding.FragmentInsertarRecetaBinding;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static androidx.core.content.ContextCompat.checkSelfPermission;


public class InsertarRecetaFragment extends Fragment {

    FragmentInsertarRecetaBinding binding;
    private RecetasViewModel recetasViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentInsertarRecetaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recetasViewModel = new ViewModelProvider(requireActivity()).get(RecetasViewModel.class);



        binding.seleccionarPortada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(requireContext(), READ_EXTERNAL_STORAGE) == PERMISSION_GRANTED) {
                    abrirGaleria();
                } else {
                    lanzadorPermisos.launch(READ_EXTERNAL_STORAGE);
                }
            }
        });

        recetasViewModel.imagenSelecc.observe(getViewLifecycleOwner(), uri -> {
            Glide.with(requireView()).load(uri).into(binding.previsualizarPortada);
        });

//        binding.insertar.setOnClickListener();
    }

    private final ActivityResultLauncher<String> lanzadorPermisos =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    abrirGaleria();
                }
            });

    private final ActivityResultLauncher<String> lanzadorGaleria =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
//                    Glide.with(requireView()).load(uri).into(binding.previsualizarPortada)

            });

    private void abrirGaleria() {
        lanzadorGaleria.launch("image/*");
    }
}