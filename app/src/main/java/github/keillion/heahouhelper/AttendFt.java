package github.keillion.heahouhelper;

import android.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Keillion on 2016/6/1.
 */
public class AttendFt extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fv = inflater.inflate(R.layout.f_attend, container, false);

        //set correct checkbox
        fv.findViewById(R.id.f_attend_c_correct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tCorrect = (EditText)fv.findViewById(R.id.f_attend_t_correct);
                //switch editable,as inputType don't work
                tCorrect.setEnabled(tCorrect.isEnabled() ? false : true);
            }
        });

        //set submit button
        fv.findViewById(R.id.f_attend_b_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //set clear button
        fv.findViewById(R.id.f_attend_b_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText)fv.findViewById(R.id.f_attend_t_house)).setText("");
                ((EditText)fv.findViewById(R.id.f_attend_t_card)).setText("");
                ((EditText)fv.findViewById(R.id.f_attend_t_people)).setText("");
                ((EditText)fv.findViewById(R.id.f_attend_t_cost)).setText("");
                ((CheckBox)fv.findViewById(R.id.f_attend_c_correct)).setChecked(false);
                ((EditText)fv.findViewById(R.id.f_attend_t_correct)).setEnabled(false);
                ((EditText)fv.findViewById(R.id.f_attend_t_correct)).setText("");
                ((EditText)fv.findViewById(R.id.f_attend_t_description)).setText("");
            }
        });

        return fv;
    }
}
