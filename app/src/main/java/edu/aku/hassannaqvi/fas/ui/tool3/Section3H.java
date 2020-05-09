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
import edu.aku.hassannaqvi.fas.databinding.ActivitySection3hBinding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

public class Section3H extends AppCompatActivity {

    ActivitySection3hBinding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section3h);
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

        json.put("ax52a", "-1");

        json.put("ax52b", "-1");

        json.put("ax53a", "-1");

        json.put("ax53b", "-1");

        json.put("ax54a", "-1");

        json.put("ax54b", "-1");

        json.put("ax56a", "-1");

        json.put("ax56b", bi.ax56b.getText().toString().trim().isEmpty() ? "-1" : bi.ax56b.getText().toString());

        json.put("ax57a", "-1");

        json.put("ax57b", bi.ax57b.getText().toString().trim().isEmpty() ? "-1" : bi.ax57b.getText().toString());

        json.put("ax58a", "-1");

        json.put("ax58b", bi.ax58b.getText().toString().trim().isEmpty() ? "-1" : bi.ax58b.getText().toString());

        json.put("ax59a", "-1");

        json.put("ax59b", bi.ax59b.getText().toString().trim().isEmpty() ? "-1" : bi.ax59b.getText().toString());


    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.GrpNameSectionN);
    }


    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Press NOT Allowed", Toast.LENGTH_SHORT).show();
    }

}
