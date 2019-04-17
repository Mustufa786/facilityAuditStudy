package edu.aku.hassannaqvi.fas.sync;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.fas.RMOperations.UpdateSyncedStatus;
import edu.aku.hassannaqvi.fas.data.DAO.FormsDAO;

import static edu.aku.hassannaqvi.fas.ui.LoginActivity.db;

public abstract class UpdateFncs {

    public static void updateSyncedForms_04_05(int _id) {

//        new syncOperations(db).execute(FormsDAO.class.getName(), "formsDao", "updateSyncedForms_04_05");
        try {
            new UpdateSyncedStatus(db, new Date().toString(), _id).execute(FormsDAO.class.getName(), "formsDao", "updateSyncedForms_04_05").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void updateSyncedForms(int _id) {

//        new syncOperations(db).execute(FormsDAO.class.getName(), "formsDao", "updateSyncedForms_04_05");
        try {
            new UpdateSyncedStatus(db, new Date().toString(), _id).execute(FormsDAO.class.getName(), "formsDao", "updateSyncedForms").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
