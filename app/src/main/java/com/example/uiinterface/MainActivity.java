package com.example.uiinterface;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uiinterface.database.AppDatabase;
import com.example.uiinterface.entities.Person;
import com.example.uiinterface.utility.AppExecutors;
import com.example.uiinterface.views.ListPerson;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener  {
    //room variables
    private EditText name, phone, address, city, zip, email, date;
    private AppDatabase appDatabase;

    //date picker variables
    private int mYear, mMonth, mDay;
    Button btnDatePicker;
    EditText txtDate;

    //spinner variables
    TextView textView;
    TextView textView2;

    String [] categories = {"Area", "United Kingdom", "United States", "France", "Spain" };
    String [] categories2 = {"State", "London", "Leicester", "Manchester", "Paris", "Madrid"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //room element
        initViews();
        initializeDatabase();


        //spinner1 element
        textView =(TextView) findViewById(R.id.textView);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener( (AdapterView.OnItemSelectedListener)this );
        spinner.setAdapter(dataAdapter);

        //spinner 2 element
        textView2 = (TextView) findViewById(R.id.textView2);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setOnItemSelectedListener( (AdapterView.OnItemSelectedListener)this );
        spinner2.setAdapter(dataAdapter2);






    date.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v == btnDatePicker) {
                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
    } );

    }



    private void initViews() {
        name = findViewById(R.id.edit_name);
        phone = findViewById(R.id.edit_phone);
        address = findViewById(R.id.edit_address);
        zip = findViewById(R.id.edit_zip);
        city = findViewById(R.id.edit_city);
        email = findViewById(R.id.edit_email);
        date = findViewById( R.id.edit_birthday );
    }

    private void initializeDatabase() {
        appDatabase = AppDatabase.getInstance(getApplicationContext());
    }

    public void savePerson(View view) {
        final Person person = new Person(

                name.getText().toString(),
                phone.getText().toString(),
                address.getText().toString(),
                zip.getText().toString(),
                city.getText().toString(),
                email.getText().toString(),
                date.getText().toString()
        );
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.personDao().insertPerson(person);
            }
        });
        nextScreenOnDataSuccess();
    }

    public void nextScreenOnDataSuccess(){
        Intent intent= new Intent(MainActivity.this, ListPerson.class);
        startActivity(intent);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner)parent;
        Spinner spinner2 = (Spinner)parent;

        if(spinner.getId() == R.id.spinner) {
            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();
            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }
        if (spinner2.getId() == R.id.spinner2) {

            String item2 = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item2, Toast.LENGTH_LONG).show();
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }


}