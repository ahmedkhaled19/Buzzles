package com.example.ahmedkhaled.buzzels.SignUp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ahmedkhaled.buzzels.MainActivity;
import com.example.ahmedkhaled.buzzels.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class SignUpActivity extends AppCompatActivity implements SignUpView, DatePickerDialog.OnDateSetListener {

    private Spinner JobSpinner, CountrySpinner;
    private EditText username, fullname, email, password, confirmpassword, date;
    private CircleImageView image;
    private RadioGroup ganeder;
    private RadioButton gselected;
    private SignUpPresenter presenter;
    private String Cid = "1", Jid = "1", DOB = " ";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        image = (CircleImageView) findViewById(R.id.reg_pic);
        username = (EditText) findViewById(R.id.reg_Uname);
        fullname = (EditText) findViewById(R.id.reg_Fname);
        email = (EditText) findViewById(R.id.reg_email);
        password = (EditText) findViewById(R.id.reg_pw);
        confirmpassword = (EditText) findViewById(R.id.reg_cpw);
        date = (EditText) findViewById(R.id.dateofbirth);
        ganeder = (RadioGroup) findViewById(R.id.radio_choose);
        JobSpinner = (Spinner) findViewById(R.id.joptitle);
        CountrySpinner = (Spinner) findViewById(R.id.Country_title);
        presenter = new SignUpPresenter(this, new SignUpModel());
        dialog = new ProgressDialog(this);
        CountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d("ahmeddd", String.valueOf(position));
                Cid = String.valueOf(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        JobSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d("ahmeddd", String.valueOf(position));
                Jid = String.valueOf(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void SignUp(View view) {
        dialog = ProgressDialog.show(SignUpActivity.this, "", "Signing Up... ", true);
        dialog.show();
        presenter.StepOne();
    }

    public void ChossePhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            image.setImageURI(data.getData());
            String filePath = "";
            String wholeID = DocumentsContract.getDocumentId(data.getData());
            // Split at colon, use second item in the array
            String id = wholeID.split(":")[1];

            String[] column = {MediaStore.Images.Media.DATA};

            // where id is equal to
            String sel = MediaStore.Images.Media._ID + "=?";

            Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    column, sel, new String[]{id}, null);

            int columnIndex = cursor.getColumnIndex(column[0]);

            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
            presenter.GetImage(filePath);
        }
    }


    @Override
    public String getUsername() {
        return username.getText().toString().trim();
    }

    @Override
    public String getFullname() {
        return fullname.getText().toString().trim();
    }

    @Override
    public String getEmail() {
        return email.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public String getCpassword() {
        return confirmpassword.getText().toString().trim();
    }

    @Override
    public String getGender() {
        int selected = ganeder.getCheckedRadioButtonId();
        gselected = (RadioButton) findViewById(selected);
        if (gselected == null) {
            return null;
        }
        return String.valueOf(gselected.getText());
    }

    @Override
    public String getDate() {
        return DOB;
    }

    @Override
    public String getCountry() {
        return Cid;
    }

    @Override
    public String getJob() {
        return Jid;
    }

    @Override
    public void SetCountry(List<String> data) {
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(this, R.layout.spinner_item, data);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CountrySpinner.setAdapter(spinnerAdapter);
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void SetJob(List<String> data) {
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(this, R.layout.spinner_item, data);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JobSpinner.setAdapter(spinnerAdapter);
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void ErrorMessage(String s) {
        dialog.dismiss();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void errorinpasword() {
        dialog.dismiss();
        Toast.makeText(this, "password is empty or not match ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enterdata() {
        dialog.dismiss();
        Toast.makeText(this, "Please fill all of the data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Startactivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void ChooseDate(View view) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        Calendar maxCalendar = Calendar.getInstance();
        maxCalendar.set(Calendar.YEAR, 2001);
        maxCalendar.set(Calendar.MONTH, 11);
        maxCalendar.set(Calendar.DAY_OF_MONTH, 31);

        Calendar minCalendar = Calendar.getInstance();
        minCalendar.set(Calendar.YEAR, 1920);
        minCalendar.set(Calendar.MONTH, 0);
        minCalendar.set(Calendar.DAY_OF_MONTH, 1);

        dpd.setMinDate(minCalendar);
        dpd.setMaxDate(maxCalendar);

        dpd.setVersion(DatePickerDialog.Version.VERSION_1);
        dpd.show(getFragmentManager(), "DatePickerDialog");

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DOB = sdf.format(c.getTime());
        date.setText(DOB);
    }
}






