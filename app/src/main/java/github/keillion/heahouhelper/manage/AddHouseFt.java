package github.keillion.heahouhelper.manage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import github.keillion.heahouhelper.R;

/**
 * Created by Keillion on 2016/6/4.
 */
public class AddHouseFt extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.f_mana_addhouse, container, false);

        return  fv;
    }
}
