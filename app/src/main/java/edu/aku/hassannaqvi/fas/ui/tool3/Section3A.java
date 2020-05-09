package edu.aku.hassannaqvi.fas.ui.tool3;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.fas.R;
import edu.aku.hassannaqvi.fas.RMOperations.crudOperations;
import edu.aku.hassannaqvi.fas.core.CONSTANTS;
import edu.aku.hassannaqvi.fas.core.MainApp;
import edu.aku.hassannaqvi.fas.data.DAO.FormsDAO;
import edu.aku.hassannaqvi.fas.data.entities.Forms;
import edu.aku.hassannaqvi.fas.databinding.ActivitySection3aBinding;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

public class Section3A extends AppCompatActivity {

    private static final String TAG = Section3A.class.getName();
    private ActivitySection3aBinding bi;
    String deviceID;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section3a);
        bi.setCallback(this);

        setContentUI();
        setListenersUI();

    }


    private void setContentUI() {
        this.setTitle(R.string.section4_tool2);
        deviceID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);
    }


    private void setListenersUI() {

       /* //   fas02d01
        bi.fas02d01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02d01a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpllSecD02fas02d02, null);
            }
        });


        bi.fas02d0298.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.fldGrpfas02d02, false);
                    bi.fldGrpfas02d02.setTag("-1");
                    bi.fas02d0298.setTag(null);
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpfas02d02, true);
                    bi.fldGrpfas02d02.setTag(null);
                    bi.fas02d0298.setTag("-1");
                }
            }
        });  */


    }


    public void BtnContinue() {
        if (!formValidation())
            return;

        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            MainApp.endActivitySetRouting(this, this, Section3B.class, true, fc);
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean UpdateDB() {

        try {

            Long longID = new crudOperations(Section3A.this, FormsDAO.class.getName(), "formsDao", "insertForm", fc).execute().get();

            if (longID != 0) {
                fc.setId(longID.intValue());

                fc.setUid(deviceID + fc.getId());

                longID = new crudOperations(Section3A.this, FormsDAO.class.getName(), "formsDao", "updateForm", fc).execute().get();
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
        setGPS(fc);



        JSONObject json = new JSONObject();

        json.put("hf1_00", bi.hf100a.isChecked() ? "2"
                : bi.hf100b.isChecked() ? "3"
                : bi.hf100v.isChecked() ? "4"
                : "-1");

        json.put("hf1_01", bi.hf101.getText().toString().trim().isEmpty() ? "-1" : bi.hf101.getText().toString());

        json.put("hf1_02", bi.hf102.getText().toString().trim().isEmpty() ? "-1" : bi.hf102.getText().toString());

        json.put("hf1_03a", bi.hf103a.getText().toString().trim().isEmpty() ? "-1" : bi.hf103a.getText().toString());

        json.put("hf1_03b", bi.hf103b.getText().toString().trim().isEmpty() ? "-1" : bi.hf103b.getText().toString());

        json.put("hf1_03c", bi.hf103c.getText().toString().trim().isEmpty() ? "-1" : bi.hf103c.getText().toString());

        json.put("hf1_03d", bi.hf103d.getText().toString().trim().isEmpty() ? "-1" : bi.hf103d.getText().toString());

        json.put("hf1_04", bi.hf104a.isChecked() ? "1"
                : bi.hf104b.isChecked() ? "2"
                : bi.hf104c.isChecked() ? "96"
                : "-1");

        json.put("hf1_04cx", bi.hf104cx.getText().toString().trim().isEmpty() ? "-1" : bi.hf104cx.getText().toString());

        json.put("hf1_05", bi.hf105a.isChecked() ? "1"
                : bi.hf105b.isChecked() ? "2"
                : bi.hf105c.isChecked() ? "3"
                : bi.hf105d.isChecked() ? "98"
                : "-1");

        fc.setSa1(String.valueOf(json));

    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.GrpName);
    }

    /*public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }*/

    /*@Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Press NOT Allowed", Toast.LENGTH_SHORT).show();
    }*/


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
