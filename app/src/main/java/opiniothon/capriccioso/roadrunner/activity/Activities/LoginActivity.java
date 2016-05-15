package opiniothon.capriccioso.roadrunner.activity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.directions.entities.DeliveryRequest;
import com.directions.entities.User;

import opiniothon.capriccioso.roadrunner.R;
import opiniothon.capriccioso.roadrunner.activity.Util;
import opiniothon.capriccioso.roadrunner.activity.api.RestClient;
import opiniothon.capriccioso.roadrunner.activity.api.ServerStatusCode;
import opiniothon.capriccioso.roadrunner.activity.modal.LoginBody;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


/**
 * Created by abdul on 01/12/15.
 * <p/>
 * this activity will get active even user has not login and login token key has got expired or blocked.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userName, password;
    private Button passwordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);
        inti();

    }

    private void inti() {
        userName = (EditText) findViewById(R.id.editTextUserName);
        password = (EditText) findViewById(R.id.editTextPassword);
        passwordButton = (Button) findViewById(R.id.buttonLogin);

        passwordButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonLogin:
                LoginBody body=new LoginBody();

                if (Util.isNetworkAvailable(this)) {
                    body.setPhoneNumber(userName.getText().toString());
                    body.setPassword(password.getText().toString());
//                    startActivity(new Intent(this, WaitingActivity.class));
                    doLogin(body);
                } else {
                    Snackbar.make(passwordButton, R.string.isNetwork, Snackbar.LENGTH_LONG).show();
                }
                break;

        }
    }

    private void doLogin(LoginBody body) {
        Call<User> call = RestClient.getApiService().doLogin(body);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                if(response.code()== ServerStatusCode.OK){
                    if(response.body().getType()== User.TYPE_BUYER){
                        startActivity(new Intent(LoginActivity.this,UserActivity.class));
                        finish();
                    }
                    else
                    if(response.body().getType()== User.TYPE_DRIVER){
                        startActivity(new Intent(LoginActivity.this,WaitingActivity.class));
                        finish();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"Ok",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Toast.makeText(LoginActivity.this,"Fail",Toast.LENGTH_LONG).show();

            }
        });

    }
}