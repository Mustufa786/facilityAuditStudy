package com.example.hassannaqvi.leaps_scaleup.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hassannaqvi.leaps_scaleup.R;
import com.example.hassannaqvi.leaps_scaleup.core.CONSTANTS;
import com.example.hassannaqvi.leaps_scaleup.core.MainApp;
import com.example.hassannaqvi.leaps_scaleup.data.DAO.GetFncDAO;
import com.example.hassannaqvi.leaps_scaleup.data.entities.Forms;
import com.example.hassannaqvi.leaps_scaleup.data.entities.Forms_04_05;
import com.example.hassannaqvi.leaps_scaleup.databinding.ActivityMainBinding;
import com.example.hassannaqvi.leaps_scaleup.get.db.GetAllDBData;
import com.example.hassannaqvi.leaps_scaleup.sync.SyncAllData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import im.dino.dbinspector.activities.DbInspectorActivity;

import static com.example.hassannaqvi.leaps_scaleup.ui.LoginActivity.db;


public class MainActivity extends Activity {

    private final String TAG = "MainActivity";

    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    AlertDialog.Builder builder;
    String m_Text = "";
    ProgressDialog mProgressDialog;
    private ProgressDialog pd;
    private Boolean exit = false;
    private String rSumText = "";
    ActivityMainBinding mainBinding;
    public static String[] usersArray;
    private boolean updata = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setCallback(this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // mainBinding.lblheader.setText("Welcome! You're assigned to block ' " + MainApp.userName);
        mainBinding.lblheader.setText("Welcome!");

        /*TagID Start*/
        sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        editor = sharedPref.edit();

        builder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog dialog = builder.create();

        ImageView img = new ImageView(getApplicationContext());
        img.setImageResource(R.drawable.tagimg);
        img.setPadding(0, 15, 0, 15);
        builder.setCustomTitle(img);

        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.requestFocus();
        input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                if (!m_Text.equals("")) {
                    editor.putString("tagName", m_Text);
                    editor.commit();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        if (sharedPref.getString("tagName", null) == "" || sharedPref.getString("tagName", null) == null) {
            builder.show();
        }
        /*TagID End*/


//        Binding setting
//        DatabaseHelper db = new DatabaseHelper(this);

//        Admin checking
        if (MainApp.admin) {
            mainBinding.adminsec.setVisibility(View.VISIBLE);

            Collection<Forms> todaysForms = null;
            Collection<Forms> unsyncedForms = null;
            try {
                unsyncedForms = (Collection<Forms>) new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms").execute().get();
                todaysForms = (Collection<Forms>) new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getTodaysForms").execute(dtToday).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            rSumText += "TODAY'S RECORDS SUMMARY\r\n";

            rSumText += "=======================\r\n";
            rSumText += "\r\n";
            rSumText += "Total Forms Today: " + todaysForms.size() + "\r\n";
            rSumText += "\r\n";
            if (todaysForms.size() > 0) {
                rSumText += "\tFORMS' LIST: \r\n";
                String iStatus;
                rSumText += "--------------------------------------------------\r\n";
                rSumText += "[ Form_ID ] \t[Form Status] \t[Sync Status]----------\r\n";
                rSumText += "--------------------------------------------------\r\n";

                for (Forms fc : todaysForms) {
                    if (fc.getIstatus() != null) {
                        switch (fc.getIstatus()) {
                            case "1":
                                iStatus = "\tComplete";
                                break;
                            case "2":
                                iStatus = "\tIncomplete";
                                break;
                            case "3":
                                iStatus = "\tRefused";
                                break;
                            case "4":
                                iStatus = "\tRefused";
                                break;
                            default:
                                iStatus = "\tN/A";
                        }
                    } else {
                        iStatus = "\tN/A";
                    }

                    rSumText += fc.getId();

                    rSumText += " " + iStatus + " ";

                    rSumText += (fc.getSynced() == null ? "\t\tNot Synced" : "\t\tSynced");
                    rSumText += "\r\n";
                    rSumText += "--------------------------------------------------\r\n";
                }
            }

            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            rSumText += "Last Data Download: \t" + syncPref.getString("LastDownSyncServer", "Never Updated");
            rSumText += "\r\n";
            rSumText += "Last Data Upload: \t" + syncPref.getString("LastUpSyncServer", "Never Synced");
            rSumText += "\r\n";
            rSumText += "\r\n";
            rSumText += "Unsynced Forms: \t" + unsyncedForms.size();
            rSumText += "\r\n";

            Log.d(TAG, "onCreate: " + rSumText);
            mainBinding.recordSummary.setText(rSumText);

        } else {
            mainBinding.adminsec.setVisibility(View.GONE);
        }

        /*Add data in Serial date wrt date*/
//        Testing visibility
        if (Integer.valueOf(MainApp.versionName.split("\\.")[0]) > 0) {
            mainBinding.testing.setVisibility(View.GONE);
        } else {
            mainBinding.testing.setVisibility(View.VISIBLE);
        }

//        Logins manage
        usersArray = new String[]{"....", MainApp.userName, MainApp.userName2};

        try {
            Collection<?> data = new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms_04_05").execute().get();
            if (data != null) {
                Toast.makeText(this, "" + data.size(), Toast.LENGTH_SHORT).show();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    public void openForm(String fType) {
        final Intent oF = new Intent(MainActivity.this, selectedForm(fType))
                .putExtra("fType", fType);

        if (sharedPref.getString("tagName", null) != "" && sharedPref.getString("tagName", null) != null && !MainApp.userName.equals("0000")) {
            startActivity(oF);
        } else {

            builder = new AlertDialog.Builder(MainActivity.this);
            ImageView img = new ImageView(getApplicationContext());
            img.setImageResource(R.drawable.tagimg);
            img.setPadding(0, 15, 0, 15);
            builder.setCustomTitle(img);

            final EditText input = new EditText(MainActivity.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);


            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text = input.getText().toString();
                    if (!m_Text.equals("")) {
                        editor.putString("tagName", m_Text);
                        editor.commit();

                        if (!MainApp.userName.equals("0000")) {
                            startActivity(oF);
                        }
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }
    }

    public void openDeviation() {
        final Intent oF = new Intent(MainActivity.this, Form14Activity.class);
        startActivity(oF);
    }

    private Class<?> selectedForm(String fType) {

        Class retClass = null;

        switch (fType) {
            case "1a":
            case "1b":
                retClass = Form01Enrolment.class;
                break;
            case "4":
            case "5":
            case "6":
                retClass = InfoActivity.class;
                break;
            case "7":
                retClass = Form07Activity.class;
                break;
            case "8":
                retClass = YouthInfoActivity.class;
                break;
            case "9":
                retClass = YouthInfoActivity.class;
                break;
        }

        return retClass;
    }

    public void openAnthro() {
        startActivity(new Intent(this, Form6AnthroActivity.class));
    }

    public void openYouthEnrol() {
        startActivity(new Intent(MainActivity.this, Form07Activity.class));
    }

    public void openEF() {
        Toast.makeText(this, "This form is under construction", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(MainActivity.this, Form05IdBAActivity.class));
    }

    public void openYouthEF() {
        startActivity(new Intent(MainActivity.this, Form05IdBAActivity.class));
    }

    public void openDB() {
        Intent dbmanager = new Intent(getApplicationContext(), DbInspectorActivity.class);
        startActivity(dbmanager);
    }

    public void testGPS(View v) {

        SharedPreferences sharedPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        Log.d("MAP", "testGPS: " + sharedPref.getAll().toString());
        Map<String, ?> allEntries = sharedPref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("Map", entry.getKey() + ": " + entry.getValue().toString());
        }

    }

    public void updateApp(View v) {
        v.setBackgroundColor(Color.GREEN);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MainActivity.this);
        alertDialogBuilder
                .setMessage("Are you sure to download new app??")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // this is how you fire the downloader
                                try {
                                    URL url = new URL(MainApp._UPDATE_URL);
                                    HttpURLConnection c = (HttpURLConnection) url.openConnection();
                                    c.setRequestMethod("GET");
                                    c.setDoOutput(true);
                                    c.connect();

                                    String PATH = Environment.getExternalStorageDirectory() + "/download/";
                                    File file = new File(PATH);
                                    file.mkdirs();
                                    File outputFile = new File(file, "app.apk");
                                    FileOutputStream fos = new FileOutputStream(outputFile);

                                    InputStream is = c.getInputStream();

                                    byte[] buffer = new byte[1024];
                                    int len1 = 0;
                                    while ((len1 = is.read(buffer)) != -1) {
                                        fos.write(buffer, 0, len1);
                                    }
                                    fos.close();
                                    is.close();//till here, it works fine - .apk is download to my sdcard in download file

                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + "app.apk")), "application/vnd.android.package-archive");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);

                                } catch (IOException e) {
                                    Toast.makeText(getApplicationContext(), "Update error!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void openDB(View v) {
        Intent dbmanager = new Intent(getApplicationContext(), DbInspectorActivity.class);
        startActivity(dbmanager);
    }

    public void uploadData() {

        if (!updata) {
            updata = true;

            // Require permissions INTERNET & ACCESS_NETWORK_STATE
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {

//            new SyncDevice(this).execute();
                Toast.makeText(getApplicationContext(), "Syncing Forms", Toast.LENGTH_SHORT).show();

                /*Upload Form 01a*/
                Collection collection1 = null;
                try {
                    collection1 = new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms_04_05").execute(MainApp.FORM01A).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new SyncAllData(
                        this,
                        "Forms01a",
                        "updateSyncedForms_04_05",
                        Forms_04_05.class,
                        MainApp._HOST_URL + CONSTANTS.URL_FORMS.replace(".php", MainApp.FORM01A + ".php"), collection1
                ).execute();
                /*Upload Form 01b*/
                Collection collection2 = null;
                try {
                    collection2 = new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms_04_05").execute(MainApp.FORM01B).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new SyncAllData(
                        this,
                        "Forms01b",
                        "updateSyncedForms_04_05",
                        Forms_04_05.class,
                        MainApp._HOST_URL + CONSTANTS.URL_FORMS.replace(".php", MainApp.FORM01B + ".php"), collection2
                ).execute();


                /*Upload Form 04*/
                Collection collection4 = null;
                try {
                    collection4 = new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms_04_05").execute(MainApp.FORM04).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new SyncAllData(
                        this,
                        "Forms04",
                        "updateSyncedForms_04_05",
                        Forms_04_05.class,
                        MainApp._HOST_URL + CONSTANTS.URL_FORMS.replace(".php", MainApp.FORM04 + ".php"), collection4
                ).execute();


                /*Upload Form 05*/
                Collection collection5 = null;
                try {
                    collection5 = new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms_04_05").execute(MainApp.FORM05).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new SyncAllData(
                        this,
                        "Forms05",
                        "updateSyncedForms_04_05",
                        Forms_04_05.class,
                        MainApp._HOST_URL + CONSTANTS.URL_FORMS.replace(".php", MainApp.FORM05 + ".php"), collection5
                ).execute();

                /*Upload Form 06*/
                Collection collection6 = null;
                try {
                    collection6 = new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms_04_05").execute(MainApp.FORM06).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new SyncAllData(
                        this,
                        "Forms06",
                        "updateSyncedForms_04_05",
                        Forms_04_05.class,
                        MainApp._HOST_URL + CONSTANTS.URL_FORMS.replace(".php", MainApp.FORM06 + ".php"), collection6
                ).execute();

                /*Upload Form 07*/
                Collection collection7 = null;
                try {
                    collection7 = new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms").execute(MainApp.FORM07).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new SyncAllData(
                        this,
                        "Forms07",
                        "updateSyncedForms",
                        Forms.class,
                        MainApp._HOST_URL + CONSTANTS.URL_FORMS.replace(".php", MainApp.FORM07 + ".php"), collection7
                ).execute();/*
                 *//*Upload Form 08*//*
            Collection collection8 = null;
            try {
                collection8 = new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms_04_05").execute(MainApp.FORM08).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                new SyncAllData(
                        this,
                        "Forms",
                        "updateSyncedForms_04_05",
                        Forms_04_05.class,
                        MainApp._HOST_URL + CONSTANTS.URL_FORMS.replace(".php",MainApp.FORM08+".php"),collection8
                ).execute();*/
                /*Upload Form 09*/
            /*Collection collection9 = null;
            try {
                collection9 = new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms_04_05").execute(MainApp.FORM09).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                new SyncAllData(
                        this,
                        "Forms",
                        "updateSyncedForms_04_05",
                        Forms_04_05.class,
                        MainApp._HOST_URL + CONSTANTS.URL_FORMS.replace(".php",MainApp.FORM09+".php"),collection9
                ).execute();
*/
                SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = syncPref.edit();

                editor.putString("LastUpSyncServer", dtToday);

                editor.apply();

            } else {
                Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
            }
            updata = false;
        }
    }

    public void CheckCluster(View v) {

    }

    public void syncServer(View view) {

        // Require permissions INTERNET & ACCESS_NETWORK_STATE
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

//            DatabaseHelper db = new DatabaseHelper(this);

            Toast.makeText(getApplicationContext(), "Under Construction", Toast.LENGTH_SHORT).show();
        /*    Toast.makeText(getApplicationContext(), "Syncing Forms", Toast.LENGTH_SHORT).show();


            new SyncAllData(
                    this,
                    "Forms",
                    "updateSyncedForms",
                    FormsContract.class,
                    MainApp._HOST_URL + FormsContract.FormsTable._URL,
                    db.getUnsyncedForms()
            ).execute();

            Toast.makeText(getApplicationContext(), "Syncing Family Members", Toast.LENGTH_SHORT).show();
            new SyncAllData(
                    this,
                    "Family Members",
                    "updateSyncedFamilyMembers",
                    FamilyMembersContract.class,
                    MainApp._HOST_URL + FamilyMembersContract.familyMembers._URL,
                    db.getUnsyncedFamilyMembers()
            ).execute();

*/
            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = syncPref.edit();

            editor.putString("LastUpSyncServer", dtToday);

            editor.apply();

        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }

    }

    public void syncDevice(View view) {

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show();

            // Sync Random
            /*new GetBLRandom(this).execute();*/

            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = syncPref.edit();

            editor.putString("LastDownSyncServer", dtToday);

            editor.apply();
        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity

            startActivity(new Intent(this, LoginActivity.class));

        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }


    public void openA(View v) {

//        startActivity1(SectionAActivity.class);

    }

    public void openB(View v) {

//        startActivity1(SectionBActivity.class);

    }

    public void openC(View v) {

//        startActivity1(SectionCActivity.class);

    }

    public void openD(View v) {

//        startActivity1(SectionDActivity.class);

    }

    public void openE(View v) {

//        startActivity1(SectionEActivity.class);

    }

    public void openF(View v) {

//        startActivity1(SectionFActivity.class);

    }

    public void openG(View v) {

//        startActivity1(SectionGActivity.class);

    }

    public void openH(View v) {

//        startActivity1(SectionHActivity.class);

    }

    public void openI(View v) {

//        startActivity1(SectionIActivity.class);

    }

    public void openJ(View v) {

//        startActivity1(SectionJActivity.class);

    }

    public void openK(View v) {

//        startActivity1(SectionKActivity.class);

    }

    public void openLMO(View v) {

//        startActivity1(SectionlmoActivity.class);

    }

    private void startActivity1(final Class<? extends Activity> ActivityToOpen) {
        startActivity(new Intent(getBaseContext(), ActivityToOpen));
    }

}