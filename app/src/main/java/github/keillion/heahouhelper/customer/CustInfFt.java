package github.keillion.heahouhelper.customer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import github.keillion.heahouhelper.R;

/**
 * Created by Keillion on 2016/6/3.
 */
public class CustInfFt extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.f_cust_custinf, container, false);

        ((SearchView)fv.findViewById(R.id.f_cust_custinf_sv)).setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "SearchClicked", Toast.LENGTH_LONG).show();
            }
        });

//        ((SearchView)fv.findViewById(R.id.f_cust_custinf_sv)).setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                return false;
//            }
//        });

        return  fv;
    }
}
