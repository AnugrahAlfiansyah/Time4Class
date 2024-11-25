package com.example.time4class;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class adapter extends FirestoreRecyclerAdapter<Jadwal, adapter.jadwalHolder> {

    Context context;
    public adapter(@NonNull FirestoreRecyclerOptions<Jadwal> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull jadwalHolder holder, int position, @NonNull Jadwal jadwal) {
        holder.matkul.setText(jadwal.Matkul);
        holder.waktum.setText(jadwal.waktumulai);
        holder.waktus.setText(jadwal.waktuselesai);

        holder.itemView.setOnClickListener((v) -> {
            Intent intent = new Intent(context, detail.class);
            intent.putExtra("Matkul", jadwal.Matkul);
            intent.putExtra("ruangan", jadwal.ruangan);
            intent.putExtra("dosen", jadwal.dosen);
            intent.putExtra("waktumulai", jadwal.waktumulai);
            intent.putExtra("waktuselesai", jadwal.waktuselesai);
            intent.putExtra("hari", jadwal.hari);
            String id = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("id", id);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public jadwalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_jadwal,parent,false);
        return new jadwalHolder(view);
    }

    class jadwalHolder extends RecyclerView.ViewHolder{
        TextView matkul, waktum, waktus;
        public jadwalHolder(@NonNull View itemView) {
            super(itemView);
            matkul = itemView.findViewById(R.id.matakuliah);
            waktum = itemView.findViewById(R.id.waktum);
            waktus = itemView.findViewById(R.id.waktus);
        }
    }
}
