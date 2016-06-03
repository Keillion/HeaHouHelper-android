package github.keillion.heahouhelper.customer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import github.keillion.heahouhelper.R;

/**
 * Created by Keillion on 2016/6/3.
 */
public class AddCardFt extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.f_cust_addcard, container, false);

        return  fv;
    }
}
