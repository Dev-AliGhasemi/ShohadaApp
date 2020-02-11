package com.ghasemi.golzarshohada.FragmentsPages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ghasemi.golzarshohada.Font.SetFonts;
import com.ghasemi.golzarshohada.R;
import com.ghasemi.golzarshohada.RecyclerViewAdapter.RecyclerAdapter;
import com.ghasemi.golzarshohada.database.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * Created by Computer Freak on 7/24/2018.
 */

public class FragmentTwo extends Fragment {
    private ImageView picSearch;
    private EditText editSearch;
    private RecyclerView recyclerView;
    private View view;
    private DatabaseHelper databaseHelper;
    private SetFonts setFonts;
    ArrayList arrayShohada;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shohada,container,false);
        init();
        setFonts = new SetFonts(getActivity(),"fonts/iran_sans.otf");
        setFonts.setFont("EditText",editSearch);
        try {
            databaseHelper = new DatabaseHelper(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        picSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editSearch.getText().toString().length()==0) {
                    Toast.makeText(getActivity(),"لطفا نام و نام خانوادگی را بنویسید.",Toast.LENGTH_SHORT).show();
                }
                else {
                    String[] ID = databaseHelper.selectMultiData("select id from tbl_shohada where Fname like '%"+editSearch.getText().toString()+"%' or Lname like '%"+editSearch.getText().toString()+"%';");
                    String[] Fname = databaseHelper.selectMultiData("select Fname from tbl_shohada where Fname like '%"+editSearch.getText().toString()+"%' or Lname like '%"+editSearch.getText().toString()+"%';");
                    String[] Lname = databaseHelper.selectMultiData("select Lname from tbl_shohada where Fname like '%"+editSearch.getText().toString()+"%' or Lname like '%"+editSearch.getText().toString()+"%';");
                    String[] DateBorn = databaseHelper.selectMultiData("select DateBorn from tbl_shohada where Fname like '%"+editSearch.getText().toString()+"%' or Lname like '%"+editSearch.getText().toString()+"%';");
                    String[] DateDead = databaseHelper.selectMultiData("select DateDead from tbl_shohada where Fname like '%"+editSearch.getText().toString()+"%' or Lname like '%"+editSearch.getText().toString()+"%';");
                    int[] Favorite = databaseHelper.selectFavorite("select favorite from tbl_shohada where Fname like '%"+editSearch.getText().toString()+"%' or Lname like '%"+editSearch.getText().toString()+"%';");
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),1,false));
                    if (Fname.length==0) {
                        Toast.makeText(getActivity(),"چنین شهیدی وجود ندارد.",Toast.LENGTH_SHORT).show();
                        recyclerView.setAdapter(new RecyclerAdapter(getActivity(),ID,Fname,Lname,DateBorn,DateDead,"Frag2",Favorite));
                    }
                    else {
                        recyclerView.setAdapter(new RecyclerAdapter(getActivity(),ID,Fname,Lname,DateBorn,DateDead,"Frag2",Favorite));
                    }
                }
            }
        });
        return view;
    }
    public void init(){
        picSearch = (ImageView) view.findViewById(R.id.act_3_picSearch);
        editSearch = (EditText) view.findViewById(R.id.act_3_editSearch);
        recyclerView = (RecyclerView) view.findViewById(R.id.act_3_recycler);
    }

    @Override
    public void onStop() {
        super.onStop();
        editSearch.setText("");
    }
}
