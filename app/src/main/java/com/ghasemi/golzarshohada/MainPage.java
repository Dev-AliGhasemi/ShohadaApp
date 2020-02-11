package com.ghasemi.golzarshohada;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ghasemi.golzarshohada.Font.SetFonts;
import com.ghasemi.golzarshohada.FragmentsPages.FragmentOne;
import com.ghasemi.golzarshohada.FragmentsPages.FragmentTwo;
import com.ghasemi.golzarshohada.RecyclerViewAdapter.RecyclerAdapter;
import com.ghasemi.golzarshohada.ViewPagerTop.ViewPagerAdapter;
import com.ghasemi.golzarshohada.database.DatabaseHelper;

import java.io.IOException;
import java.security.spec.ECField;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainPage extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ImageView imageToggle;
    private TextView textTitleOne,textMenu,textSokhan,textTitleTwo,textTitleThree,textHadisImam,textHadis,textTitleAbout,textTextAbout,textTitleAboutUs,textTextAboutUs,textHomeButtonNavigation,textShohadaButtonNavigation,textGalleryButtonNavigation;
    private ViewPager viewPager;
    private RecyclerView recyclerViewSokhan;
    private NavigationView navigationView;
    private Timer timer = new Timer();
    private Dialog dialog;
    private Button btnConfirm,btnPlay1,btnPlay2,btnPlay3,btnPlay4,btnPlay5,btnPlay6,btnPlay7,btnPlay8;
    private SetFonts setFonts;
    private NestedScrollView nestedScrollView;
    private View viewMenuNavigation;
    private LinearLayout linearLayout_shohada,linearLayout_home;
    private FrameLayout frameParent;
    private  FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentTransaction fragmentTransaction;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        init();
        try {
            databaseHelper = new DatabaseHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String hadis,textHadis;
        Random random = new Random();
        int num = random.nextInt(8);
        hadis = databaseHelper.selectSingleData("select narrator from tbl_hadis where id ="+num);
        textHadis = databaseHelper.selectSingleData("select hadisArabi from tbl_hadis where id ="+num)+"\n"+databaseHelper.selectSingleData("select hadisFarsi from tbl_hadis where id ="+num)+"\n"+databaseHelper.selectSingleData("select address from tbl_hadis where id ="+num);
        databaseHelper.close();
        Bundle fragmentData = new Bundle();
        fragmentData.putString("hadis",hadis);
        fragmentData.putString("texthadis",textHadis);
        fragmentOne = new FragmentOne();
        fragmentOne.setArguments(fragmentData);
        fragmentTwo = new FragmentTwo();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.parent_main,fragmentOne);
        fragmentTransaction.commit();
        //Toast.makeText(getApplicationContext(),databaseHelper.getCoutFielde().toString(),Toast.LENGTH_LONG).show();
        //databaseHelper.selectCommandMulti("select * from tbl_sokhan where id==1");
//        Display display = getWindowManager().getDefaultDisplay();
//        width = display.getWidth();
//        he ght = display.getHeight();
        setSupportActionBar(toolbar);
        imageToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)==false) {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getTitle().toString()) {
                    case "خانه":
                        linearLayout_home.setBackgroundColor(Color.parseColor("#FF232529"));
                        linearLayout_shohada.setBackgroundColor(Color.parseColor("#FF34373E"));
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.parent_main,fragmentOne);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case "علاقه مندی":
                        Intent intent = new Intent(MainPage.this,Favorite.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;
                    case "اشتراک گذاری":
                        break;
                    case "امتیاز به برنامه":
                        break;
                    case "درباره ما":
                        setDialog();
                        break;
                    case "خروج":
                        finishAffinity();
                        break;
                }
                return false;
            }
        });
        setFont();
    }
    private void init()
    {
        //برای تغییر فونت هدر منو
        navigationView = (NavigationView) findViewById(R.id.navigation);
        viewMenuNavigation = navigationView.getHeaderView(0);
        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        imageToggle = (ImageView) findViewById(R.id.toogle);
        drawerLayout = (DrawerLayout) findViewById(R.id.act_2_drawer);
        textHomeButtonNavigation = (TextView) findViewById(R.id.act_2_text_home);
        textShohadaButtonNavigation = (TextView) findViewById(R.id.act_2_text_shohada);
        textMenu = (TextView)viewMenuNavigation.findViewById(R.id.text_navigation);
        linearLayout_shohada = (LinearLayout)findViewById(R.id.act_2_shohada);
        linearLayout_home = (LinearLayout)findViewById(R.id.act_2_home);
        frameParent = (FrameLayout) findViewById(R.id.parent_main);
    }

    public void clickNavigationButton(View view){
        switch (view.getId()){

            case R.id.act_2_shohada:
                linearLayout_home.setBackgroundColor(Color.parseColor("#FF34373E"));
                linearLayout_shohada.setBackgroundColor(Color.parseColor("#FF232529"));
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.parent_main,fragmentTwo);
                fragmentTransaction.commit();
                break;
            case R.id.act_2_home:
                linearLayout_home.setBackgroundColor(Color.parseColor("#FF232529"));
                linearLayout_shohada.setBackgroundColor(Color.parseColor("#FF34373E"));
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.parent_main,fragmentOne);
                fragmentTransaction.commit();
                break;
        }
    }
    private  void setFont()
    {
        setFonts = new SetFonts(MainPage.this,"fonts/iran_sans.otf");
        setFonts.setFont("TextView",textMenu);
        setFonts.setFont("TextView",textHomeButtonNavigation);
        setFonts.setFont("TextView",textShohadaButtonNavigation);
    }
    public void setDialog()
    {
        dialog = new Dialog(MainPage.this);
        dialog.setContentView(R.layout.layout_about_us);
        btnConfirm = (Button) dialog.findViewById(R.id.btn_confirm);
        textTitleAbout = (TextView) dialog.findViewById(R.id.text_title_about);
        textTextAbout = (TextView) dialog.findViewById(R.id.text_text_about);
        textTitleAboutUs = (TextView) dialog.findViewById(R.id.text_title_aboutus);
        textTextAboutUs = (TextView) dialog.findViewById(R.id.text_text_aboutus);
        setFonts.setFont("TextView",textTitleAbout);
        setFonts.setFont("TextView",textTextAbout);
        setFonts.setFont("TextView",textTitleAboutUs);
        setFonts.setFont("TextView",textTextAboutUs);
        setFonts.setFont("Button",btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
