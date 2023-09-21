package com.example.constrainlayout.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.constrainlayout.NuevaNotaDialogViewModel;
import com.example.constrainlayout.R;
import com.example.constrainlayout.databinding.FragmentNotaBinding;
import com.example.constrainlayout.db.entity.NotaEntity;

import java.util.List;


public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private List<NotaEntity> mValues;
    private Context ctx;
    private NuevaNotaDialogViewModel viewModel;

    public MyNotaRecyclerViewAdapter(List<NotaEntity> items, Context ctx ) {
       mValues = items;
       this.ctx = ctx;
       viewModel = new ViewModelProvider((AppCompatActivity)ctx).get(NuevaNotaDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentNotaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvTitulo.setText(holder.mItem.getTitulo());
        holder.tvContenido.setText(holder.mItem.getContenido());

        if(holder.mItem.isFavorita()){
            holder.ivFavorita.setImageResource(R.drawable.ic_baseline_star_24);
        }

        holder.ivFavorita.setOnClickListener(v -> {
            if(holder.mItem.isFavorita()) {
                holder.mItem.setFavorita(false);
                holder.ivFavorita.setImageResource(R.drawable.ic_baseline_star_border_24);
            } else {
                holder.mItem.setFavorita(true);
                holder.ivFavorita.setImageResource(R.drawable.ic_baseline_star_24);
            }

            viewModel.updateNota(holder.mItem);
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    //Metodo que recibe la lista de los datos de la nueva nota
    public void setNuevasNotas(List<NotaEntity> nuevasNotas) {
        this.mValues = nuevasNotas;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public final TextView mIdView;
        public final TextView tvTitulo;
        public final TextView tvContenido;
        public final ImageView ivFavorita;
        public NotaEntity mItem;

        public ViewHolder(FragmentNotaBinding binding) {
            super(binding.getRoot());
            //mIdView = binding.itemNumber;
            tvTitulo = itemView.findViewById(R.id.textViewTitulo);
            tvContenido= itemView.findViewById(R.id.textViewContenido);
            ivFavorita= itemView.findViewById(R.id.imageViewFavorita);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvContenido.getText() + "'";
        }
    }
}