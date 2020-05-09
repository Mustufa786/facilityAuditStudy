package edu.aku.hassannaqvi.fas.ui.tool3;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.fas.R;
import edu.aku.hassannaqvi.fas.RMOperations.crudOperations;
import edu.aku.hassannaqvi.fas.core.CONSTANTS;
import edu.aku.hassannaqvi.fas.core.MainApp;
import edu.aku.hassannaqvi.fas.data.DAO.FormsDAO;
import edu.aku.hassannaqvi.fas.data.entities.Forms;
import edu.aku.hassannaqvi.fas.databinding.ActivitySectionKBinding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.ui.tool1.SectionDActivity;
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

        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    private void SaveDraft() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("ax21a", bi.ax21a.getText().toString());
        json.put("ax21b", bi.ax21b.getText().toString());
        json.put("ax21c", bi.ax21c.getText().toString());
        json.put("ax21d", bi.ax21d.getText().toString());
        json.put("ax21e", bi.ax21e.getText().toString());
        json.put("ax21f", bi.ax21f.getText().toString());
        json.put("ax21g", bi.ax21g.getText().toString());
        json.put("ax22", bi.ax22.getText().toString());
        json.put("ax23", bi.ax23a.isChecked() ? "1"
                : bi.ax23b.isChecked() ? "2"
                : bi.ax23c.isChecked() ? "3"
                : bi.ax23d.isChecked() ? "98"
                : "-1");
        json.put("ax23cx", bi.ax23cx.getText().toString());
        json.put("ax24", bi.ax24a.isChecked() ? "1"
                : bi.ax24b.isChecked() ? "2"
                : bi.ax24c.isChecked() ? "3"
                : bi.ax24d.isChecked() ? "4"
                : bi.ax24e.isChecked() ? "5"
                : bi.ax24f.isChecked() ? "98"
                : "-1");
        json.put("ax25",
                bi.ax25a.isChecked() ? "1" :
                        bi.ax25b.isChecked() ? "2" :
                                bi.ax25c.isChecked() ? "3" :
                                        bi.ax25d.isChecked() ? "4" :
                                                bi.ax25e.isChecked() ? "5" :
                                                        bi.ax25f.isChecked() ? "98" :
                                                                "-1");
        json.put("ax26",
                bi.ax26a.isChecked() ? "1" :
                        bi.ax26b.isChecked() ? "2" :
                                bi.ax26c.isChecked() ? "66" :
                                        bi.ax26d.isChecked() ? "98" :
                                                "-1");
        json.put("ax27",
                bi.ax27a.isChecked() ? "1" :
                        bi.ax27b.isChecked() ? "2" :
                                bi.ax27c.isChecked() ? "3" :
                                        bi.ax27d.isChecked() ? "98" :
                                                "-1");
        json.put("ax271",
                bi.ax271a.isChecked() ? "1" :
                        bi.ax271b.isChecked() ? "2" :
                                bi.ax271c.isChecked() ? "96" :
                                        "-1");
        json.put("ax271cx", bi.ax271cx.getText().toString());

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
