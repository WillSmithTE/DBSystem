package ses1group6.dbandroid.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import ses1group6.dbandroid.NavigationActivity;
import ses1group6.dbandroid.R;


public class SetupFragment extends Fragment implements
        TextWatcher,
        AdapterView.OnItemSelectedListener,
        View.OnClickListener{

    private NavigationActivity activity;
    private SharedPreferences sharedPreferences;

    private EditText nicknameET;
    private EditText weightET;
    private EditText heightET;
    private EditText ageET;
    private EditText stepGoalET;
    private TextView bmiTV;
    private TextView energyTV;
    private RadioGroup genderRG;
    private Spinner activityLevelSP;
    private Button submitBTN;

    private double requiredEnergy;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getActivity() == null || getContext() == null){
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_setup, container, false);
        activity = (NavigationActivity) getActivity();
        activity.hideLoadingIcon();
        sharedPreferences = activity.getSharedPreferences();

        initialiseViews(view);

        return view;
    }

    private void initialiseViews(View view) {
        nicknameET          = view.findViewById(R.id.editName);
        weightET            = view.findViewById(R.id.editWeight);
        heightET            = view.findViewById(R.id.editHeight);
        ageET               = view.findViewById(R.id.editAge);
        stepGoalET          = view.findViewById(R.id.editSteps);
        bmiTV               = view.findViewById(R.id.bmi);
        energyTV            = view.findViewById(R.id.energy);
        genderRG            = view.findViewById(R.id.genderBtns);
        activityLevelSP     = view.findViewById(R.id.activity_spinner);
        submitBTN           = view.findViewById(R.id.submit);

        nicknameET.addTextChangedListener(this);
        weightET.addTextChangedListener(this);
        heightET.addTextChangedListener(this);
        ageET.addTextChangedListener(this);

        submitBTN.setEnabled(false);
        submitBTN.setOnClickListener(this);

        activityLevelSP.setOnItemSelectedListener(this);
        genderRG.setOnClickListener(this);
    }

    private void validateFields(){
        if( weightET.length() != 0 && heightET.length() != 0){

            Double weight = 0.0;
            Double height = 0.0;

            try {
                weight = Double.parseDouble(weightET.getText().toString());
                height = Double.parseDouble(heightET.getText().toString());

            }catch (NumberFormatException e){
                Toast.makeText(activity, "Invalid weight or height", Toast.LENGTH_LONG).show();
            }

            if(ageET.length() != 0){

                if(genderRG.getCheckedRadioButtonId() == R.id.femaleSelect){
                }

                try{
                    int age = Integer.parseInt(ageET.getText().toString());

                    String energyString = String.format(Locale.US,"Required Energy: %.2f kJ", requiredEnergy);

                    energyTV.setText(energyString);
                }catch (NumberFormatException e){
                    Toast.makeText(activity, "Invalid age", Toast.LENGTH_LONG).show();
                }
            }
        }

        if(nicknameET.getText() != null && !nicknameET.getText().toString().equals("")){
            submitBTN.setEnabled(true);
        }
    }

    private void openHomeFragment() {
        activity.updateView(HomeFragment.class);
    }

    private void saveSharedPreferences() {
        requiredEnergy = requiredEnergy * 100; //Round to 2 dp
        requiredEnergy = (double)((int) requiredEnergy);
        requiredEnergy = requiredEnergy /100;

        sharedPreferences.edit()
                .putString(activity.getString(R.string.pref_key_personal_name), nicknameET.getText().toString())
                .apply();
    }

    // Text field updates
    @Override
    public void afterTextChanged(Editable editable) {
        validateFields();
    }

    //Spinner changes
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        validateFields();
    }

    //Submit or radio pressed
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.submit) {
            saveSharedPreferences();
            openHomeFragment();
        }else{
            validateFields();
        }
    }

    //Unused callbacks
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}


}
