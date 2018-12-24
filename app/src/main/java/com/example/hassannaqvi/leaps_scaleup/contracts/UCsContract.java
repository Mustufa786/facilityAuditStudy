package com.example.hassannaqvi.leaps_scaleup.contracts;


import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class UCsContract {

    private static final String TAG = "UCs_CONTRACT";
    String uccode;
    String ucs;
    String tehsil_code;

    public UCsContract() {
        // Default Constructor
    }

    public UCsContract Sync(JSONObject jsonObject) throws JSONException {
        this.uccode = jsonObject.getString(singleUCs.COLUMN_UCCODE);
        this.ucs = jsonObject.getString(singleUCs.COLUMN_UCS);
        this.tehsil_code = jsonObject.getString(singleUCs.COLUMN_TEHSIL_CODE);
        return this;
    }

    public UCsContract HydrateUCs(Cursor cursor) {
        this.uccode = cursor.getString(cursor.getColumnIndex(singleUCs.COLUMN_UCCODE));
        this.ucs = cursor.getString(cursor.getColumnIndex(singleUCs.COLUMN_UCS));
        this.tehsil_code = cursor.getString(cursor.getColumnIndex(singleUCs.COLUMN_TEHSIL_CODE));
        return this;
    }

    public String getUccode() {
        return uccode;
    }

    public void setUccode(String uccode) {
        this.uccode = uccode;
    }

    public String getUcs() {
        return ucs;
    }

    public void setUcs(String ucs) {
        this.ucs = ucs;
    }

    public String getTehsil_code() {
        return tehsil_code;
    }

    public void setTehsil_code(String tehsil_code) {
        this.tehsil_code = tehsil_code;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(singleUCs.COLUMN_UCCODE, this.uccode == null ? JSONObject.NULL : this.uccode);
        json.put(singleUCs.COLUMN_UCS, this.ucs == null ? JSONObject.NULL : this.ucs);
        json.put(singleUCs.COLUMN_TEHSIL_CODE, this.tehsil_code == null ? JSONObject.NULL : this.tehsil_code);
        return json;
    }


    public static abstract class singleUCs implements BaseColumns {

        public static final String TABLE_NAME = "ucs";
        public static final String COLUMN_UCCODE = "uc_code";
        public static final String COLUMN_UCS = "uc_name";
        public static final String COLUMN_TEHSIL_CODE = "tehsil_code";

        public static final String _URI = "ucs.php";
    }
}