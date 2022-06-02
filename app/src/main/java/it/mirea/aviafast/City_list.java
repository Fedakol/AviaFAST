package it.mirea.aviafast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class City_list extends ListActivity {

    final String[] Airports_array = new String[]{"Москва Шереметьево", "Москва Внуково", "Москва Домодевово",
            "Москва Жуковский", "Санкт-Петербурк Пулково", "Нижний Новгород", "Казань", "Симферопль"};

    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> airports_list = new ArrayList<>(Arrays.asList(Airports_array));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, airports_list);
        setListAdapter(mAdapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String airport_name_to_pass = l.getItemAtPosition(position).toString();
        Intent intent = new Intent(City_list.this, flights_listActivity.class);
        intent.putExtra("message key", airport_name_to_pass);
        startActivity(intent);

        Toast.makeText(getApplicationContext(),
                "Вы выбрали " + l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }
}