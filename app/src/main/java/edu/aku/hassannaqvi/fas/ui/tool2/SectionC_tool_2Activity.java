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
    private boolean c23Flag = false;

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

//        fas02c08t
        bi.fas02c08t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (bi.fas02c08t.getText().toString().length() > 0) {
                    bi.fas02c0898.setChecked(false);
                    bi.fas02c0898.setEnabled(false);
                } else {
                    bi.fas02c0898.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        bi.fas02c0898.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.fas02c08t.setText(null);
                    bi.fas02c08t.setEnabled(false);
                } else {
                    bi.fas02c08t.setEnabled(true);
                }
            }
        });

//        fas02c14
        bi.fas02c14.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (bi.fas02c14.getText().toString().length() > 0) {
                    bi.fas02c1498.setChecked(false);
                    bi.fas02c1498.setEnabled(false);
                } else {
                    bi.fas02c1498.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        bi.fas02c1498.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.fas02c14.setText(null);
                    bi.fas02c14.setEnabled(false);
                } else {
                    bi.fas02c14.setEnabled(true);
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

        bi.fas02c13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.fas02c13b.getId()) {
                    bi.fas02c14.setText(null);
                    bi.fas02c1498.setChecked(false);
                }
            }
        });

//        fas02c24f
        bi.fas02c24f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        bi.fas02c01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02c01a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpllSecC02a, null);
            }
        });

//        fas02c03
        bi.fas02c03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bi.fas02c03a.getId()) {
                    bi.fas02c04.clearCheck();
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpllSecC02b, null);
                }
            }
        });

//        fas02c10
        bi.fas02c10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02c10a.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpfas02c11, null);
                }
            }
        });

//        fas02c17
        bi.fas02c17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02c17a.getId() || i != bi.fas02c17b.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpfas02c18, null);
                }
            }
        });

//        fas02c19
        CheckBox.OnCheckedChangeListener chbxc19 = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!bi.fas02c19e.isChecked())
                    ClearClass.ClearAllFields(bi.fldGrpfas02c20, null);
            }
        };
        bi.fas02c19e.setOnCheckedChangeListener(chbxc19);
        bi.fas02c1998.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.fldGrpfas02c19, false);
                    bi.fldGrpfas02c19.setTag("-1");
                    bi.fas02c1998.setTag(null);
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpfas02c19, true);
                    bi.fldGrpfas02c19.setTag(null);
                    bi.fas02c1998.setTag("-1");
                }
            }
        });

//        fas02c21
        bi.fas02c21.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != bi.fas02c21a.getId())
                    ClearClass.ClearAllFields(bi.fldGrpfas02c22, null);
            }
        });

