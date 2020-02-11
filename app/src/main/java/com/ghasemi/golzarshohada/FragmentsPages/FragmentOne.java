package com.ghasemi.golzarshohada.FragmentsPages;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ghasemi.golzarshohada.Font.SetFonts;
import com.ghasemi.golzarshohada.R;
import com.ghasemi.golzarshohada.RecyclerViewAdapter.RecyclerAdapter;
import com.ghasemi.golzarshohada.ViewPagerTop.ViewPagerAdapter;
import com.ghasemi.golzarshohada.database.DatabaseHelper;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Computer Freak on 7/24/2018.
 */

public class FragmentOne extends Fragment {
    DatabaseHelper databaseHelper;
    private TextView textTitleOne, textMenu, textSokhan, textTitleTwo, textTitleThree, textHadisImam, textHadis, textTitleAbout, textTextAbout, textTitleAboutUs, textTextAboutUs, textHomeButtonNavigation, textShohadaButtonNavigation, textGalleryButtonNavigation;
    private ViewPager viewPager;
    private RecyclerView recyclerViewSokhan;
    private Timer timer = new Timer();
    private Button btnConfirm, btnPlay1, btnPlay2, btnPlay3, btnPlay4, btnPlay5, btnPlay6, btnPlay7, btnPlay8;
    private SetFonts setFonts;
    private NestedScrollView nestedScrollView;
    private MediaPlayer musicNava;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("OnCreateView", "!!!!!!");
        view = inflater.inflate(R.layout.fragment_main, container, false);
        init();
        setFont();

