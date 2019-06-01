package ses1grp6.dbsystemandroid.donor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ses1grp6.dbsystemandroid.R;
import ses1grp6.dbsystemandroid.model.Listing;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.DonorListViewHolder> {
    ArrayList<Listing> donors;
    private ItemClickListener itemClickListener;

    public int getItem(int position) { return donors.get(position - 1).getId(); }

    public class DonorListViewHolder extends RecyclerView.ViewHolder {
        TextView listingTitle, listingDesc, listingIndustry, listingBy;
        public DonorListViewHolder(@NonNull View itemView) {
            super(itemView);
            listingTitle = (TextView) itemView.findViewById(R.id.listing_charities_item_title);
            listingDesc = (TextView) itemView.findViewById(R.id.listing_charities_item_desc);
            listingBy = itemView.findViewById(R.id.listing_charities_item_by);
            listingIndustry = itemView.findViewById(R.id.listing_charities_item_industry);
        }
    }

    public ListingAdapter(ArrayList<Listing> donors){
        this.donors = donors;
    }

    @Override
    public DonorListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listing_charities_item, viewGroup, false);
        return new DonorListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final DonorListViewHolder donorListViewHolder, int i) {
        Listing listing = donors.get(i);

        String listingTitle = listing.getListingTitle();
        String listingDesc = listing.getListingDescription();
        String listingBy = listing.getCharity().getName();
        String listingIndustry = listing.getIndustry().getIndustryName();
        final int id = listing.getId();

        donorListViewHolder.listingTitle.setText(listingTitle);
        donorListViewHolder.listingDesc.setText(listingDesc);
        donorListViewHolder.listingBy.setText("By " + listingBy);
        donorListViewHolder.listingIndustry.setText("Under " + listingIndustry);

        donorListViewHolder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) itemClickListener.onItemClick(view, id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return donors.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int id);
    }
}
