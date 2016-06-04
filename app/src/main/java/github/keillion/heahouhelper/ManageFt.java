package github.keillion.heahouhelper;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import github.keillion.heahouhelper.manage.AddCclassFt;
import github.keillion.heahouhelper.manage.AddEmployeeFt;
import github.keillion.heahouhelper.manage.AddHouseFt;
import github.keillion.heahouhelper.manage.CclassInfFt;
import github.keillion.heahouhelper.manage.EmployeeInfFt;
import github.keillion.heahouhelper.manage.HouseInfFt;

/**
 * Created by Keillion on 2016/6/2.
 */
public class ManageFt extends Fragment {

    View fv;
    ArrayList<String> fragmentTags = new ArrayList<>();
    String activeFragmentTag;
    Integer activeButtonId;
    Drawable actBtnOriBg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fv = inflater.inflate(R.layout.f_manage_main, container, false);

        if(savedInstanceState == null){
            //set default fragment
            switchFragment(CclassInfFt.class.getName());
            switchActiveButton(fv.findViewById(R.id.f_mana_b_cclassinf));
        }else{

            //resume states
            activeFragmentTag = savedInstanceState.getString("github.keillion.heahouhelper.ManageFt.activeFragmentTag");
            fragmentTags = savedInstanceState.getStringArrayList("github.keillion.heahouhelper.ManageFt.fragmentTags");
            int tempActiveButtonId = savedInstanceState.getInt("github.keillion.heahouhelper.ManageFt.activeButtonId");

            //hide all
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            for(String tag : fragmentTags){
                ft.hide(getFragmentManager().findFragmentByTag(tag));
            }
            ft.commit();

            //show active fragment
            switchFragment(activeFragmentTag);
            switchActiveButton(fv.findViewById(tempActiveButtonId));
        }

        //set tab buttons
        fv.findViewById(R.id.f_mana_b_cclassinf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(CclassInfFt.class.getName());
                switchActiveButton(v);
            }
        });
        fv.findViewById(R.id.f_mana_b_houseInf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(HouseInfFt.class.getName());
                switchActiveButton(v);
            }
        });
        fv.findViewById(R.id.f_mana_b_employeeInf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(EmployeeInfFt.class.getName());
                switchActiveButton(v);
            }
        });
        fv.findViewById(R.id.f_mana_b_addcclass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(AddCclassFt.class.getName());
                switchActiveButton(v);
            }
        });
        fv.findViewById(R.id.f_mana_b_addhouse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(AddHouseFt.class.getName());
                switchActiveButton(v);
            }
        });
        fv.findViewById(R.id.f_mana_b_addemployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(AddEmployeeFt.class.getName());
                switchActiveButton(v);
            }
        });
        return fv;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("github.keillion.heahouhelper.ManageFt.activeFragmentTag", activeFragmentTag);
        savedInstanceState.putStringArrayList("github.keillion.heahouhelper.ManageFt.fragmentTags", fragmentTags);
        savedInstanceState.putInt("github.keillion.heahouhelper.ManageFt.activeButtonId", activeButtonId);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void switchFragment(String tag){

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        //hide the old
        if(activeFragmentTag != null){
            ft.hide(getFragmentManager().findFragmentByTag(activeFragmentTag));
        }

        //show the new
        Fragment newFragment = getFragmentManager().findFragmentByTag(tag);
        if(newFragment == null){
            //instantiate new Fragment,if hasn't added
            try {
                newFragment = (Fragment) Class.forName(tag).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //add the new
            ft.add(R.id.f_customer_container, newFragment, tag);
            fragmentTags.add(tag);
        }else{
            //show the new directly,if has existed
            ft.show(newFragment);
        }

        ft.commit();
        activeFragmentTag = tag;
    }

    private void switchActiveButton(View btn){

        //recover the old
        if(activeButtonId != null){
            fv.findViewById(activeButtonId).setBackground(actBtnOriBg);
        }

        //store the new id
        activeButtonId = btn.getId();
        actBtnOriBg = btn.getBackground();

        //light the new
        btn.setBackgroundColor(Color.WHITE);
    }
}
