package com.example.hassannaqvi.leaps_scaleup.get.server;

import com.example.hassannaqvi.leaps_scaleup.RMOperations.crudOperations;
import com.example.hassannaqvi.leaps_scaleup.RMOperations.syncOperations;
import com.example.hassannaqvi.leaps_scaleup.data.DAO.FormsDAO;
import com.example.hassannaqvi.leaps_scaleup.data.entities.Clusters;
import com.example.hassannaqvi.leaps_scaleup.data.entities.Users;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.example.hassannaqvi.leaps_scaleup.ui.LoginActivity.db;

public abstract class GetSyncFncs {

    public static void syncUsers(JSONArray userlist) {

        new syncOperations(db).execute(FormsDAO.class.getName(), "formsDao", "deleteUsers");

        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);
                String userName = jsonObjectUser.getString("username");
                String password = jsonObjectUser.getString("password");

                Users users = new Users(userName, password);
                new crudOperations(db, users).execute(FormsDAO.class.getName(), "formsDao", "insertUsers").get();
            }
            db.close();

        } catch (Exception e) {
        }
    }

    public static void syncClusters(JSONArray clusterList) {

        new syncOperations(db).execute(FormsDAO.class.getName(), "formsDao", "deleteClusters");

        try {
            JSONArray jsonArray = clusterList;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                Clusters clusters = new Clusters();
                clusters.Sync(jsonObjectUser);

                new crudOperations(db, clusters).execute(FormsDAO.class.getName(), "formsDao", "insertClusters").get();
            }
            db.close();

        } catch (Exception e) {
        }
    }

}
