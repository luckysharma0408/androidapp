package com.example.stufac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup_act extends AppCompatActivity {
    private TextView lgn;
    private FirebaseAuth firebaseAuth;
    private EditText email,pass;
    private Button sgn_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_act);

        lgn=findViewById(R.id.textView);
        email=findViewById(R.id.editText);
        pass=findViewById(R.id.editText2);
        sgn_btn=findViewById(R.id.button);
        firebaseAuth=FirebaseAuth.getInstance();

        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n=new Intent(signup_act.this,login_act.class);
                startActivity(n);
            }
        });

        sgn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emal=email.getText().toString();
                String passs=pass.getText().toString();
                if(!emal.isEmpty() && !passs.isEmpty()){

                    firebaseAuth.createUserWithEmailAndPassword(emal,passs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent n=new Intent(signup_act.this,login_act.class);
                                startActivity(n);
                            }
                            else{
                                String err=task.getException().getMessage();
                                Toast.makeText(signup_act.this, err, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(signup_act.this, "please input email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

