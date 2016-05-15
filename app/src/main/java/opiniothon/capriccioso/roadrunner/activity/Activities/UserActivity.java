package opiniothon.capriccioso.roadrunner.activity.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import opiniothon.capriccioso.roadrunner.R;

/**
 * Created by abdul on 15/05/16.
 */
public class UserActivity extends AppCompatActivity {

    EditText location, company, flat_no, post_code, address;
    Button buttonOrder;
    AlertDialog.Builder liveTrackingAlert;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        init();

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
                //todo : hv to implement api;
            }
        });
    }

    private void showAlertDialog() {

        liveTrackingAlert = new AlertDialog.Builder(
                UserActivity.this);

        // set title
        liveTrackingAlert.setTitle(R.string.live_track);

        // set dialog message
        liveTrackingAlert
                .setMessage(R.string.enable_live)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //todo : need to on gps and send lat long to server;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = liveTrackingAlert.create();

        // show it
        alertDialog.show();

    }

    private void init() {
        location = (EditText) findViewById(R.id.location);
        company = (EditText) findViewById(R.id.company);
        flat_no = (EditText) findViewById(R.id.flat_no);
        post_code = (EditText) findViewById(R.id.post_code);
        address = (EditText) findViewById(R.id.address);

        buttonOrder = (Button) findViewById(R.id.orderNowButton);


    }


}
