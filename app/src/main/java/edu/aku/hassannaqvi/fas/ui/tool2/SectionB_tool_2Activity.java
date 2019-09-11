package edu.aku.hassannaqvi.fas.ui.tool2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.fas.R;
import edu.aku.hassannaqvi.fas.RMOperations.crudOperations;
import edu.aku.hassannaqvi.fas.core.CONSTANTS;
import edu.aku.hassannaqvi.fas.core.MainApp;
import edu.aku.hassannaqvi.fas.data.DAO.FormsDAO;
import edu.aku.hassannaqvi.fas.data.entities.Forms;
import edu.aku.hassannaqvi.fas.databinding.ActivitySectionBTool2Binding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.utils.DateUtils;
import edu.aku.hassannaqvi.fas.validation.ClearClass;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

public class SectionB_tool_2Activity extends AppCompatActivity {

    private ActivitySectionBTool2Binding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_b_tool_2);
        bi.setCallback(this);

        setContentUI();
        setListeners();
    }

    private void setContentUI() {
        this.setTitle(R.string.section2_tool2);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

        bi.fas02b0102ax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().equalsIgnoreCase("")) {
                    if ((bi.fas02b0102ax.getText().toString().length() == 4)) {
                        if (Integer.parseInt(bi.fas02b0102ax.getText().toString()) >= 1920 && Integer.parseInt(bi.fas02b0102ax.getText().toString()) <= Calendar.getInstance().get(Calendar.YEAR)) {
                            bi.fas02b02.setText(String.valueOf(DateUtils.getAgeInYears(Integer.parseInt(bi.fas02b0102ax.getText().toString()))));
                        } else {
                            Toast.makeText(SectionB_tool_2Activity.this, "Age must be between 1920 to " + Calendar.getInstance().get(Calendar.YEAR), Toast.LENGTH_LONG).show();
                        }
                    }


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setListeners() {

//        fas02b03
        bi.fas02b03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02b03a.getId())
                    ClearClass.ClearAllCardFields(bi.fldGrpfas02ba);
            }
        });

        bi.fas02b04.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.fas02b04a.getId()) {
                    ClearClass.ClearAllCardFields(bi.fldGrpfas02b05);
                }
            }
        });


    }

    public void BtnContinue() {
        if (!formValidation())
            return;

        try {
            SaveDraft();
            if (UpdateDB()) {
                MainApp.stActivity(this, this, SectionC_tool_2Activity.class, fc);
            } else {
                Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
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

    private void SaveDraft() throws JSONException {

        JSONObject s02 = new JSONObject();

        s02.put("fas02b0101",
                bi.fas02b0101a.isChecked() ? "1"
                        : bi.fas02b0101b.isChecked() ? "98"
                        : "0");
        s02.put("fas02b0101ax", bi.fas02b0101ax.getText().toString());


        s02.put("fas02b0102",
                bi.fas02b0102a.isChecked() ? "1"
                        : bi.fas02b0102b.isChecked() ? "98"
                        : "0");
        s02.put("fas02b0102ax", bi.fas02b0102ax.getText().toString());

        s02.put("fas02b02", bi.fas02b02.getText().toString());

        s02.put("fas02b03",
                bi.fas02b03a.isChecked() ? "1"
                        : bi.fas02b03b.isChecked() ? "2"
                        : "0");


        s02.put("fas02b04",
                bi.fas02b04a.isChecked() ? "1"
                        : bi.fas02b04b.isChecked() ? "2"
                        : bi.fas02b04c.isChecked() ? "3"
                        : bi.fas02b04d.isChecked() ? "4"
                        : bi.fas02b0499.isChecked() ? "99"
                        : "0");

        s02.put("fas02b05", bi.fas02b05.getText().toString());


        s02.put("fas02b06",
                bi.fas02b06a.isChecked() ? "1"
                        : bi.fas02b06b.isChecked() ? "2"
                        : bi.fas02b06c.isChecked() ? "3"
                        : bi.fas02b06d.isChecked() ? "4"
                        : bi.fas02b06e.isChecked() ? "5"
                        : bi.fas02b0699.isChecked() ? "99"
                        : "0");


        s02.put("fas02b07a",
                bi.fas02b07a01.isChecked() ? "1"
                        : bi.fas02b07a02.isChecked() ? "2"
                        : "0");


        s02.put("fas02b07b",
                bi.fas02b07b01.isChecked() ? "1"
                        : bi.fas02b07b02.isChecked() ? "2"
                        : "0");


        s02.put("fas02b07c",
                bi.fas02b07c01.isChecked() ? "1"
                        : bi.fas02b07c02.isChecked() ? "2"
                        : "0");


        s02.put("fas02b07d",
                bi.fas02b07d01.isChecked() ? "1"
                        : bi.fas02b07d02.isChecked() ? "2"
                        : "0");


        s02.put("fas02b07e",
                bi.fas02b07e01.isChecked() ? "1"
                        : bi.fas02b07e02.isChecked() ? "2"
                        : "0");

        s02.put("fas02b08",
                bi.fas02b08a.isChecked() ? "1"
                        : bi.fas02b08b.isChecked() ? "2"
                        : bi.fas02b08c.isChecked() ? "3"
                        : bi.fas02b08d.isChecked() ? "4"
                        : bi.fas02b08e.isChecked() ? "5"
                        : bi.fas02b08f.isChecked() ? "6"
                        : bi.fas02b0896.isChecked() ? "96"
                        : "0");
        s02.put("fas02b0896x", bi.fas02b0896x.getText().toString());


        s02.put("fas02b09",
                bi.fas02b09a.isChecked() ? "1"
                        : bi.fas02b09b.isChecked() ? "2"
                        : bi.fas02b09c.isChecked() ? "3"
                        : bi.fas02b09d.isChecked() ? "4"
                        : bi.fas02b09e.isChecked() ? "5"
                        : bi.fas02b09f.isChecked() ? "6"
                        : bi.fas02b0996.isChecked() ? "96"
                        : "0");
        s02.put("fas02b0996x", bi.fas02b0996x.getText().toString());


        s02.put("fas02b10",
                bi.fas02b10a.isChecked() ? "1"
                        : bi.fas02b10b.isChecked() ? "2"
                        : bi.fas02b10c.isChecked() ? "3"
                        : bi.fas02b10d.isChecked() ? "4"
                        : bi.fas02b10e.isChecked() ? "5"
                        : bi.fas02b10f.isChecked() ? "6"
                        : bi.fas02b1097.isChecked() ? "97"
                        : bi.fas02b1099.isChecked() ? "99"
                        : bi.fas02b1096.isChecked() ? "96"
                        : "0");
        s02.put("fas02b1096x", bi.fas02b1096x.getText().toString());


        s02.put("fas02b11",
                bi.fas02b11a.isChecked() ? "1"
                        : bi.fas02b11b.isChecked() ? "2"
                        : bi.fas02b11c.isChecked() ? "3"
                        : bi.fas02b11d.isChecked() ? "4"
                        : bi.fas02b11e.isChecked() ? "5"
                        : bi.fas02b1198.isChecked() ? "98"
                        : "0");

        fc.setSa2(String.valueOf(s02));

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecB02);
    }

    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
