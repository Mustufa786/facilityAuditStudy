package com.example.hassannaqvi.fas.ui.tool1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hassannaqvi.fas.R;
import com.example.hassannaqvi.fas.RMOperations.crudOperations;
import com.example.hassannaqvi.fas.core.MainApp;
import com.example.hassannaqvi.fas.data.DAO.FormsDAO;
import com.example.hassannaqvi.fas.data.entities.Forms_04_05;
import com.example.hassannaqvi.fas.databinding.ActivityInfoBinding;
import com.example.hassannaqvi.fas.ui.EndingActivity;
import com.example.hassannaqvi.fas.ui.LoginActivity;
import com.example.hassannaqvi.fas.validation.validatorClass;

import java.util.concurrent.ExecutionException;

public class InfoActivity extends AppCompatActivity {

    private static final String TAG = InfoActivity.class.getName();
    ActivityInfoBinding bi;
    private Forms_04_05 fc;
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

            Long longID = new crudOperations(LoginActivity.db, fc).execute(FormsDAO.class.getName(), "formsDao", "insertForm").get();

            if (longID != 0) {
                fc.setId(longID.intValue());

                fc.setUid(deviceID + fc.getId());

                longID = new crudOperations(LoginActivity.db, fc).execute(FormsDAO.class.getName(), "formsDao", "updateForm").get();
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
        fc = new Forms_04_05();
    }


    private boolean formValidation() {
        return validatorClass.EmptyCheckingContainer(this, bi.fldGrpllInfoA);
    }

    public void BtnEnd() {

        SaveDraft();
        if (UpdateDB()) {
            MainApp.endActivity(this, this, EndingActivity.class, false, fc);
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }
    }


}
