package za.ac.cput.yusiry.barapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import za.ac.cput.yusiry.barapp.model.Staff;
import za.ac.cput.yusiry.barapp.repositories.rest.RestStaffAPI;

public class ViewStaffActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<String> gridItems = new ArrayList<String>();

    //STAFF
    RestStaffAPI restStaffAPI = new RestStaffAPI();
    List<Staff> staff = new LinkedList<Staff>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_staff);

        gridView = (GridView) findViewById(R.id.gridViewStaff);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddStaffActivity.class);
                intent.putExtra("id", staff.get(position).getId());
                intent.putExtra("name", staff.get(position).getName());
                intent.putExtra("surname", staff.get(position).getSurname());
                intent.putExtra("phone", staff.get(position).getPhone());
                intent.putExtra("address", staff.get(position).getAddress());
                intent.putExtra("idnumber", staff.get(position).getIDnumber());
                startActivity(intent);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Staff staff1 = staff.get(position);
                //String message = restStaffAPI.delete(staff1);
                restStaffAPI.delete(staff1);
                Toast.makeText(ViewStaffActivity.this, staff1.getName() + " " + position + " " + id, Toast.LENGTH_LONG).show();
                return true;
            }
        });

        new HttpAsyncTask().execute();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        gridView = (GridView) findViewById(R.id.gridViewStaff);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddStaffActivity.class);
                intent.putExtra("id", staff.get(position).getId());
                intent.putExtra("name", staff.get(position).getName());
                intent.putExtra("surname", staff.get(position).getSurname());
                intent.putExtra("phone", staff.get(position).getPhone());
                intent.putExtra("address", staff.get(position).getAddress());
                intent.putExtra("idnumber", staff.get(position).getIDnumber());
                startActivity(intent);
            }
        });
        new HttpAsyncTask().execute();
    }

    private class HttpAsyncTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog pdLoading = new ProgressDialog(ViewStaffActivity.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pdLoading.setMessage("Loading...");
            pdLoading.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                staff = restStaffAPI.getAll();
            }catch (Exception e){
                Log.e("ViewStaffActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(staff.size() != 0){
                String[] temp = new String[staff.size()];
                for(int i = 0; i < staff.size(); i++){
                    temp[i] = staff.get(i).getId() + " " + staff.get(i).getName() + " " + staff.get(i).getSurname();
                }
                populateGridView(temp);
            }
            else{
                gridView.setAdapter(null);
            }
            pdLoading.dismiss();
        }
    }

    public void populateGridView(String[] arr2){
        gridItems.clear();

        for(int i=0; i< arr2.length; i++){
            gridItems.add(arr2[i]);
        }

        ArrayAdapter<String> gridAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gridItems);
        gridView.setAdapter(gridAdapter);
    }
}
