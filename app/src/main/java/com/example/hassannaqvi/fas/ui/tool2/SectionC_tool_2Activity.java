package com.example.hassannaqvi.fas.ui.tool2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hassannaqvi.fas.R;
import com.example.hassannaqvi.fas.validation.ValidatorClass;

import org.json.JSONException;
import org.json.JSONObject;

public class SectionC_tool_2Activity extends AppCompatActivity {

    private ActivitySectionCTool2Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_c_tool_2);
    }

    public void BtnContinue() {
        if (!formValidation())
            return;
        SaveDraft();
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


        s02.put("fas02c001", bi.fas02c001.getText().toString());
        s02.put("fas02cmw1", bi.fas02cmw1.getText().toString());


        s02.put("fas02c01",
                bi.fas02c01a.isChecked() ? "1"
                        : bi.fas02c01b.isChecked() ? "2"
                        : "0");

        s02.put("fas02c02m", bi.fas02c02m.getText().toString());
        s02.put("fas02c02y", bi.fas02c02y.getText().toString());


        s02.put("fas02c03",
                bi.fas02c03a.isChecked() ? "1"
                        : bi.fas02c03b.isChecked() ? "2"
                        : bi.fas02c0398.isChecked() ? "98"
                        : "0");

        s02.put("fas02c04",
                bi.fas02c04a.isChecked() ? "1"
                        : bi.fas02c04b.isChecked() ? "2"
                        : bi.fas02c04c.isChecked() ? "3"
                        : bi.fas02c04d.isChecked() ? "4"
                        : bi.fas02c04e.isChecked() ? "5"
                        : bi.fas02c04f.isChecked() ? "6"
                        : bi.fas02c04g.isChecked() ? "7"
                        : bi.fas02c04h.isChecked() ? "8"
                        : bi.fas02c04i.isChecked() ? "9"
                        : bi.fas02c04j.isChecked() ? "10"
                        : bi.fas02c04k.isChecked() ? "11"
                        : bi.fas02c04l.isChecked() ? "12"
                        : bi.fas02c0496.isChecked() ? "96"


                        : "0");


        s02.put("fas02c0496x", bi.fas02c0496x.getText().toString());


        s02.put("fas02c05",
                bi.fas02c05a.isChecked() ? "1"
                        : bi.fas02c05b.isChecked() ? "2"
                        : bi.fas02c05c.isChecked() ? "3"
                        : bi.fas02c05d.isChecked() ? "4"
                        : bi.fas02c05e.isChecked() ? "5"
                        : bi.fas02c05f.isChecked() ? "6"
                        : bi.fas02c05g.isChecked() ? "7"
                        : bi.fas02c05h.isChecked() ? "8"
                        : bi.fas02c05i.isChecked() ? "9"
                        : bi.fas02c05j.isChecked() ? "10"
                        : bi.fas02c05k.isChecked() ? "11"
                        : bi.fas02c05l.isChecked() ? "12"
                        : bi.fas02c0596.isChecked() ? "96"
                        : "0");


        s02.put("fas02c0596x", bi.fas02c0596x.getText().toString());


        s02.put("fas02c06a",
                bi.fas02c06a.isChecked() ? "1"
                        : "0");


        s02.put("fas02c06b",
                bi.fas02c06b.isChecked() ? "1"
                        : "0");


        s02.put("fas02c06c",
                bi.fas02c06c.isChecked() ? "1"
                        : "0");


        s02.put("fas02c06d",
                bi.fas02c06d.isChecked() ? "1"
                        : "0");


        s02.put("fas02c06e",
                bi.fas02c06e.isChecked() ? "1"
                        : "0");


        s02.put("fas02c06f",
                bi.fas02c06f.isChecked() ? "1"
                        : "0");


        s02.put("fas02c06g",
                bi.fas02c06g.isChecked() ? "1"
                        : "0");


        s02.put("fas02c06h",
                bi.fas02c06h.isChecked() ? "1"
                        : "0");


        s02.put("fas02c0696",
                bi.fas02c0696.isChecked() ? "1"
                        : "0");


        s02.put("fas02c0696x", bi.fas02c0696x.getText().toString());


        s02.put("fas02c07a",
                bi.fas02c07a.isChecked() ? "1"
                        : "0");
        s02.put("fas02c07w", bi.fas02c07w.getText().toString());


        s02.put("fas02c07b",
                bi.fas02c07b.isChecked() ? "1"
                        : "0");
        s02.put("fas02c07m", bi.fas02c07m.getText().toString());


        s02.put("fas02c0798",
                bi.fas02c0798.isChecked() ? "1"
                        : "0");


        s02.put("fas02c0898",
                bi.fas02c0898.isChecked() ? "1"
                        : "0");
        s02.put("fas02c08t", bi.fas02c08t.getText().toString());


        s02.put("fas02c09",
                bi.fas02c09a.isChecked() ? "1"
                        : bi.fas02c09b.isChecked() ? "2"
                        : "0");


        s02.put("fas02c10",
                bi.fas02c10a.isChecked() ? "1"
                        : bi.fas02c10b.isChecked() ? "2"
                        : "0");


        s02.put("fas02c11a",
                bi.fas02c11a.isChecked() ? "1"
                        : "0");
        s02.put("fas02c11ax", bi.fas02c11ax.getText().toString());


        s02.put("fas02c11b",
                bi.fas02c11b.isChecked() ? "1"
                        : "0");
        s02.put("fas02c11bx", bi.fas02c11bx.getText().toString());

        s02.put("fas02c11c",
                bi.fas02c11c.isChecked() ? "1"
                        : "0");
        s02.put("fas02c11cx", bi.fas02c11bx.getText().toString());


        s02.put("fas02c1201",
                bi.fas02c1201a.isChecked() ? "1"
                        : bi.fas02c1201b.isChecked() ? "2"
                        : bi.fas02c120198.isChecked() ? "98"
                        : "0");

        s02.put("fas02c1202",
                bi.fas02c1202a.isChecked() ? "1"
                        : bi.fas02c1202b.isChecked() ? "2"
                        : bi.fas02c120298.isChecked() ? "98"
                        : "0");

        s02.put("fas02c1203",
                bi.fas02c1203a.isChecked() ? "1"
                        : bi.fas02c1203b.isChecked() ? "2"
                        : bi.fas02c120398.isChecked() ? "98"
                        : "0");


        s02.put("fas02c13",
                bi.fas02c13a.isChecked() ? "1"
                        : bi.fas02c13b.isChecked() ? "2"
                        : bi.fas02c1398.isChecked() ? "98"
                        : "0");


        s02.put("fas02c1498",
                bi.fas02c1498.isChecked() ? "1"
                        : "0");
        s02.put("fas02c14", bi.fas02c14.getText().toString());


        s02.put("fas02c15",
                bi.fas02c15a.isChecked() ? "1"
                        : bi.fas02c15b.isChecked() ? "2"
                        : bi.fas02c1598.isChecked() ? "98"
                        : "0");


        s02.put("fas02c15",
                bi.fas02c15a.isChecked() ? "1"
                        : bi.fas02c15b.isChecked() ? "2"
                        : bi.fas02c1598.isChecked() ? "98"
                        : "0");


        s02.put("fas02c16",
                bi.fas02c16a.isChecked() ? "1"
                        : bi.fas02c16b.isChecked() ? "2"
                        : bi.fas02c1698.isChecked() ? "98"
                        : "0");


        s02.put("fas02c17",
                bi.fas02c17a.isChecked() ? "1"
                        : bi.fas02c17b.isChecked() ? "2"
                        : bi.fas02c17c.isChecked() ? "11"
                        : bi.fas02c17d.isChecked() ? "12"
                        : bi.fas02c17e.isChecked() ? "13"
                        : bi.fas02c17f.isChecked() ? "16"
                        : bi.fas02c17g.isChecked() ? "21"
                        : bi.fas02c17h.isChecked() ? "26"

                        : bi.fas02c1796.isChecked() ? "96"
                        : "0");


        s02.put("fas02c1796x", bi.fas02c1796x.getText().toString());
        s02.put("fas02c17fx", bi.fas02c17fx.getText().toString());
        s02.put("fas02c17hx", bi.fas02c17hx.getText().toString());


        s02.put("fas02c18a",
                bi.fas02c18a.isChecked() ? "1"
                        : "0");


        s02.put("fas02c18b",
                bi.fas02c18b.isChecked() ? "1"
                        : "0");


        s02.put("fas02c18c",
                bi.fas02c18c.isChecked() ? "1"
                        : "0");

        s02.put("fas02c18d",
                bi.fas02c18d.isChecked() ? "1"
                        : "0");


        s02.put("fas02c18e",
                bi.fas02c18e.isChecked() ? "1"
                        : "0");

        s02.put("fas02c18f",
                bi.fas02c18f.isChecked() ? "1"
                        : "0");

        s02.put("fas02c18g",
                bi.fas02c18g.isChecked() ? "1"
                        : "0");

        s02.put("fas02c18h",
                bi.fas02c18h.isChecked() ? "1"
                        : "0");

        s02.put("fas02c18i",
                bi.fas02c18i.isChecked() ? "1"
                        : "0");

        s02.put("fas02c18j",
                bi.fas02c18j.isChecked() ? "1"
                        : "0");

        s02.put("fas02c18k",
                bi.fas02c18k.isChecked() ? "1"
                        : "0");

        s02.put("fas02c1896",
                bi.fas02c1896.isChecked() ? "1"
                        : "0");
        s02.put("fas02c1896x", bi.fas02c1896x.getText().toString());


        s02.put("fas02c19",
                bi.fas02c19a.isChecked() ? "11"
                        : bi.fas02c19b.isChecked() ? "12"
                        : bi.fas02c19c.isChecked() ? "13"
                        : bi.fas02c19d.isChecked() ? "14"
                        : bi.fas02c19e.isChecked() ? "21"
                        : bi.fas02c19f.isChecked() ? "22"
                        : bi.fas02c19g.isChecked() ? "23"
                        : bi.fas02c19h.isChecked() ? "24"
                        : bi.fas02c19i.isChecked() ? "25"
                        : bi.fas02c19j.isChecked() ? "96"
                        : bi.fas02c19k.isChecked() ? "98"
                        : "0");


        s02.put("fas02c1996x", bi.fas02c1996x.getText().toString());


        s02.put("fas02c20a",
                bi.fas02c20a.isChecked() ? "1"
                        : "0");


        s02.put("fas02c20b",
                bi.fas02c20b.isChecked() ? "1"
                        : "0");


        s02.put("fas02c20c",
                bi.fas02c20c.isChecked() ? "1"
                        : "0");


        s02.put("fas02c20d",
                bi.fas02c20d.isChecked() ? "1"
                        : "0");


        s02.put("fas02c20e",
                bi.fas02c20e.isChecked() ? "1"
                        : "0");


        s02.put("fas02c20f",
                bi.fas02c20f.isChecked() ? "1"
                        : "0");


        s02.put("fas02c2096",
                bi.fas02c2096.isChecked() ? "1"
                        : "0");


        s02.put("fas02c2096x", bi.fas02c2096x.getText().toString());

        s02.put("fas02c21",
                bi.fas02c21a.isChecked() ? "1"
                        : bi.fas02c21b.isChecked() ? "2"
                        : "0");


        s02.put("fas02c2201",
                bi.fas02c2201a.isChecked() ? "1"
                        : bi.fas02c2201b.isChecked() ? "2"
                        : "0");


        s02.put("fas02c2202",
                bi.fas02c2202a.isChecked() ? "1"
                        : bi.fas02c2202b.isChecked() ? "2"
                        : "0");


        s02.put("fas02c2203",
                bi.fas02c2203a.isChecked() ? "1"
                        : bi.fas02c2203b.isChecked() ? "2"
                        : "0");


        s02.put("fas02c2204",
                bi.fas02c2204a.isChecked() ? "1"
                        : bi.fas02c2204b.isChecked() ? "2"
                        : "0");


        s02.put("fas02c2205",
                bi.fas02c2205a.isChecked() ? "1"
                        : bi.fas02c2205b.isChecked() ? "2"
                        : "0");


        s02.put("fas02c2206",
                bi.fas02c2206a.isChecked() ? "1"
                        : bi.fas02c2206b.isChecked() ? "2"
                        : "0");


        s02.put("fas02c2207",
                bi.fas02c2207a.isChecked() ? "1"
                        : bi.fas02c2207b.isChecked() ? "2"
                        : "0");


        s02.put("fas02c2208",
                bi.fas02c2208a.isChecked() ? "1"
                        : bi.fas02c2208b.isChecked() ? "2"
                        : "0");


        s02.put("fas02c2301",
                bi.fas02c2301a.isChecked() ? "1"
                        : bi.fas02c2301b.isChecked() ? "2"
                        : bi.fas02c2301c.isChecked() ? "98"
                        : "0");

        s02.put("fas02c2302",
                bi.fas02c2302a.isChecked() ? "1"
                        : bi.fas02c2302b.isChecked() ? "2"
                        : bi.fas02c2302c.isChecked() ? "98"
                        : "0");


        s02.put("fas02c2303",
                bi.fas02c2303a.isChecked() ? "1"
                        : bi.fas02c2303b.isChecked() ? "2"
                        : bi.fas02c2303c.isChecked() ? "98"
                        : "0");

        s02.put("fas02c2304",
                bi.fas02c2304a.isChecked() ? "1"
                        : bi.fas02c2304b.isChecked() ? "2"
                        : bi.fas02c2304c.isChecked() ? "98"
                        : "0");

        s02.put("fas02c2305",
                bi.fas02c2305a.isChecked() ? "1"
                        : bi.fas02c2305b.isChecked() ? "2"
                        : bi.fas02c2305c.isChecked() ? "98"
                        : "0");

        s02.put("fas02c2306",
                bi.fas02c2306a.isChecked() ? "1"
                        : bi.fas02c2306b.isChecked() ? "2"
                        : bi.fas02c2306c.isChecked() ? "98"
                        : "0");


        s02.put("fas02c24a",
                bi.fas02c24a.isChecked() ? "1"
                        : "0");


        s02.put("fas02c24b",
                bi.fas02c24b.isChecked() ? "1"
                        : "0");


        s02.put("fas02c24c",
                bi.fas02c24c.isChecked() ? "1"
                        : "0");

        s02.put("fas02c24d",
                bi.fas02c24d.isChecked() ? "1"
                        : "0");


        s02.put("fas02c24e",
                bi.fas02c24e.isChecked() ? "1"
                        : "0");


        s02.put("fas02c2496",
                bi.fas02c2496.isChecked() ? "1"
                        : "0");

        s02.put("fas02c24f",
                bi.fas02c24f.isChecked() ? "1"
                        : "0");

        s02.put("fas02c2496x", bi.fas02c2496x.getText().toString());


        s02.put("fas02c25",
                bi.fas02c25a.isChecked() ? "1"
                        : bi.fas02c25b.isChecked() ? "2"
                        : bi.fas02c25c.isChecked() ? "3"
                        : bi.fas02c25d.isChecked() ? "4"
                        : bi.fas02c25e.isChecked() ? "5"
                        : bi.fas02c25f.isChecked() ? "6"
                        : bi.fas02c2596.isChecked() ? "96"
                        : "0");

        s02.put("fas02c2596x", bi.fas02c2596x.getText().toString());


        s02.put("fas02c26a",
                bi.fas02c26aa.isChecked() ? "1"
                        : bi.fas02c26ab.isChecked() ? "2"
                        : bi.fas02c26ac.isChecked() ? "3"
                        : bi.fas02c26ad.isChecked() ? "4"
                        : bi.fas02c26ae.isChecked() ? "5"
                        : bi.fas02c26af.isChecked() ? "6"
                        : bi.fas02c26a96.isChecked() ? "96"
                        : "0");

        s02.put("fas02c26a96x", bi.fas02c26a96x.getText().toString());





    }

    private boolean formValidation() {


        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecC02);
    }

    public void BtnEnd() {
//        MainApp.endActivity(this, this, EndingActivity.class, false, InfoActivity.fc);
    }
}
