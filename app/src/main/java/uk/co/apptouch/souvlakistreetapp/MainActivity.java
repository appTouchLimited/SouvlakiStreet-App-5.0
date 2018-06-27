package uk.co.apptouch.souvlakistreetapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    BottomNavigationView bottomNavigationView;

    //This is our viewPager
    private ViewPager viewPager;

    //Fragments
    FragmentMain mainFragment;
    FragmentTwo twoFragment;
    FragmentThree threeFragment;
    FragmentFour fourFragment;
    MenuItem prevMenuItem;

/*    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          //  android.app.FragmentManager fragmentManager = getFragmentManager();
            switch (item.getItemId()) {

                case R.id.navigation_home:
                 //  fragmentManager.beginTransaction().replace(R.id.content_frame, new FragmentMain()).commit();
                    return true;
                case R.id.navigation_menu:
                  //  fragmentManager.beginTransaction().replace(R.id.content_frame, new FragmentTwo()).commit();
                    return true;
                case R.id.navigation_location:
                   // fragmentManager.beginTransaction().replace(R.id.content_frame, new FragmentThree()).commit();
                    return true;
                case R.id.navigation_payment:
                   // fragmentManager.beginTransaction().replace(R.id.content_frame, new FragmentFour()).commit();
                    return true;
            }
            return false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
      //  android.app.FragmentManager fragmentManager = getFragmentManager();
        setContentView(R.layout.activity_main);

        //Initializing viewPager
        viewPager = findViewById(R.id.viewpager);

        mTextMessage =  findViewById(R.id.message);
        bottomNavigationView = findViewById(R.id.navigation);


       // fragmentManager.beginTransaction().replace(R.id.content_frame, new FragmentMain()).commit();
       // BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
       // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_menu:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.navigation_location:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.navigation_payment:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       /*  //Disable ViewPager Swipe

       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        */
        setupViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mainFragment = new FragmentMain();
        twoFragment = new FragmentTwo();
        threeFragment = new FragmentThree();
        fourFragment = new FragmentFour();

        adapter.addFragment(mainFragment);
        adapter.addFragment(twoFragment);
        adapter.addFragment(threeFragment);
        adapter.addFragment(fourFragment);

        viewPager.setAdapter(adapter);
    }


}
