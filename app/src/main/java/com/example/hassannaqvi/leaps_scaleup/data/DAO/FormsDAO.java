package com.example.hassannaqvi.leaps_scaleup.data.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hassannaqvi.leaps_scaleup.core.MainApp;
import com.example.hassannaqvi.leaps_scaleup.data.AppDatabase.Sub_DBConnection;
import com.example.hassannaqvi.leaps_scaleup.data.entities.Clusters;
import com.example.hassannaqvi.leaps_scaleup.data.entities.Forms;
import com.example.hassannaqvi.leaps_scaleup.data.entities.Forms_04_05;
import com.example.hassannaqvi.leaps_scaleup.data.entities.Users;

import java.util.Date;

@Dao
public interface FormsDAO {

    /*Form*/
    @Insert
    Long insertForm(Forms forms);

    @Insert
    Long insertForm_04_05(Forms_04_05 forms);

    @Delete
    void delete(Forms forms);

    @Update
    int updateForm(Forms forms);

    @Update
    int updateForm_04_05(Forms_04_05 forms);

    /*Others Sync*/
    @Insert
    Long insertUsers(Users users);

    @Insert
    Long insertClusters(Clusters clusters);

    @Query("DELETE from " + Sub_DBConnection.TABLE_USERS)
    int deleteUsers();

    @Query("DELETE from " + Sub_DBConnection.TABLE_CLUSTERS)
    int deleteClusters();


    /*Update methods after upload on server*/

    /**
     * Updating only sync and syncDate
     * By order id
     */
    @Query("UPDATE Forms SET synced = :synced, synced_date= :date WHERE id =:id")
    int updateSyncedForms(String synced, String date, int id);

    @Query("UPDATE Forms_04_05 SET synced = '1' , synced_date= :synced_date WHERE id =:id")
    int updateSyncedForms_04_05( String synced_date, int id);


}
