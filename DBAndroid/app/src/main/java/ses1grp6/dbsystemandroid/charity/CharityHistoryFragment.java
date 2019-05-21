package ses1grp6.dbsystemandroid.charity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.SimpleRecyclerAdaptor;

public class CharityHistoryFragment extends Fragment implements SimpleRecyclerAdaptor.Binder<CharityHistoryFragment.HistoryHolder> {


    public CharityHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_charity_history, container, false);
        buildRecyclerView(rootView);
        return rootView;
    }

    private void buildRecyclerView(View rootView) {
        // TODO Sample/Test code
        RecyclerView recyclerView = rootView.findViewById(R.id.charityHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        List<String> l = new ArrayList<>();
        recyclerView.setAdapter(new SimpleRecyclerAdaptor<>(this, R.layout.history_item, l));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder viewHolder, int i) {
        //TODO BIND DATA
    }

    public static class HistoryHolder extends SimpleRecyclerAdaptor.ViewHolder {

        public final TextView title, date, description;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.historyTitle);
            date = itemView.findViewById(R.id.historyTime);
            description = itemView.findViewById(R.id.historyDescription);
        }
    }
}
