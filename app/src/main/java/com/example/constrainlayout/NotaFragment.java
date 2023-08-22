package com.example.constrainlayout;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private NotasInteractionListener mListener;
    private List<Nota> notaList;
    private MyNotaRecyclerViewAdapter adapterNotas;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWith = displayMetrics.widthPixels / displayMetrics.density;
                int numeroColumnas = (int) (dpWith /180);
                //recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numeroColumnas, StaggeredGridLayoutManager.VERTICAL));
            }
            //recyclerView.setAdapter(new MyNotaRecyclerViewAdapter(notaList, mListener));

            //Notas
            notaList = new ArrayList<>();
            notaList.add(new Nota("Lista de la compra de la semana", "Comprar tostado", true, android.R.color.holo_blue_light));
            notaList.add(new Nota("Recordar", "He aparcado el coche en la calle Republica, no olvidarme de pagar el parquimetro", false, android.R.color.holo_green_light));
            notaList.add(new Nota("Fiesta cumpleanos de Margaret","Contrary to popular belief, Lorem Ipsum is not simply random text. It has " +
                    "roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor " +
                    "at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, " +
                    "and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections " +
                    "1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is" +
                    " a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", " +
                    "comes from a line in section 1.10.32.",false, android.R.color.holo_orange_light));
            notaList.add(new Nota("Lista de la compra (Hoy)", "Comprar huevo, lechuga y jamon", true, android.R.color.holo_red_light));
            notaList.add(new Nota("Recordar Aniversario (Amoorcito)", "Hacer Reserva en el Restaurante Favorito de Lukas" +
                    " is a long established fact that a reader will be distracted by the readable content of a page when looking at its " +
                    "layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to " +
                    "using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web " +
                    "page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web " +
                    "sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on" +
                    " purpose (injected humour and the like).\n", true, android.R.color.holo_purple));
            notaList.add(new Nota("Lista de la compra de la semana", "Comprar tostado", true, android.R.color.holo_blue_light));
            notaList.add(new Nota("Recordar", "He aparcado el coche en la calle Republica, no olvidarme de pagar el parquimetro", false, android.R.color.holo_green_light));
            notaList.add(new Nota("Fiesta cumpleanos de Margaret","Contrary to popular belief, Lorem Ipsum is not simply random text. It has " +
                    "roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor " +
                    "at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, " +
                    "and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections " +
                    "1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is" +
                    " a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", " +
                    "comes from a line in section 1.10.32.",false, android.R.color.holo_orange_light));
            notaList.add(new Nota("Lista de la compra de la semana", "Comprar tostado", true, android.R.color.holo_blue_light));
            notaList.add(new Nota("Recordar", "He aparcado el coche en la calle Republica, no olvidarme de pagar el parquimetro", false, android.R.color.holo_green_light));


            adapterNotas = new MyNotaRecyclerViewAdapter(notaList, mListener);
            recyclerView.setAdapter(adapterNotas);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NotasInteractionListener) {
            mListener = (NotasInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
            + "debe implementarse NotasInteractionListener");
        }
    }
}