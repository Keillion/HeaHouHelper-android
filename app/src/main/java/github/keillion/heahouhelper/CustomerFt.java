package github.keillion.heahouhelper;

import android.app.Fragment;
import android.app.FragmentTransaction;
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

    ArrayList<String> fragmentTags = new ArrayList<>();
    String activeFragmentTag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.f_customer_main, container, false);

        if(savedInstanceState == null){
            //set default fragment
            switchFragment(AddCustFt.class.getName());
        }else{

            //resume states
            activeFragmentTag = savedInstanceState.getString("github.keillion.heahouhelper.CustomerFt.activeFragmentTag");
            fragmentTags = savedInstanceState.getStringArrayList("github.keillion.heahouhelper.CustomerFt.fragmentTags");

            //hide all
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            for(String tag : fragmentTags){
                ft.hide(getFragmentManager().findFragmentByTag(tag));
            }
            ft.commit();

            //show active fragment
            switchFragment(activeFragmentTag);
        }

        //set tab buttons
        fv.findViewById(R.id.f_customer_b_addCust).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(AddCustFt.class.getName());
            }
        });
        fv.findViewById(R.id.f_customer_b_custInf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(CustInfFt.class.getName());
            }
        });
        fv.findViewById(R.id.f_customer_b_addCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(AddCardFt.class.getName());
            }
        });
        fv.findViewById(R.id.f_customer_b_cardInf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(CardInfFt.class.getName());
            }
        });

        return fv;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("github.keillion.heahouhelper.CustomerFt.activeFragmentTag", activeFragmentTag);
        savedInstanceState.putStringArrayList("github.keillion.heahouhelper.CustomerFt.fragmentTags", fragmentTags);
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
}
