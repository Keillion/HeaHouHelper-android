package github.keillion.heahouhelper;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> fragmentTags = new ArrayList<>();
    String activeFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //debug
        //Toast.makeText(getActivity(), "", Toast.LENGTH_LONG).show();

        if(savedInstanceState == null){
            //set default fragment
            switchFragment(AttendFt.class.getName());
        }else{

            //resume states
            activeFragmentTag = savedInstanceState.getString("github.keillion.heahouhelper.MainActivity.activeFragmentTag");
            fragmentTags = savedInstanceState.getStringArrayList("github.keillion.heahouhelper.MainActivity.fragmentTags");

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
        findViewById(R.id.a_main_b_attend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(AttendFt.class.getName());
            }
        });
        findViewById(R.id.a_main_b_customer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(CustomerFt.class.getName());
            }
        });
        findViewById(R.id.a_main_b_manage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(ManageFt.class.getName());
            }
        });
        findViewById(R.id.a_main_b_statistics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(StatisticsFt.class.getName());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("github.keillion.heahouhelper.MainActivity.activeFragmentTag", activeFragmentTag);
        savedInstanceState.putStringArrayList("github.keillion.heahouhelper.MainActivity.fragmentTags", fragmentTags);
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
            ft.add(R.id.a_main_container, newFragment, tag);
            fragmentTags.add(tag);
        }else{
            //show the new directly,if has existed
            ft.show(newFragment);
        }

        ft.commit();
        activeFragmentTag = tag;
    }
}
