package com.softlc.login.ui.registro;

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
import com.softlc.login.databinding.ActivityRegistroBinding;
import com.softlc.login.model.Usuario;

public class RegistroActivity extends AppCompatActivity {
    Context context = this;
    private ActivityRegistroBinding binding;
    private RegistroActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);


        vm.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDni.setText(usuario.getDni());
                binding.etApellido.setText(usuario.getApellido());
                binding.etNombre.setText(usuario.getNombre());
                binding.etMail.setText(usuario.getEmail());
                binding.etPass.setText(usuario.getPassword());
            }
        });
        Intent intent = getIntent();
        vm.cargarDatosUsuario((Usuario)intent.getSerializableExtra("usuario"));

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = binding.etDni.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String nombre = binding.etNombre.getText().toString();
                String email = binding.etMail.getText().toString();
                String pass = binding.etPass.getText().toString();
                vm.registrar(context,dni,apellido,nombre,email,pass);
                Log.d("salida", "registrar: "+email+pass);
            }
        });

        binding.btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.dialogoSalir(context);
            }
        });
    }
}