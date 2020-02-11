package com.ghasemi.golzarshohada.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.service.carrier.CarrierMessagingService;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ghasemi.golzarshohada.Font.SetFonts;
import com.ghasemi.golzarshohada.MainPage;
import com.ghasemi.golzarshohada.R;
import com.ghasemi.golzarshohada.StoryShohada;
import com.ghasemi.golzarshohada.database.DatabaseHelper;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Computer Freak on 7/18/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    SetFonts setFonts;
    String currentFragment;
    String id;
    int[] arrayFavorite;
    DatabaseHelper databaseHelper;
    String[] arrayFname,arrayLname,arrayDateBorn,arrayDateDaed,ID;
    public RecyclerAdapter(Context context ,String[] ID ,String[] arrayFname, String[] arrayLname, String[] arrayDateBorn, String[] arrayDateDead,String currentFragment,int[] favorite) {
        this.context = context;
        this.arrayFname = arrayFname;
        this.arrayLname = arrayLname;
        this.arrayDateBorn = arrayDateBorn;
        this.arrayDateDaed = arrayDateDead;
        this.currentFragment = currentFragment;
        this.arrayFavorite = favorite;
        this.ID = ID;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if (currentFragment.equals("Frag1")) {
            view = LayoutInflater.from(context).inflate(R.layout.recycler_sokhan,null);
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.recycler_shohada,null);
        }
        return new ViewHolder(view,currentFragment);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder)holder;
        setFonts = new SetFonts(context,"fonts/iran_sans.otf");
        if (currentFragment.equals("Frag1")){
            switch (position){
                case 0:
                    viewHolder.textSokhan.setText(R.string.sokhan_0);
                    break;
                case 1:
                    viewHolder.textSokhan.setText(R.string.sokhan_1);
                    break;
                case 2:
                    viewHolder.textSokhan.setText(R.string.sokhan_2);
                    break;
                case 3:
                    viewHolder.textSokhan.setText(R.string.sokhan_3);
                    break;
                case 4:
                    viewHolder.textSokhan.setText(R.string.sokhan_4);
                    break;
                case 5:
                    viewHolder.textSokhan.setText(R.string.sokhan_5);
                    break;
            }
//       برای کار کردن با تگ های ایج تی ام ال در یک متن مثل تغیرر رنگ یک کلمه در وسط یک متن
//   String a = "";
//        TextView w;
//        w.setText(Html.fromHtml(a));
//
        //((ViewHolder) holder).textSokhan.setText("lksjfslkdf");
        //برای گرفتن متن از دیتابیس باید در یک آرایه در هنگام کریت کردن ذخیره می کنیم و اینجا فرا می خونیم اون خونه آرایه
        //............................
            setFonts.setFont("TextView",viewHolder.textSokhan);
        }
        else {
            if (arrayFavorite[position]==0) {
                viewHolder.imageView.setColorFilter(Color.parseColor("#432a15"));
            }else {
                viewHolder.imageView.setColorFilter(Color.parseColor("#b9371d"));
            }
            viewHolder.textName.setText(arrayFname[position]+" "+arrayLname[position]);
            //viewHolder.textName.setText(arrayFavorite[position]+ arrayFavorite.length+"");
            viewHolder.textDateBorn.setText(arrayDateBorn[position]);
            viewHolder.textDateDead.setText(arrayDateDaed[position]);

            setFonts.setFont("TextView",viewHolder.textDateBorn);
            setFonts.setFont("TextView",viewHolder.textDateDead);
            setFonts.setFont("TextView",viewHolder.textName);
            setFonts.setFont("TextView",viewHolder.textDead);
            setFonts.setFont("TextView",viewHolder.textBorn);
            setFonts.setFont("TextView",viewHolder.textFamilyName);
            setFonts.setFont("Button",viewHolder.btnRead);

            viewHolder.btnRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, StoryShohada.class);
                    intent.putExtra("textTitle",viewHolder.textName.getText().toString());
                    intent.putExtra("id",ID[position]);
                    intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                    context.startActivity(intent);
                }
            });
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        databaseHelper = new DatabaseHelper(context);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Animation animation = AnimationUtils.loadAnimation(context,R.anim.zoom_in);
                    animation.setRepeatMode(Animation.REVERSE);
                    if (arrayFavorite[position]==1) {
                        databaseHelper.updateData("update tbl_shohada set favorite = 0 where id ="+ID[position]);
                        viewHolder.imageView.setColorFilter(Color.parseColor("#432a15"));
                        arrayFavorite[position] = 0;
                    }
                    else {
                        databaseHelper.updateData("update tbl_shohada set favorite = 1 where id ="+ID[position]);
                        viewHolder.imageView.setColorFilter(Color.parseColor("#b9371d"));
                        arrayFavorite[position] = 1;
                    }
                    databaseHelper.close();
                    viewHolder.imageView.startAnimation(animation);
                }
            });
        }
    }
    //برای ورژن بعدی انشا الله
//    private String fillTextView() {
//        rnd = new Random();
//        int id = rnd.nextInt(6);
//        return databaseHelper.selectSingleData("select * from tbl_sokhan where id ="+id+1);
//    }
    @Override
    public int getItemCount() {
        if (currentFragment.equals("Frag1")) {
            return 6;
        }
        else {
            return arrayFname.length;
        }

    }
}