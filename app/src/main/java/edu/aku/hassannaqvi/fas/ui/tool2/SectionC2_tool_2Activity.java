package edu.aku.hassannaqvi.fas.ui.tool2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
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
import edu.aku.hassannaqvi.fas.databinding.ActivitySectionC2Tool2Binding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ClearClass;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

public class SectionC2_tool_2Activity extends AppCompatActivity {

    private ActivitySectionC2Tool2Binding bi;
    private Forms fc;
    private boolean c25Flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_c2_tool_2);
        bi.setCallback(this);

        setContentUI();
        setListenersUI();
    }

    private void setContentUI() {
        this.setTitle(R.string.section3_tool2);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

        if (MainApp.WI2C.equals("1")) {
            bi.fas02c25cv.setVisibility(View.VISIBLE);
        } else {
            ClearClass.ClearAllFields(bi.fas02c25cv, null);
            bi.fas02c25cv.setVisibility(View.GONE);
        }

    }


    private void setListenersUI() {


        //fas02c19
        bi.fas02c19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == bi.fas02c19a.getId() || i == bi.fas02c19b.getId()) {
                    bi.fas02c20cv.setVisibility(View.VISIBLE);
                } else {
                    bi.fas02c20cv.setVisibility(View.GONE);
                    ClearClass.ClearAllFields(bi.fas02c20cv, null);
                }
            }
        });


       /* bi.fas02c27.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.fas02c27a.getId()) {
                    bi.fas02c03.clearCheck();
                }
            }
        });*/


        //fas02c21
        bi.fas02c2198.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.llfas02c21, false);
                    bi.llfas02c21.setTag("-1");
                    bi.fas02c2198.setTag(null);
                } else {
                    ClearClass.ClearAllFields(bi.llfas02c21, true);
                    bi.llfas02c21.setTag(null);
                    bi.fas02c2198.setTag("-1");
                }
            }
        });
        bi.fas02c21e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.fas02c22cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.llfas02c22, null);
                    bi.fas02c22cv.setVisibility(View.GONE);
                }
            }
        });
        bi.fas02c21h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.fas02c22cv.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.llfas02c22, null);
                    bi.fas02c22cv.setVisibility(View.GONE);
                }
            }
        });

        //fas02c23
        bi.fas02c23.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02c23a.getId())
                    ClearClass.ClearAllFields(bi.fas02c24cv, null);
            }
        });

        //fas02c25
        RadioGroup.OnCheckedChangeListener rdg25 = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                c25Flag = bi.fas02c2501b.isChecked();
                c25Flag = bi.fas02c2502b.isChecked();
                c25Flag = bi.fas02c2503b.isChecked();
                c25Flag = bi.fas02c2504b.isChecked();
                c25Flag = bi.fas02c2505b.isChecked();
                c25Flag = bi.fas02c2506b.isChecked();

                if (c25Flag) {
                    bi.fldGrpfas02c26.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpfas02c26, null);
                    bi.fldGrpfas02c26.setVisibility(View.GONE);
                }

            }
        };

        bi.fas02c2501.setOnCheckedChangeListener(rdg25);
        bi.fas02c2502.setOnCheckedChangeListener(rdg25);
        bi.fas02c2503.setOnCheckedChangeListener(rdg25);
        bi.fas02c2504.setOnCheckedChangeListener(rdg25);
        bi.fas02c2505.setOnCheckedChangeListener(rdg25);
        bi.fas02c2506.setOnCheckedChangeListener(rdg25);


        //fas02c26f
        bi.fas02c26f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.llfas02c26, false);
                    bi.llfas02c26.setTag("-1");
                } else {
                    ClearClass.ClearAllFields(bi.llfas02c26, true);
                    bi.llfas02c26.setTag(null);
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
                MainApp.stActivity(this, this, SectionD_tool_2Activity.class, fc);
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

        s03.put("fas02c19", bi.fas02c19a.isChecked() ? "1"
                : bi.fas02c19b.isChecked() ? "2"
                : bi.fas02c19c.isChecked() ? "3"
                : bi.fas02c19d.isChecked() ? "4"
                : bi.fas02c19e.isChecked() ? "5"
                : bi.fas02c19f.isChecked() ? "6"
                : bi.fas02c19g.isChecked() ? "7"
                : bi.fas02c19h.isChecked() ? "8"
                : bi.fas02c19i.isChecked() ? "9"
                : bi.fas02c1996.isChecked() ? "96"
                : "0");
        s03.put("fas02c1996x", bi.fas02c1996x.getText().toString());
        s03.put("fas02c19fx", bi.fas02c19fx.getText().toString());
        s03.put("fas02c19hx", bi.fas02c19hx.getText().toString());


        s03.put("fas02c20a", bi.fas02c20a.isChecked() ? "1" : "0");
        s03.put("fas02c20b", bi.fas02c20b.isChecked() ? "2" : "0");
        s03.put("fas02c20c", bi.fas02c20c.isChecked() ? "3" : "0");
        s03.put("fas02c20d", bi.fas02c20d.isChecked() ? "4" : "0");
        s03.put("fas02c20e", bi.fas02c20e.isChecked() ? "5" : "0");
        s03.put("fas02c20f", bi.fas02c20f.isChecked() ? "6" : "0");
        s03.put("fas02c20g", bi.fas02c20g.isChecked() ? "7" : "0");
        s03.put("fas02c20h", bi.fas02c20h.isChecked() ? "8" : "0");
        s03.put("fas02c20i", bi.fas02c20i.isChecked() ? "9" : "0");
        s03.put("fas02c20j", bi.fas02c20j.isChecked() ? "10" : "0");
        s03.put("fas02c20k", bi.fas02c20k.isChecked() ? "11" : "0");
        s03.put("fas02c2096", bi.fas02c2096.isChecked() ? "96" : "0");
        s03.put("fas02c2096x", bi.fas02c2096x.getText().toString());


        s03.put("fas02c21a", bi.fas02c21a.isChecked() ? "1" : "0");
        s03.put("fas02c21b", bi.fas02c21b.isChecked() ? "2" : "0");
        s03.put("fas02c21c", bi.fas02c21c.isChecked() ? "3" : "0");
        s03.put("fas02c21d", bi.fas02c21d.isChecked() ? "4" : "0");
        s03.put("fas02c21e", bi.fas02c21e.isChecked() ? "5" : "0");
        s03.put("fas02c21f", bi.fas02c21f.isChecked() ? "6" : "0");
        s03.put("fas02c21g", bi.fas02c21g.isChecked() ? "7" : "0");
        s03.put("fas02c21h", bi.fas02c21h.isChecked() ? "8" : "0");
        s03.put("fas02c21i", bi.fas02c21i.isChecked() ? "9" : "0");
        s03.put("fas02c2196", bi.fas02c2196.isChecked() ? "96" : "0");
        s03.put("fas02c2198", bi.fas02c2198.isChecked() ? "98" : "0");
        s03.put("fas02c2196x", bi.fas02c2196x.getText().toString());


        s03.put("fas02c22a", bi.fas02c22a.isChecked() ? "1" : "0");
        s03.put("fas02c22b", bi.fas02c22b.isChecked() ? "2" : "0");
        s03.put("fas02c22c", bi.fas02c22c.isChecked() ? "3" : "0");
        s03.put("fas02c22d", bi.fas02c22d.isChecked() ? "4" : "0");
        s03.put("fas02c22e", bi.fas02c22e.isChecked() ? "5" : "0");
        s03.put("fas02c22f", bi.fas02c22f.isChecked() ? "6" : "0");
        s03.put("fas02c2296", bi.fas02c2296.isChecked() ? "96" : "0");
        s03.put("fas02c2296x", bi.fas02c2296x.getText().toString());

        s03.put("fas02c23", bi.fas02c23a.isChecked() ? "1"
                : bi.fas02c23b.isChecked() ? "2"
                : "0");


        s03.put("fas02c2401", bi.fas02c2401a.isChecked() ? "1"
                : bi.fas02c2401b.isChecked() ? "2"
                : "0");


        s03.put("fas02c2402", bi.fas02c2402a.isChecked() ? "1"
                : bi.fas02c2402b.isChecked() ? "2"
                : "0");


        s03.put("fas02c2403", bi.fas02c2403a.isChecked() ? "1"
                : bi.fas02c2403b.isChecked() ? "2"
                : "0");


        s03.put("fas02c2404", bi.fas02c2404a.isChecked() ? "1"
                : bi.fas02c2404b.isChecked() ? "2"
                : "0");


        s03.put("fas02c2405", bi.fas02c2405a.isChecked() ? "1"
                : bi.fas02c2405b.isChecked() ? "2"
                : "0");


        s03.put("fas02c2406", bi.fas02c2406a.isChecked() ? "1"
                : bi.fas02c2406b.isChecked() ? "2"
                : "0");


        s03.put("fas02c2407", bi.fas02c2407a.isChecked() ? "1"
                : bi.fas02c2407b.isChecked() ? "2"
                : "0");


        s03.put("fas02c2408", bi.fas02c2408a.isChecked() ? "1"
                : bi.fas02c2408b.isChecked() ? "2"
                : "0");


        s03.put("fas02c2501", bi.fas02c2501a.isChecked() ? "1"
                : bi.fas02c2501b.isChecked() ? "2"
                : bi.fas02c250198.isChecked() ? "98"
                : "0");

        s03.put("fas02c2502", bi.fas02c2502a.isChecked() ? "1"
                : bi.fas02c2502b.isChecked() ? "2"
                : bi.fas02c250298.isChecked() ? "98"
                : "0");


        s03.put("fas02c2503", bi.fas02c2503a.isChecked() ? "1"
                : bi.fas02c2503b.isChecked() ? "2"
                : bi.fas02c250398.isChecked() ? "98"
                : "0");

        s03.put("fas02c2504", bi.fas02c2504a.isChecked() ? "1"
                : bi.fas02c2504b.isChecked() ? "2"
                : bi.fas02c250498.isChecked() ? "98"
                : "0");

        s03.put("fas02c2505", bi.fas02c2505a.isChecked() ? "1"
                : bi.fas02c2505b.isChecked() ? "2"
                : bi.fas02c250598.isChecked() ? "98"
                : "0");

        s03.put("fas02c2506", bi.fas02c2506a.isChecked() ? "1"
                : bi.fas02c2506b.isChecked() ? "2"
                : bi.fas02c250698.isChecked() ? "98"
                : "0");


        s03.put("fas02c26a", bi.fas02c26a.isChecked() ? "1" : "0");
        s03.put("fas02c26b", bi.fas02c26b.isChecked() ? "2" : "0");
        s03.put("fas02c26c", bi.fas02c26c.isChecked() ? "3" : "0");
        s03.put("fas02c26d", bi.fas02c26d.isChecked() ? "4" : "0");
        s03.put("fas02c26e", bi.fas02c26e.isChecked() ? "5" : "0");
        s03.put("fas02c26f", bi.fas02c26f.isChecked() ? "6" : "0");
        s03.put("fas02c2696", bi.fas02c2696.isChecked() ? "96" : "0");
        s03.put("fas02c2696x", bi.fas02c2696x.getText().toString());


        s03.put("fas02c27", bi.fas02c27a.isChecked() ? "1"
                : bi.fas02c27b.isChecked() ? "2"
                : bi.fas02c27c.isChecked() ? "3"
                : bi.fas02c27d.isChecked() ? "4"
                : bi.fas02c27e.isChecked() ? "5"
                : bi.fas02c27f.isChecked() ? "6"
                : bi.fas02c2796.isChecked() ? "96"
                : "0");
        s03.put("fas02c2796x", bi.fas02c2796x.getText().toString());

        s03.put("fas02c28", bi.fas02c28a.isChecked() ? "1"
                : bi.fas02c28b.isChecked() ? "2"
                : bi.fas02c28c.isChecked() ? "3"
                : bi.fas02c28d.isChecked() ? "4"
                : bi.fas02c28e.isChecked() ? "5"
                : bi.fas02c28f.isChecked() ? "6"
                : bi.fas02c2896.isChecked() ? "96"
                : "0");
        s03.put("fas02c2896x", bi.fas02c2896x.getText().toString());

        fc.setSa4(String.valueOf(s03));

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.llc2tool2);
    }

    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
