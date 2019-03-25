package com.example.hassannaqvi.fas.ui.tool1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hassannaqvi.fas.JSON.GeneratorClass;
import com.example.hassannaqvi.fas.R;
import com.example.hassannaqvi.fas.RMOperations.crudOperations;
import com.example.hassannaqvi.fas.core.CONSTANTS;
import com.example.hassannaqvi.fas.core.MainApp;
import com.example.hassannaqvi.fas.data.DAO.FormsDAO;
import com.example.hassannaqvi.fas.data.entities.Forms;
import com.example.hassannaqvi.fas.databinding.ActivitySectionEBinding;
import com.example.hassannaqvi.fas.ui.EndingActivity;
import com.example.hassannaqvi.fas.validation.ClearClass;
import com.example.hassannaqvi.fas.validation.ValidatorClass;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SectionEActivity extends AppCompatActivity {

    private ActivitySectionEBinding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_e);
        bi.setCallback(this);

        setContentUI();
    }

    private void setContentUI() {
        this.setTitle(R.string.section5);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

        ClearClass.ClearAllFields(bi.fas01e00, false);
        String getSurvey = MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_SURVEY_TYPE);
        if (!getSurvey.equals("0"))
            bi.fas01e00.check(bi.fas01e00.getChildAt(Integer.valueOf(getSurvey) - 1).getId());

        bi.fas01e001.setText(MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_HF_NO));
    }

    public void onRadioClickChanged(RadioGroup radioGroup, int id) {
        if (radioGroup.getCheckedRadioButtonId() == bi.fas01e02a03.getId())
            bi.fas01e02b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e03a03.getId())
            bi.fas01e03b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e04a03.getId())
            bi.fas01e04b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e05a03.getId())
            bi.fas01e05b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e06a03.getId())
            bi.fas01e06b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e07a03.getId())
            bi.fas01e07b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e08a03.getId())
            bi.fas01e08b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e09a03.getId())
            bi.fas01e09b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e10a03.getId())
            bi.fas01e10b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e11a03.getId())
            bi.fas01e11b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e12a03.getId())
            bi.fas01e12b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e13a03.getId())
            bi.fas01e13b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e14a03.getId())
            bi.fas01e14b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e15a03.getId())
            bi.fas01e15b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e16a03.getId())
            bi.fas01e16b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e17a03.getId())
            bi.fas01e17b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e18a03.getId())
            bi.fas01e18b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e19a03.getId())
            bi.fas01e19b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01e20a03.getId())
            bi.fas01e20b.clearCheck();
    }

    public void BtnContinue() {

        if (!formValidation())
            return;

        SaveDraft();
        if (UpdateDB()) {
            MainApp.stActivity(this, this, SectionFActivity.class, fc);
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean UpdateDB() {
        try {

            Long longID = new crudOperations(this, FormsDAO.class.getName(), "formsDao", "updateForm", fc).execute().get();
            return longID == 1;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void SaveDraft() {
        JSONObject Json = GeneratorClass.getContainerJSON(bi.fldGrpllSecE, true);
        fc.setSa5(String.valueOf(Json));
    }

    private boolean formValidation() {

        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecE);
    }

    public void BtnEnd() {
        MainApp.endActivity(this, this, EndingActivity.class, false, fc);
    }
}
