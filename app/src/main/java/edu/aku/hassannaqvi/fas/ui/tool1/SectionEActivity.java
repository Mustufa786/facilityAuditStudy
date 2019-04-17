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
import edu.aku.hassannaqvi.fas.databinding.ActivitySectionEBinding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ClearClass;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

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
        this.setTitle(R.string.hfa15);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

        ClearClass.ClearAllFields(bi.hfa1500, false);
        String getSurvey = MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_SURVEY_TYPE);
        if (!getSurvey.equals("0"))
            bi.hfa1500.check(bi.hfa1500.getChildAt(Integer.valueOf(getSurvey) - 1).getId());

        bi.hfa15001.setText(MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_HF_NO));
    }

    public void onRadioClickChanged(RadioGroup radioGroup, int id) {
        if (radioGroup.getCheckedRadioButtonId() == bi.hfa1502avc.getId())
            bi.hfa1502bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1503avc.getId())
            bi.hfa1503bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1504avc.getId())
            bi.hfa1504bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1505avc.getId())
            bi.hfa1505bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1506avc.getId())
            bi.hfa1506bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1507avc.getId())
            bi.hfa1507bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1508avc.getId())
            bi.hfa1508bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1509avc.getId())
            bi.hfa1509bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1510avc.getId())
            bi.hfa1510bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1511avc.getId())
            bi.hfa1511bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1512avc.getId())
            bi.hfa1512bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1513avc.getId())
            bi.hfa1513bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1514avc.getId())
            bi.hfa1514bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1515avc.getId())
            bi.hfa1515bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1516avc.getId())
            bi.hfa1516bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1517avc.getId())
            bi.hfa1517bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1518avc.getId()) {
            bi.hfa1518bv.clearCheck();
            bi.hfa1519bv.clearCheck();
        } else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1520avc.getId())
            bi.hfa1519bv.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1521avc.getId())
            bi.hfa1520bv.clearCheck();
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
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }
}
