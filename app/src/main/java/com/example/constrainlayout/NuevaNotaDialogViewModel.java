package com.example.constrainlayout;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.constrainlayout.db.entity.NotaEntity;

import java.util.List;

public class NuevaNotaDialogViewModel extends AndroidViewModel {
    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository notaRepository;

    public NuevaNotaDialogViewModel(Application application) {
        super(application);

        notaRepository = new NotaRepository(application);
        allNotas = notaRepository.getAll();

    }

    // Fragmento que necesita recibir la nueva lista de datos
    public LiveData<List<NotaEntity>> getAllNotas() { return allNotas; }

    // Fragmento que inserte una nueva nota, debe comunicarlo a este ViewModel
    public void insertarNota(NotaEntity nuevaNotaEntity) { notaRepository.insert(nuevaNotaEntity); }
}