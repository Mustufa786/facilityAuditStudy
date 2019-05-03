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
import edu.aku.hassannaqvi.fas.databinding.ActivitySectionDBinding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ClearClass;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

public class SectionDActivity extends AppCompatActivity {

    private ActivitySectionDBinding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d);
        bi.setCallback(this);

        setContentUI();
        setListeners();
    }

    private void setListeners() {
//        bi.hfa1416.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if (i != bi.hfa1416a.getId())
//                    ClearClass.ClearAllFields(bi.fldGrpllSecD01, null);
//            }
//        });
//        bi.hfa1418.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if (i != bi.hfa1418.getId())
//                    ClearClass.ClearAllFields(bi.fldGrpllSecD02, null);
//            }
//        });
    }

    private void setContentUI() {
        this.setTitle(R.string.hfa14);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

        ClearClass.ClearAllFields(bi.hfa1400, false);
        String getSurvey = MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_SURVEY_TYPE);
        if (!getSurvey.equals("0"))
            bi.hfa1400.check(bi.hfa1400.getChildAt(Integer.valueOf(getSurvey) - 1).getId());

        bi.hfa14001.setText(MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_HF_NO));
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
        JSONObject Json = GeneratorClass.getContainerJSON(bi.fldGrpllSecD, true);
        fc.setSa4(String.valueOf(Json));
    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecD);
    }

    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }
}
