package com.softlc.login.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.softlc.login.model.Usuario;

public class ApiClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if (sp==null){
            sp=context.getSharedPreferences("datos",0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario){
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("dni",usuario.getDni());
        editor.putString("nombre",usuario.getNombre());
        editor.putString("apellido",usuario.getApellido());
        editor.putString("email",usuario.getEmail());
        editor.putString("password",usuario.getPassword());
        editor.commit();
    }

    public static Usuario leer(Context context){
        SharedPreferences sp = conectar(context);
        String dni = sp.getString("dni",null);
        String nombre = sp.getString("nombre",null);
        String apellido = sp.getString("apellido",null);
        String email = sp.getString("email",null);
        String password = sp.getString("password",null);

        Usuario usuario = new Usuario(dni,nombre,apellido,email,password);
        return usuario;
    }

    public static Usuario login(Context context,String email,String password){
        Usuario usuario = null;
        SharedPreferences sp = conectar(context);
        String dni = sp.getString("dni",null);
        String nombre = sp.getString("nombre",null);
        String apellido = sp.getString("apellido",null);
        String mail = sp.getString("email","null");
        String pass = sp.getString("password","null");

        if (mail.equals(email)&&pass.equals(password)){
            usuario = new Usuario(dni,nombre,apellido,email,password);
        }
        return usuario;
    }
}