//        fas02c23
        RadioGroup.OnCheckedChangeListener rdg23 = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                c23Flag = bi.fas02c2301b.isChecked();
                c23Flag = bi.fas02c2302b.isChecked();
                c23Flag = bi.fas02c2303b.isChecked();
                c23Flag = bi.fas02c2304b.isChecked();
                c23Flag = bi.fas02c2305b.isChecked();
                c23Flag = bi.fas02c2306b.isChecked();

                if (c23Flag) {
                    bi.fldGrpfas02c24.setVisibility(View.VISIBLE);
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpfas02c24, null);
                    bi.fldGrpfas02c24.setVisibility(View.GONE);
                }

            }
        };
        bi.fas02c2301.setOnCheckedChangeListener(rdg23);
        bi.fas02c2302.setOnCheckedChangeListener(rdg23);
        bi.fas02c2303.setOnCheckedChangeListener(rdg23);
        bi.fas02c2304.setOnCheckedChangeListener(rdg23);
        bi.fas02c2305.setOnCheckedChangeListener(rdg23);
        bi.fas02c2306.setOnCheckedChangeListener(rdg23);

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

        s03.put("fas02c01",
                bi.fas02c01a.isChecked() ? "1"
                        : bi.fas02c01b.isChecked() ? "2"
                        : "0");

        s03.put("fas02c02m", bi.fas02c02m.getText().toString());
        s03.put("fas02c02y", bi.fas02c02y.getText().toString());


        s03.put("fas02c03",
                bi.fas02c03a.isChecked() ? "1"
                        : bi.fas02c03b.isChecked() ? "2"
                        : bi.fas02c0398.isChecked() ? "98"
                        : "0");

        s03.put("fas02c04",
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


        s03.put("fas02c0496x", bi.fas02c0496x.getText().toString());


        s03.put("fas02c05",
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
                        : bi.fas02c0596.isChecked() ? "96"
                        : "0");


        s03.put("fas02c0596x", bi.fas02c0596x.getText().toString());


        s03.put("fas02c06a",
                bi.fas02c06a.isChecked() ? "1"
                        : "0");


        s03.put("fas02c06b",
                bi.fas02c06b.isChecked() ? "1"
                        : "0");


        s03.put("fas02c06c",
                bi.fas02c06c.isChecked() ? "1"
                        : "0");


        s03.put("fas02c06d",
                bi.fas02c06d.isChecked() ? "1"
                        : "0");


        s03.put("fas02c06e",
                bi.fas02c06e.isChecked() ? "1"
                        : "0");


        s03.put("fas02c06f",
                bi.fas02c06f.isChecked() ? "1"
                        : "0");


        s03.put("fas02c06g",
                bi.fas02c06g.isChecked() ? "1"
                        : "0");


        s03.put("fas02c06h",
                bi.fas02c06h.isChecked() ? "1"
                        : "0");


        s03.put("fas02c0696",
                bi.fas02c0696.isChecked() ? "1"
                        : "0");


        s03.put("fas02c0696x", bi.fas02c0696x.getText().toString());

        s03.put("fas02c07", bi.fas02c07a.isChecked() ? "1" : bi.fas02c07b.isChecked() ? "2" : bi.fas02c0798.isChecked() ? "98" : "0");
        s03.put("fas02c07w", bi.fas02c07w.getText().toString());
        s03.put("fas02c07m", bi.fas02c07m.getText().toString());

        s03.put("fas02c0898",
                bi.fas02c0898.isChecked() ? "1"
                        : "0");
        s03.put("fas02c08t", bi.fas02c08t.getText().toString());


        s03.put("fas02c09",
                bi.fas02c09a.isChecked() ? "1"
                        : bi.fas02c09b.isChecked() ? "2"
                        : "0");


        s03.put("fas02c10",
                bi.fas02c10a.isChecked() ? "1"
                        : bi.fas02c10b.isChecked() ? "2"
                        : "0");


        s03.put("fas02c11a",
                bi.fas02c11a.isChecked() ? "1"
                        : "0");
        s03.put("fas02c11ax", bi.fas02c11ax.getText().toString());


        s03.put("fas02c11b",
                bi.fas02c11b.isChecked() ? "1"
                        : "0");
        s03.put("fas02c11bx", bi.fas02c11bx.getText().toString());

        s03.put("fas02c11c",
                bi.fas02c11c.isChecked() ? "1"
                        : "0");
        s03.put("fas02c11cx", bi.fas02c11bx.getText().toString());


        s03.put("fas02c1201",
                bi.fas02c1201a.isChecked() ? "1"
                        : bi.fas02c1201b.isChecked() ? "2"
                        : bi.fas02c120198.isChecked() ? "98"
                        : "0");

        s03.put("fas02c1202",
                bi.fas02c1202a.isChecked() ? "1"
                        : bi.fas02c1202b.isChecked() ? "2"
                        : bi.fas02c120298.isChecked() ? "98"
                        : "0");

        s03.put("fas02c1203",
                bi.fas02c1203a.isChecked() ? "1"
                        : bi.fas02c1203b.isChecked() ? "2"
                        : bi.fas02c120398.isChecked() ? "98"
                        : "0");


        s03.put("fas02c13",
                bi.fas02c13a.isChecked() ? "1"
                        : bi.fas02c13b.isChecked() ? "2"
                        : bi.fas02c1398.isChecked() ? "98"
                        : "0");


        s03.put("fas02c1498",
                bi.fas02c1498.isChecked() ? "1"
                        : "0");
        s03.put("fas02c14", bi.fas02c14.getText().toString());


        s03.put("fas02c15",
                bi.fas02c15a.isChecked() ? "1"
                        : bi.fas02c15b.isChecked() ? "2"
                        : bi.fas02c1598.isChecked() ? "98"
                        : "0");


        s03.put("fas02c15",
                bi.fas02c15a.isChecked() ? "1"
                        : bi.fas02c15b.isChecked() ? "2"
                        : bi.fas02c1598.isChecked() ? "98"
                        : "0");


        s03.put("fas02c16",
                bi.fas02c16a.isChecked() ? "1"
                        : bi.fas02c16b.isChecked() ? "2"
                        : bi.fas02c1698.isChecked() ? "98"
                        : "0");


        s03.put("fas02c17",
                bi.fas02c17a.isChecked() ? "1"
                        : bi.fas02c17b.isChecked() ? "2"
                        : bi.fas02c17c.isChecked() ? "11"
                        : bi.fas02c17d.isChecked() ? "12"
                        : bi.fas02c17e.isChecked() ? "13"
                        : bi.fas02c17f.isChecked() ? "16"
                        : bi.fas02c17g.isChecked() ? "21"
                        : bi.fas02c17h.isChecked() ? "26"
                        : bi.fas02c17i.isChecked() ? "31"
                        : bi.fas02c1796.isChecked() ? "96"
                        : "0");


        s03.put("fas02c1796x", bi.fas02c1796x.getText().toString());
        s03.put("fas02c17fx", bi.fas02c17fx.getText().toString());
        s03.put("fas02c17hx", bi.fas02c17hx.getText().toString());


        s03.put("fas02c18a",
                bi.fas02c18a.isChecked() ? "1"
                        : "0");


        s03.put("fas02c18b",
                bi.fas02c18b.isChecked() ? "1"
                        : "0");


        s03.put("fas02c18c",
                bi.fas02c18c.isChecked() ? "1"
                        : "0");

        s03.put("fas02c18d",
                bi.fas02c18d.isChecked() ? "1"
                        : "0");


        s03.put("fas02c18e",
                bi.fas02c18e.isChecked() ? "1"
                        : "0");

        s03.put("fas02c18f",
                bi.fas02c18f.isChecked() ? "1"
                        : "0");

        s03.put("fas02c18g",
                bi.fas02c18g.isChecked() ? "1"
                        : "0");

        s03.put("fas02c18h",
                bi.fas02c18h.isChecked() ? "1"
                        : "0");

        s03.put("fas02c18i",
                bi.fas02c18i.isChecked() ? "1"
                        : "0");

        s03.put("fas02c18j",
                bi.fas02c18j.isChecked() ? "1"
                        : "0");

        s03.put("fas02c18k",
                bi.fas02c18k.isChecked() ? "1"
                        : "0");

        s03.put("fas02c1896",
                bi.fas02c1896.isChecked() ? "1"
                        : "0");
        s03.put("fas02c1896x", bi.fas02c1896x.getText().toString());


        s03.put("fas02c19a", bi.fas02c19a.isChecked() ? "11" : "0");
        s03.put("fas02c19b", bi.fas02c19b.isChecked() ? "12" : "0");
        s03.put("fas02c19c", bi.fas02c19c.isChecked() ? "13" : "0");
        s03.put("fas02c19d", bi.fas02c19d.isChecked() ? "14" : "0");
        s03.put("fas02c19e", bi.fas02c19e.isChecked() ? "21" : "0");
        s03.put("fas02c19f", bi.fas02c19f.isChecked() ? "22" : "0");
        s03.put("fas02c19g", bi.fas02c19g.isChecked() ? "23" : "0");
        s03.put("fas02c19h", bi.fas02c19h.isChecked() ? "24" : "0");
        s03.put("fas02c19i", bi.fas02c19i.isChecked() ? "25" : "0");
        s03.put("fas02c1996", bi.fas02c1996.isChecked() ? "96" : "0");
        s03.put("fas02c1998", bi.fas02c1998.isChecked() ? "98" : "0");

        s03.put("fas02c1996x", bi.fas02c1996x.getText().toString());


        s03.put("fas02c20a",
                bi.fas02c20a.isChecked() ? "1"
                        : "0");


        s03.put("fas02c20b",
                bi.fas02c20b.isChecked() ? "1"
                        : "0");


        s03.put("fas02c20c",
                bi.fas02c20c.isChecked() ? "1"
                        : "0");


        s03.put("fas02c20d",
                bi.fas02c20d.isChecked() ? "1"
                        : "0");


        s03.put("fas02c20e",
                bi.fas02c20e.isChecked() ? "1"
                        : "0");


        s03.put("fas02c20f",
                bi.fas02c20f.isChecked() ? "1"
                        : "0");


        s03.put("fas02c2096",
                bi.fas02c2096.isChecked() ? "1"
                        : "0");


        s03.put("fas02c2096x", bi.fas02c2096x.getText().toString());

        s03.put("fas02c21",
                bi.fas02c21a.isChecked() ? "1"
                        : bi.fas02c21b.isChecked() ? "2"
                        : "0");


        s03.put("fas02c2201",
                bi.fas02c2201a.isChecked() ? "1"
                        : bi.fas02c2201b.isChecked() ? "2"
                        : "0");


        s03.put("fas02c2202",
                bi.fas02c2202a.isChecked() ? "1"
                        : bi.fas02c2202b.isChecked() ? "2"
                        : "0");


        s03.put("fas02c2203",
                bi.fas02c2203a.isChecked() ? "1"
                        : bi.fas02c2203b.isChecked() ? "2"
                        : "0");


        s03.put("fas02c2204",
                bi.fas02c2204a.isChecked() ? "1"
                        : bi.fas02c2204b.isChecked() ? "2"
                        : "0");


        s03.put("fas02c2205",
                bi.fas02c2205a.isChecked() ? "1"
                        : bi.fas02c2205b.isChecked() ? "2"
                        : "0");


        s03.put("fas02c2206",
                bi.fas02c2206a.isChecked() ? "1"
                        : bi.fas02c2206b.isChecked() ? "2"
                        : "0");


        s03.put("fas02c2207",
                bi.fas02c2207a.isChecked() ? "1"
                        : bi.fas02c2207b.isChecked() ? "2"
                        : "0");


        s03.put("fas02c2208",
                bi.fas02c2208a.isChecked() ? "1"
                        : bi.fas02c2208b.isChecked() ? "2"
                        : "0");


        s03.put("fas02c2301",
                bi.fas02c2301a.isChecked() ? "1"
                        : bi.fas02c2301b.isChecked() ? "2"
                        : bi.fas02c230198.isChecked() ? "98"
                        : "0");

        s03.put("fas02c2302",
                bi.fas02c2302a.isChecked() ? "1"
                        : bi.fas02c2302b.isChecked() ? "2"
                        : bi.fas02c230298.isChecked() ? "98"
                        : "0");


        s03.put("fas02c2303",
                bi.fas02c2303a.isChecked() ? "1"
                        : bi.fas02c2303b.isChecked() ? "2"
                        : bi.fas02c230398.isChecked() ? "98"
                        : "0");

        s03.put("fas02c2304",
                bi.fas02c2304a.isChecked() ? "1"
                        : bi.fas02c2304b.isChecked() ? "2"
                        : bi.fas02c230498.isChecked() ? "98"
                        : "0");

        s03.put("fas02c2305",
                bi.fas02c2305a.isChecked() ? "1"
                        : bi.fas02c2305b.isChecked() ? "2"
                        : bi.fas02c230598.isChecked() ? "98"
                        : "0");

        s03.put("fas02c2306",
                bi.fas02c2306a.isChecked() ? "1"
                        : bi.fas02c2306b.isChecked() ? "2"
                        : bi.fas02c230698.isChecked() ? "98"
                        : "0");


        s03.put("fas02c24a",
                bi.fas02c24a.isChecked() ? "1"
                        : "0");


        s03.put("fas02c24b",
                bi.fas02c24b.isChecked() ? "1"
                        : "0");


        s03.put("fas02c24c",
                bi.fas02c24c.isChecked() ? "1"
                        : "0");

        s03.put("fas02c24d",
                bi.fas02c24d.isChecked() ? "1"
                        : "0");


        s03.put("fas02c24e",
                bi.fas02c24e.isChecked() ? "1"
                        : "0");


        s03.put("fas02c2496",
                bi.fas02c2496.isChecked() ? "1"
                        : "0");

        s03.put("fas02c24f",
                bi.fas02c24f.isChecked() ? "1"
                        : "0");

        s03.put("fas02c2496x", bi.fas02c2496x.getText().toString());


        s03.put("fas02c25",
                bi.fas02c25a.isChecked() ? "1"
                        : bi.fas02c25b.isChecked() ? "2"
                        : bi.fas02c25c.isChecked() ? "3"
                        : bi.fas02c25d.isChecked() ? "4"
                        : bi.fas02c25e.isChecked() ? "5"
                        : bi.fas02c25f.isChecked() ? "6"
                        : bi.fas02c2596.isChecked() ? "96"
                        : "0");

        s03.put("fas02c2596x", bi.fas02c2596x.getText().toString());


        s03.put("fas02c2601",
                bi.fas02c26a.isChecked() ? "1"
                        : bi.fas02c26b.isChecked() ? "2"
                        : bi.fas02c26c.isChecked() ? "3"
                        : bi.fas02c26d.isChecked() ? "4"
                        : bi.fas02c26e.isChecked() ? "5"
                        : bi.fas02c26f.isChecked() ? "6"
                        : bi.fas02c2696.isChecked() ? "96"
                        : "0");

        s03.put("fas02c2696x", bi.fas02c2696x.getText().toString());

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
