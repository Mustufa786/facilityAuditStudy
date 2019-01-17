package com.example.hassannaqvi.fas.ui.tool1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hassannaqvi.fas.R;
import com.example.hassannaqvi.fas.core.CONSTANTS;
import com.example.hassannaqvi.fas.core.MainApp;
import com.example.hassannaqvi.fas.data.entities.Forms_04_05;
import com.example.hassannaqvi.fas.databinding.ActivitySectionDBinding;
import com.example.hassannaqvi.fas.ui.EndingActivity;
import com.example.hassannaqvi.fas.validation.validatorClass;

public class SectionDActivity extends AppCompatActivity {

    private ActivitySectionDBinding bi;
    private Forms_04_05 fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d);
        bi.setCallback(this);

        setContentUI();
    }

    private void setContentUI() {
        this.setTitle(R.string.section4);
        fc = (Forms_04_05) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

    }

    public void BtnContinue() {

        if (!formValidation())
            return;

        SaveDraft();
        if (UpdateDB()) {
            MainApp.stActivity(this, this, SectionD02Activity.class, fc);
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {


        return true;
    }

    private void SaveDraft() {


    }


    private boolean formValidation() {
        return validatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecD);
    }

    public void BtnEnd() {
        MainApp.endActivity(this, this, EndingActivity.class, false, fc);
    }
}
