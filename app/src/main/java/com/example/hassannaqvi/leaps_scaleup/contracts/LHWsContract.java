package com.example.hassannaqvi.leaps_scaleup.contracts;


import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class LHWsContract {

    private static final String TAG = "LHWs_CONTRACT";
    String lhwname;
    String hf_code;
    String lhwcode;

    public LHWsContract() {
        // Default Constructor
    }

    public LHWsContract Sync(JSONObject jsonObject) throws JSONException {
        this.lhwname = jsonObject.getString(singleLHWs.COLUMN_LHW_NAME);
        this.hf_code = jsonObject.getString(singleLHWs.COLUMN_HF_CODE);
        this.lhwcode = jsonObject.getString(singleLHWs.COLUMN_LHW_CODE);

        return this;

    }

    public LHWsContract HydrateLHWs(Cursor cursor) {
        this.hf_code = cursor.getString(cursor.getColumnIndex(singleLHWs.COLUMN_HF_CODE));
        this.lhwname = cursor.getString(cursor.getColumnIndex(singleLHWs.COLUMN_LHW_NAME));
        this.lhwcode = cursor.getString(cursor.getColumnIndex(singleLHWs.COLUMN_LHW_CODE));
        return this;
    }

    public String getLhwname() {
        return lhwname;
    }

    public String getHf_code() {
        return hf_code;
    }

    public String getLhwcode() {
        return lhwcode;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(singleLHWs.COLUMN_LHW_NAME, this.lhwname == null ? JSONObject.NULL : this.lhwname);
        json.put(singleLHWs.COLUMN_HF_CODE, this.hf_code == null ? JSONObject.NULL : this.hf_code);
        json.put(singleLHWs.COLUMN_LHW_CODE, this.lhwcode == null ? JSONObject.NULL : this.lhwcode);

        return json;
    }


    public static abstract class singleLHWs implements BaseColumns {

        public static final String TABLE_NAME = "LHWs";
        public static final String COLUMN_LHW_NAME = "lhw_name";
        public static final String COLUMN_HF_CODE = "hf_code";
        public static final String COLUMN_LHW_CODE = "lhw_code";

        public static final String _URI = "lhws.php";
    }
}