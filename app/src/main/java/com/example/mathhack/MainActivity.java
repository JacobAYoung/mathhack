package com.example.mathhack;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mathhack.Addition;

public class MainActivity extends AppCompatActivity {

    ListView myListView;
    String[] names;
    String[] descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        names = res.getStringArray(R.array.names);
        descriptions = res.getStringArray(R.array.descriptions);

        ItemAdapter itemAdapter = new ItemAdapter(this, names, descriptions);
        myListView.setAdapter(itemAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showDetailActivity = new Intent(getApplicationContext(), DetailActivity.class);
                showDetailActivity.putExtra("com.example.mathhack.ITEM_INDEX", position);
                startActivity(showDetailActivity);
            }
        });




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Button btn = findViewById(R.id.button);
        final Addition add = new Addition(1, 10);
        TextView textView = findViewById(R.id.simpleTextView);
        textView.setText(add.GetEquation()); //set text for text



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("MathHack", "Button doing something");
                //Toast.makeText(getApplicationContext(), "Works!", Toast.LENGTH_SHORT).show();

                //Textbox input
                EditText answer = findViewById(R.id.answer);
                String content = answer.getText().toString(); //gets you the contents of edit text

                //Clear input screen
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(answer.getWindowToken(), 0);

                int userAnswer = Integer.parseInt(content);

//                ImageView img= findViewById(R.id.imageview1);
//                img.setVisibility(View.VISIBLE);

                //System.out.println(add.CheckAnswer(userAnswer));

                int returnCode = -1;

                int realAnswer = add.GetAnswer();

                if (add.CheckAnswer(userAnswer)) {
                    returnCode = 0;
                } else if (userAnswer > realAnswer) {
                    if ((userAnswer - 1) == realAnswer)
                    {
                        returnCode = 1;
                    } else if ((userAnswer - 4) <= realAnswer && (userAnswer - 1) >= realAnswer) {
                        returnCode = 2;
                    } else {
                        returnCode = 3;
                    }
                } else if (userAnswer < realAnswer) {
                    if ((userAnswer + 1) == realAnswer) {
                        returnCode = 1;
                    } else if ((userAnswer + 4) >= realAnswer && (userAnswer + 1) <= realAnswer) {
                        returnCode = 4;
                    } else {
                        returnCode = 5;
                    }
                }

                TextView textView1 = findViewById(R.id.simpleTextView1);
                textView1.setVisibility(View.VISIBLE);
                textView1.setText(add.GetReturnMessage(returnCode)); //set text for text view
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
