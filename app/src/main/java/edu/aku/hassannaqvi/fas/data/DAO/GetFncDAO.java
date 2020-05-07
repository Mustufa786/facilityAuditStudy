package edu.aku.hassannaqvi.fas.data.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.aku.hassannaqvi.fas.core.CONSTANTS;
import edu.aku.hassannaqvi.fas.data.entities.Clusters;
import edu.aku.hassannaqvi.fas.data.entities.Districts;
import edu.aku.hassannaqvi.fas.data.entities.Forms;
import edu.aku.hassannaqvi.fas.data.entities.HFA;
import edu.aku.hassannaqvi.fas.data.entities.UCs;
import edu.aku.hassannaqvi.fas.data.entities.Users;

@Dao
public interface GetFncDAO {

    @Query("SELECT * FROM " + CONSTANTS.TABLE_FORMS + " WHERE synced = ''")
    List<Forms> getUnSyncedForms();

    @Query("SELECT * FROM " + CONSTANTS.TABLE_FORMS)
    List<Forms> getForms();

    @Query("SELECT * FROM " + CONSTANTS.TABLE_FORMS + " WHERE synced = '' AND formType = :formType")
    List<Forms> getUnSyncedForms(String formType);

    @Query("SELECT * FROM " + CONSTANTS.TABLE_FORMS + " WHERE formType = :formType")
    List<Forms> getForms(String formType);

    @Query("SELECT * FROM " + CONSTANTS.TABLE_FORMS + " WHERE formDate LIKE :date")
    List<Forms> getTodaysForms(String date);

    @Query("SELECT * FROM " + CONSTANTS.TABLE_USERS + " where ROW_USERNAME=:username AND ROW_PASSWORD=:password")
    Users login(String username, String password);

    @Query("SELECT * FROM " + CONSTANTS.TABLE_CLUSTERS + " where cluster_code=:clusterCode")
    Clusters getClusterRecord(String clusterCode);

    @Query("SELECT * FROM " + CONSTANTS.TABLE_FORMS + " where participantID=:child_id and (formType = '1a' or formType = '1b') and istatus = '1' order by id DESC")
    Forms getChildRecord(String child_id);

    @Query("SELECT * FROM " + CONSTANTS.TABLE_FORMS + " where participantID=:part_id and istatus = '1' and formType != '14' order by id DESC")
    Forms checkParticipantExist(String part_id);

    /*
     * Spinner Items
     */
    @Query("SELECT * FROM " + CONSTANTS.TABLE_DISTRICTS)
    List<Districts> getAllDistricts();

    @Query("SELECT * FROM " + CONSTANTS.TABLE_UCS + " where dist_code=:distCode")
    List<UCs> getAllUcsByDistricts(String distCode);

    @Query("SELECT * FROM " + CONSTANTS.TABLE_HFA + " where dist_code=:distCode and uc_code=:UcCode")
    List<HFA> getAllHfaByDistrictUC(String distCode, String UcCode);

}
