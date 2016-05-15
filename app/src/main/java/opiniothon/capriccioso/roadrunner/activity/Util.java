package opiniothon.capriccioso.roadrunner.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import opiniothon.capriccioso.roadrunner.R;
import utils.CircleTransform;

/**
 * Created by abdul.
 */
public final class Util {
    public static final class Operations {
        private Operations() throws InstantiationException {
            throw new InstantiationException("This class is not for instantiation");
        }
        /**
         * Checks to see if the device is online before carrying out any operations.
         *
         * @return
         */
        public static boolean isOnline(Context context) {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        }
    }
    private Util() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }


    /***
     * load image using glider
     */

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadImage(final Activity context, final ImageView img, String url) {
        if (context == null || context.isDestroyed()) return;

            if (!url.equals("") && url.length() > 1)//Added listener into glide to display progressBar
                Glide.with(context).load(url).centerCrop().crossFade().transform(new CircleTransform(context))
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                Glide.with(context).load(R.drawable.place_holder).transform(new CircleTransform(context)).into(img);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                Glide.with(context).load(R.drawable.place_holder).transform(new CircleTransform(context)).into(img);

                                return false;
                            }
                        }).into(img);
    }

    /***
     * @param context passing to get the network status
     * @return boolean value as true or false
     * true: if network is available
     * false: if not available
     */
    //Added NullPointerException to handle the exception
    public static boolean isNetworkAvailable(final Context context) {
        if (context == null) return false;
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
