package ses1grp6.dbsystemandroid.util;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * A Simple Generic RecycleView Adapter.
 * Can be used when a simple adapter is needed without needing implement an entire adapter class.
 *
 * Requires putting data into a list, a {@link Binder} as a callback to when data is actually binded and
 * a view holder which can describe how each item in the recycler view will look like.
 */
public class SimpleRecyclerAdaptor<A extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<A> {

    private final List<T> items;
    private final Binder<A> binder;
    private final Class<A> holderCls;
    private final @LayoutRes int viewHolderRes;

    /**
     * @param holderCls A class that extends {@link RecyclerView.ViewHolder}, e.g myViewHolder.class
     * @param binder See {@link Binder}.
     * @param viewHolderRes The resource file for the view holder.
     * @param items A list of data.
     */
    public SimpleRecyclerAdaptor(Class<A> holderCls, Binder<A> binder, @LayoutRes int viewHolderRes, List<T> items) {
        this.binder = binder;
        this.viewHolderRes = viewHolderRes;
        this.items = items;
        this.holderCls = holderCls;
    }

    @NonNull
    @Override
    public A onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(viewHolderRes, viewGroup, false);

        try {
            return holderCls.getConstructor(View.class).newInstance(view);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull A viewHolder, int i) {
        binder.onBindViewHolder(viewHolder, i);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * A callback object that will be called when data needs to be binded to the viewholder.
     * @param <A> something that {@link RecyclerView.ViewHolder}
     */
    public interface Binder<A> {

        void onBindViewHolder(@NonNull A viewHolder, int i);
    }
}
