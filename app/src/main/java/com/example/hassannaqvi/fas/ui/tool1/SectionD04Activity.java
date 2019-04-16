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
import com.example.hassannaqvi.fas.databinding.ActivitySectionD04Binding;
import com.example.hassannaqvi.fas.ui.EndingActivity;
import com.example.hassannaqvi.fas.utils.JsonUtils;
import com.example.hassannaqvi.fas.validation.ValidatorClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SectionD04Activity extends AppCompatActivity {

    private ActivitySectionD04Binding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d04);
        bi.setCallback(this);

        setContentUI();
    }

    private void setContentUI() {
        this.setTitle(R.string.hfa14);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

    }

    public void onRadioClickChanged(RadioGroup radioGroup, int id) {
        if (radioGroup.getCheckedRadioButtonId() == bi.hfa1462avc.getId())
            bi.hfa1462bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1463avc.getId())
            bi.hfa1463bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1464avc.getId())
            bi.hfa1464bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1465avc.getId())
            bi.hfa1465bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1466avc.getId())
            bi.hfa1466bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1467avc.getId())
            bi.hfa1467bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1468avc.getId())
            bi.hfa1468bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1469avc.getId())
            bi.hfa1469bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1470avc.getId())
            bi.hfa1470bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1471avc.getId())
            bi.hfa1471bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1472avc.getId())
            bi.hfa1472bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1473avc.getId())
            bi.hfa1473bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1474avc.getId())
            bi.hfa1474bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1475avc.getId())
            bi.hfa1475bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1476avc.getId())
            bi.hfa1476bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1477avc.getId())
            bi.hfa1477bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1478avc.getId())
            bi.hfa1478bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1479avc.getId())
            bi.hfa1479bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1480avc.getId())
            bi.hfa1480bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1481avc.getId())
            bi.hfa1481bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1482avc.getId())
            bi.hfa1482bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1483avc.getId())
            bi.hfa1483bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1484avc.getId())
            bi.hfa1484bf.clearCheck();
    }


    public void BtnContinue() {

        if (!formValidation())
            return;

        SaveDraft();
        if (UpdateDB()) {
            MainApp.stActivity(this, this, SectionD05Activity.class, fc);
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
        JSONObject Json4 = GeneratorClass.getContainerJSON(bi.fldGrpllSecD04, true);

        try {
            JSONObject s4_merge = JsonUtils.mergeJSONObjects(new JSONObject(fc.getSa4()), Json4);

            fc.setSa4(String.valueOf(s4_merge));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecD04);
    }

    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }
}
