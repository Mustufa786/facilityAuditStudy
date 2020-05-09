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
import edu.aku.hassannaqvi.fas.databinding.ActivitySection3gBinding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

public class Section3G extends AppCompatActivity {

    ActivitySection3gBinding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section3g);
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

        json.put("ax41", bi.ax41.getText().toString().trim().isEmpty() ? "-1" : bi.ax41.getText().toString());

        json.put("ax411", bi.ax411.getText().toString().trim().isEmpty() ? "-1" : bi.ax411.getText().toString());

        json.put("ax42", bi.ax42a.isChecked() ? "1"
                : bi.ax42b.isChecked() ? "2"
                : bi.ax42c.isChecked() ? "3"
                : bi.ax42d.isChecked() ? "4"
                : "-1");

        json.put("ax43", bi.ax43a.isChecked() ? "1"
                : bi.ax43b.isChecked() ? "2"
                : bi.ax43c.isChecked() ? "98"
                : "-1");

        json.put("ax44", bi.ax44a.isChecked() ? "1"
                : bi.ax44b.isChecked() ? "2"
                : bi.ax44c.isChecked() ? "3"
                : bi.ax44d.isChecked() ? "4"
                : bi.ax44e.isChecked() ? "5"
                : bi.ax44f.isChecked() ? "96"
                : bi.ax44g.isChecked() ? "98"
                : "-1");
        json.put("ax44fx", bi.ax44fx.getText().toString());

    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.GrpNameSectionM);
    }


    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
