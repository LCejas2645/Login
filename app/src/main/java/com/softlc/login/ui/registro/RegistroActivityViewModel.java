package com.softlc.login.ui.registro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.softlc.login.databinding.ActivityRegistroBinding;
import com.softlc.login.model.Usuario;
import com.softlc.login.request.ApiClient;

public class RegistroActivityViewModel extends AndroidViewModel {
    private ActivityRegistroBinding binding;
    private MutableLiveData<Usuario> mUsuario;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Usuario> getMUsuario(){
        if (mUsuario==null){
            mUsuario = new MutableLiveData<Usuario>();
        }
        return mUsuario;
    }

    public void cargarDatosUsuario(Usuario usuario){
        if (usuario!=null){
            mUsuario.setValue(usuario);
        }
    }
    public void registrar(Context context,String dni,String apellido,String nombre,String email,String pass){
        if (TextUtils.isEmpty(dni) || TextUtils.isEmpty(apellido) || TextUtils.isEmpty(nombre) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        Usuario usuario = new Usuario(dni,apellido,nombre,email,pass);
        ApiClient.guardar(context,usuario);
        Toast.makeText(getApplication(),"Usuario registrado: "+email, Toast.LENGTH_LONG).show();
    }

    public  void dialogoSalir(Context contexto){
        new AlertDialog.Builder(contexto)
                .setTitle("Salir")
                .setMessage("Desea Salir?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }


}
