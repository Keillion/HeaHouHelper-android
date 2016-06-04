package github.keillion.heahouhelper.manage;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import github.keillion.heahouhelper.R;

/**
 * Created by Keillion on 2016/6/4.
 */
public class CclassInfFt extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.f_mana_cclassinf, container, false);

        //register keycode_search push down of searchView
        ((SearchView)fv.findViewById(R.id.f_mana_ccinf_sv)).setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_SEARCH && event.getAction() == KeyEvent.ACTION_DOWN){

                    //hide the soft keyboard
                    InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if(inputMethodManager.isActive()){
                        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }

                    // TODO:add search event

                    return true;
                }
                return false;
            }
        });

        return  fv;
    }
}
