package uk.co.apptouch.souvlakistreetapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class FragmentMain  extends Fragment{
    public static FragmentMain newInstance() {
        FragmentMain fragment = new FragmentMain();
        return fragment;
    }

    //remote config
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private static final String FRAGMAIN_IMAGE_1 = "frag_main_image_1";

    RelativeLayout layout;
    View myView;
    String dateResult, monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    TextView mainText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remote config code
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);





    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_main, container, false);



        mainText = myView.findViewById(R.id.txtViewMain);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        dateResult = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());

//get todays date in the format of DAY, DATE MONTH YEAR
        Format formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String today = formatter.format(new Date());



        layout = myView.findViewById(R.id.relative_layout_main);



        switch (dateResult) {

            case "Monday": //layout.setBackgroundResource(R.drawable.monday);
                mainText.setText(today);

                break;

            case "Tuesday": //layout.setBackgroundResource(R.drawable.tuesday);
                mainText.setText(today);

                break;

            case "Wednesday":  //layout.setBackgroundResource(R.drawable.wednesday);
                mainText.setText(today);
                break;

            case "Thursday": //layout.setBackgroundResource(R.drawable.thursday);
                mainText.setText(today);
                break;

            case "Friday": mainText.setText(today);
                break;

            case "Saturday": mainText.setText(today);
                break;

            case "Sunday": mainText.setText(today);
                break;

        }

        fetchWelcome();
        return myView;
    }

    private void fetchWelcome() {

        long cacheExpiration = 3600; // 1 hour in seconds.

        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Fetch Succeeded",
                                    Toast.LENGTH_SHORT).show();

                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Toast.makeText(getActivity(), "Fetch Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        displayWelcomeMessage();
                    }
                });
    }

    private void displayWelcomeMessage() {

        String updateImage = mFirebaseRemoteConfig.getString(FRAGMAIN_IMAGE_1);
        new DownloadImageTask((ImageView) myView.findViewById(R.id.imageViewFragMain))
                .execute(updateImage);
    }
}
