package com.example.erickivet.listish;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ToDoListActivity extends AppCompatActivity {
    private ListItemAdapter mListItemAdapter;
    private MainListSingleton mMainListSingleton;
    private int mListIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addItemDialog();
            }
        });

        mMainListSingleton = MainListSingleton.getInstance();

        mListIndex = getIntent().getIntExtra("list index",-1);

        toolbar.setTitle(mMainListSingleton.getMainList().get(mListIndex).getListTitle());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mListItemAdapter = new ListItemAdapter(this, mMainListSingleton.getMainList()
                .get(mListIndex).getListItems());

        ListView itemsListView = (ListView)findViewById(R.id.items_listview);
        TextView nothingHereTextView = (TextView)findViewById(R.id.nothing_here_textview);
        itemsListView.setAdapter(mListItemAdapter);
        itemsListView.setEmptyView(nothingHereTextView);


        itemsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int k, long l) {

                AlertDialog.Builder removeItemDialogBuilder = new AlertDialog.Builder(ToDoListActivity.this);
                LayoutInflater inflater = ToDoListActivity.this.getLayoutInflater();
                View removeItemDialogView = inflater.inflate(R.layout.remove_item_dialog, null);
                removeItemDialogBuilder.setView(removeItemDialogView);
                removeItemDialogBuilder.setTitle("Remove Item");
                removeItemDialogBuilder.setMessage("Are you sure you want to remove your list item?");

                removeItemDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mMainListSingleton.getMainList().get(mListIndex).getListItems().remove(k);
                        mListItemAdapter.notifyDataSetChanged();
                        Toast.makeText(ToDoListActivity.this, "Item Removed", Toast.LENGTH_SHORT).show();
                    }
                });

                removeItemDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog removeItem = removeItemDialogBuilder.create();
                removeItem.show();
                return true;
            }
        });
    }

    public void addItemDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View inputItemDialog = inflater.inflate(R.layout.input_item_dialog, null);
        dialogBuilder.setView(inputItemDialog);

        final EditText itemEditText = (EditText) inputItemDialog.findViewById(R.id.add_item_edittext);

        dialogBuilder.setTitle("Add Item");
        dialogBuilder.setMessage("Add your To-Do item");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                   if(itemEditText.getText().toString().length() > 0){
                       mMainListSingleton.getMainList().get(mListIndex).getListItems()
                               .add(new ListItems(itemEditText.getText().toString(),false));
                       mListItemAdapter.notifyDataSetChanged();
                   }else {
                       Toast.makeText(ToDoListActivity.this,"Enter a list item!",Toast.LENGTH_LONG).show();
                   }
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog addItem = dialogBuilder.create();
        addItem.show();

    }

}
