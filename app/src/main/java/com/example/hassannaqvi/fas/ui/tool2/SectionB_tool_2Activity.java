package com.example.hassannaqvi.fas.ui.tool2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hassannaqvi.fas.R;
import com.example.hassannaqvi.fas.data.entities.Forms;
import com.example.hassannaqvi.fas.databinding.ActivitySectionBTool2Binding;
import com.example.hassannaqvi.fas.validation.ValidatorClass;

import org.json.JSONException;
import org.json.JSONObject;

public class SectionB_tool_2Activity extends AppCompatActivity {

    private ActivitySectionBTool2Binding bi;
    private Forms fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_b_tool_2);
        bi.setCallback(this);
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
            startActivity(new Intent(this, SectionB_tool_2Activity.class));
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean UpdateDB() {

        return true;
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
                bi.fas02b04a.isChecked() ? "0"
                        : bi.fas02b04b.isChecked() ? "1"
                        : bi.fas02b04c.isChecked() ? "2"
                        : bi.fas02b04d.isChecked() ? "3"
                        : "0");
        s02.put("fas02b05", bi.fas02b05.getText().toString());


        s02.put("fas02b06",
                bi.fas02b06a.isChecked() ? "1"
                        : bi.fas02b06b.isChecked() ? "2"
                        : bi.fas02b06c.isChecked() ? "3"
                        : bi.fas02b06c.isChecked() ? "4"
                        : bi.fas02b06e.isChecked() ? "5"
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
                        : bi.fas02b08c.isChecked() ? "4"
                        : bi.fas02b08e.isChecked() ? "5"
                        : bi.fas02b08f.isChecked() ? "6"
                        : bi.fas02b0896.isChecked() ? "96"
                        : "0");


        s02.put("fas02b0896x", bi.fas02b0896x.getText().toString());


        s02.put("fas02b09",
                bi.fas02b09a.isChecked() ? "1"
                        : bi.fas02b09b.isChecked() ? "2"
                        : bi.fas02b09c.isChecked() ? "3"
                        : bi.fas02b09c.isChecked() ? "4"
                        : bi.fas02b09e.isChecked() ? "5"
                        : bi.fas02b09f.isChecked() ? "6"
                        : bi.fas02b0996.isChecked() ? "96"
                        : "0");


        s02.put("fas02b0996x", bi.fas02b0996x.getText().toString());


        s02.put("fas02b10",
                bi.fas02b10a.isChecked() ? "1"
                        : bi.fas02b10b.isChecked() ? "2"
                        : bi.fas02b10c.isChecked() ? "3"
                        : bi.fas02b10c.isChecked() ? "4"
                        : bi.fas02b10e.isChecked() ? "5"
                        : bi.fas02b10f.isChecked() ? "6"
                        : bi.fas02b1096.isChecked() ? "96"
                        : "0");


        s02.put("fas02b1096x", bi.fas02b1096x.getText().toString());


        s02.put("fas02b11",
                bi.fas02b11a.isChecked() ? "1"
                        : bi.fas02b11b.isChecked() ? "2"
                        : bi.fas02b11c.isChecked() ? "3"
                        : bi.fas02b11c.isChecked() ? "4"
                        : bi.fas02b11e.isChecked() ? "5"

                        : bi.fas02b1198.isChecked() ? "98"
                        : "0");


    }

    private boolean formValidation() {

        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecB02);
    }

    public void BtnEnd() {
//        MainApp.endActivity(this, this, EndingActivity.class, false, InfoActivity.fc);
    }
}
