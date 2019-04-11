package com.example.hassannaqvi.fas.ui.tool2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.example.hassannaqvi.fas.R;
import com.example.hassannaqvi.fas.RMOperations.crudOperations;
import com.example.hassannaqvi.fas.core.CONSTANTS;
import com.example.hassannaqvi.fas.core.MainApp;
import com.example.hassannaqvi.fas.data.DAO.FormsDAO;
import com.example.hassannaqvi.fas.data.entities.Forms;
import com.example.hassannaqvi.fas.databinding.ActivitySectionATool2Binding;
import com.example.hassannaqvi.fas.ui.EndingActivity;
import com.example.hassannaqvi.fas.validation.ValidatorClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class SectionA_tool_2Activity extends AppCompatActivity {

    private static final String TAG = SectionA_tool_2Activity.class.getName();
    ActivitySectionATool2Binding bi;
    String deviceID;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_a_tool_2);
        bi.setCallback(this);

        setContentUI();
    }

    private void setContentUI() {
        this.setTitle(R.string.section1_tool2);

        deviceID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public void BtnContinue() {
        if (!formValidation())
            return;

        try {
            SaveDraft();
            if (UpdateDB()) {

                String surveyType = bi.fas02a00a.isChecked() ? "1" : bi.fas02a00d.isChecked() ? "4" : "0";
                MainApp.setParamValues(this, CONSTANTS._URI_DATAMAP_02_SURVEY_TYPE, surveyType);
                MainApp.setParamValues(this, CONSTANTS._URI_DATAMAP_02_HF_NO, bi.fas02a001.getText().toString());
                MainApp.setParamValues(this, CONSTANTS._URI_DATAMAP_02_W_ID, bi.fas02amw01.getText().toString());

                MainApp.stActivity(this, this, SectionB_tool_2Activity.class, fc);
            } else {
                Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean UpdateDB() {

        try {

            Long longID = new crudOperations(SectionA_tool_2Activity.this, FormsDAO.class.getName(), "formsDao", "insertForm", fc).execute().get();

            if (longID != 0) {
                fc.setId(longID.intValue());

                fc.setUid(deviceID + fc.getId());

                longID = new crudOperations(SectionA_tool_2Activity.this, FormsDAO.class.getName(), "formsDao", "updateForm", fc).execute().get();
                return longID == 1;

            } else {
                return false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return false;

    }

    private void SaveDraft() throws JSONException {
        fc = new Forms();
        fc.setDevicetagID(MainApp.getTagName(this));
        fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        fc.setUsername(MainApp.userName);
        fc.setFormDate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        fc.setDeviceID(deviceID);
        fc.setFormType(CONSTANTS._URI_FORM_TOOL1);
        setGPS(fc);


        JSONObject s01 = new JSONObject();


        s01.put("fas02a00",
                bi.fas02a00a.isChecked() ? "1"
                        : bi.fas02a00b.isChecked() ? "2"
                        : bi.fas02a00c.isChecked() ? "3"
                        : bi.fas02a00d.isChecked() ? "4" : "0");

        s01.put("fas02a001", bi.fas02a001.getText().toString());
        s01.put("fas02amw01", bi.fas02amw01.getText().toString());

        s01.put("fas02a02", bi.fas02a02.getText().toString());


        s01.put("fas02a07",
                bi.fas02a07a.isChecked() ? "1"
                        : bi.fas02a07b.isChecked() ? "2"
                        : "0");

        s01.put("fas02a08a", bi.fas02a08a.isChecked() ? "1" : "0");
        s01.put("fas02a08b", bi.fas02a08b.isChecked() ? "2" : "0");
        s01.put("fas02a08c", bi.fas02a08c.isChecked() ? "3" : "0");
        s01.put("fas02a08d", bi.fas02a08d.isChecked() ? "4" : "0");
        s01.put("fas02a0896", bi.fas02a0896.isChecked() ? "96" : "0");
        s01.put("fas02a0896x", bi.fas02a0896x.getText().toString());

        fc.setSa1(String.valueOf(s01));

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpSecAtool02);
    }

    public void BtnEnd() {

        if (!ValidatorClass.EmptyRadioButton(this, bi.fas02a00, bi.fas02a00a, getString(R.string.fas01a00)))
            return;
        if (!ValidatorClass.EmptyTextBox(this, bi.fas02amw01, getString(R.string.fas02a01)))
            return;
        if (!ValidatorClass.EmptyTextBox(this, bi.fas02a001, getString(R.string.fas01a01)))
            return;

        new AlertDialog.Builder(this)
                .setTitle("END INTERVIEW")
                .setIcon(R.drawable.ic_power_settings_new_black_24dp)
                .setCancelable(false)
                .setCancelable(false)
                .setMessage("Do you want to End Interview??")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        try {
                            SaveDraft();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (!UpdateDB()) {
                            Toast.makeText(SectionA_tool_2Activity.this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        MainApp.endActivitySetRouting(SectionA_tool_2Activity.this, SectionA_tool_2Activity.this, EndingActivity.class, false, fc);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();

    }

    public void setGPS(Forms fc) {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");
            String elevation = GPSPref.getString("Elevation", "0");

            if (lat == "0" && lang == "0") {
                Toast.makeText(this, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            fc.setGpsLat(lat);
            fc.setGpsLng(lang);
            fc.setGpsAcc(acc);
            fc.setGpsDT(date); // Timestamp is converted to date above
            fc.setGpsElev(elevation);

            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }
}
