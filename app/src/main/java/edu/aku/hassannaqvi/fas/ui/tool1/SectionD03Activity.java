package edu.aku.hassannaqvi.fas.ui.tool1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.fas.JSON.GeneratorClass;
import edu.aku.hassannaqvi.fas.R;
import edu.aku.hassannaqvi.fas.RMOperations.crudOperations;
import edu.aku.hassannaqvi.fas.core.CONSTANTS;
import edu.aku.hassannaqvi.fas.core.MainApp;
import edu.aku.hassannaqvi.fas.data.DAO.FormsDAO;
import edu.aku.hassannaqvi.fas.data.entities.Forms;
import edu.aku.hassannaqvi.fas.databinding.ActivitySectionD03Binding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.utils.JsonUtils;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

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
        this.setTitle(R.string.hfa14);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

    }

    public void onRadioClickChanged(RadioGroup radioGroup, int id) {
        if (radioGroup.getCheckedRadioButtonId() == bi.hfa1426avc.getId())
            bi.hfa1426bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1427avc.getId())
            bi.hfa1426bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1426avc.getId())
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


    }


    public void BtnContinue() {

        if (!formValidation())
            return;

        SaveDraft();
        if (UpdateDB()) {
            MainApp.stActivity(this, this, SectionD04Activity.class, fc);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
