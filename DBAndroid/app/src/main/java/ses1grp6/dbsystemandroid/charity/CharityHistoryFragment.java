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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.SimpleRecyclerAdaptor;

public class CharityHistoryFragment extends Fragment implements SimpleRecyclerAdaptor.Binder<CharityHistoryFragment.HistoryHolder> {

    List<CharityHistory> history = new ArrayList<>();

    public CharityHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        buildRecyclerView(rootView);
        return rootView;
    }

    private void buildRecyclerView(View rootView) {
        // TODO REMOVE Sample/Test data
        history.add(new CharityHistory("Some Title", new Date(), "99 Some Street, Jakarta", "Some kind of transaction was performed"));
        // TODO END

        // Setup recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.charityHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new SimpleRecyclerAdaptor<>(HistoryHolder.class, this, R.layout.charity_history_card, history));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder viewHolder, int i) {
        CharityHistory hist = history.get(i);
        viewHolder.title.setText(hist.title);
        viewHolder.date.setText(new SimpleDateFormat("d MMM y", Locale.getDefault()).format(hist.date) + " "); // Hacky fix to cut off text by adding space.
        viewHolder.address.setText(hist.address);
        viewHolder.description.setText(hist.description);
    }

    public static class HistoryHolder extends RecyclerView.ViewHolder {

        public final TextView title, date, address, description;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.charityHistoryTitle);
            date = itemView.findViewById(R.id.charityHistoryTime);
            address = itemView.findViewById(R.id.charityHistoryAddress);
            description = itemView.findViewById(R.id.charityHistoryDescrip);
        }
    }
}
