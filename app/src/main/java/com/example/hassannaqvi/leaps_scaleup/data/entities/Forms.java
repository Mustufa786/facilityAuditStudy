package com.example.hassannaqvi.leaps_scaleup.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.hassannaqvi.leaps_scaleup.data.AppDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

@Entity(tableName = AppDatabase.Sub_DBConnection.TABLE_FORMS)
public class Forms implements Serializable {

    @Ignore
    private final String _projectName = "Leaps-Sup";
    @Ignore
    private final String _surveyType = "";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String uid = "";
    private String formType = "";
    private String formDate = ""; // Date
    private String username = ""; // Interviewer
    private String istatus = ""; // Interview Status
    private String istatus88x = ""; // Interview Status
    private String sa1 = "";     // Info Section
    private String endtime = "";
    private String clustercode = "";
    private String youthID = "";
    private String youthName = "";
    private String studyID = "";
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String gpsElev = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String round = "";
    private String pdeviation = "";

    @Ignore
    public Forms(Forms forms) {

        this.uid = forms.uid;
        this.formDate = forms.formDate;
        this.formType = forms.formType;
        this.username = forms.username;
        this.istatus = forms.istatus;
        this.istatus88x = forms.istatus88x;
        this.sa1 = forms.sa1;
        this.endtime = forms.endtime;
        this.clustercode = forms.clustercode;
        this.youthID = forms.youthID;
        this.youthName = forms.youthName;
        this.studyID = forms.studyID;
        this.gpsLat = forms.gpsLat;
        this.gpsLng = forms.gpsLng;
        this.gpsDT = forms.gpsDT;
        this.gpsAcc = forms.gpsAcc;
        this.gpsElev = forms.gpsElev;
        this.deviceID = forms.deviceID;
        this.devicetagID = forms.devicetagID;
        this.synced = forms.synced;
        this.synced_date = forms.synced_date;
        this.appversion = forms.appversion;
        this.round = forms.round;
        this.pdeviation = forms.pdeviation;

    }

    public Forms() {
    }

    @Ignore
    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("projectName", this._projectName);
        json.put("_id", this.id == 0 ? JSONObject.NULL : this.id);
        json.put("formType", this.formType == null ? JSONObject.NULL : this.formType);
        json.put("formDate", this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put("uid", this.uid == null ? JSONObject.NULL : this.uid);
        json.put("username", this.username == null ? JSONObject.NULL : this.username);
        json.put("youthID", this.youthID == null ? JSONObject.NULL : this.youthID);
        json.put("youthName", this.youthName == null ? JSONObject.NULL : this.youthName);
        json.put("studyID", this.studyID == null ? JSONObject.NULL : this.studyID);
        json.put("clustercode", this.clustercode == null ? JSONObject.NULL : this.clustercode);
        json.put("endtime", this.endtime == null ? JSONObject.NULL : this.endtime);
        json.put("gpsLat", this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put("gpsLng", this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put("gpsDT", this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
        json.put("gpsAcc", this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
        json.put("deviceID", this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put("gpsElev", this.gpsElev == null ? JSONObject.NULL : this.gpsElev);
        json.put("devicetagID", this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        /*json.put("synced", this.synced == null ? JSONObject.NULL : this.synced);
        json.put("synced_date", this.synced_date == null ? JSONObject.NULL : this.synced_date);*/
        json.put("appversion", this.appversion == null ? JSONObject.NULL : this.appversion);

        json.put("round", this.round == null ? JSONObject.NULL : this.round);
        json.put("pdeviation", this.pdeviation == null ? JSONObject.NULL : this.pdeviation);

        if (!this.sa1.equals("")) {
            json.put("sa1", this.sa1.equals("") ? JSONObject.NULL : new JSONObject(this.sa1));
        }


        return json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getIstatus88x() {
        return istatus88x;
    }

    public void setIstatus88x(String istatus88x) {
        this.istatus88x = istatus88x;
    }

    public String getSa1() {
        return sa1;
    }

    public void setSa1(String sa1) {
        this.sa1 = sa1;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getClustercode() {
        return clustercode;
    }

    public void setClustercode(String clustercode) {
        this.clustercode = clustercode;
    }

    public String getYouthID() {
        return youthID;
    }

    public void setYouthID(String youthID) {
        this.youthID = youthID;
    }

    public String getStudyID() {
        return studyID;
    }

    public void setStudyID(String studyID) {
        this.studyID = studyID;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }

    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }

    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }

    public String getGpsElev() {
        return gpsElev;
    }

    public void setGpsElev(String gpsElev) {
        this.gpsElev = gpsElev;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getYouthName() {
        return youthName;
    }

    public void setYouthName(String youthName) {
        this.youthName = youthName;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getPdeviation() {
        return pdeviation;
    }

    public void setPdeviation(String pdeviation) {
        this.pdeviation = pdeviation;
    }

    public class Simple_Forms {

        String ls01a06, ls07y07, ls07y18;

        public String getLs01a06() {
            return ls01a06;
        }

        public String getLs07y07() {
            return ls07y07;
        }

        public String getLs07y18() {
            return ls07y18;
        }
    }

}
