package com.example.diorous.quanlykhachsan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.diorous.quanlykhachsan.ParamsContants.ParamContans;
import com.example.diorous.quanlykhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, FirebaseAuth.AuthStateListener, ValueEventListener {
    Button btnLogin;
    AppCompatEditText edtEmail, edtPassword;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initFirebaseAuth();

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin.setOnClickListener(this);
    }

    private void initFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(ParamContans.NHAN_VIEN);
        databaseReference.addValueEventListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                handleLogin();
                break;
        }
    }

    private void handleLogin() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                } else {
                    gotoMainScreen();
                }
            }
        });
    }

    private void gotoMainScreen() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            //gotoMainScreen();
        } else {

        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Iterable<DataSnapshot> childrens = dataSnapshot.getChildren();
        for (DataSnapshot snapshot:childrens) {
            Log.d("kiemtra", snapshot.toString());
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
