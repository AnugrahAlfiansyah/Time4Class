package com.example.time4class;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class utility {

    static CollectionReference getCollectionReferenceRorJadwal(){

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("notes").document(currentUser.getUid()).collection("my_schedule");
    }
}
