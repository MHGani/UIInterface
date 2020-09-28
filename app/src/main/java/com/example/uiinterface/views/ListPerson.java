package com.example.uiinterface.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uiinterface.R;
import com.example.uiinterface.database.AppDatabase;
import com.example.uiinterface.entities.Person;
import com.example.uiinterface.utility.AppExecutors;

import java.util.List;


public class ListPerson extends AppCompatActivity {
    private AppDatabase appDatabase;
    private List<Person> personList;
    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_person);
        appDatabase= AppDatabase.getInstance(getApplicationContext());
        retrievePersonTable();
    }

    public void retrievePersonTable(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {

            @Override
            public void run() {
                personList = appDatabase.personDao().loadAllPersons();
                for(int i=0; i< personList.size(); i++){
                    Log.i("TABLE","Person Table values" + personList.get(i).getName());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Listview
                        Log.d("TABLE", "ListView ");
                        String[] userArr = new String[personList.size()];
                        for(int i=0; i< personList.size(); i++){
                            Log.i("TABLE", "Person Table values " + personList.get(i).getName());
                            userArr[i] = personList.get(i).toString();
                        }
                        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, userArr);
                        // recyclerview
                    }
                });

            }
        });
    }

}
