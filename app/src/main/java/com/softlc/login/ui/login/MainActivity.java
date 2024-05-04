package com.softlc.login.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softlc.login.R;
import com.softlc.login.databinding.ActivityMainBinding;
import com.softlc.login.model.Usuario;
import com.softlc.login.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    private ActivityMainBinding binding;
    private MainActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        vm.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                Intent intent =  new Intent(getApplication(), RegistroActivity.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etEmail.getText().toString();
                String pass = binding.etPassword.getText().toString();
                vm.login(context,email,pass);
                Log.d("salida", "onClick: mail"+email+"pass "+pass);

            }
        });


        binding.btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplication(), RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}