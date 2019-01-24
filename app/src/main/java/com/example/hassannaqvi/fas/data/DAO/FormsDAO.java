package com.example.hassannaqvi.fas.data.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hassannaqvi.fas.core.CONSTANTS;
import com.example.hassannaqvi.fas.data.entities.Clusters;
import com.example.hassannaqvi.fas.data.entities.Forms;
import com.example.hassannaqvi.fas.data.entities.Users;

@Dao
public interface FormsDAO {

    /*Form*/
    @Insert
    Long insertForm(Forms forms);

    @Update
    int updateForm(Forms forms);

    /*Others Sync*/
    @Insert
    Long insertUsers(Users users);

    @Insert
    Long insertClusters(Clusters clusters);

    @Query("DELETE from " + CONSTANTS.TABLE_USERS)
    int deleteUsers();

    @Query("DELETE from " + CONSTANTS.TABLE_CLUSTERS)
    int deleteClusters();

    /*Update methods after upload on server*/

    /**
     * Updating only sync and syncDate
     * By order id
     */
    @Query("UPDATE Forms SET synced = '1' , synced_date= :synced_date WHERE id =:id")
    int updateSyncedForms(String synced_date, int id);


}
