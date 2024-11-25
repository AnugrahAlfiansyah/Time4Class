package com.example.time4class;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class edit extends AppCompatActivity {

    private String Matkul, hari, ruangan, dosen, wmulai, wselesai, id, selectedDay;
    private TextView mk, rn, dn, wm, ws;
    private Button edit, batal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mk = findViewById(R.id.matakuliah);
        rn = findViewById(R.id.ruang);
        dn = findViewById(R.id.dosen);
        wm = findViewById(R.id.waktum);
        ws = findViewById(R.id.day);
        edit = findViewById(R.id.edit);
        batal = findViewById(R.id.batal);
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

        Matkul = getIntent().getStringExtra("Matkul");
        hari = getIntent().getStringExtra("hari");
        ruangan = getIntent().getStringExtra("ruangan");
        dosen = getIntent().getStringExtra("dosen");
        wmulai = getIntent().getStringExtra("waktumulai");
        wselesai = getIntent().getStringExtra("waktuselesai");
        id = getIntent().getStringExtra("id");

        mk.setText(Matkul);
        rn.setText(ruangan);
        dn.setText(dosen);
        wm.setText(wmulai);
        ws.setText(wselesai);

        if (hari != null && arrayList.contains(hari)) {
            int index = arrayList.indexOf(hari);
            spinner.setSelection(index);
        }

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edit.setOnClickListener( (v)->save());
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void save(){
        String matak = mk.getText().toString();
        String ruang =rn.getText().toString();
        String dsn =dn.getText().toString();
        String timem =wm.getText().toString();
        String times =ws.getText().toString();
        String hr = selectedDay;


        Jadwal j = new Jadwal();
        j.setMatkul(matak);
        j.setDosen(dsn);
        j.setRuangan(ruang);
        j.setWaktumulai(timem);
        j.setWaktuselesai(times);
        j.setHari(hr);

        savetofirebase(j);
    }
    void savetofirebase(Jadwal j){
        DocumentReference documentReference;
        documentReference = utility.getCollectionReferenceRorJadwal().document(id);
        Map<String, Object> data = new HashMap<>();
        data.put("matkul", j.getMatkul());
        data.put("dosen", j.getDosen());
        data.put("ruangan", j.getRuangan());
        data.put("waktumulai", j.getWaktumulai());
        data.put("waktuselesai", j.getWaktuselesai());
        data.put("hari", j.getHari());
        documentReference.set(data);
        finish();
    }
}