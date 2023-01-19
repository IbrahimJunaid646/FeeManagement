package com.dodo.feemanagement;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddStudent extends AppCompatActivity {

    EditText addStudentEmail, addStudentPassword, addStudentName,addStudentID;
    AppCompatImageView buttonBack;
    MaterialButton buttonAddStudent;
    ProgressBar addStudentpbar;
    FirebaseAuth frAuth;
    FirebaseFirestore frStore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        getSupportActionBar().hide();
        buttonBack=findViewById(R.id.buttonBack);
        addStudentEmail=findViewById(R.id.addStudentEmail);
        addStudentPassword=findViewById(R.id.addStudentPassword);
        addStudentName=findViewById(R.id.addStudentName);
        addStudentID=findViewById(R.id.addStudentID);
//        addStudentpbar=findViewById(R.id.addStudentPbar);
        frStore=FirebaseFirestore.getInstance();
        frAuth=FirebaseAuth.getInstance();
        buttonAddStudent=findViewById(R.id.addStudentButton);
        setListeners();

        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email= addStudentEmail.getText().toString().trim();
                String Password= addStudentPassword.getText().toString().trim();

                if (TextUtils.isEmpty(Email)){
                    addStudentEmail.setError("Enter Valid Email");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    addStudentPassword.setError("Enter Valid Password");
                    return;
                }
                if (Password.length() <6){
                    addStudentPassword.setError("Password Must be >= 6 Character");
                    return;
                }
                addStudentpbar.setVisibility(View.VISIBLE);
                buttonAddStudent.setVisibility(View.GONE);


                frAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user=frAuth.getCurrentUser();
                            Toast.makeText(AddStudent.this, "User Created", Toast.LENGTH_SHORT).show();
                            DocumentReference df=frStore.collection("Student").document(user.getUid());
                            Map<String,Object> userInfo=new HashMap<>();
                            userInfo.put("FullName",addStudentName.getText().toString());
                            userInfo.put("StaffEmail",addStudentEmail.getText().toString());
                            userInfo.put("StaffId",addStudentID.getText().toString());

                            userInfo.put("isStudent","1");
                            df.set(userInfo);

                            addStudentpbar.setVisibility(View.GONE);
                            buttonAddStudent.setVisibility(View.VISIBLE);
                        }else {
                            Toast.makeText(AddStudent.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void setListeners(){
        buttonBack.setOnClickListener( v -> onBackPressed());
    }
}