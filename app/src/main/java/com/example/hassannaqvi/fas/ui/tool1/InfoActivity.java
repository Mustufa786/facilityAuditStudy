package com.example.hassannaqvi.fas.ui.tool1;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.example.hassannaqvi.fas.JSON.GeneratorClass;
import com.example.hassannaqvi.fas.R;
import com.example.hassannaqvi.fas.RMOperations.crudOperations;
import com.example.hassannaqvi.fas.core.CONSTANTS;
import com.example.hassannaqvi.fas.core.MainApp;
import com.example.hassannaqvi.fas.data.DAO.FormsDAO;
import com.example.hassannaqvi.fas.data.entities.Forms;
import com.example.hassannaqvi.fas.databinding.ActivityInfoBinding;
import com.example.hassannaqvi.fas.ui.EndingActivity;
import com.example.hassannaqvi.fas.validation.ValidatorClass;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class InfoActivity extends AppCompatActivity {

    private static final String TAG = InfoActivity.class.getName();
    ActivityInfoBinding bi;
    Forms childDT;
    String fTYPE = "", fExt = "", deviceID;
    Class<?> routeClass;
    Forms.Simple_Forms_04_05 sInfo_parse;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_info);
        bi.setCallback(this);
        this.setTitle(R.string.section1);
        deviceID = Settings.Secure.getString(InfoActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
        setContentUI();
    }

    private void setContentUI() {

    }

    private Class<?> selectedForm(String fType) {

        Class retClass = null;

        switch (fType) {
            case "4":
                fExt = "f4_";
                //retClass = Form04_EF_A.class;
                break;
            case "5":
                fExt = "f5_";
                // retClass = Form05IdBAActivity.class;
                break;
            case "6":
                fExt = "f6_";
                //retClass = Form6AnthroActivity.class;
                break;

        }

        return retClass;
    }

    public void BtnContinue() {
        if (!formValidation())
            return;

        SaveDraft();
        if (UpdateDB()) {
            MainApp.stActivity(this, this, SectionCActivity.class, fc);
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean UpdateDB() {

        try {

            Long longID = new crudOperations(InfoActivity.this, FormsDAO.class.getName(), "formsDao", "insertForm", fc).execute().get();

            if (longID != 0) {
                fc.setId(longID.intValue());

                fc.setUid(deviceID + fc.getId());

                longID = new crudOperations(InfoActivity.this, FormsDAO.class.getName(), "formsDao", "updateForm", fc).execute().get();
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

    private void SaveDraft() {
        fc = new Forms();
        fc.setDevicetagID(MainApp.getTagName(this));
        fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        fc.setUsername(MainApp.userName);
        fc.setFormDate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        fc.setDeviceID(deviceID);
        fc.setFormType(CONSTANTS._URI_FORM_TOOL1);
        setGPS(fc);

        JSONObject Json = GeneratorClass.getContainerJSON(bi.fldGrpllInfoA, true);
        fc.setSa1(String.valueOf(Json));
    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllInfoA);
    }

    public void BtnEnd() {

        if (!ValidatorClass.EmptyRadioButton(this, bi.fas01a00, bi.fas01a00a, getString(R.string.fas01a00)))
            return;
        if (!ValidatorClass.EmptyTextBox(this, bi.fas01a01, getString(R.string.fas01a01)))
            return;
        if (!ValidatorClass.EmptyTextBox(this, bi.fas01a02, getString(R.string.fas01a02)))
            return;

        SaveDraft();
        if (UpdateDB()) {
            MainApp.endActivity(this, this, EndingActivity.class, false, fc);
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }
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
