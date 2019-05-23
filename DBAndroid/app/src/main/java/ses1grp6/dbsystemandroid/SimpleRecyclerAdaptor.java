package ses1grp6.dbsystemandroid;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SimpleRecyclerAdaptor<A extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<A> {

    private final List<T> items;
    private final Binder<A> binder;
    private final Class<A> holderCls;
    private final @LayoutRes int viewHolderRes;

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

    public interface Binder<A> {

        void onBindViewHolder(@NonNull A viewHolder, int i);
    }
}
