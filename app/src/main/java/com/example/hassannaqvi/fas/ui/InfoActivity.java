package com.example.hassannaqvi.fas.ui;

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

import com.example.hassannaqvi.fas.JSON.GeneratorClass;
import com.example.hassannaqvi.fas.R;
import com.example.hassannaqvi.fas.RMOperations.crudOperations;
import com.example.hassannaqvi.fas.core.MainApp;
import com.example.hassannaqvi.fas.data.DAO.FormsDAO;
import com.example.hassannaqvi.fas.data.DAO.GetFncDAO;
import com.example.hassannaqvi.fas.data.entities.Forms_04_05;
import com.example.hassannaqvi.fas.databinding.ActivityInfoBinding;
import com.example.hassannaqvi.fas.get.db.GetIndDBData;
import com.example.hassannaqvi.fas.validation.ClearClass;
import com.example.hassannaqvi.fas.validation.validatorClass;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.hassannaqvi.fas.ui.LoginActivity.db;
import static com.example.hassannaqvi.fas.utils.JsonUtils.mergeJSONObjects;

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
        this.setTitle(R.string.section1);
        deviceID = Settings.Secure.getString(InfoActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
        setContentUI();
    }

    private void setContentUI() {

//
//        bi.fasa06.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//                if (i == bi.fasa06b.getId()) {
//                    bi.btnContinue.setVisibility(GONE);
//                } else {
//                    bi.btnContinue.setVisibility(VISIBLE);
//                }
//            }
//        });

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
        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {
                startActivity(new Intent(getApplicationContext(), routeClass));
            } else {
                Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private boolean UpdateDB() {


        return false;

    }

    private void SaveDraft() {


    }


    private boolean formValidation() {

        return true;
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
