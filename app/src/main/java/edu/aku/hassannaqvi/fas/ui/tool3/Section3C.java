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
import edu.aku.hassannaqvi.fas.databinding.ActivitySection3cBinding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;


public class Section3C extends AppCompatActivity {

    private ActivitySection3cBinding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section3c);
        bi.setCallback(this);

        setContentUI();
        setListenersUI();

    }


    private void setContentUI() {
        this.setTitle(R.string.section4_tool2);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);
    }


    private void setListenersUI() {

       /* //   fas02d01
        bi.fas02d01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02d01a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpllSecD02fas02d02, null);
            }
        });


        bi.fas02d0298.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.fldGrpfas02d02, false);
                    bi.fldGrpfas02d02.setTag("-1");
                    bi.fas02d0298.setTag(null);
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpfas02d02, true);
                    bi.fldGrpfas02d02.setTag(null);
                    bi.fas02d0298.setTag("-1");
                }
            }
        });  */


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
            MainApp.endActivitySetRouting(this, this, EndingActivity.class, true, fc);
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

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("mt7_01", bi.mt701a.isChecked() ? "1"
                : bi.mt701b.isChecked() ? "2"
                : bi.mt701c.isChecked() ? "98"
                : "-1");

        json.put("mt7_01a1", bi.mt701aa.isChecked() ? "1"
                : bi.mt701ab.isChecked() ? "2"
                : bi.mt701ac.isChecked() ? "3"
                : bi.mt701ad.isChecked() ? "4"
                : bi.mt701ae.isChecked() ? "5"
                : bi.mt701af.isChecked() ? "98"
                : bi.mt701ag.isChecked() ? "96"
                : "-1");

        json.put("mt7_02", bi.mt702a.isChecked() ? "1"
                : bi.mt702b.isChecked() ? "2"
                : bi.mt702c.isChecked() ? "98"
                : "-1");

        json.put("mt7_02a1", bi.mt702a1a.isChecked() ? "1"
                : bi.mt702a1b.isChecked() ? "2"
                : bi.mt702a1c.isChecked() ? "3"
                : bi.mt702a1d.isChecked() ? "4"
                : bi.mt702a1e.isChecked() ? "5"
                : bi.mt702a1f.isChecked() ? "98"
                : bi.mt702a1g.isChecked() ? "96"
                : "-1");

        json.put("mt7_02a1gx", bi.mt702a1gx.getText().toString().trim().isEmpty() ? "-1" : bi.mt702a1gx.getText().toString());

        json.put("mt7_03", bi.mt703a.isChecked() ? "1"
                : bi.mt703b.isChecked() ? "2"
                : bi.mt703c.isChecked() ? "98"
                : "-1");

        json.put("mt7_03a1", bi.mt703a1a.isChecked() ? "1"
                : bi.mt703a1b.isChecked() ? "2"
                : bi.mt703a1c.isChecked() ? "3"
                : bi.mt703a1d.isChecked() ? "4"
                : bi.mt703a1e.isChecked() ? "5"
                : bi.mt703a1f.isChecked() ? "98"
                : bi.mt703a1g.isChecked() ? "96"
                : "-1");

        json.put("mt7_03a1gx", bi.mt703a1gx.getText().toString().trim().isEmpty() ? "-1" : bi.mt703a1gx.getText().toString());

        json.put("mt7_03b1", bi.mt703b1a.isChecked() ? "1"
                : bi.mt703b1b.isChecked() ? "2"
                : bi.mt703b1c.isChecked() ? "3"
                : bi.mt703b1d.isChecked() ? "4"
                : bi.mt703b1e.isChecked() ? "5"
                : bi.mt703b1f.isChecked() ? "98"
                : bi.mt703b1g.isChecked() ? "96"
                : "-1");

        json.put("mt7_03b1gx", bi.mt703b1gx.getText().toString().trim().isEmpty() ? "-1" : bi.mt703b1gx.getText().toString());

        fc.setSa5(String.valueOf(json));

    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.GrpName);
    }

    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Press NOT Allowed", Toast.LENGTH_SHORT).show();
    }

}
