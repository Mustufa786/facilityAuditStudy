package edu.aku.hassannaqvi.fas.ui.tool2;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.fas.R;
import edu.aku.hassannaqvi.fas.RMOperations.crudOperations;
import edu.aku.hassannaqvi.fas.core.CONSTANTS;
import edu.aku.hassannaqvi.fas.core.MainApp;
import edu.aku.hassannaqvi.fas.data.DAO.FormsDAO;
import edu.aku.hassannaqvi.fas.data.DAO.GetFncDAO;
import edu.aku.hassannaqvi.fas.data.entities.Districts;
import edu.aku.hassannaqvi.fas.data.entities.Forms;
import edu.aku.hassannaqvi.fas.data.entities.HFA;
import edu.aku.hassannaqvi.fas.data.entities.UCs;
import edu.aku.hassannaqvi.fas.databinding.ActivitySectionATool2Binding;
import edu.aku.hassannaqvi.fas.get.db.GetAllDBData;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.utils.DateUtils;
import edu.aku.hassannaqvi.fas.validation.ClearClass;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

import static edu.aku.hassannaqvi.fas.ui.LoginActivity.db;


public class SectionA_tool_2Activity extends AppCompatActivity {

    private static final String TAG = SectionA_tool_2Activity.class.getName();
    ActivitySectionATool2Binding bi;
    String deviceID;
    Map<String, HFA> hfaMap;
    List<String> district_code, tehsil_code, uc_code;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_a_tool_2);
        bi.setCallback(this);
        bi.fas02a004.setMinDate(DateUtils.getYearsBack("dd/MM/yyyy", -1));
        setContentUI();

    }


    private void setContentUI() {
        this.setTitle(R.string.section1_tool2);
        deviceID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);


        //districts
        district_code = new ArrayList<>();
        List<String> district_names = new ArrayList<>();
        district_code.add("....");
        district_names.add("....");
        try {
            Collection<Districts> col_districts = (Collection<Districts>) new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getAllDistricts").execute().get();
            for (Districts dist : col_districts) {
                district_names.add(dist.getDist_name());
                district_code.add(dist.getDist_code());
            }

            bi.fas02a001a.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, district_names));

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bi.fas02a001a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;

                //Tehsil
                /*tehsil_code = new ArrayList<>();
                List<String> tehsil_names = new ArrayList<>();
                tehsil_code.add("1234");
                tehsil_names.add("Working");
                bi.fas02a001d.setAdapter(new ArrayAdapter<>(SectionA_tool_2Activity.this, android.R.layout.simple_spinner_dropdown_item, tehsil_names));*/


                //UC
                uc_code = new ArrayList<>();
                List<String> uc_names = new ArrayList<>();
                uc_code.add("....");
                uc_names.add("....");
                try {
                    Collection<UCs> col_ucs = (Collection<UCs>) new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getAllUcsByDistricts")
                            .execute(district_code.get(i)).get();
                    for (UCs uc : col_ucs) {
                        uc_names.add(uc.getUc_name());
                        uc_code.add(uc.getUc_code());
                    }

                    bi.fas02a001b.setAdapter(new ArrayAdapter<>(SectionA_tool_2Activity.this, android.R.layout.simple_spinner_dropdown_item, uc_names));

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bi.fas02a001b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;

                //HFA
                hfaMap = new HashMap<>();
                List<String> hfa_names = new ArrayList<>();
                hfa_names.add("....");
                try {
                    Collection<HFA> col_ucs = (Collection<HFA>) new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getAllHfaByDistrictUC")
                            .execute(district_code.get(bi.fas02a001a.getSelectedItemPosition()), uc_code.get(bi.fas02a001b.getSelectedItemPosition())).get();
                    for (HFA hfa : col_ucs) {
                        hfa_names.add(hfa.getHf_name());
                        hfaMap.put(hfa.getHf_name(), hfa);
                    }

                    bi.fas02a001c.setAdapter(new ArrayAdapter<>(SectionA_tool_2Activity.this, android.R.layout.simple_spinner_dropdown_item, hfa_names));

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bi.fas02a001c.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;
                bi.fas02a001.setText(hfaMap.get(bi.fas02a001c.getSelectedItem().toString()).getHf_code());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        bi.fas02a07.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.fas02a07a.getId()) {
                    ClearClass.ClearAllFields(bi.fas02a08cv, null);
                } else {
                    ClearClass.ClearAllFields(bi.fas02a08cv, null);
                }
            }
        });

    }

    public void BtnContinue() {
        if (!formValidation())
            return;

        try {
            SaveDraft();
            if (UpdateDB()) {

                String surveyType = bi.fas02a00a.isChecked() ? "1" : bi.fas02a00b.isChecked() ? "2" : "0";
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
        fc.setFormType(CONSTANTS._URI_FORM_TOOL2);
        /*fc.setDistrictcode();
        fc.setTehsilCode();
        fc.setUccode(bi.fas02a001b.getSelectedItemPosition());
        fc.setHfcode();*/
        fc.setDistrictcode(hfaMap.get(bi.fas02a001c.getSelectedItem().toString()).getDist_code());
        //fc.setTehsilcode(hfaMap.get(bi.fas02a001c.getSelectedItem().toString()).getTehsil_code());
        fc.setUccode(hfaMap.get(bi.fas02a001c.getSelectedItem().toString()).getUc_code());
        fc.setHfcode(hfaMap.get(bi.fas02a001c.getSelectedItem().toString()).getHf_code());
        fc.setHfname(hfaMap.get(bi.fas02a001c.getSelectedItem().toString()).getHf_name());
        setGPS(fc);

        JSONObject s01 = new JSONObject();

        s01.put("fas02a00", bi.fas02a00a.isChecked() ? "1"
                : bi.fas02a00b.isChecked() ? "4"
                : "0");

        //s01.put("fas02a001", bi.fas02a001.getText().toString());
        s01.put("fas02a002", bi.fas02a002.getText().toString());
        s01.put("fas02a003", bi.fas02a003.getText().toString());
        s01.put("fas02a004", bi.fas02a004.getText().toString());

        s01.put("fas02amw01", bi.fas02amw01.getText().toString());

        s01.put("fas02a07", bi.fas02a07a.isChecked() ? "1"
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

        if (!ValidatorClass.EmptyRadioButton(this, bi.fas02a00, bi.fas02a00a, getString(R.string.hfa1100)))
            return;
        if (!ValidatorClass.EmptyTextBox(this, bi.fas02amw01, getString(R.string.fas02a01)))
            return;
        if (!ValidatorClass.EmptyTextBox(this, bi.fas02a001, getString(R.string.hfa1101)))
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
