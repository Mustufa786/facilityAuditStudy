package edu.aku.hassannaqvi.fas.ui.tool2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import edu.aku.hassannaqvi.fas.databinding.ActivitySectionDTool2Binding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ClearClass;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

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
        });


        bi.fas02d0498.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.fldGrpfas02d04, false);
                    bi.fldGrpfas02d04.setTag("-1");
                    bi.fas02d0498.setTag(null);
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpfas02d04, true);
                    bi.fldGrpfas02d04.setTag(null);
                    bi.fas02d0498.setTag("-1");
                }
            }
        });


        bi.fas02d0698.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.fldGrpfas02d06, false);
                    bi.fldGrpfas02d06.setTag("-1");
                    bi.fas02d0698.setTag(null);
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpfas02d06, true);
                    bi.fldGrpfas02d06.setTag(null);
                    bi.fas02d0698.setTag("-1");
                }
            }
        });
        bi.fas02d0898.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ClearClass.ClearAllFields(bi.fldGrpfas02d08, false);
                    bi.fldGrpfas02d08.setTag("-1");
                    bi.fas02d0898.setTag(null);
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpfas02d08, true);
                    bi.fldGrpfas02d08.setTag(null);
                    bi.fas02d0898.setTag("-1");
                }
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

        JSONObject s04 = new JSONObject();

        s04.put("fas02d01", bi.fas02d01a.isChecked() ? "1"
                        : bi.fas02d01b.isChecked() ? "2"
                        : "0");


        s04.put("fas02d02a", bi.fas02d02a.isChecked() ? "1" : "0");
        s04.put("fas02d02b", bi.fas02d02b.isChecked() ? "2" : "0");
        s04.put("fas02d02c", bi.fas02d02c.isChecked() ? "3" : "0");
        s04.put("fas02d02d", bi.fas02d02d.isChecked() ? "4" : "0");
        s04.put("fas02d02e", bi.fas02d02e.isChecked() ? "5" : "0");
        s04.put("fas02d02f", bi.fas02d02f.isChecked() ? "6" : "0");
        s04.put("fas02d02g", bi.fas02d02g.isChecked() ? "7" : "0");
        s04.put("fas02d02h", bi.fas02d02h.isChecked() ? "8" : "0");
        s04.put("fas02d02i", bi.fas02d02i.isChecked() ? "9" : "0");
        s04.put("fas02d02j", bi.fas02d02j.isChecked() ? "10" : "0");
        s04.put("fas02d02k", bi.fas02d02k.isChecked() ? "11" : "0");
        s04.put("fas02d02l", bi.fas02d02l.isChecked() ? "12" : "0");
        s04.put("fas02d0298", bi.fas02d0298.isChecked() ? "98" : "0");


        s04.put("fas02d03", bi.fas02d03a.isChecked() ? "1"
                        : bi.fas02d03b.isChecked() ? "2"
                        : "0");

        s04.put("fas02d04a", bi.fas02d04a.isChecked() ? "1" : "0");
        s04.put("fas02d04b", bi.fas02d04b.isChecked() ? "2" : "0");
        s04.put("fas02d04c", bi.fas02d04c.isChecked() ? "3" : "0");
        s04.put("fas02d04d", bi.fas02d04d.isChecked() ? "4" : "0");
        s04.put("fas02d04e", bi.fas02d04e.isChecked() ? "5" : "0");
        s04.put("fas02d04f", bi.fas02d04f.isChecked() ? "6" : "0");
        s04.put("fas02d04g", bi.fas02d04g.isChecked() ? "7" : "0");
        s04.put("fas02d04h", bi.fas02d04h.isChecked() ? "8" : "0");
        s04.put("fas02d04i", bi.fas02d04i.isChecked() ? "9" : "0");
        s04.put("fas02d04j", bi.fas02d04j.isChecked() ? "10" : "0");
        s04.put("fas02d04k", bi.fas02d04k.isChecked() ? "11" : "0");
        s04.put("fas02d04l", bi.fas02d04l.isChecked() ? "12" : "0");
        s04.put("fas02d04m", bi.fas02d04m.isChecked() ? "13" : "0");
        s04.put("fas02d0498", bi.fas02d0498.isChecked() ? "98" : "0");


        s04.put("fas02d05", bi.fas02d05a.isChecked() ? "1"
                        : bi.fas02d05b.isChecked() ? "2"
                        : "0");

        s04.put("fas02d06a", bi.fas02d06a.isChecked() ? "1" : "0");
        s04.put("fas02d06b", bi.fas02d06b.isChecked() ? "2" : "0");
        s04.put("fas02d06c", bi.fas02d06c.isChecked() ? "3" : "0");
        s04.put("fas02d06d", bi.fas02d06d.isChecked() ? "4" : "0");
        s04.put("fas02d06e", bi.fas02d06e.isChecked() ? "5" : "0");
        s04.put("fas02d06f", bi.fas02d06f.isChecked() ? "6" : "0");
        s04.put("fas02d06g", bi.fas02d06g.isChecked() ? "7" : "0");
        s04.put("fas02d06h", bi.fas02d06h.isChecked() ? "8" : "0");
        s04.put("fas02d0698", bi.fas02d0698.isChecked() ? "98" : "0");


        s04.put("fas02d07", bi.fas02d07a.isChecked() ? "1"
                        : bi.fas02d07b.isChecked() ? "2"
                        : "0");


        s04.put("fas02d08a", bi.fas02d08a.isChecked() ? "1" : "0");
        s04.put("fas02d08b", bi.fas02d08b.isChecked() ? "2" : "0");
        s04.put("fas02d08c", bi.fas02d08c.isChecked() ? "3" : "0");
        s04.put("fas02d08d", bi.fas02d08d.isChecked() ? "4" : "0");
        s04.put("fas02d08e", bi.fas02d08e.isChecked() ? "5" : "0");
        s04.put("fas02d08f", bi.fas02d08f.isChecked() ? "6" : "0");
        s04.put("fas02d08g", bi.fas02d08g.isChecked() ? "7" : "0");
        s04.put("fas02d08h", bi.fas02d08h.isChecked() ? "8" : "0");
        s04.put("fas02d08i", bi.fas02d08i.isChecked() ? "9" : "0");
        s04.put("fas02d0898", bi.fas02d0898.isChecked() ? "98" : "0");

        fc.setSa4(String.valueOf(s04));

    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecD02);
    }

    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
