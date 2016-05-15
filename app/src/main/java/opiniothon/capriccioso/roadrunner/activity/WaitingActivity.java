package opiniothon.capriccioso.roadrunner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import com.bumptech.glide.Glide;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import opiniothon.capriccioso.roadrunner.R;
import utils.CircleTransform;

/**
 * Created by abdul on 14/05/16.
 */
public class WaitingActivity extends AppCompatActivity {

    private CardView cardViewWaiting;
    private ImageView imageLoad;
    private TextView info;
    private DilatingDotsProgressBar mDilatingDotsProgressBar;
    private String url = "https://www.google.com/imgres?imgurl=http%3A%2F%2Fcdn.photonesta.com%2Fimages%2Fhinh.trochoivui.com%2Fdata%2Fmedia%2F62%2Fhot_boy_23.jpg&imgrefurl=http%3A%2F%2Fwww.photonesta.com%2Fhinh-hot-boy.html&docid=79mVkQi6OVlKqM&tbnid=8HLPWqW6NTmuqM%3A&w=354&h=504&bih=612&biw=1280&ved=0ahUKEwjh2piemNrMAhVFro8KHeW4DRA4rAIQMwggKB0wHQ&iact=mrc&uact=8";


    private Button buttonNavigate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.waiting_layout_delivery_boy);

        init();
        waitingAnimation();

    }

    private void init() {
        cardViewWaiting = (CardView) findViewById(R.id.cardViewWaiting);
        imageLoad = (ImageView) findViewById(R.id.imageLoad);
        info=(TextView)findViewById(R.id.textSubTitle);

        buttonNavigate= (Button) findViewById(R.id.buttonNavigation);
        mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();

        Glide.with(this).load(R.drawable.place_holder).transform(new CircleTransform(this)).into(imageLoad);//to set default image

//        Util.loadImage(this, imageLoad, url);//loading from url;

        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WaitingActivity.this, DriverMapActivity.class));
            }
        });

    }

    private void waitingAnimation() {
    //todo: have to change here , it would be based on response;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDilatingDotsProgressBar.hideNow();
                        showButton();
                    }
                },5000);


    }

    private void showButton() {
        buttonNavigate.setVisibility(View.VISIBLE);
        info.setText(R.string.new_pickup);
    }

}
