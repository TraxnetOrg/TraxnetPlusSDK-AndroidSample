package ee.traxnet.plussample.android.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ee.traxnet.plus.AdHolder;
import ee.traxnet.plus.AdShowListener;
import ee.traxnet.plus.TraxnetPlus;
import ee.traxnet.plussample.android.BuildConfig;
import ee.traxnet.plussample.android.R;
import ee.traxnet.plussample.android.enums.ListItemType;
import ee.traxnet.plussample.android.model.ItemList;

public class NativeBannerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_AD = 1;
    private final Activity activity;
    private final LayoutInflater inflater;
    private List<ItemList> items;

    public NativeBannerAdapter(Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
        items = new ArrayList<>();
    }

    public void updateItem(List<ItemList> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_AD) {
            return new TraxnetListItemAdHolder(
                    inflater.inflate(R.layout.list_ad_item, parent, false), activity);
        } else {
            return new ItemHolder(inflater.inflate(R.layout.list_item, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position).listItemType == ListItemType.ITEM) {
            return VIEW_TYPE_ITEM;
        }

        return VIEW_TYPE_AD;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            ((ItemHolder) holder).bindView(position);
            return;
        }

        ((TraxnetListItemAdHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvTitle;

        ItemHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

        void bindView(int position) {
            tvTitle.setText(items.get(position).title);
        }
    }

    public class TraxnetListItemAdHolder extends RecyclerView.ViewHolder {
        private FrameLayout adContainer;
        private AdHolder adHolder;

        TraxnetListItemAdHolder(View itemView, Activity activity) {
            super(itemView);
            adContainer = itemView.findViewById(R.id.adContainer);
            adHolder = TraxnetPlus.createAdHolder(
                    activity, adContainer, R.layout.native_banner);
        }

        void bindView(int position) {
            TraxnetPlus.showAd(
                    activity,
                    adHolder,
                    BuildConfig.TRAXNET_NATIVE_BANNER,
                    items.get(position).id,
                    new AdShowListener() {
                        @Override
                        public void onOpened() {
                        }

                        @Override
                        public void onClosed() {
                        }

                        @Override
                        public void onRewarded() {
                        }

                        @Override
                        public void onError(String s) {
                        }
                    });
        }
    }
}
