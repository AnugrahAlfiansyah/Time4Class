package com.example.time4class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.firestore.Query;

public class MainActivity2 extends AppCompatActivity {

    private Button tambah, setting, today;
    private TextView uname;
    private ImageView fprofile;
    private RecyclerView recyclerView;
    adapter2 adapter2;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tambah = findViewById(R.id.tambah);
        setting = findViewById(R.id.setting);
        today = findViewById(R.id.today);
        uname = findViewById(R.id.uname);
        fprofile = findViewById(R.id.fprofile);
        recyclerView = findViewById(R.id.recycle);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, tambah.class);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, setting.class);
                startActivity(intent);
            }
        });
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null){
            email = account.getEmail();
            String usern = account.getDisplayName();
            uname.setText(usern);

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
        Query query = utility.getCollectionReferenceRorJadwal()
                .whereEqualTo("email", email) // Filter berdasarkan email pengguna
                .orderBy("hari", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Jadwal> options = new FirestoreRecyclerOptions.Builder<Jadwal>()
                .setQuery(query, Jadwal.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new adapter2(options, this);
        recyclerView.setAdapter(adapter2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter2.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter2.notifyDataSetChanged();
    }
}