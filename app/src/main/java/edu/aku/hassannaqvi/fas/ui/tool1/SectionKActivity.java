package edu.aku.hassannaqvi.fas.ui.tool1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import edu.aku.hassannaqvi.fas.databinding.ActivitySectionKBinding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ClearClass;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

public class SectionKActivity extends AppCompatActivity {

    ActivitySectionKBinding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_k);
        bi.setCallback(this);

        setContentUI();
        setListeners();
    }
    private void setListeners() {

//        bi.hfa1332.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                if (!s.toString().isEmpty()) {
//                    String[] nums = s.toString().split("-");
//                    int day = Integer.parseInt(nums[0]);
//                    int month = Integer.parseInt(nums[1]);
//                    int year = Integer.parseInt(nums[2]);
//                    String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date().getTime());
//                    String[] nums1 = currentDate.split("-");
//                    int cr_day = Integer.parseInt(nums1[0]);
//                    int cr_month = Integer.parseInt(nums1[1]);
//                    int cr_year = Integer.parseInt(nums1[2]);
//
//                    if (day > cr_day || month > cr_month || year > cr_year) {
//                        bi.hfa1332.setError("Can not be greater than " + currentDate);
//                    } else {
//                        bi.hfa1332.setError(null);
//                    }
//
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }

    private void setContentUI() {
        this.setTitle(R.string.hfa13);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);


       /* ClearClass.ClearAllFields(bi.hfa1300, false);
        String getSurvey = MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_SURVEY_TYPE);
        if (!getSurvey.equals("0"))
            bi.hfa1300.check(bi.hfa1300.getChildAt(Integer.valueOf(getSurvey) - 1).getId());

        bi.hfa13001.setText(MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_HF_NO));*/

    }

    public void BtnContinue() {

        if (!formValidation())
            return;

        SaveDraft();
        if (UpdateDB()) {
            MainApp.stActivity(this, this, SectionDActivity.class, fc);
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean UpdateDB() {
        try {

            Long longID = new crudOperations(this, FormsDAO.class.getName(),
                    "formsDao", "updateForm", fc).execute().get();
            return longID == 1;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void SaveDraft() {
        JSONObject Json = GeneratorClass.getContainerJSON(bi.GrpNameSectionK, true);
        fc.setSa3(String.valueOf(Json));
    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.GrpNameSectionK);
    }

    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
