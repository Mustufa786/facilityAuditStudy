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
import com.example.hassannaqvi.fas.databinding.ActivitySectionD02Binding;
import com.example.hassannaqvi.fas.ui.EndingActivity;
import com.example.hassannaqvi.fas.utils.JsonUtils;
import com.example.hassannaqvi.fas.validation.ValidatorClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SectionD02Activity extends AppCompatActivity {

    private ActivitySectionD02Binding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d02);
        bi.setCallback(this);

        setContentUI();
    }

    private void setContentUI() {
        this.setTitle(R.string.section402);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

    }

    public void onRadioClickChanged(RadioGroup radioGroup, int id) {
        if (radioGroup.getCheckedRadioButtonId() == bi.hfa1423avc.getId())
            bi.hfa1423bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1424avc.getId())
            bi.hfa1424bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1425avc.getId())
            bi.hfa1425bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1426avc.getId())
            bi.hfa1426bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1427avc.getId())
            bi.hfa1427bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1428avc.getId())
            bi.hfa1428bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1429avc.getId())
            bi.hfa1429bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1430avc.getId())
            bi.hfa1430bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1431avc.getId())
            bi.hfa1431bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1432avc.getId())
            bi.hfa1432bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1433avc.getId())
            bi.hfa1433bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1434avc.getId())
            bi.hfa1434bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1435avc.getId())
            bi.hfa1435bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1436avc.getId())
            bi.hfa1436bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1437avc.getId())
            bi.hfa1437bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1438avc.getId())
            bi.hfa1438bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1439avc.getId())
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
    }


    public void BtnContinue() {

        if (!formValidation())
            return;

        SaveDraft();
        if (UpdateDB()) {
            MainApp.stActivity(this, this, SectionD03Activity.class, fc);
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
        JSONObject Json2 = GeneratorClass.getContainerJSON(bi.fldGrpllSecD02, true);

        try {
            JSONObject s4_merge = JsonUtils.mergeJSONObjects(new JSONObject(fc.getSa4()), Json2);

            fc.setSa4(String.valueOf(s4_merge));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecD02);
    }

    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }
}
