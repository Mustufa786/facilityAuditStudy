package edu.aku.hassannaqvi.fas.ui.tool3;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.fas.R;
import edu.aku.hassannaqvi.fas.RMOperations.crudOperations;
import edu.aku.hassannaqvi.fas.core.MainApp;
import edu.aku.hassannaqvi.fas.data.DAO.FormsDAO;
import edu.aku.hassannaqvi.fas.data.entities.Forms;
import edu.aku.hassannaqvi.fas.databinding.ActivitySection3fBinding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

public class Section3F extends AppCompatActivity {

    ActivitySection3fBinding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section3f);
        bi.setCallback(this);

    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();

                /*selectedChildren = FamilyMembersListActivity.mainVModel.getAllUnder12();
                Class<?> T = EndingActivity.class;
                if (selectedChildren.getFirst().size() > 0) T = SectionCHAActivity.class;
                startActivity(new Intent(this, T).putExtra("complete", true));*/
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }

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

        json.put("ax31", bi.ax31a.isChecked() ? "1"
                : bi.ax31b.isChecked() ? "98"
                : "-1");

        json.put("ax31ax", bi.ax31ax.getText().toString().trim().isEmpty() ? "-1" : bi.ax31ax.getText().toString());

        json.put("ax310", bi.ax310a.isChecked() ? "1"
                : bi.ax310b.isChecked() ? "2"
                : bi.ax310c.isChecked() ? "3"
                : bi.ax310d.isChecked() ? "98"
                : "-1");

        json.put("ax311", bi.ax311a.isChecked() ? "1"
                : bi.ax311b.isChecked() ? "2"
                : bi.ax311c.isChecked() ? "3"
                : bi.ax311d.isChecked() ? "96"
                : bi.ax311e.isChecked() ? "98"
                : "-1");
        json.put("ax311dx", bi.ax311dx.getText().toString().trim().isEmpty() ? "-1" : bi.ax311dx.getText().toString());

        json.put("ax312", bi.ax312a.isChecked() ? "1"
                : bi.ax312b.isChecked() ? "2"
                : bi.ax312c.isChecked() ? "98"
                : "-1");

        json.put("ax313", bi.ax313a.isChecked() ? "1"
                : bi.ax313b.isChecked() ? "2"
                : bi.ax313c.isChecked() ? "3"
                : bi.ax313d.isChecked() ? "4"
                : bi.ax313e.isChecked() ? "5"
                : bi.ax313f.isChecked() ? "98"
                : "-1");

        //json.put("ax32", bi.ax32.getText().toString().trim().isEmpty() ? "-1" : bi.ax32.getText().toString());

        json.put("ax321", bi.ax321.getText().toString().trim().isEmpty() ? "-1" : bi.ax321.getText().toString());

        json.put("ax33", bi.ax33a.isChecked() ? "1"
                : bi.ax33b.isChecked() ? "98"
                : "-1");

        json.put("ax33ax", bi.ax33ax.getText().toString().trim().isEmpty() ? "-1" : bi.ax33ax.getText().toString());

        json.put("ax331", bi.ax331a.isChecked() ? "1"
                : bi.ax331b.isChecked() ? "2"
                : bi.ax331c.isChecked() ? "3"
                : bi.ax331d.isChecked() ? "4"
                : bi.ax331e.isChecked() ? "98"
                : "-1");
        json.put("ax331ex", bi.ax331ex.getText().toString().trim().isEmpty() ? "-1" : bi.ax331ex.getText().toString());

        json.put("ax34", bi.ax34b.isChecked() ? "2"
                : bi.ax34c.isChecked() ? "3"
                : bi.ax34d.isChecked() ? "96"
                : bi.ax34e.isChecked() ? "98"
                : "-1");
        json.put("ax34dx", bi.ax34dx.getText().toString().trim().isEmpty() ? "-1" : bi.ax34dx.getText().toString());

        json.put("ax341", bi.ax341a.isChecked() ? "1"
                : bi.ax341b.isChecked() ? "2"
                : bi.ax341c.isChecked() ? "3"
                : bi.ax341d.isChecked() ? "96"
                : bi.ax341e.isChecked() ? "4"
                : bi.ax341f.isChecked() ? "98"
                : "-1");
        json.put("ax341dx", bi.ax341dx.getText().toString().trim().isEmpty() ? "-1" : bi.ax341dx.getText().toString());

        json.put("ax35", bi.ax35a.isChecked() ? "1"
                : bi.ax35b.isChecked() ? "2"
                : bi.ax35c.isChecked() ? "3"
                : bi.ax35d.isChecked() ? "98"
                : "-1");

        json.put("ax36", bi.ax36a.isChecked() ? "1"
                : bi.ax36b.isChecked() ? "2"
                : bi.ax36c.isChecked() ? "98"
                : "-1");

        json.put("ax37", bi.ax37a.isChecked() ? "1"
                : bi.ax37b.isChecked() ? "2"
                : bi.ax37c.isChecked() ? "98"
                : "-1");

        json.put("ax38", bi.ax38a.isChecked() ? "1"
                : bi.ax38b.isChecked() ? "2"
                : bi.ax38c.isChecked() ? "3"
                : bi.ax38d.isChecked() ? "96"
                : bi.ax38e.isChecked() ? "98"
                : "-1");
        json.put("ax38dx", bi.ax38dx.getText().toString().trim().isEmpty() ? "-1" : bi.ax38dx.getText().toString());

        json.put("ax39", bi.ax39a.isChecked() ? "1"
                : bi.ax39b.isChecked() ? "2"
                : bi.ax39c.isChecked() ? "3"
                : bi.ax39d.isChecked() ? "4"
                : bi.ax39e.isChecked() ? "98"
                : "-1");

    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.GrpNameSectionL);
    }


    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Press NOT Allowed", Toast.LENGTH_SHORT).show();
    }
}
