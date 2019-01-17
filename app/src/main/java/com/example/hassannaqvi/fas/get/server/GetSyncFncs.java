package com.example.hassannaqvi.fas.get.server;

import android.content.Context;

import com.example.hassannaqvi.fas.RMOperations.crudOperations;
import com.example.hassannaqvi.fas.RMOperations.syncOperations;
import com.example.hassannaqvi.fas.data.DAO.FormsDAO;
import com.example.hassannaqvi.fas.data.entities.Clusters;
import com.example.hassannaqvi.fas.data.entities.Users;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.example.hassannaqvi.fas.ui.LoginActivity.db;

public abstract class GetSyncFncs {

    public static void syncUsers(Context mContext, JSONArray userlist) {

        new syncOperations(db).execute(FormsDAO.class.getName(), "formsDao", "deleteUsers");

        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);
                String userName = jsonObjectUser.getString("username");
                String password = jsonObjectUser.getString("password");

                Users users = new Users(userName, password);
                new crudOperations(mContext, FormsDAO.class.getName(), "formsDao", "insertUsers", users).execute().get();
            }
            db.close();

        } catch (Exception e) {
        }
    }

    public static void syncClusters(Context mContext, JSONArray clusterList) {

        new syncOperations(db).execute(FormsDAO.class.getName(), "formsDao", "deleteClusters");

        try {
            JSONArray jsonArray = clusterList;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                Clusters clusters = new Clusters();
                clusters.Sync(jsonObjectUser);

                new crudOperations(mContext, FormsDAO.class.getName(), "formsDao", "insertClusters", clusters).execute().get();
            }
            db.close();

        } catch (Exception e) {
        }
    }

}
