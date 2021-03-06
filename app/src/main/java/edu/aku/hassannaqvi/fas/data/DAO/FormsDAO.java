package edu.aku.hassannaqvi.fas.data.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import edu.aku.hassannaqvi.fas.core.CONSTANTS;
import edu.aku.hassannaqvi.fas.data.entities.Clusters;
import edu.aku.hassannaqvi.fas.data.entities.Districts;
import edu.aku.hassannaqvi.fas.data.entities.Forms;
import edu.aku.hassannaqvi.fas.data.entities.HFA;
import edu.aku.hassannaqvi.fas.data.entities.UCs;
import edu.aku.hassannaqvi.fas.data.entities.Users;

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

    @Insert
    Long insertDistricts(Districts dist);

    @Insert
    Long insertHFA(HFA hfa);

    @Insert
    Long insertUcs(UCs uc);

    @Query("DELETE from " + CONSTANTS.TABLE_USERS)
    int deleteUsers();

    @Query("DELETE from " + CONSTANTS.TABLE_CLUSTERS)
    int deleteClusters();

    @Query("DELETE from " + CONSTANTS.TABLE_UCS)
    int deleteUCs();

    @Query("DELETE from " + CONSTANTS.TABLE_HFA)
    int deleteHfa();

    @Query("DELETE from " + CONSTANTS.TABLE_DISTRICTS)
    int deleteDistricts();

    /*Update methods after upload on server*/

    /**
     * Updating only sync and syncDate
     * By order id
     */
    @Query("UPDATE Forms SET synced = '1' , synced_date= :synced_date WHERE id =:id")
    int updateSyncedForms(String synced_date, int id);


}
