package za.ac.cput.yusiry.barapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import za.ac.cput.yusiry.barapp.model.Staff;
import za.ac.cput.yusiry.barapp.repositories.rest.RestStaffAPI;

public class AddStaffActivity extends AppCompatActivity {

    Button btnSave;
    EditText txtStaffName, txtStaffSurname, txtStaffPhone, txtStaffIIDNumber, txtAddress;
    int id = 0;

    //STAFF
    static Staff staffer = new Staff();
    List<Staff> staff = new LinkedList<Staff>();
    RestStaffAPI restStaffAPI = new RestStaffAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        txtStaffName = (EditText) findViewById(R.id.txtStaffName);
        txtStaffSurname = (EditText) findViewById(R.id.txtStaffSurname);
        txtStaffPhone = (EditText) findViewById(R.id.txtPhone);
        txtStaffIIDNumber = (EditText) findViewById(R.id.txtIDnumber);
        txtAddress = (EditText) findViewById(R.id.txtStaffAddress);


        btnSave = (Button) findViewById(R.id.btnStaffSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id == 0){
                    staffer.setName(txtStaffName.getText().toString());
                    staffer.setSurname(txtStaffSurname.getText().toString());
                    staffer.setPhone(txtStaffPhone.getText().toString());
                    staffer.setIDnumber(txtStaffIIDNumber.getText().toString());
                    staffer.setAddress(txtAddress.getText().toString());
                }else{
                    staffer.setId(id);
                    staffer.setName(txtStaffName.getText().toString());
                    staffer.setSurname(txtStaffSurname.getText().toString());
                    staffer.setPhone(txtStaffPhone.getText().toString());
                    staffer.setIDnumber(txtStaffIIDNumber.getText().toString());
                    staffer.setAddress(txtAddress.getText().toString());
                }
                new HttpRequestTask().execute();
            }
        });

        Bundle extras = getIntent().getExtras();

        if(extras != null)
        {
            staffer.setId(extras.getInt("id"));
            staffer.setName(extras.getString("name"));
            staffer.setSurname(extras.getString("surname"));
            staffer.setPhone(extras.getString("phone"));
            staffer.setIDnumber(extras.getString("idnumber"));
            staffer.setAddress(extras.getString("address"));

            id = staffer.getId();
            txtStaffName.setText(staffer.getName());
            txtStaffSurname.setText(staffer.getSurname());
            txtStaffPhone.setText(staffer.getPhone());
            txtStaffIIDNumber.setText(staffer.getIDnumber());
            txtAddress.setText(staffer.getAddress());

        }
    }

    private class HttpRequestTask extends AsyncTask<String, Void, String> {
        ProgressDialog pd = new ProgressDialog(AddStaffActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("Loading...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            if(id != 0){
                try{
                    restStaffAPI.post(staffer);
                }catch (Exception e){

                }
                return "Successfully Updated Staff Member";
            }
            else{
                try{
                    restStaffAPI.post(staffer);
                }catch (Exception e){

                }
                return "Successfully Created New Staff Member";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }
}
