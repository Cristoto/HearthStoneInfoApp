package com.hsi.hearthstoneinfo;

/**
 * Created by Cristo on 03/02/2018.
 */
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsTab extends Fragment{

    private EditText telefonoText, mensajeText;
    private Button enviarBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sms_tab, container, false);

        //Caputurando elementos de la vista
        telefonoText = (EditText) rootView.findViewById(R.id.telefonoText);
        mensajeText = (EditText) rootView.findViewById(R.id.mensajeText);
        enviarBtn = (Button) rootView.findViewById(R.id.enviarBtn);

        return rootView;
    }

    public void enviarBtnOnClick(){
        String telefono = telefonoText.getText().toString();
        String mensaje = mensajeText.getText().toString();
        if(!telefono.equals("") || !mensaje.equals(""))
            enviarMensaje(telefono, mensaje);
        else
            mensajeError("No se permiten campos vacios");
    }

    private void mensajeError(String mensajeError){
        Toast toast = Toast.makeText(getActivity(), mensajeError, Toast.LENGTH_LONG);
        toast.show();
    }

    private void enviarMensaje(String numero, String mensaje){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + numero));
        intent.putExtra("sms_body", mensaje);
        startActivity(intent);
    }
}
