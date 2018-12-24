package com.example.hassannaqvi.leaps_scaleup.contracts;


import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class VillagesContract {

    private static final String TAG = "Villages_CONTRACT";
    //    String ID;
    String villagename;
    String uc_code;
    //    String tehsil_name;
    String villagecode;

    public VillagesContract() {
        // Default Constructor
    }

    public VillagesContract(VillagesContract l) {
//        this.ID = l.getID();
        this.villagename = l.getVillagename();
        this.uc_code = l.getUc_code();
//        this.tehsil_name = l.getTehsil_name();
        this.villagecode = l.getVillagecode();
    }

    public VillagesContract Sync(JSONObject jsonObject) throws JSONException {
//        this.ID = jsonObject.getString(singleVillages.COLUMN_ID);
        this.villagename = jsonObject.getString(singleVillages.COLUMN_VILLAGE_NAME);
        this.uc_code = jsonObject.getString(singleVillages.COLUMN_UC_CODE);
//        this.tehsil_name = jsonObject.getString(singleVillages.COLUMN_TEHSIL_NAME);
        this.villagecode = jsonObject.getString(singleVillages.COLUMN_VILLAGE_CODE);

        return this;

    }

    public VillagesContract HydrateVillages(Cursor cursor) {
//        this.ID = cursor.getString(cursor.getColumnIndex(singleVillages.COLUMN_ID));
        this.uc_code = cursor.getString(cursor.getColumnIndex(singleVillages.COLUMN_UC_CODE));
//        this.tehsil_name = cursor.getString(cursor.getColumnIndex(singleVillages.COLUMN_TEHSIL_NAME));
        this.villagename = cursor.getString(cursor.getColumnIndex(singleVillages.COLUMN_VILLAGE_NAME));
        this.villagecode = cursor.getString(cursor.getColumnIndex(singleVillages.COLUMN_VILLAGE_CODE));
        return this;
    }
/*
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }*/

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public String getUc_code() {
        return uc_code;
    }

    public void setUc_code(String uc_code) {
        this.uc_code = uc_code;
    }

/*    public String getTehsil_name() {
        return tehsil_name;
    }

    public void setTehsil_name(String tehsil_name) {
        this.tehsil_name = tehsil_name;
    }*/

    public String getVillagecode() {
        return villagecode;
    }

    public void setVillagecode(String villagecode) {
        this.villagecode = villagecode;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
//        json.put(singleVillages.COLUMN_ID, this.ID == null ? JSONObject.NULL : this.ID);
        json.put(singleVillages.COLUMN_VILLAGE_NAME, this.villagename == null ? JSONObject.NULL : this.villagename);
        json.put(singleVillages.COLUMN_UC_CODE, this.uc_code == null ? JSONObject.NULL : this.uc_code);
//        json.put(singleVillages.COLUMN_TEHSIL_NAME, this.tehsil_name == null ? JSONObject.NULL : this.tehsil_name);
        json.put(singleVillages.COLUMN_VILLAGE_CODE, this.villagecode == null ? JSONObject.NULL : this.villagecode);

        return json;
    }


    public static abstract class singleVillages implements BaseColumns {

        public static final String TABLE_NAME = "villages";
        //        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_VILLAGE_NAME = "village_name";
        public static final String COLUMN_UC_CODE = "uc_code";
        //        public static final String COLUMN_TEHSIL_NAME = "tehsil_name";
        public static final String COLUMN_VILLAGE_CODE = "village_code";

        public static final String _URI = "villages.php";
    }
}