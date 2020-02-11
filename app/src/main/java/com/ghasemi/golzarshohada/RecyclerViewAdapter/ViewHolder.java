package com.ghasemi.golzarshohada.RecyclerViewAdapter;

import android.app.usage.NetworkStats;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ghasemi.golzarshohada.Font.SetFonts;
import com.ghasemi.golzarshohada.R;
import com.ghasemi.golzarshohada.database.DatabaseHelper;
import java.io.IOException;

/**
 * Created by Computer Freak on 7/18/2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView textSokhan,textDateBorn,textDateDead,textName,textDead,textBorn,textFamilyName,textNameShahid;
    Button btnRead;
    ImageView imageView,imageFavorite;
    RecyclerView recyclerView;
    public ViewHolder(View itemView , String currentFragment) {
        super(itemView);
        if (currentFragment.equals("Frag1"))
            textSokhan = (TextView) itemView.findViewById(R.id.recycler_sokhan_text);
        else if (currentFragment.equals("Frag2")){
            textDateBorn = (TextView) itemView.findViewById(R.id.act_3_textDateBorn);
            textDateDead = (TextView) itemView.findViewById(R.id.act_3_textDateDead);
            textBorn = (TextView) itemView.findViewById(R.id.act_3_textBorn);
            textDead = (TextView) itemView.findViewById(R.id.act_3_textDead);
            textFamilyName = (TextView) itemView.findViewById(R.id.act_3_textFamilyName);
            textName = (TextView) itemView.findViewById(R.id.act_3_textName);
            btnRead = (Button) itemView.findViewById(R.id.act_3_btnRead);
            imageView = (ImageView) itemView.findViewById(R.id.act_3_imageFavorite);
        }
        else if (currentFragment.equals("ActivityFavorite")){
                textNameShahid = (TextView) itemView.findViewById(R.id.text_title_name_shahid);
                imageFavorite = (ImageView) itemView.findViewById(R.id.imageFavorite);
                recyclerView = (RecyclerView) itemView.findViewById(R.id.act_5_recyclerFavorite);
        }
    }
}
