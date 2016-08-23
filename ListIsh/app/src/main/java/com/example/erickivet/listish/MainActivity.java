package com.example.erickivet.listish;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //BaseAdapter mBaseAdapter;
    private MainListSingleton mMainListSingleton;
    private MainListAdapter mMainListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //create floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAddDialog();
            }
        });

        //get instance of main list singleton
        mMainListSingleton = MainListSingleton.getInstance();

        //create new instance of main list adapter
        mMainListAdapter = new MainListAdapter(this, mMainListSingleton.getMainList());

        //cast listview layout to listview variable
        ListView mainListView = (ListView)findViewById(R.id.listview_main);

        //set textview for an empty list
        TextView nullTextView = (TextView)findViewById(R.id.nothing_here_textview);

        //set list adapter to listview variable
        mainListView.setAdapter(mMainListAdapter);

        //set textview for empty list
        mainListView.setEmptyView(nullTextView);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ToDoListActivity.class);
                intent.putExtra("list index",i);
                startActivity(intent);
            }
        });


        //launch dialog prompt on long click to remove list
        mainListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int k, long l) {



                AlertDialog.Builder removeListDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View removeListDialogView = inflater.inflate(R.layout.remove_list_dialog, null);
                removeListDialogBuilder.setView(removeListDialogView);
                removeListDialogBuilder.setTitle("Remove List");
                removeListDialogBuilder.setMessage("Are you sure you want to remove your list?");

                removeListDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mMainListSingleton.getMainList().remove(k);
                        mMainListAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "List Removed", Toast.LENGTH_SHORT).show();
                    }
                });

                removeListDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog removeList = removeListDialogBuilder.create();
                removeList.show();
                return true;
            }
        });
    }

    //launch dialog to add list
    public void launchAddDialog(){

        //create new instance of dialog builder
        AlertDialog.Builder addListDialogBuilder = new AlertDialog.Builder(this);
        //call layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        //inflate layout dialog
        View addListDialogView = inflater.inflate(R.layout.input_list_dialog, null);
        addListDialogBuilder.setView(addListDialogView);

        final EditText inputList = (EditText)addListDialogView.findViewById
                (R.id.input_dialog_edittext);

        addListDialogBuilder.setTitle("Create a New List");
        addListDialogBuilder.setMessage("Please name your list");

        addListDialogBuilder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(inputList.getText().toString().length() > 0){
                    mMainListSingleton.setMainList(new MainList(inputList.getText().toString()));
                    mMainListAdapter.notifyDataSetChanged();

                    Intent intent = new Intent (MainActivity.this, ToDoListActivity.class);
                    intent.putExtra("list index", mMainListSingleton.getMainList().size() - 1 );
                    startActivity(intent);

                }else{
                    Toast.makeText
                            (MainActivity.this,"List Name Can't Be Blank",Toast.LENGTH_LONG).show();

                }

            }
        });

        addListDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog addList = addListDialogBuilder.create();
        addList.show();
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
