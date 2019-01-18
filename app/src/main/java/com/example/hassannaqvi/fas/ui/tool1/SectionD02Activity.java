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
        if (radioGroup.getCheckedRadioButtonId() == bi.fas01d23a03.getId())
            bi.fas01d23b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d24a03.getId())
            bi.fas01d24b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d25a03.getId())
            bi.fas01d25b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d26a03.getId())
            bi.fas01d26b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d27a03.getId())
            bi.fas01d27b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d28a03.getId())
            bi.fas01d28b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d29a03.getId())
            bi.fas01d29b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d30a03.getId())
            bi.fas01d30b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d31a03.getId())
            bi.fas01d31b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d32a03.getId())
            bi.fas01d32b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d33a03.getId())
            bi.fas01d33b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d34a03.getId())
            bi.fas01d34b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d35a03.getId())
            bi.fas01d35b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d36a03.getId())
            bi.fas01d36b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d37a03.getId())
            bi.fas01d37b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d38a03.getId())
            bi.fas01d38b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d39a03.getId())
            bi.fas01d39b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d40a03.getId())
            bi.fas01d40b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d41a03.getId())
            bi.fas01d41b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d42a03.getId())
            bi.fas01d42b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d43a03.getId())
            bi.fas01d43b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d44a03.getId())
            bi.fas01d44b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d45a03.getId())
            bi.fas01d45b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d46a03.getId())
            bi.fas01d46b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d47a03.getId())
            bi.fas01d47b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d48a03.getId())
            bi.fas01d48b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d49a03.getId())
            bi.fas01d49b.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.fas01d50a03.getId())
            bi.fas01d50b.clearCheck();
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
        MainApp.endActivity(this, this, EndingActivity.class, false, fc);
    }
}
