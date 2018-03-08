package corp.poopapps.login;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int error1;

    String date="";
    RadioGroup Radiog;
    RadioButton Rfemenino, Rmasculino;
    CheckBox hobbi1, hobbi2, hobbi3, hobbi4;
    EditText Elogin, Eemail, Epassword, Epassword2;
    TextView Tdatos, Tfecha;
    Spinner spinner;
    String[] ciudades = {"MEDELLIN", "BOGOTA", "CALI", "BARRANQUILLA", "CARTAGENA", "PEREIRA", "BUCARAMANGA"};
    String aux = "";
    String usuario = "", contrasena = "", contrasena2 = "", correo = "", radiobtext = "MASCULINO", ciudad = "MEDELLIN";
    String[] hobbies;
    DatePickerDialog.OnDateSetListener DateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Elogin = (EditText) findViewById(R.id.ELogin);
        Eemail = (EditText) findViewById(R.id.Eemail);
        Epassword = (EditText) findViewById(R.id.EPassword);
        Epassword2 = (EditText) findViewById(R.id.EPassword2);
        Tdatos = (TextView) findViewById(R.id.Tdatos);
        Tfecha = (TextView) findViewById(R.id.Tfecha);


        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ciudades));

        Radiog = (RadioGroup) findViewById(R.id.RadioG);

        hobbi1 = (CheckBox) findViewById(R.id.hobbi1);
        hobbi2 = (CheckBox) findViewById(R.id.hobbi2);
        hobbi3 = (CheckBox) findViewById(R.id.hobbi3);
        hobbi4 = (CheckBox) findViewById(R.id.hobbi4);

        Rfemenino = (RadioButton) findViewById(R.id.Rfemenino);
        Rmasculino = (RadioButton) findViewById(R.id.Rmasculino);

        Tfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int ano = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        DateSetListener,
                        ano, mes, dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                int month = mes + 1;

                date = month + "/" + dia + "/" + ano;
                Tfecha.setText(date);

            }
        };
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.hobbi1:
                if (checked)
                    if (aux == "")
                        aux = "Correr";
                    else
                        aux = aux + ", correr";
                break;
            case R.id.hobbi2:
                if (checked)
                    if (aux == "")
                        aux = "Nadar";
                    else
                        aux = aux + ", nadar";
                break;
            case R.id.hobbi3:
                if (checked)
                    if (aux == "")
                        aux = "Leer";
                    else
                        aux = aux + ", leer";
                break;
            case R.id.hobbi4:
                if (checked)
                    if (aux == "")
                        aux = "Viajar";
                    else
                        aux = aux + ", viajar";
                break;
        }

}

    public void OnClickRadioButton(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.Rfemenino:
                if (checked)
                    radiobtext = "FEMENINO";
                    break;
            case R.id.Rmasculino:
                if (checked)
                    radiobtext = "MASCULINO";
                    break;
        }

    }

    public void OnClickButton(View view) {
        int error=0;

        ciudad = spinner.getSelectedItem().toString();

        if(Elogin.getText().toString().matches("")){

            Elogin.setError("Campo vacío.");
            error = 1;
        }else usuario = Elogin.getText().toString();

        if(Epassword.getText().toString().matches("")){
            Epassword.setError("Campo vacío.");
            error = 1;
        }else contrasena = Epassword.getText().toString();

        if(Epassword2.getText().toString().matches("")){
            Epassword2.setError("Campo vacío.");
            error = 1;
        }else contrasena2 = Epassword2.getText().toString();

        if(!contrasena.equals(contrasena2)){
            error = 1;
            Epassword.setText("");
            Epassword2.setText("");
            Epassword.setError("Las contraseñas no coinciden.");
            Epassword2.setError("Las contraseñas no coinciden.");
        }

        if(Eemail.getText().toString().matches("")){
            Eemail.setError("Campo vacío.");
            error = 1;
        }else correo = Eemail.getText().toString();

        if(date.matches("")){
            error = 1;
        }

        if(aux.matches("")){
            error = 1;
        }

        if(error == 1){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Registro incompleto.", Toast.LENGTH_LONG);

            toast1.show();

            Tdatos.setText("Por favor complete todos los campos.");

        } else {
            Tdatos.setText("Usuario: " + usuario + "\n" + "Contraseña: " + contrasena + "\n" + "Correo: " + correo + "\n" + "Género: " + radiobtext + "\n" + "Fecha: " +  date + "\n" + "Ciudad: " + ciudad +  "\n" + "Hobbies: " + aux );

            Elogin.setText("");
            Epassword.setText("");
            Epassword2.setText("");
            Eemail.setText("");

            aux = "";
            hobbi1.setChecked(false);
            hobbi2.setChecked(false);
            hobbi3.setChecked(false);
            hobbi4.setChecked(false);
        }

        }
    }






