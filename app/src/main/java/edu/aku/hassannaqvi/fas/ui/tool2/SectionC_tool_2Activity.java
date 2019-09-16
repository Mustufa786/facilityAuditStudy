package edu.aku.hassannaqvi.fas.ui.tool2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
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

        ClearClass.ClearAllFields(bi.fas02c00, false);

        String getSurvey = MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_02_SURVEY_TYPE);
        if (!getSurvey.equals("0"))
            bi.fas02c00.check(bi.fas02c00.getChildAt(Integer.valueOf(getSurvey) - 1).getId());
        bi.fas02c001.setText(MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_02_HF_NO));
        bi.fas02cmw01.setText(MainApp.getParamValue(this, CONSTANTS._URI_DATAMAP_02_W_ID));

    }


    private void setListenersUI() {

//        fas02c10t
        bi.fas02c10t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (bi.fas02c10t.getText().toString().length() > 0) {
                    bi.fas02c1098.setChecked(false);
                    bi.fas02c1098.setEnabled(false);
                } else {
                    bi.fas02c1098.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        bi.fas02c1098.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.fas02c10t.setText(null);
                    bi.fas02c10t.setEnabled(false);
                } else {
                    bi.fas02c10t.setEnabled(true);
                }
            }
        });

//        fas02c16
        bi.fas02c16.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (bi.fas02c16.getText().toString().length() > 0) {
                    bi.fas02c1698.setChecked(false);
                    bi.fas02c1698.setEnabled(false);
                } else {
                    bi.fas02c1698.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        bi.fas02c1698.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.fas02c16.setText(null);
                    bi.fas02c16.setEnabled(false);
                } else {
                    bi.fas02c16.setEnabled(true);
                }
            }
        });

        bi.fas02c27.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.fas02c27a.getId()) {
                    bi.fas02c03.clearCheck();
                }
            }
        });

        bi.fas02c15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.fas02c15b.getId()) {
                    bi.fas02c16.setText(null);
                    bi.fas02c1698.setChecked(false);
                }
            }
        });

//        fas02c26f
        bi.fas02c26f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.fldGrpfas02c10a, false);
                    bi.fldGrpfas02c10a.setTag("-1");
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpfas02c10a, true);
                    bi.fldGrpfas02c10a.setTag(null);
                }
            }
        });

//        fas02c01
//        bi.fas02c01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if (i != bi.fas02c01a.getId())
//                    ClearClass.ClearAllFields(bi.fldGrpllSecC02a, null);
//            }
//        });

//        fas02c05
        bi.fas02c05.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.fas02c05a.getId()) {
                    bi.fas02c06.clearCheck();
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpllSecC02b, null);
                }
            }
        });

//        fas02c12
        bi.fas02c12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02c12a.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpfas02c13, null);
                }
            }
        });

//        fas02c17
        bi.fas02c17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02c17a.getId() || i != bi.fas02c17b.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpfas02c20, null);
                }
            }
        });

//        fas02c21
        CheckBox.OnCheckedChangeListener chbxc19 = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!bi.fas02c21e.isChecked())
                    ClearClass.ClearAllFields(bi.fldGrpfas02c22, null);
            }
        };
        bi.fas02c21e.setOnCheckedChangeListener(chbxc19);
        bi.fas02c2198.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.fldGrpfas02c21, false);
                    bi.fldGrpfas02c21.setTag("-1");
                    bi.fas02c2198.setTag(null);
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpfas02c21, true);
                    bi.fldGrpfas02c21.setTag(null);
                    bi.fas02c2198.setTag("-1");
                }
            }
        });

//        fas02c23
        bi.fas02c23.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02c23a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpfas02c24, null);
            }
        });

//        fas02c25
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

        s03.put("fas02c001", bi.fas02c001.getText().toString());
        s03.put("fas02cmw01", bi.fas02cmw01.getText().toString());

        s03.put("fas02c02", bi.fas02c02a.isChecked() ? "1"
                : bi.fas02c02b.isChecked() ? "2"
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
                : bi.fas02c0796.isChecked() ? "96"
                        : "0");
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
        s03.put("fas02c09w", bi.fas02c09w.getText().toString());
        s03.put("fas02c09m", bi.fas02c09m.getText().toString());


        s03.put("fas02c1098", bi.fas02c1098.isChecked() ? "98" : "0");
        s03.put("fas02c10t", bi.fas02c10t.getText().toString());


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
