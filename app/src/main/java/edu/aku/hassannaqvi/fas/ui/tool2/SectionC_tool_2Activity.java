package edu.aku.hassannaqvi.fas.ui.tool2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
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
import edu.aku.hassannaqvi.fas.databinding.ActivitySectionCTool2Binding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ClearClass;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

public class SectionC_tool_2Activity extends AppCompatActivity {

    private ActivitySectionCTool2Binding bi;
    private Forms fc;
    private boolean c25Flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_c_tool_2);
        bi.setCallback(this);

        setContentUI();
        setListenersUI();
    }

    private void setContentUI() {
        this.setTitle(R.string.section3_tool2);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

        if (MainApp.WI2C.equals("1")) {
            bi.fas02c11cv.setVisibility(View.VISIBLE);
        } else {
            ClearClass.ClearAllFields(bi.fas02c11cv, null);
            bi.fas02c11cv.setVisibility(View.GONE);
        }

        ClearClass.ClearAllFields(bi.fas02c00, false);

        String getSurvey = MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_02_SURVEY_TYPE);
        if (!getSurvey.equals("0"))
            bi.fas02c00.check(bi.fas02c00.getChildAt(Integer.valueOf(getSurvey) - 1).getId());
        bi.fas02c001.setText(MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_02_HF_NO));
        bi.fas02cmw01.setText(MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_02_W_ID));

    }


    private void setListenersUI() {

        //fas02c01
        bi.fas02c01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.fas02c01a.getId()) {
                    bi.fldGrpllSecC02a.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpllSecC02a, null);
                    bi.fldGrpllSecC02a.setVisibility(View.GONE);
                }
            }
        });

        //fas02c03
        bi.fas02c03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.fas02c03b.getId()) {
                    bi.fas02c04cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.fas02c04cv, null);
                    bi.fas02c04cv.setVisibility(View.GONE);
                }
            }
        });

        //fas02c05
        bi.fas02c05.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                ClearClass.ClearAllFields(bi.fas02c06cv, null);
                ClearClass.ClearAllFields(bi.fldGrpllSecC02b, null);
                bi.fas02c06cv.setVisibility(View.GONE);
                bi.fldGrpllSecC02b.setVisibility(View.GONE);

                if (i == bi.fas02c05b.getId()) {
                    bi.fas02c06cv.setVisibility(View.VISIBLE);
                } else if (i != bi.fas02c05b.getId()) {
                    bi.fldGrpllSecC02b.setVisibility(View.VISIBLE);
                }
            }
        });


        //fas02c12
        bi.fas02c12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.fas02c12a.getId()) {
                    bi.fas02c13cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.fas02c13cv, null);
                    bi.fas02c13cv.setVisibility(View.GONE);
                }
            }
        });


        //fas02c15
        bi.fas02c15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.fas02c15b.getId()) {
                    ClearClass.ClearAllFields(bi.fas02c16cv, null);
                    bi.fas02c16cv.setVisibility(View.GONE);
                } else {
                    bi.fas02c16cv.setVisibility(View.VISIBLE);
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
                MainApp.stActivity(this, this, SectionC2_tool_2Activity.class, fc);
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

        JSONObject s03 = new JSONObject();

        s03.put("fas02c001", bi.fas02c001.getText().toString());
        s03.put("fas02cmw01", bi.fas02cmw01.getText().toString());

        s03.put("fas02c01", bi.fas02c01a.isChecked() ? "1"
                : bi.fas02c01b.isChecked() ? "2"
                : "0");

        s03.put("fas02c02m", bi.fas02c02m.getText().toString());
        s03.put("fas02c02y", bi.fas02c02y.getText().toString());

        s03.put("fas02c03", bi.fas02c03a.isChecked() ? "1"
                : bi.fas02c03b.isChecked() ? "2"
                : "0");

        s03.put("fas02c04", bi.fas02c04a.isChecked() ? "1"
                : bi.fas02c04b.isChecked() ? "2"
                : bi.fas02c04c.isChecked() ? "3"
                : bi.fas02c0496.isChecked() ? "96"
                : "0");
        s03.put("fas02c0496x", bi.fas02c0496x.getText().toString());


        s03.put("fas02c05", bi.fas02c05a.isChecked() ? "1"
                : bi.fas02c05b.isChecked() ? "2"
                : bi.fas02c0598.isChecked() ? "98"
                : "0");

        s03.put("fas02c06", bi.fas02c06a.isChecked() ? "1"
                : bi.fas02c06b.isChecked() ? "2"
                : bi.fas02c06c.isChecked() ? "3"
                : bi.fas02c06d.isChecked() ? "4"
                : bi.fas02c06e.isChecked() ? "5"
                : bi.fas02c06f.isChecked() ? "6"
                : bi.fas02c06g.isChecked() ? "7"
                : bi.fas02c06h.isChecked() ? "8"
                : bi.fas02c06i.isChecked() ? "9"
                : bi.fas02c06j.isChecked() ? "10"
                : bi.fas02c06k.isChecked() ? "11"
                : bi.fas02c06l.isChecked() ? "12"
                : bi.fas02c0696.isChecked() ? "96"
                : "0");
        s03.put("fas02c0696x", bi.fas02c0696x.getText().toString());


        s03.put("fas02c07", bi.fas02c07a.isChecked() ? "1"
                : bi.fas02c07b.isChecked() ? "2"
                : bi.fas02c07c.isChecked() ? "3"
                : bi.fas02c07d.isChecked() ? "4"
                : bi.fas02c07e.isChecked() ? "5"
                : bi.fas02c07f.isChecked() ? "6"
                : bi.fas02c07g.isChecked() ? "7"
                : bi.fas02c07h.isChecked() ? "8"
                : bi.fas02c07i.isChecked() ? "9"
                : bi.fas02c07j.isChecked() ? "10"
                : bi.fas02c07k.isChecked() ? "11"
                : bi.fas02c07l.isChecked() ? "12"
                : bi.fas02c0796.isChecked() ? "96"
                : "0");
        s03.put("fas02c07fx", bi.fas02c07fx.getText().toString());
        s03.put("fas02c07jx", bi.fas02c07jx.getText().toString());
        s03.put("fas02c0796x", bi.fas02c0796x.getText().toString());


        s03.put("fas02c08a", bi.fas02c08a.isChecked() ? "1" : "0");
        s03.put("fas02c08b", bi.fas02c08b.isChecked() ? "2" : "0");
        s03.put("fas02c08c", bi.fas02c08c.isChecked() ? "3" : "0");
        s03.put("fas02c08d", bi.fas02c08d.isChecked() ? "4" : "0");
        s03.put("fas02c08e", bi.fas02c08e.isChecked() ? "5" : "0");
        s03.put("fas02c08f", bi.fas02c08f.isChecked() ? "6" : "0");
        s03.put("fas02c08g", bi.fas02c08g.isChecked() ? "7" : "0");
        s03.put("fas02c08h", bi.fas02c08h.isChecked() ? "8" : "0");
        s03.put("fas02c0896", bi.fas02c0896.isChecked() ? "96" : "0");
        s03.put("fas02c0896x", bi.fas02c0896x.getText().toString());


        s03.put("fas02c09", bi.fas02c09a.isChecked() ? "1"
                : bi.fas02c09b.isChecked() ? "2"
                : bi.fas02c0998.isChecked() ? "98"
                : "0");
        s03.put("fas02c09ax", bi.fas02c09ax.getText().toString());
        s03.put("fas02c09bx", bi.fas02c09bx.getText().toString());


        s03.put("fas02c10", bi.fas02c10.getText().toString());
        s03.put("fas02c1098", bi.fas02c1098.isChecked() ? "98" : "0");


        s03.put("fas02c11", bi.fas02c11a.isChecked() ? "1"
                : bi.fas02c11b.isChecked() ? "2"
                : "0");

        s03.put("fas02c12", bi.fas02c12a.isChecked() ? "1"
                : bi.fas02c12b.isChecked() ? "2"
                : "0");

        s03.put("fas02c13a", bi.fas02c13a.isChecked() ? "1" : "0");
        s03.put("fas02c13ax", bi.fas02c13ax.getText().toString());

        s03.put("fas02c13b", bi.fas02c13b.isChecked() ? "2" : "0");
        s03.put("fas02c13bx", bi.fas02c13bx.getText().toString());

        s03.put("fas02c1396", bi.fas02c1396.isChecked() ? "96" : "0");
        s03.put("fas02c1396x", bi.fas02c1396x.getText().toString());


        s03.put("fas02c1401", bi.fas02c1401a.isChecked() ? "1"
                : bi.fas02c1401b.isChecked() ? "2"
                : bi.fas02c140198.isChecked() ? "98"
                : "0");

        s03.put("fas02c1402", bi.fas02c1402a.isChecked() ? "1"
                : bi.fas02c1402b.isChecked() ? "2"
                : bi.fas02c140298.isChecked() ? "98"
                : "0");

        s03.put("fas02c1403", bi.fas02c1403a.isChecked() ? "1"
                : bi.fas02c1403b.isChecked() ? "2"
                : bi.fas02c140398.isChecked() ? "98"
                : "0");


        s03.put("fas02c15", bi.fas02c15a.isChecked() ? "1"
                : bi.fas02c15b.isChecked() ? "2"
                : bi.fas02c1598.isChecked() ? "98"
                : "0");


        s03.put("fas02c1698", bi.fas02c1698.isChecked() ? "98" : "0");
        s03.put("fas02c16", bi.fas02c16.getText().toString());


        s03.put("fas02c17", bi.fas02c17a.isChecked() ? "1"
                : bi.fas02c17b.isChecked() ? "2"
                : bi.fas02c1798.isChecked() ? "98"
                : "0");


        s03.put("fas02c18", bi.fas02c18a.isChecked() ? "1"
                : bi.fas02c18b.isChecked() ? "2"
                : bi.fas02c1898.isChecked() ? "98"
                : "0");

        fc.setSa3(String.valueOf(s03));

    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecC02);
    }


    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
