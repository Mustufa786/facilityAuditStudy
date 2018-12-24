package com.example.hassannaqvi.leaps_scaleup.contracts;


import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class TehsilContract {

    private static final String TAG = "Tehsils_CONTRACT";
    String tehsilcode;
    String tehsil_name;

    public TehsilContract() {
        // Default Constructor
    }

    public TehsilContract Sync(JSONObject jsonObject) throws JSONException {
        this.tehsilcode = jsonObject.getString(singleTehsil.COLUMN_TEHSIL_CODE);
        this.tehsil_name = jsonObject.getString(singleTehsil.COLUMN_TEHSIL_NAME);
        return this;
    }

    public TehsilContract HydrateTehsils(Cursor cursor) {
        this.tehsilcode = cursor.getString(cursor.getColumnIndex(singleTehsil.COLUMN_TEHSIL_CODE));
        this.tehsil_name = cursor.getString(cursor.getColumnIndex(singleTehsil.COLUMN_TEHSIL_NAME));
        return this;
    }

    public String getTehsilcode() {
        return tehsilcode;
    }

    public void setTehsilcode(String tehsilcode) {
        this.tehsilcode = tehsilcode;
    }

    public String getTehsil_name() {
        return tehsil_name;
    }

    public void setTehsil_name(String tehsil_name) {
        this.tehsil_name = tehsil_name;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(singleTehsil.COLUMN_TEHSIL_CODE, this.tehsilcode == null ? JSONObject.NULL : this.tehsilcode);
        json.put(singleTehsil.COLUMN_TEHSIL_NAME, this.tehsil_name == null ? JSONObject.NULL : this.tehsil_name);
        return json;
    }


    public static abstract class singleTehsil implements BaseColumns {

        public static final String TABLE_NAME = "tehsils";
        public static final String COLUMN_TEHSIL_CODE = "tehsil_code";
        public static final String COLUMN_TEHSIL_NAME = "tehsil_name";

        public static final String _URI = "tehsils.php";
    }
}