package com.example.hassannaqvi.leaps_scaleup.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hassannaqvi.leaps_scaleup.JSON.GeneratorClass;
import com.example.hassannaqvi.leaps_scaleup.R;
import com.example.hassannaqvi.leaps_scaleup.RMOperations.crudOperations;
import com.example.hassannaqvi.leaps_scaleup.core.MainApp;
import com.example.hassannaqvi.leaps_scaleup.data.DAO.FormsDAO;
import com.example.hassannaqvi.leaps_scaleup.data.DAO.GetFncDAO;
import com.example.hassannaqvi.leaps_scaleup.data.entities.Forms_04_05;
import com.example.hassannaqvi.leaps_scaleup.databinding.ActivityInfoBinding;
import com.example.hassannaqvi.leaps_scaleup.get.db.GetIndDBData;
import com.example.hassannaqvi.leaps_scaleup.validation.ClearClass;
import com.example.hassannaqvi.leaps_scaleup.validation.validatorClass;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.hassannaqvi.leaps_scaleup.ui.LoginActivity.db;
import static com.example.hassannaqvi.leaps_scaleup.utils.JsonUtils.mergeJSONObjects;

public class InfoActivity extends AppCompatActivity {

    private static final String TAG = InfoActivity.class.getName();
    ActivityInfoBinding bi;
    public static Forms_04_05 fc_4_5;
    String fTYPE = "", fExt = "", deviceID;
    Class<?> routeClass;
    Forms_04_05 childDT;
    Forms_04_05.Simple_Forms_04_05 sInfo_parse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_info);
        bi.setCallback(this);
        deviceID = Settings.Secure.getString(InfoActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
        setContentUI();
    }

    private void setContentUI() {

        /*Getting intent data*/
        fTYPE = getIntent().getStringExtra("fType");

        /*Setting BlackBox date picker*/
        bi.lsid5.setManager(getSupportFragmentManager());
        bi.lsid5.setMaxDate(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));
        bi.lsid14.setManager(getSupportFragmentManager());
        bi.lsid14.setMaxDate(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));

        /*Setting edittext*/
        bi.lsid7m.setManager(this, "Month");

        /*Setting listeners*/
        bi.lsid1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                bi.fldgrpls01.setVisibility(GONE);
                ClearClass.ClearAllFields(bi.fldgrpls01);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bi.lsid11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.lsid11b.getId()) {
                    bi.lsid12.clearCheck();
                    bi.lsid13.clearCheck();
                }
            }
        });

        bi.lsid12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.lsid12a.getId()) {
                    bi.lsid13.clearCheck();
                }
            }
        });

        /*Calling fnc*/
        routeClass = selectedForm(fTYPE);

    }

    private Class<?> selectedForm(String fType) {

        Class retClass = null;

        switch (fType) {
            case "4":
                fExt = "f4_";
                retClass = Form04_EF_A.class;
                break;
            case "5":
                fExt = "f5_";
                retClass = Form05IdBAActivity.class;
                break;
            case "6":
                fExt = "f6_";
                retClass = Form6AnthroActivity.class;
                break;

        }

        return retClass;
    }

    public void BtnContinue() {
        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {
                startActivity(new Intent(getApplicationContext(), routeClass));
            } else {
                Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void BtnIDValid() {
        if (!validatorClass.EmptyTextBox(this, bi.lsid1, getString(R.string.ls01a01))) {
            return;
        }

        try {
            Object childData = new GetIndDBData(db, GetFncDAO.class.getName(), "getFncDao", "getChildRecord").execute(bi.lsid1.getText().toString()).get();

            if (childData != null) {
                Toast.makeText(this, "Child ID validate..", Toast.LENGTH_SHORT).show();
                childDT = (Forms_04_05) childData;

                // Child Name
                bi.lsid4.setText(childDT.getParticipantName());
                // Form date of enrollment
                bi.lsid5.setText(childDT.getFormDate());

                sInfo_parse = new Gson().fromJson(childDT.getSInfo(), Forms_04_05.Simple_Forms_04_05.class);

                // Age setting
                String y, m;
                if (sInfo_parse.getLs01f04().equals("2")) {
                    y = sInfo_parse.getLs01f05y();
                    m = sInfo_parse.getLs01f05m();
                } else {
                    y = String.valueOf(MainApp.ageInYear_MonthByDOB(sInfo_parse.getLs01f03(), 'y'));
                    m = String.valueOf(MainApp.ageInYear_MonthByDOB(sInfo_parse.getLs01f03(), 'm'));
                }
                bi.lsid7y.setText(y);
                bi.lsid7m.setText(m);

                // Round Setting
                bi.lsid10.check(
                        sInfo_parse.getLs01a07().equals("1") ? bi.lsid10a.getId() :
                                sInfo_parse.getLs01a07().equals("2") ? bi.lsid10b.getId() :
                                        sInfo_parse.getLs01a07().equals("3") ? bi.lsid10c.getId() :
                                                sInfo_parse.getLs01a07().equals("4") ? bi.lsid10d.getId() : bi.lsid10a.getId());

                for (byte i = 0; i < bi.lsid10.getChildCount(); i++) {
                    bi.lsid10.getChildAt(i).setEnabled(false);
                }

                // Enable view
                bi.fldgrpls01.setVisibility(VISIBLE);

            } else {
                Toast.makeText(this, "Child ID not found!!", Toast.LENGTH_SHORT).show();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    private boolean UpdateDB() {

        try {

            Long longID = new crudOperations(db, fc_4_5).execute(FormsDAO.class.getName(), "formsDao", "insertForm_04_05").get();

            if (longID != 0) {
                fc_4_5.setId(longID.intValue());

                fc_4_5.setUid(deviceID + fc_4_5.getId());

                longID = new crudOperations(db, fc_4_5).execute(FormsDAO.class.getName(), "formsDao", "updateForm_04_05").get();
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

        fc_4_5 = new Forms_04_05();
        fc_4_5.setDevicetagID(MainApp.getTagName(this));
        fc_4_5.setFormType(fTYPE);
        fc_4_5.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        fc_4_5.setUsername(MainApp.userName);
        fc_4_5.setFormDate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        fc_4_5.setDeviceID(deviceID);
        fc_4_5.setParticipantID(bi.lsid1.getText().toString());
        fc_4_5.setClustercode(childDT.getClustercode());

        setGPS(fc_4_5); // Set GPS

        JSONObject jsonInfo = new JSONObject();
        try {
            jsonInfo.put(fExt + "lsid3", sInfo_parse.getLs01a06());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject Json = GeneratorClass.getContainerJSON(bi.fldgrpls01, true, fExt);
        fc_4_5.setSInfo(String.valueOf(mergeJSONObjects(jsonInfo, Json)));

    }

    public void setGPS(Forms_04_05 fc_4_5) {
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

            fc_4_5.setGpsLat(lat);
            fc_4_5.setGpsLng(lang);
            fc_4_5.setGpsAcc(acc);
            fc_4_5.setGpsDT(date); // Timestamp is converted to date above
            fc_4_5.setGpsElev(elevation);

            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }

    private boolean formValidation() {

        return validatorClass.EmptyCheckingContainer(this, bi.fldgrpls01);
    }

    public void BtnEnd() {

        SaveDraft();
        if (UpdateDB()) {
            MainApp.endActivity(this, this, EndingActivity.class, false, fc_4_5);
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }
    }


}
