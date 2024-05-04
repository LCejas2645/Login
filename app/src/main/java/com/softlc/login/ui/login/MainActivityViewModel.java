package com.softlc.login.ui.login;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softlc.login.databinding.ActivityMainBinding;
import com.softlc.login.model.Usuario;
import com.softlc.login.request.ApiClient;
import com.softlc.login.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private ActivityMainBinding binding;

    private MutableLiveData<Usuario> mUsuario;

    private MutableLiveData<String> mError;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Usuario> getMUsuario(){
        if(mUsuario == null){
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public LiveData<String> getMProducto(){
        if(mError == null){
            mError = new MutableLiveData<>();
        }
        return mError;
    }

    public void login(Context context,String mail, String pass){
        if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass)) {
            // Si uno de los campos está vacío, muestra un mensaje de error
            Toast.makeText(context, "Por favor, ingresa el correo electrónico y la contraseña", Toast.LENGTH_SHORT).show();
            return; // Sale del método sin intentar iniciar sesión
        }
        Usuario usuario = ApiClient.login(context,mail,pass);
        if (usuario!=null){
            mUsuario.setValue(usuario);
            Toast.makeText(getApplication(),"iniciando..", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplication(),"Usuario o contraseña incorrectos!", Toast.LENGTH_LONG).show();
        }
    }
}
