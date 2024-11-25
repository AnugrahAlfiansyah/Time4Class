package com.example.time4class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;

public class detail extends AppCompatActivity {

    private String Matkul, hari, ruangan, dosen, wmulai, wselesai, id;
    private TextView mk, hr, rn, dn, wm, ws;
    private Button edit, hapus, kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mk = findViewById(R.id.matkul);
        hr = findViewById(R.id.hari);
        rn = findViewById(R.id.ruang);
        dn = findViewById(R.id.dosen);
        wm = findViewById(R.id.waktum);
        ws = findViewById(R.id.waktus);
        edit = findViewById(R.id.edit);
        hapus = findViewById(R.id.hapus);
        kembali = findViewById(R.id.back);

        Matkul = getIntent().getStringExtra("Matkul");
        hari = getIntent().getStringExtra("hari");
        ruangan = getIntent().getStringExtra("ruangan");
        dosen = getIntent().getStringExtra("dosen");
        wmulai = getIntent().getStringExtra("waktumulai");
        wselesai = getIntent().getStringExtra("waktuselesai");
        id = getIntent().getStringExtra("id");

        mk.setText(Matkul);
        hr.setText(hari);
        rn.setText(ruangan);
        dn.setText(dosen);
        wm.setText(wmulai);
        ws.setText(wselesai);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detail.this, edit.class);
                intent.putExtra("Matkul", Matkul);
                intent.putExtra("ruangan", ruangan);
                intent.putExtra("dosen", dosen);
                intent.putExtra("waktumulai", wmulai);
                intent.putExtra("waktuselesai", wselesai);
                intent.putExtra("hari", hari);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        hapus.setOnClickListener((v)-> deletefromfirebase());
    }

    void deletefromfirebase(){
        DocumentReference documentReference;
        documentReference = utility.getCollectionReferenceRorJadwal().document(id);
        documentReference.delete();
        finish();
    }
}