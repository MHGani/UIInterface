package com.example.uiinterface.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.uiinterface.R;
import com.example.uiinterface.database.AppDatabase;
import com.example.uiinterface.entities.Person;
import com.example.uiinterface.utility.AppExecutors;

import java.util.List;

public class rvPerson extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppDatabase appDatabase;

    private List<Person> personList;


    PersonAdapter personAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.rv_person );
        appDatabase= AppDatabase.getInstance(getApplicationContext());

        initializeRecyclerview();
        retrievePersonTable();
    }

    public void initializeRecyclerview(){
        recyclerView = findViewById( R.id.rvPerson );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        personAdapter = new PersonAdapter(this);
        recyclerView.setAdapter( personAdapter );


    }

    public void retrievePersonTable(){
        AppExecutors.getInstance().diskIO().execute( new Runnable() {

            @Override
            public void run() {
                personList = appDatabase.personDao().loadAllPersons();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        personAdapter.setPersonList( personList );

                    }
                });

            }
        });
    }
}