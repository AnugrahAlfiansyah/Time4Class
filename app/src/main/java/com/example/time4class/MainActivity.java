package com.example.time4class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button tambah, setting, jadwal;
    private TextView uname;
    private RecyclerView recyclerView;
    private ImageView fprofile;
    String email;
    adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tambah = findViewById(R.id.tambah);
        setting = findViewById(R.id.setting);
        jadwal = findViewById(R.id.jadwal);
        uname = findViewById(R.id.uname);
        fprofile = findViewById(R.id.fprofile);
        recyclerView = findViewById(R.id.recycle);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, tambah.class);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, setting.class);
                startActivity(intent);
            }
        });
        jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null){
            String usern = account.getDisplayName();
            uname.setText(usern);
            email = account.getEmail();

            if (account.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(account.getPhotoUrl()) // Memuat URL foto profil
                        .circleCrop()
                        .into(fprofile); // Menampilkan di ImageView
            }

        }

        setupRecyclerview();
    }

    void setupRecyclerview(){
        String currentDay = getCurrentDay();

        Query query = utility.getCollectionReferenceRorJadwal()
                .whereEqualTo("email", email) // Filter berdasarkan email pengguna
                .whereEqualTo("hari", currentDay) // Filter hanya untuk hari ini
                .orderBy("waktumulai", Query.Direction.ASCENDING); // Urutkan berdasarkan waktu mulai

        FirestoreRecyclerOptions<Jadwal> options = new FirestoreRecyclerOptions.Builder<Jadwal>()
                .setQuery(query, Jadwal.class).build();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adapter(options, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("id", "ID"));
    }

}