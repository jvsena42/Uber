package com.balatech.uber.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfiguracaoFirebase {

    private static DatabaseReference databaseReference;
    private static FirebaseAuth firebaseAuth;
    private static StorageReference firebaseStorage;

    //Instancia do firebase database
    public static DatabaseReference getFirebaseDatabase(){
        if (databaseReference == null){
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return databaseReference;
    }

    //Instancia do FirebaseAuth
    public static FirebaseAuth getFirebaseAuth(){
        if (firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }

    //Instancia do FirebaseStorage
    public static StorageReference getFirebaseStorage(){
        if (firebaseStorage == null){
            firebaseStorage = FirebaseStorage.getInstance().getReference();
        }
        return firebaseStorage;
    }
}