        viewPager.setAdapter(new ViewPagerAdapter(getFragmentManager(), getActivity()));
        // اعمال کردن روشی دیگر برای اسلاید شو
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() >= 4) {
                            viewPager.setCurrentItem(0, true);
                        } else {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    }
                });
            }
        }, 0, 5000);
        recyclerViewSokhan.setLayoutManager(new LinearLayoutManager(getActivity(), 0, true));
        recyclerViewSokhan.setAdapter(new RecyclerAdapter(getActivity(), null, null, null, null, null, "Frag1",null));
        try {
            databaseHelper = new DatabaseHelper(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        btnPlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        btnPlay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        btnPlay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        btnPlay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        btnPlay6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        btnPlay7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        btnPlay8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        textHadis.setText(getArguments().getString("hadis"));
        textHadisImam.setText(getArguments().getString("texthadis"));
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onCreate", "!!!!!");
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void init() {
        viewPager = (ViewPager) view.findViewById(R.id.act_2_viewpager);
        textTitleOne = (TextView) view.findViewById(R.id.act_2_title_sokhan);
        textTitleTwo = (TextView) view.findViewById(R.id.act_2_title_nava);
        textTitleThree = (TextView) view.findViewById(R.id.act_2_title_hadis);
        textHadis = (TextView) view.findViewById(R.id.act_2_title_hadis_imam);
        textHadisImam = (TextView) view.findViewById(R.id.act_2_text_hadis_imam);
        recyclerViewSokhan = (RecyclerView) view.findViewById(R.id.recycler_sokhan);
        btnPlay1 = (Button) view.findViewById(R.id.act_2_btnPlay1);
        btnPlay2 = (Button) view.findViewById(R.id.act_2_btnPlay2);
        btnPlay3 = (Button) view.findViewById(R.id.act_2_btnPlay3);
        btnPlay4 = (Button) view.findViewById(R.id.act_2_btnPlay4);
        btnPlay5 = (Button) view.findViewById(R.id.act_2_btnPlay5);
        btnPlay6 = (Button) view.findViewById(R.id.act_2_btnPlay6);
        btnPlay7 = (Button) view.findViewById(R.id.act_2_btnPlay7);
        btnPlay8 = (Button) view.findViewById(R.id.act_2_btnPlay8);
        btnPlay1.setText("پخش");
        btnPlay2.setText("پخش");
        btnPlay3.setText("پخش");
        btnPlay4.setText("پخش");
        btnPlay5.setText("پخش");
        btnPlay6.setText("پخش");
        btnPlay7.setText("پخش");
        btnPlay8.setText("پخش");
    }

    private void setFont() {
        setFonts = new SetFonts(getActivity(), "fonts/iran_sans.otf");
        setFonts.setFont("TextView", textTitleOne);
        setFonts.setFont("TextView", textTitleTwo);
        setFonts.setFont("TextView", textTitleThree);
        setFonts.setFont("TextView", textHadis);
        setFonts.setFont("TextView", textHadisImam);
        setFonts.setFont("Button", btnPlay1);
        setFonts.setFont("Button", btnPlay2);
        setFonts.setFont("Button", btnPlay3);
        setFonts.setFont("Button", btnPlay4);
        setFonts.setFont("Button", btnPlay5);
        setFonts.setFont("Button", btnPlay6);
        setFonts.setFont("Button", btnPlay7);
        setFonts.setFont("Button", btnPlay8);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.act_2_btnPlay1:
                if (btnPlay1.getText().toString() == "پخش") {
                    stopNava();
                    musicNava = MediaPlayer.create(getActivity(), R.raw.nava1);
                    btnPlay1.setText("توقف");
                    musicNava.start();
                } else {
                    musicNava.stop();
                    btnPlay1.setText("پخش");
                }
                break;
            case R.id.act_2_btnPlay2:
                if (btnPlay2.getText().toString() == "پخش") {
                    stopNava();
                    musicNava = MediaPlayer.create(getActivity(), R.raw.nava2);
                    btnPlay2.setText("توقف");
                    musicNava.start();
                } else {
                    btnPlay2.setText("پخش");
                    musicNava.stop();
                }
                break;
            case R.id.act_2_btnPlay3:
                if (btnPlay3.getText().toString() == "پخش") {
                    stopNava();
                    musicNava = MediaPlayer.create(getActivity(), R.raw.nava3);
                    btnPlay3.setText("توقف");
                    musicNava.start();
                } else {
                    btnPlay3.setText("پخش");
                    musicNava.stop();
                }
                break;
            case R.id.act_2_btnPlay4:
                if (btnPlay4.getText().toString() == "پخش") {
                    stopNava();
                    musicNava = MediaPlayer.create(getActivity(), R.raw.nava4);
                    btnPlay4.setText("توقف");
                    musicNava.start();
                } else {
                    btnPlay4.setText("پخش");
                    musicNava.stop();
                }
                break;
            case R.id.act_2_btnPlay5:
                if (btnPlay5.getText().toString() == "پخش") {
                    stopNava();
                    musicNava = MediaPlayer.create(getActivity(), R.raw.nava5);
                    btnPlay5.setText("توقف");
                    musicNava.start();
                } else {
                    btnPlay5.setText("پخش");
                    musicNava.stop();
                }
                break;
            case R.id.act_2_btnPlay6:
                if (btnPlay6.getText().toString() == "پخش") {
                    stopNava();
                    musicNava = MediaPlayer.create(getActivity(), R.raw.nava6);
                    btnPlay6.setText("توقف");
                    musicNava.start();
                } else {
                    btnPlay6.setText("پخش");
                    musicNava.stop();
                }
                break;
            case R.id.act_2_btnPlay7:
                if (btnPlay7.getText().toString() == "پخش") {
                    stopNava();
                    musicNava = MediaPlayer.create(getActivity(), R.raw.nava7);
                    btnPlay7.setText("توقف");
                    musicNava.start();
                } else {
                    btnPlay7.setText("پخش");
                    musicNava.stop();
                }
                break;
            case R.id.act_2_btnPlay8:
                if (btnPlay8.getText().toString() == "پخش") {
                    stopNava();
                    musicNava = MediaPlayer.create(getActivity(), R.raw.nava8);
                    btnPlay8.setText("توقف");
                    musicNava.start();
                } else {
                    btnPlay8.setText("پخش");
                    musicNava.stop();
                }
                break;
        }
    }

    private void stopNava() {
        if (btnPlay1.getText() == "توقف" || btnPlay2.getText() == "توقف" || btnPlay3.getText() == "توقف" || btnPlay4.getText() == "توقف" || btnPlay5.getText() == "توقف" || btnPlay6.getText() == "توقف" || btnPlay7.getText() == "توقف" || btnPlay8.getText() == "توقف") {
            try {
                musicNava.stop();
            } catch (Exception e) {

            }
            btnPlay1.setText("پخش");
            btnPlay2.setText("پخش");
            btnPlay3.setText("پخش");
            btnPlay4.setText("پخش");
            btnPlay5.setText("پخش");
            btnPlay6.setText("پخش");
            btnPlay7.setText("پخش");
            btnPlay8.setText("پخش");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            timer.cancel();
            musicNava.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopNava();
    }
}
