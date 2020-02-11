package com.ghasemi.golzarshohada.RecyclerViewAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.ghasemi.golzarshohada.Font.SetFonts;
import com.ghasemi.golzarshohada.R;
import com.ghasemi.golzarshohada.StoryShohada;
import com.ghasemi.golzarshohada.database.DatabaseHelper;

import java.io.IOException;

/**
 * Created by Computer Freak on 7/26/2018.
 */

public class RecyclerAdapterForFavorite extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    String[] arrayFname,arrayLname,arrayID;
    SetFonts setFonts;
    AlertDialog.Builder alertDialog;
    DatabaseHelper databaseHelper;
    public RecyclerAdapterForFavorite(Context context,String[] Fname,String[] Lname,String[] ID) {
        this.arrayFname = Fname;
        this.arrayLname = Lname;
        this.context = context;
        this.arrayID = ID;
        setFonts = new SetFonts(context,"fonts/iran_sans.otf");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_favorite,null);
        ViewHolder viewHolder = new ViewHolder(view,"ActivityFavorite");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       final ViewHolder viewHolder = (ViewHolder) holder;
        setFonts.setFont("TextView",viewHolder.textNameShahid);
        viewHolder.textNameShahid.setText(arrayFname[position]+" "+arrayLname[position]);
        viewHolder.textNameShahid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoryShohada.class);
                intent.putExtra("textTitle",viewHolder.textNameShahid.getText().toString());
                intent.putExtra("id",arrayID[position]);
                intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(intent);
            }
        });
        viewHolder.imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("بیام!");
                alertDialog.setMessage("آیا این ایتم مورد علاقه شما نیست؟");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            databaseHelper = new DatabaseHelper(context);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        databaseHelper.updateData("update tbl_shohada set Favorite = 0 where id ="+arrayID[position]);
                        Toast.makeText(context,"برای اعمال تغییرات دوباره وارد شوید.",Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return arrayFname.length;
    }
}
