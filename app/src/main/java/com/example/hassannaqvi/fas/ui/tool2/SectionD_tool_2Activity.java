package com.example.hassannaqvi.fas.ui.tool2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hassannaqvi.fas.R;
import com.example.hassannaqvi.fas.RMOperations.crudOperations;
import com.example.hassannaqvi.fas.core.CONSTANTS;
import com.example.hassannaqvi.fas.core.MainApp;
import com.example.hassannaqvi.fas.data.DAO.FormsDAO;
import com.example.hassannaqvi.fas.data.entities.Forms;
import com.example.hassannaqvi.fas.databinding.ActivitySectionDTool2Binding;
import com.example.hassannaqvi.fas.ui.EndingActivity;
import com.example.hassannaqvi.fas.validation.ClearClass;
import com.example.hassannaqvi.fas.validation.ValidatorClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SectionD_tool_2Activity extends AppCompatActivity {

    private ActivitySectionDTool2Binding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d_tool_2);
        bi.setCallback(this);

        setContentUI();
        setListenersUI();
    }

    private void setContentUI() {
        this.setTitle(R.string.section4_tool2);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);
    }


    private void setListenersUI() {

        //   fas02d01
        bi.fas02d01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02d01a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpllSecD02fas02d02, null);
            }
        });


        //   fas02d03
        bi.fas02d03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02d03a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpllSecD02fas02d04, null);
            }
        });

        //   fas02d05
        bi.fas02d05.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02d05a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpllSecD02fas02d06, null);
            }
        });


        //   fas02d07
        bi.fas02d07.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02d07a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpllSecD02fas02d08, null);
            }
        });


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
            MainApp.endActivity(this, this, EndingActivity.class, true, fc);
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


        JSONObject s04 = new JSONObject();


        s04.put("fas02d01",
                bi.fas02d01a.isChecked() ? "1"
                        : bi.fas02d01b.isChecked() ? "2"
                        : "0");

        s04.put("fas02d02",
                bi.fas02d02a.isChecked() ? "1"
                        : bi.fas02d02b.isChecked() ? "2"
                        : bi.fas02d02c.isChecked() ? "3"
                        : bi.fas02d02d.isChecked() ? "4"
                        : bi.fas02d02e.isChecked() ? "5"
                        : bi.fas02d02f.isChecked() ? "6"
                        : bi.fas02d02g.isChecked() ? "7"
                        : bi.fas02d02h.isChecked() ? "8"
                        : bi.fas02d02i.isChecked() ? "9"
                        : bi.fas02d02j.isChecked() ? "10"
                        : bi.fas02d02k.isChecked() ? "11"

                        : bi.fas02d0298.isChecked() ? "98"


                        : "0");


        s04.put("fas02d03",
                bi.fas02d03a.isChecked() ? "1"
                        : bi.fas02d03b.isChecked() ? "2"
                        : "0");


        s04.put("fas02d04",
                bi.fas02d04a.isChecked() ? "1"
                        : bi.fas02d04b.isChecked() ? "2"
                        : bi.fas02d04c.isChecked() ? "3"
                        : bi.fas02d04d.isChecked() ? "4"
                        : bi.fas02d04e.isChecked() ? "5"
                        : bi.fas02d04f.isChecked() ? "6"
                        : bi.fas02d04g.isChecked() ? "7"
                        : bi.fas02d04h.isChecked() ? "8"
                        : bi.fas02d04i.isChecked() ? "9"
                        : bi.fas02d04j.isChecked() ? "10"
                        : bi.fas02d04k.isChecked() ? "11"
                        : bi.fas02d04l.isChecked() ? "12"
                        : bi.fas02d04m.isChecked() ? "13"
                        : bi.fas02d0498.isChecked() ? "98"


                        : "0");


        s04.put("fas02d05",
                bi.fas02d05a.isChecked() ? "1"
                        : bi.fas02d05b.isChecked() ? "2"
                        : "0");


        s04.put("fas02d06a",
                bi.fas02d06a.isChecked() ? "1"

                        : "0");

        s04.put("fas02d06b",
                bi.fas02d06b.isChecked() ? "1"

                        : "0");

        s04.put("fas02d06c",
                bi.fas02d06c.isChecked() ? "1"

                        : "0");


        s04.put("fas02d06d",
                bi.fas02d06d.isChecked() ? "1"

                        : "0");

        s04.put("fas02d06e",
                bi.fas02d06e.isChecked() ? "1"

                        : "0");
        s04.put("fas02d06f",
                bi.fas02d06f.isChecked() ? "1"

                        : "0");
        s04.put("fas02d06g",
                bi.fas02d06g.isChecked() ? "1"

                        : "0");
        s04.put("fas02d06h",
                bi.fas02d06h.isChecked() ? "1"

                        : "0");
        s04.put("fas02d0698",
                bi.fas02d0698.isChecked() ? "1"

                        : "0");



        s04.put("fas02d07",
                bi.fas02d07a.isChecked() ? "1"
                        : bi.fas02d07b.isChecked() ? "2"
                        : "0");


        s04.put("fas02d08",
                bi.fas02d08a.isChecked() ? "1"
                        : bi.fas02d08b.isChecked() ? "2"
                        : bi.fas02d08c.isChecked() ? "3"
                        : bi.fas02d08d.isChecked() ? "4"
                        : bi.fas02d08e.isChecked() ? "5"
                        : bi.fas02d08f.isChecked() ? "6"
                        : bi.fas02d08g.isChecked() ? "7"
                        : bi.fas02d08h.isChecked() ? "8"
                        : bi.fas02d0898.isChecked() ? "98" : "0");

        fc.setSa4(String.valueOf(s04));

    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecD02);
    }

    public void BtnEnd() {
        MainApp.endActivity(this, this, EndingActivity.class, false, fc);
    }
}
