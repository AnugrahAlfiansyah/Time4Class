package com.example.time4class;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

public class tambah extends AppCompatActivity {

    private EditText mk, ruangan, dosen, wm, ws;
    private Button  simpan, batal;

    private String selectedDay = "", email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        mk = findViewById(R.id.matkul);
        ruangan = findViewById(R.id.ruang);
        dosen = findViewById(R.id.dosen);
        wm = findViewById(R.id.waktum);
        ws = findViewById(R.id.waktus);
        batal = findViewById(R.id.batal);
        simpan = findViewById(R.id.simpan);
        Spinner spinner = findViewById(R.id.hari);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                 selectedDay = adapterView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Senin");
        arrayList.add("Selasa");
        arrayList.add("Rabu");
        arrayList.add("Kamis");
        arrayList.add("Jum'at");
        arrayList.add("Sabtu");
        arrayList.add("Minggu");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        email = account.getEmail();

        simpan.setOnClickListener( (v)->save());
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void save(){
        String mkul = mk.getText().toString();
        String r = ruangan.getText().toString();
        String dsn = dosen.getText().toString();
        String wmulai = wm.getText().toString();
        String wselesai = ws.getText().toString();
        String hari = selectedDay;

        if (mkul.isEmpty() || r.isEmpty() || dsn.isEmpty() || wmulai.isEmpty() || wselesai.isEmpty() || hari.isEmpty()) {
            Toast.makeText(this, "Harap isi jadwal terlebih dahulu!!", Toast.LENGTH_SHORT).show();
        }

        Jadwal j = new Jadwal();
        j.setMatkul(mkul);
        j.setDosen(dsn);
        j.setRuangan(r);
        j.setWaktumulai(wmulai);
        j.setWaktuselesai(wselesai);
        j.setHari(hari);
        j.setEmail(email);

        savetofirebase(j);
    }

    void savetofirebase(Jadwal j){
        DocumentReference documentReference;
        documentReference = utility.getCollectionReferenceRorJadwal().document();
        documentReference.set(j).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(tambah.this, "Jadwal berhasil ditambahkan!!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(tambah.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}