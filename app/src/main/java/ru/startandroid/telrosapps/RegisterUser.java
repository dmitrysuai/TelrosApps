package ru.startandroid.telrosapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, registerUser;
    private EditText editTextFullName, editTextPassword, editTextPatronymic, editTextDateOfBirth, editTextEmail, editTextPhone, editTextFullName2;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser =(Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextFullName =(EditText) findViewById(R.id.fullName);
        editTextPassword =(EditText) findViewById(R.id.password);
        editTextPatronymic =(EditText) findViewById(R.id.patronymic);
        editTextDateOfBirth =(EditText) findViewById(R.id.dateOfBirth);
        editTextEmail =(EditText) findViewById(R.id.email);
        editTextPhone =(EditText) findViewById(R.id.phone);
        editTextFullName2 =(EditText) findViewById(R.id.fullName2);

        progressBar =(ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
        case R.id.banner:
            startActivity(new Intent(this, MainActivity.class));
            break;
            case R.id.registerUser:
                registerUser();
                break;
         }
    }
    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String patronymic =editTextPatronymic.getText().toString().trim();
        String dateOfBirth = editTextDateOfBirth.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String fullName2 = editTextFullName2.getText().toString().trim();

        if (fullName.isEmpty()) {
            editTextFullName.setError("???????? ???? ??????????????????!");
            editTextFullName.requestFocus();
            return;
        }
        if (fullName2.isEmpty()) {
            editTextFullName2.setError("???????? ???? ??????????????????!");
            editTextFullName2.requestFocus();
            return;
        }
        if (patronymic.isEmpty()) {
            editTextPatronymic.setError("???????? ???? ??????????????????!");
            editTextPatronymic.requestFocus();
            return;
        }
        if (dateOfBirth.isEmpty()){
            editTextDateOfBirth.setError("???????? ???? ??????????????????!");
            editTextDateOfBirth.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextEmail.setError("???????? ???? ??????????????????!");
            editTextEmail.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            editTextPhone.setError("???????? ???? ??????????????????!");
            editTextPhone.requestFocus();
            return;
        }


        if (password.length()<6){
            editTextPassword.setError("?????????????? 6 ????????????");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fullName, dateOfBirth, email, password, fullName2, patronymic, phone);

                            FirebaseDatabase.getInstance().getReference("Users")                    // ?????????????????????? ?? ???????? ????????????
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {


                                            if (task.isSuccessful()){
                                                Toast.makeText(RegisterUser.this, "???? ????????????????????????????????!", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.VISIBLE);
                                                // redirect to logout
                                            } else {
                                                Toast.makeText(RegisterUser.this, "???????????? ??????????????????????:(", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });


                        }
                    }
                });

    }
}
