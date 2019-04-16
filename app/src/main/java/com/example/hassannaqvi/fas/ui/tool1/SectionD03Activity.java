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
import com.example.hassannaqvi.fas.databinding.ActivitySectionD03Binding;
import com.example.hassannaqvi.fas.ui.EndingActivity;
import com.example.hassannaqvi.fas.utils.JsonUtils;
import com.example.hassannaqvi.fas.validation.ValidatorClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SectionD03Activity extends AppCompatActivity {

    private ActivitySectionD03Binding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d03);
        bi.setCallback(this);

        setContentUI();
    }

    private void setContentUI() {
        this.setTitle(R.string.section402);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

    }

    public void onRadioClickChanged(RadioGroup radioGroup, int id) {
        if (radioGroup.getCheckedRadioButtonId() == bi.hfa1439avc.getId())
            bi.hfa1439bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1440avc.getId())
            bi.hfa1440bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1441avc.getId())
            bi.hfa1441bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1442avc.getId())
            bi.hfa1442bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1443avc.getId())
            bi.hfa1443bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1444avc.getId())
            bi.hfa1444bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1445avc.getId())
            bi.hfa1445bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1446avc.getId())
            bi.hfa1446bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1447avc.getId())
            bi.hfa1447bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1448avc.getId())
            bi.hfa1448bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1449avc.getId())
            bi.hfa1449bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1450avc.getId())
            bi.hfa1450bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1451avc.getId())
            bi.hfa1451bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1452avc.getId())
            bi.hfa1452bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1453avc.getId())
            bi.hfa1453bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1454avc.getId())
            bi.hfa1454bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1455avc.getId())
            bi.hfa1455bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1456avc.getId())
            bi.hfa1456bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1457avc.getId())
            bi.hfa1457bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1458avc.getId())
            bi.hfa1458bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1459avc.getId())
            bi.hfa1459bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1460avc.getId())
            bi.hfa1460bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1461avc.getId())
            bi.hfa1461bf.clearCheck();
    }


    public void BtnContinue() {

        if (!formValidation())
            return;

        SaveDraft();
        if (UpdateDB()) {
            MainApp.stActivity(this, this, SectionEActivity.class, fc);
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
        JSONObject Json3 = GeneratorClass.getContainerJSON(bi.fldGrpllSecD03, true);

        try {
            JSONObject s4_merge = JsonUtils.mergeJSONObjects(new JSONObject(fc.getSa4()), Json3);

            fc.setSa4(String.valueOf(s4_merge));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecD03);
    }

    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }
}
