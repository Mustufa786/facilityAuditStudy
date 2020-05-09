package edu.aku.hassannaqvi.fas.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.fas.R;
import edu.aku.hassannaqvi.fas.appVersion.VersionAppContract;
import edu.aku.hassannaqvi.fas.core.CONSTANTS;
import edu.aku.hassannaqvi.fas.core.MainApp;
import edu.aku.hassannaqvi.fas.data.DAO.GetFncDAO;
import edu.aku.hassannaqvi.fas.data.entities.Forms;
import edu.aku.hassannaqvi.fas.databinding.ActivityMainBinding;
import edu.aku.hassannaqvi.fas.get.db.GetAllDBData;
import edu.aku.hassannaqvi.fas.sync.SyncAllData;
import edu.aku.hassannaqvi.fas.ui.tool1.InfoActivity;
import edu.aku.hassannaqvi.fas.ui.tool2.SectionA_tool_2Activity;
import edu.aku.hassannaqvi.fas.ui.tool3.Section3A;
import im.dino.dbinspector.activities.DbInspectorActivity;

import static edu.aku.hassannaqvi.fas.ui.LoginActivity.db;


public class MainActivity extends AppCompatActivity {

    public static String[] usersArray;
    private final String TAG = "MainActivity";
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    AlertDialog.Builder builder;
    String m_Text = "", preVer = "", newVer = "";
    ProgressDialog mProgressDialog;
    ActivityMainBinding mainBinding;
    private ProgressDialog pd;
    private Boolean exit = false;
    private String rSumText = "";
    private boolean updata = false;
    DownloadManager downloadManager;
    Long refID;
    SharedPreferences sharedPrefDownload;
    SharedPreferences.Editor editorDownload;
    VersionAppContract versionAppContract;
    static File file;

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
        sharedPrefDownload = getSharedPreferences("appDownload", MODE_PRIVATE);
        editorDownload = sharedPrefDownload.edit();

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
            Collection<?> data = new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms").execute().get();
            if (data != null) {
                Toast.makeText(this, "" + data.size(), Toast.LENGTH_SHORT).show();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {

                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(sharedPrefDownload.getLong("refID", 0));

                    downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    Cursor cursor = downloadManager.query(query);
                    if (cursor.moveToFirst()) {
                        int colIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(colIndex)) {

                            editorDownload.putBoolean("flag", true);
                            editorDownload.commit();

                            Toast.makeText(context, "New App downloaded!!", Toast.LENGTH_SHORT).show();
                            mainBinding.lblAppVersion.setText("HFA App New Version " + newVer + "  Downloaded.");

                            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
                            if (taskInfo.get(0).topActivity.getClassName().equals(MainActivity.class.getName())) {
//                                InstallNewApp(newVer, preVer);
                                showDialog(newVer, preVer);
                            }
                        }
                    }
                }
            }
        };

        versionAppContract = new Gson().fromJson(getSharedPreferences("main", Context.MODE_PRIVATE).getString("appVersion", ""), VersionAppContract.class);
        if (versionAppContract != null) {

            if (versionAppContract.getVersioncode() != null) {
                preVer = MainApp.versionName + "." + MainApp.versionCode;
                newVer = versionAppContract.getVersionname() + "." + versionAppContract.getVersioncode();

                if (MainApp.versionCode < Integer.valueOf(versionAppContract.getVersioncode())) {
                    mainBinding.lblAppVersion.setVisibility(View.VISIBLE);

                    String fileName = CONSTANTS.DATABASE_NAME.replace(".db", "-New-Apps");
                    file = new File(Environment.getExternalStorageDirectory() + File.separator + fileName, versionAppContract.getPathname());

                    if (file.exists()) {
                        mainBinding.lblAppVersion.setText("HFA New Version " + newVer + "  Downloaded.");
//                    InstallNewApp(newVer, preVer);
                        showDialog(newVer, preVer);
                    } else {
                        NetworkInfo networkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
                        if (networkInfo != null && networkInfo.isConnected()) {

                            mainBinding.lblAppVersion.setText("HFA App New Version " + newVer + " Downloading..");
                            downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                            Uri uri = Uri.parse(MainApp._UPDATE_URL + versionAppContract.getPathname());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setDestinationInExternalPublicDir(fileName, versionAppContract.getPathname())
                                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                    .setTitle("Downloading HFA App new App ver." + newVer);
                            refID = downloadManager.enqueue(request);

                            editorDownload.putLong("refID", refID);
                            editorDownload.putBoolean("flag", false);
                            editorDownload.commit();

                        } else {
                            mainBinding.lblAppVersion.setText("HFA App New Version " + newVer + "  Available..\n(Can't download.. Internet connectivity issue!!)");
                        }
                    }

                } else {
                    mainBinding.lblAppVersion.setVisibility(View.GONE);
                    mainBinding.lblAppVersion.setText(null);
                }
            }
        }

        registerReceiver(broadcastReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));


    }

    void showDialog(String newVer, String preVer) {
        FragmentManager ft = getSupportFragmentManager();
        FragmentTransaction transaction = ft.beginTransaction();
        Fragment prev = ft.findFragmentByTag("dialog");
        if (prev != null) {
            transaction.remove(prev);
        }
        transaction.addToBackStack(null);
        DialogFragment newFragment = MyDialogFragment.newInstance(newVer, preVer);
        newFragment.show(ft, "dialog");

    }


    public void openForm(String fType, String formType) {
        final Intent oF = new Intent(MainActivity.this, selectedForm(fType)).putExtra("fType", formType);

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

    private Class<?> selectedForm(String fType) {

        Class retClass = null;

        switch (fType) {
            case "t1":
                retClass = InfoActivity.class;
                break;
            case "t2":
                retClass = SectionA_tool_2Activity.class;
                break;
            case "t3":
                retClass = Section3A.class;
                break;
        }

        return retClass;
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

//    public void updateApp(View v) {
//        v.setBackgroundColor(Color.GREEN);
//
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                MainActivity.this);
//        alertDialogBuilder
//                .setMessage("Are you sure to download new app??")
//                .setCancelable(false)
//                .setPositiveButton("Yes",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,
//                                                int id) {
//                                // this is how you fire the downloader
//                                try {
//                                    URL url = new URL(MainApp._UPDATE_URL);
//                                    HttpURLConnection c = (HttpURLConnection) url.openConnection();
//                                    c.setRequestMethod("GET");
//                                    c.setDoOutput(true);
//                                    c.connect();
//
//                                    String PATH = Environment.getExternalStorageDirectory() + "/download/";
//                                    File file = new File(PATH);
//                                    file.mkdirs();
//                                    File outputFile = new File(file, "app.apk");
//                                    FileOutputStream fos = new FileOutputStream(outputFile);
//
//                                    InputStream is = c.getInputStream();
//
//                                    byte[] buffer = new byte[1024];
//                                    int len1 = 0;
//                                    while ((len1 = is.read(buffer)) != -1) {
//                                        fos.write(buffer, 0, len1);
//                                    }
//                                    fos.close();
//                                    is.close();//till here, it works fine - .apk is download to my sdcard in download file
//
//                                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                                    intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + "app.apk")), "application/vnd.android.package-archive");
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    startActivity(intent);
//
//                                } catch (IOException e) {
//                                    Toast.makeText(getApplicationContext(), "Update error!", Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
//        alertDialogBuilder.setNegativeButton("No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alert = alertDialogBuilder.create();
//        alert.show();
//    }

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

                Toast.makeText(MainActivity.this, "Syncing Forms", Toast.LENGTH_SHORT).show();

                /*Upload Form TOOL 1*/
                Collection collection1 = null;
                try {
                    collection1 = new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms").execute(CONSTANTS._URI_FORM_TOOL1).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new SyncAllData(
                        this,
                        "Forms-Tool1",
                        "updateSyncedForms",
                        Forms.class,
                        MainApp._HOST_URL + CONSTANTS.URL_FORMS.replace(".php", CONSTANTS._URI_FORM_TOOL1 + ".php"), collection1
                ).execute();

                /*Upload Form TOOL 2*/
                Collection collection2 = null;
                try {
                    collection2 = new GetAllDBData(db, GetFncDAO.class.getName(), "getFncDao", "getUnSyncedForms").execute(CONSTANTS._URI_FORM_TOOL2).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new SyncAllData(
                        this,
                        "Forms-Tool2",
                        "updateSyncedForms",
                        Forms.class,
                        MainApp._HOST_URL + CONSTANTS.URL_FORMS.replace(".php", CONSTANTS._URI_FORM_TOOL2 + ".php"), collection2
                ).execute();

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

    public static class MyDialogFragment extends DialogFragment {

        String newVer, preVer;

        static MyDialogFragment newInstance(String newVer, String preVer) {
            MyDialogFragment f = new MyDialogFragment();

            Bundle args = new Bundle();
            args.putString("newVer", newVer);
            args.putString("preVer", preVer);
            f.setArguments(args);

            return f;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            newVer = getArguments().getString("newVer");
            preVer = getArguments().getString("preVer");

            return new AlertDialog.Builder(getActivity())
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("HFA App is available!")
                    .setMessage("HFA App " + newVer + " is now available. Your are currently using older version " + preVer + ".\nInstall new version to use this app.")
                    .setPositiveButton("INSTALL!!",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }
                    )
                    .create();
        }

    }

}