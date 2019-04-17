package edu.aku.hassannaqvi.fas.ui.tool1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.fas.JSON.GeneratorClass;
import edu.aku.hassannaqvi.fas.R;
import edu.aku.hassannaqvi.fas.RMOperations.crudOperations;
import edu.aku.hassannaqvi.fas.core.CONSTANTS;
import edu.aku.hassannaqvi.fas.core.MainApp;
import edu.aku.hassannaqvi.fas.data.DAO.FormsDAO;
import edu.aku.hassannaqvi.fas.data.entities.Forms;
import edu.aku.hassannaqvi.fas.databinding.ActivitySectionHBinding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ClearClass;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

public class SectionHActivity extends AppCompatActivity {

    private ActivitySectionHBinding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_h);
        bi.setCallback(this);

        setContentUI();
    }

    private void setContentUI() {
        this.setTitle(R.string.hfa18);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

        ClearClass.ClearAllFields(bi.hfa1800, false);
        String getSurvey = MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_SURVEY_TYPE);
        if (!getSurvey.equals("0"))
            bi.hfa1800.check(bi.hfa1800.getChildAt(Integer.valueOf(getSurvey) - 1).getId());

        bi.hfa18001.setText(MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_HF_NO));
    }

    public void BtnContinue() {

        if (!formValidation())
            return;

        SaveDraft();
        if (UpdateDB()) {
            MainApp.stActivity(this, this, SectionIActivity.class, fc);
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
        JSONObject Json = GeneratorClass.getContainerJSON(bi.fldGrpllSecH, true);
        fc.setSa8(String.valueOf(Json));
    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecH);
    }

    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }

    public void onRadioClickChanged(RadioGroup radioGroup, int id) {

        if (radioGroup.getCheckedRadioButtonId() == bi.hfa1801.getId())
            bi.hfa1802.clearCheck();

        if (radioGroup.getCheckedRadioButtonId() == bi.hfa1803.getId()) {
            bi.hfa1804.clearCheck();
            bi.hfa1805.clearCheck();
            bi.hfa1806.clearCheck();
        }

        if (radioGroup.getCheckedRadioButtonId() == bi.hfa1805.getId())
            bi.hfa1806.clearCheck();

        if (radioGroup.getCheckedRadioButtonId() == bi.hfa1807.getId()) {
            bi.hfa1808.clearCheck();
            bi.hfa1809.clearCheck();
        }

    }
}
