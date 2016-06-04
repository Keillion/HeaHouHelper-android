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

import github.keillion.heahouhelper.customer.AddCardFt;
import github.keillion.heahouhelper.customer.AddCustFt;
import github.keillion.heahouhelper.customer.CardInfFt;
import github.keillion.heahouhelper.customer.CustInfFt;

/**
 * Created by Keillion on 2016/6/2.
 */
public class CustomerFt extends Fragment {

    View fv;
    ArrayList<String> fragmentTags = new ArrayList<>();
    String activeFragmentTag;
    Integer activeButtonId;
    Drawable actBtnOriBg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fv = inflater.inflate(R.layout.f_customer_main, container, false);

        if(savedInstanceState == null){
            //set default fragment
            switchFragment(AddCustFt.class.getName());
            switchActiveButton(fv.findViewById(R.id.f_customer_b_addCust));
        }else{

            //resume states
            activeFragmentTag = savedInstanceState.getString("github.keillion.heahouhelper.CustomerFt.activeFragmentTag");
            fragmentTags = savedInstanceState.getStringArrayList("github.keillion.heahouhelper.CustomerFt.fragmentTags");
            int tempActiveButtonId = savedInstanceState.getInt("github.keillion.heahouhelper.MainActivity.activeButtonId");

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
        fv.findViewById(R.id.f_customer_b_addCust).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(AddCustFt.class.getName());
                switchActiveButton(v);
            }
        });
        fv.findViewById(R.id.f_customer_b_custInf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(CustInfFt.class.getName());
                switchActiveButton(v);
            }
        });
        fv.findViewById(R.id.f_customer_b_addCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(AddCardFt.class.getName());
                switchActiveButton(v);
            }
        });
        fv.findViewById(R.id.f_customer_b_cardInf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(CardInfFt.class.getName());
                switchActiveButton(v);
            }
        });

        return fv;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("github.keillion.heahouhelper.CustomerFt.activeFragmentTag", activeFragmentTag);
        savedInstanceState.putStringArrayList("github.keillion.heahouhelper.CustomerFt.fragmentTags", fragmentTags);
        savedInstanceState.putInt("github.keillion.heahouhelper.CustomerFt.activeButtonId", activeButtonId);
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
