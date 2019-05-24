package ses1grp6.dbsystemandroid.donor;


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
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.SimpleRecyclerAdaptor;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonorHistoryFragment extends Fragment implements SimpleRecyclerAdaptor.Binder<DonorHistoryFragment.HistoryHolder> {

    List<DonorHistory> history = new ArrayList<>();

    public DonorHistoryFragment() {
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
        // TODO REMOVE SAMPLE TEST DATA
        history.add(new DonorHistory("Save The Team", new Date(), "Help Master", "10 Lane, Brisbane", "An event intended to help teams that struggle. Bla bla bla, bla bLA bla."));
        // TODO END

        // Setup recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.charityHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new SimpleRecyclerAdaptor<>(HistoryHolder.class, this, R.layout.donor_history_card, history));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder viewHolder, int i) {
        DonorHistory hist = history.get(i);
        viewHolder.title.setText(hist.title);
        viewHolder.time.setText(new SimpleDateFormat("d MMM Y", Locale.getDefault()).format(hist.date) + " "); // Hacky fix to cut off text by adding space.
        viewHolder.address.setText(hist.address);
        viewHolder.description.setText(hist.description);
    }

    public static class HistoryHolder extends RecyclerView.ViewHolder {

        public final TextView title, charityName, address, time, description;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.donorHistoryTitle);
            time = itemView.findViewById(R.id.donorHistoryTime);
            charityName = itemView.findViewById(R.id.donorHistoryCharityName);
            address = itemView.findViewById(R.id.donorHistoryAddress);
            description = itemView.findViewById(R.id.donorHistoryDescrip);
        }
    }


}
