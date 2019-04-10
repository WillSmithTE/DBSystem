package ses1group6.dbandroid.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;

import ses1group6.dbandroid.NavigationActivity;
import ses1group6.dbandroid.R;

public class HomeFragment extends Fragment implements
        View.OnClickListener {

    private Double goalEnergy;

    private TextView currentEnergyTV;
    private TextView remainingEnergyTV;
    private TextView stepsTV;

    private NavigationActivity activity;

    private boolean diarySynced;
    private boolean dailyDataSynced;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getActivity() == null || getContext() == null){
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        activity = (NavigationActivity) getActivity();

//        CardView diaryCard = view.findViewById(R.id.diarycard);
//        CardView pedometerCard = view.findViewById(R.id.pedometercard);
//        CardView leaderboardCard = view.findViewById(R.id.leaderboardcard);
//
//        TextView goalEnergyTV = view.findViewById(R.id.goalcal);
//        RecyclerView leaderboardList = view.findViewById(R.id.menu_leaderboard);
//        currentEnergyTV     = view.findViewById(R.id.currentcal);
//        remainingEnergyTV   = view.findViewById(R.id.remainingcal);
//        stepsTV             = view.findViewById(R.id.pedometer_text);
//
//        diaryCard.setOnClickListener(this);
//        pedometerCard.setOnClickListener(this);
//        leaderboardCard.setOnClickListener(this);
//
//        DiaryManager diaryManager = new DiaryManager(activity.getCredentialsProvider().getIdentityId(), this);
//        diaryManager.syncDiary();
//
//        goalEnergy = getGoalEnergy();
//        goalEnergyTV.setText(goalEnergy.toString());
//
//        leaderboard = new ArrayList<>();

//        leaderboardList.setHasFixedSize(true);
//        DividerItemDecoration spacer = new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL);
//        spacer.setDrawable(ContextCompat.getDrawable(activity, R.drawable.item_divider));
//        leaderboardList.addItemDecoration(spacer);
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
//        leaderboardList.setLayoutManager(layoutManager);
//
//        leaderboardAdapter = new LeaderboardAdapter(leaderboard);
//        leaderboardList.setAdapter(leaderboardAdapter);
//
//        dailyDataManager = activity.getDailyDataManager();
//        dailyDataManager.setCallback(this);
//        dailyDataManager.syncAllDailyData(StringUtils.getCurrentDateFormatted());

        return view;
    }

    @Override
    public void onPause() {
//        dailyDataManager.setCallback(activity);
        super.onPause();
    }

    @Override
    public void onClick(View view) {

//        switch(view.getId()){
//            case R.id.diarycard :
//                activity.onNavigationItemSelected(activity.getNavigationView().getMenu().findItem(R.id.diary));
//                getActivity().setTitle("Diary");
//                break;
//            case R.id.pedometercard :
//                activity.onNavigationItemSelected(activity.getNavigationView().getMenu().findItem(R.id.pedometer));
//                getActivity().setTitle("Pedometer");
//                break;
//            case R.id.leaderboardcard :
//                activity.onNavigationItemSelected(activity.getNavigationView().getMenu().findItem(R.id.menu_leaderboard));
//                getActivity().setTitle("Leaderboard");
//                break;
//        }

    }

}
