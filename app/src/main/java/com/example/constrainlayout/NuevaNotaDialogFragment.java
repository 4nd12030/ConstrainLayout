package com.example.constrainlayout;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.constrainlayout.db.entity.NotaEntity;

public class NuevaNotaDialogFragment extends DialogFragment {

    public static NuevaNotaDialogFragment newInstance() {
        return new NuevaNotaDialogFragment();
    }

    private View view;
    private EditText etTitulo, etContenido;
    private RadioGroup rgColor;
    private Switch swNotaFavorital;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Nueva Nota");
        builder.setMessage("Introduzca los datos de la nueva nota")
                .setPositiveButton("Guardar", (dialog, id)  -> {
                    String titulo = etTitulo.getText().toString();
                    String contenido = etContenido.getText().toString();
                    String color = "azul";
                    switch (rgColor.getCheckedRadioButtonId()) {
                        case R.id.radioButtonRojo:
                            color = "rojo";
                            break;

                        case R.id.radioButtonVerde:
                            color = "verde";
                            break;
                    }
                    boolean esFavorita = swNotaFavorital.isChecked();

                    //Comunicar al viewmodel el nuevo dato(nueva nota).
                    NuevaNotaDialogViewModel mViewModel = new ViewModelProvider(this).get(NuevaNotaDialogViewModel.class);
                    mViewModel.insertarNota(new NotaEntity(titulo, contenido,esFavorita, color));
                    dialog.dismiss();
                })
                .setNegativeButton("Cancelar", (dialog, id) -> {
                    dialog.dismiss();
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_nueva_nota_dialog, null);

        etTitulo = view.findViewById(R.id.editTextTitulo);
        etContenido = view.findViewById(R.id.editTextContenido);
        rgColor = view.findViewById(R.id.radioGroupColor);
        swNotaFavorital = view.findViewById(R.id.switchNotaFavorita);

        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }


}