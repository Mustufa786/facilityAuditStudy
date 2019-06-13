package edu.aku.hassannaqvi.fas.ui.tool1;

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

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.fas.JSON.GeneratorClass;
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
import edu.aku.hassannaqvi.fas.databinding.ActivityInfoBinding;
import edu.aku.hassannaqvi.fas.get.db.GetAllDBData;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ClearClass;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

import static edu.aku.hassannaqvi.fas.ui.LoginActivity.db;

public class InfoActivity extends AppCompatActivity {

    private static final String TAG = InfoActivity.class.getName();
    ActivityInfoBinding bi;
    Forms childDT;
    String fTYPE = "", fExt = "", deviceID;
    Class<?> routeClass;
    private Forms fc;
    Map<String, HFA> hfaMap;
    List<String> district_code, uc_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_info);
        bi.setCallback(this);
        this.setTitle(R.string.hfa11);
        deviceID = Settings.Secure.getString(InfoActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
        setContentUI();

        listeneres();
    }

    private void listeneres() {

        bi.hfa1116.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (group.getCheckedRadioButtonId() == bi.hfa1116b.getId()) {
                    bi.fldGrp11720.setVisibility(View.GONE);
                    ClearClass.ClearAllFields(bi.fldGrp11720, null);
                    bi.btnEnd.setVisibility(View.VISIBLE);
                    bi.btnContinue.setVisibility(View.GONE);
                } else {
                    bi.fldGrp11720.setVisibility(View.VISIBLE);
                    bi.btnEnd.setVisibility(View.GONE);
                    bi.btnContinue.setVisibility(View.VISIBLE);
                }

            }
        });

        bi.hfa1114.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId != bi.hfa1114a.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrphfa1115, null);
                }
            }
        });
//        bi.hfa1119.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                if (group.getCheckedRadioButtonId() == bi.hfa1119a.getId()) {
//                    bi.btnContinue.setVisibility(View.VISIBLE);
//                    bi.btnEnd.setVisibility(View.GONE);
//                } else {
//                    bi.btnEnd.setVisibility(View.VISIBLE);
//                    bi.btnContinue.setVisibility(View.GONE);
//                }
//
//            }
//        });
    }

    private void setContentUI() {
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

            bi.hfa1103a.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, district_names));

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bi.hfa1103a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;

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

                    bi.hfa1103b.setAdapter(new ArrayAdapter<>(InfoActivity.this, android.R.layout.simple_spinner_dropdown_item, uc_names));

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

        bi.hfa1103b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;

                //HFA
                hfaMap = new HashMap<>();
                List<String> hfa_names = new ArrayList<>();
                hfa_names.add("....");
                try {
                    Collection<HFA> col_ucs = (Collection<HFA>) new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getAllHfaByDistrictUC")
                            .execute(district_code.get(bi.hfa1103a.getSelectedItemPosition()), uc_code.get(bi.hfa1103b.getSelectedItemPosition())).get();
                    for (HFA hfa : col_ucs) {
                        hfa_names.add(hfa.getHf_name());
                        hfaMap.put(hfa.getHf_name(), hfa);
                    }

                    bi.hfa1103c.setAdapter(new ArrayAdapter<>(InfoActivity.this, android.R.layout.simple_spinner_dropdown_item, hfa_names));

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

        bi.hfa1103c.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;
                bi.hfa1101.setText(hfaMap.get(bi.hfa1103c.getSelectedItem().toString()).getHf_code());
                bi.hfa1102.setText(hfaMap.get(bi.hfa1103c.getSelectedItem().toString()).getHf_name());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

            String surveyType = bi.hfa1100a.isChecked() ? "1" : bi.hfa1100b.isChecked() ? "2" : "0";
            MainApp.setParamValues(this, CONSTANTS._URI_DATAMAP_SURVEY_TYPE, surveyType);
            MainApp.setParamValues(this, CONSTANTS._URI_DATAMAP_HF_NO, bi.hfa1101.getText().toString());

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

    private void SaveDraft() throws JSONException {
        fc = new Forms();
        fc.setDevicetagID(MainApp.getTagName(this));
        fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        fc.setUsername(MainApp.userName);
        fc.setFormDate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        fc.setDeviceID(deviceID);
        fc.setFormType(CONSTANTS._URI_FORM_TOOL1);
        fc.setDistrictcode(hfaMap.get(bi.hfa1103c.getSelectedItem().toString()).getDist_code());
        fc.setUccode(hfaMap.get(bi.hfa1103c.getSelectedItem().toString()).getUc_code());
        fc.setHfcode(hfaMap.get(bi.hfa1103c.getSelectedItem().toString()).getHf_code());

        setGPS(fc);

        //JSONObject Json = GeneratorClass.getContainerJSON(bi.fldGrpllInfoA, true);
        JSONObject Json = GeneratorClass.getContainerJSON(bi.fldGrpllInfoA, true);

        fc.setSa1(String.valueOf(Json));
    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllInfoA);
    }


    public void BtnEnd() {

        if (!ValidatorClass.EmptyRadioButton(this, bi.hfa1100, bi.hfa1100a, getString(R.string.hfa1100)))
            return;
        if (!ValidatorClass.EmptyTextBox(this, bi.hfa1101, getString(R.string.hfa1101)))
            return;
        if (!ValidatorClass.EmptyTextBox(this, bi.hfa1102, getString(R.string.hfa1102)))
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
                        SaveDraft();
                        if (!UpdateDB()) {
                            Toast.makeText(InfoActivity.this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        MainApp.endActivitySetRouting(InfoActivity.this, InfoActivity.this, EndingActivity.class, false, fc);
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


    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}
