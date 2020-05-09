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
import edu.aku.hassannaqvi.fas.databinding.ActivitySection3bBinding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;


public class Section3B extends AppCompatActivity {

    private ActivitySection3bBinding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section3b);
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

        json.put("hf2_23", bi.hf223.getText().toString().trim().isEmpty() ? "-1" : bi.hf223.getText().toString());

        json.put("hf2_24", bi.hf224.getText().toString().trim().isEmpty() ? "-1" : bi.hf224.getText().toString());

        json.put("hf2_25", bi.hf225.getText().toString().trim().isEmpty() ? "-1" : bi.hf225.getText().toString());

        json.put("hf2_26", bi.hf226.getText().toString().trim().isEmpty() ? "-1" : bi.hf226.getText().toString());

        json.put("hf2_27", bi.hf227.getText().toString().trim().isEmpty() ? "-1" : bi.hf227.getText().toString());

        json.put("hf2_28", bi.hf228.getText().toString().trim().isEmpty() ? "-1" : bi.hf228.getText().toString());

        json.put("hf2_29", bi.hf229.getText().toString().trim().isEmpty() ? "-1" : bi.hf229.getText().toString());

        json.put("hf2_30", bi.hf230.getText().toString().trim().isEmpty() ? "-1" : bi.hf230.getText().toString());

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
        Toast.makeText(this, "Back Press Locked", Toast.LENGTH_SHORT).show();
    }

}
