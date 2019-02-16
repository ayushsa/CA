package com.tact.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.tact.kumbhca.R;

import java.util.ArrayList;
import java.util.List;


public class DialogPickImage implements View.OnClickListener
{
    private final boolean isSurveyFragment;
    Dialog d;
    DialogPickImageResponse di;
    Button btSearch;
    private Spinner spCitizenType;
    private EditText etSearch;
    private Context con;

    public DialogPickImage(Context con, boolean isSurveyFragment, DialogPickImageResponse di)
    {
        d = new Dialog(con, R.style.ThemeOverlay_MyDialogActivity);
        d.setContentView(R.layout.dialog_pick_image);

        this.con = con;
        this.isSurveyFragment = isSurveyFragment;

        this.di = di;
        init();
        show();
    }

    private void init()
    {
        spCitizenType =  (Spinner) d.findViewById(R.id.spCitizenType);
        spCitizenType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 1:

                        etSearch.setHint("Serial Number");
                        etSearch.setInputType(InputType.TYPE_CLASS_NUMBER);

                        break;

                    case 2:

                        etSearch.setHint("Voter Id");
                        etSearch.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                        break;

                    case 3:

                        etSearch.setHint("Voter Name");
                        etSearch.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                        break;

                    case 4:

                        etSearch.setHint("House Number");
                        etSearch.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                        break;

                    default:
                      break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etSearch = (EditText) d.findViewById(R.id.etSearch);
        btSearch = (Button) d.findViewById(R.id.btSearch);
        btSearch.setOnClickListener(this);

        List<String> incident = new ArrayList<String>();
        incident.add("--Select Search Filters--");
        incident.add("Serial Number");
        incident.add("Voter Id");
        incident.add("Voter Name");
        incident.add("House Number");



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(con, android.R.layout.simple_spinner_item, incident);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCitizenType.setAdapter(arrayAdapter);
        spCitizenType.setSelection(0);
    }

    private void show()
    {
        d.show();
    }

    @Override
    public void onClick(View v)
    {
        int id = R.id.btSearch;

        switch (id)
        {
            case R.id.btSearch:

                if (spCitizenType.getSelectedItemPosition() != 0)
                {
                    di.onOptionSelected(spCitizenType.getSelectedItemPosition() , etSearch.getText().toString());
                    d.dismiss();
                }
                else
                {
                    Support.showToast(con , "Please pick an filter");
                }

                break;

                default:
                    break;
        }
    }


    public interface DialogPickImageResponse
    {
        void onOptionSelected(int PickType , String searchString);
    }
}
