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

public class adapter2 extends FirestoreRecyclerAdapter<Jadwal, adapter2.jadwalHolder2> {

    Context context;
    public adapter2(@NonNull FirestoreRecyclerOptions<Jadwal> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull jadwalHolder2 holder, int position, @NonNull Jadwal jadwal) {
        holder.matkul.setText(jadwal.Matkul);
        holder.waktum.setText(jadwal.waktumulai);
        holder.waktus.setText(jadwal.waktuselesai);
        holder.hari.setText(jadwal.hari);

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
    public jadwalHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_jadwal2,parent,false);
        return new jadwalHolder2(view);
    }

    class jadwalHolder2 extends RecyclerView.ViewHolder{
        TextView matkul, waktum, waktus, hari;
        public jadwalHolder2(@NonNull View itemView) {
            super(itemView);
            matkul = itemView.findViewById(R.id.matakuliah2);
            waktum = itemView.findViewById(R.id.waktum2);
            waktus = itemView.findViewById(R.id.waktus2);
            hari = itemView.findViewById(R.id.day);
        }
    }

}