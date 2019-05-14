package ses1grp6.dbsystemandroid.donor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ses1grp6.dbsystemandroid.R;

public class DonorsAdapter extends RecyclerView.Adapter<DonorsAdapter.DonorListViewHolder> {
    ArrayList<Donor> donors;

    public class DonorListViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, phone;
        public DonorListViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.donor_item_name);
            email = (TextView) itemView.findViewById(R.id.donor_item_email);
            phone = (TextView) itemView.findViewById(R.id.donor_item_phone);
        }
    }

    public DonorsAdapter(ArrayList<Donor> donors){
        this.donors = donors;
    }

    @Override
    public DonorListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.donor_item, viewGroup, false);
        return new DonorListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DonorListViewHolder donorListViewHolder, int i) {
        Donor donor = donors.get(i);

        String name = donor.getName();
        String email = donor.getEmail();
        String phone = donor.getPhone();

        donorListViewHolder.name.setText(name);
        donorListViewHolder.email.setText(email);
        donorListViewHolder.phone.setText(phone);
    }

    @Override
    public int getItemCount() {
        return donors.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
