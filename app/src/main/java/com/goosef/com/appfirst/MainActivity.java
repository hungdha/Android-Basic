package com.goosef.com.appfirst;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity implements View.OnClickListener, View.OnFocusChangeListener,View.OnKeyListener,
View.OnLongClickListener{
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.press_dialog_1).setOnClickListener(this);
        findViewById(R.id.press_dialog_2).setOnClickListener(this);
        findViewById(R.id.btn_alert_dialog1).setOnClickListener(this);
        findViewById(R.id.date_picker_dialog1).setOnClickListener(this);
        findViewById(R.id.date_picker_dialog2).setOnClickListener(this);
        findViewById(R.id.notify_dialog1).setOnClickListener(this);
        findViewById(R.id.btn_connect_database).setOnClickListener(this);
        setDateTimeField();
        /*
        fromEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Click From Edit Text" , Toast.LENGTH_SHORT );
            }
        });
        */
        //findViewById(R.id.toDateEtxt).setOnClickListener(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        switch (b.getId()){
            case R.id.btn_login:{
                //Toast.makeText(getApplicationContext(),"Clicked button login", Toast.LENGTH_LONG).show();

                // Alert Dialog
                /*
                AlertDialog.Builder alertDialog = new AlertDialog.Builder( MainActivity.this);
                alertDialog.setTitle("title login");
                alertDialog.setMessage("login...");
                alertDialog.show();
                */

                // PregressDialog

                //ProgressDialog1();
                break;
            }
            case R.id.press_dialog_1:{
                ProgressDialog_1();
                break;
            }
            case R.id.press_dialog_2:{
                ProgressDialog_2();
                break;
            }
            case R.id.btn_alert_dialog1: {
                // DemoAlertDialog_1();
                DemoAlertDialog_2();
                break;
            }
            case R.id.date_picker_dialog1:{
                //Toast.makeText(getApplicationContext(), "Button Date Picker Dialog 1", Toast.LENGTH_SHORT).show();
                toDatePickerDialog.show();
                break;
            }
            case R.id.date_picker_dialog2:{
                fromDatePickerDialog.show();
                break;
            }
            case  R.id.notify_dialog1:{
                NotifycationsDialog();
                break;
            }
            case R.id.btn_cancel:{
                Toast.makeText(getApplicationContext(),"Clicked button canel", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.btn_connect_database:{
                DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
                db.close();
                break;
            }
        }
    }

    public void ProgressDialog_1() {
        final ProgressDialog barProgressDialog = new ProgressDialog(MainActivity.this);
        barProgressDialog.setTitle("Downloading Image ...");
        barProgressDialog.setMessage("Download in progress ...");
        barProgressDialog.setProgressStyle(barProgressDialog.STYLE_HORIZONTAL);
        barProgressDialog.setProgress(0);
        barProgressDialog.setMax(20);
        barProgressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Here you should write your time consuming task...
                    while (barProgressDialog.getProgress() <= barProgressDialog.getMax()) {
                        Thread.sleep(2000);
                        barProgressDialog.incrementProgressBy(2);
                        if (barProgressDialog.getProgress() == barProgressDialog.getMax()) {
                            barProgressDialog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    Log.d("EX", e.getMessage());
                }
            }
        }).start();
    }

    public void ProgressDialog_2() {

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this, ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 3000);

    }

    public AlertDialog DemoAlertDialog_1(){
        return new AlertDialog.Builder( MainActivity.this ).setTitle("Confirm Delete").setMessage("Do you want delete this item !").create();
    }

    public void DemoAlertDialog_2(){
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder( MainActivity.this);
        // Setting Dialog Title
        alertDialog2.setTitle("Confirm Delete...");
        // Setting Dialog Message
        alertDialog2.setMessage("Are you sure you want delete this file?");

        // Setting Icon to Dialog
        // alertDialog2.setIcon(R.drawable.delete);

        // Setting Positive "Yes" Btn
        alertDialog2.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        Toast.makeText(getApplicationContext(),
                                "You clicked on YES", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        // Setting Negative "NO" Btn
        alertDialog2.setNegativeButton("NO",
        new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                Toast.makeText(getApplicationContext(),
                        "You clicked on NO", Toast.LENGTH_SHORT)
                        .show();
                dialog.cancel();
            }
        });
        alertDialog2.setNeutralButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        Toast.makeText(getApplicationContext(),
                                "You clicked on Cancel", Toast.LENGTH_SHORT)
                                .show();
                        dialog.cancel();
                    }
                });
        // Showing Alert Dialog
        alertDialog2.show();
    }

    public void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String fromText = dateFormatter.format(newDate.getTime());
                Toast.makeText(MainActivity.this, fromText, Toast.LENGTH_SHORT).show();
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String toText = dateFormatter.format(newDate.getTime());
                Toast.makeText(MainActivity.this, toText, Toast.LENGTH_SHORT).show();
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    public void NotifycationsDialog(){
        // prepare intent which is triggered if the
// notification is selected

        Intent intent = new Intent(this, MainActivity.class);
// use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

// build notification
// the addAction re-use the same intent to keep the example short
        Notification n  = new Notification.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject")
                .setSmallIcon(R.drawable.notification_icon48x48)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.notification_icon48x48, "Call", pIntent)
                .addAction(R.drawable.notification_icon48x48, "More", pIntent)
                .addAction(R.drawable.notification_icon48x48, "And more", pIntent).build();


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
