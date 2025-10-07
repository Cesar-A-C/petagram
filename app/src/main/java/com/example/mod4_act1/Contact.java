package com.example.mod4_act1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Contact extends AppCompatActivity {
    private EditText name,email,message;
    private Button send;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /*********************************************************************************/
        //Inicializador de todos los componentes
        setup();
        /*********************************************************************************/

        send.setOnClickListener(v -> {
            String strName = name.getText().toString();
            String strEmail = email.getText().toString();
            String strMessage = message.getText().toString();
            if(strName.isEmpty() || strEmail.isEmpty() || strMessage.isEmpty()){
                Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
            }else{
                new Thread(() -> sendEmail(strName, strEmail, strMessage)).start();
            }

        });



    }

    private void setup(){
        /*********************************************************************************/
        //CÓDIGO NECESARIO PARA UN TOOLBAR PERSONALIZADO CON FLECHA DE VOLVER
        /*********************************************************************************/
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Flecha de volver
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setTint(Color.BLACK);
        }
        /*********************************************************************************/

        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        message = (EditText) findViewById(R.id.etMessage);
        send = (Button) findViewById(R.id.btnSend);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendEmail(String name, String email, String messageBody) {
        final String username = "tuemail@gmail.com"; // tu correo
        final String password = "tu_app_password";   // app password si usas Gmail

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("destinatario@correo.com")); // a quién enviar
            message.setSubject("Nuevo mensaje de " + name);
            message.setText("Correo: " + email + "\n\nMensaje:\n" + messageBody);

            Transport.send(message);

            runOnUiThread(() ->
                    Toast.makeText(this, "Correo enviado correctamente!", Toast.LENGTH_SHORT).show()
            );

        } catch (MessagingException e) {
            e.printStackTrace();
            runOnUiThread(() ->
                    Toast.makeText(this, "Error al enviar correo", Toast.LENGTH_SHORT).show()
            );
        }
    }

}