package com.project.shimi.creative_theory_test.ui.rawmaterial.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.shimi.creative_theory_test.R;
import com.project.shimi.creative_theory_test.model.RawMaterialItem;
import com.project.shimi.creative_theory_test.ui.MainActivity;
import com.project.shimi.creative_theory_test.ui.rawmaterial.RawMaterialFragment;

import java.util.List;

public class RawMaterialAdapter extends RecyclerView.Adapter<RawMaterialAdapter.ViewHolder> {

    private MainActivity activity;
    private RawMaterialFragment context;

    private List<RawMaterialItem> rawMaterialItemList;

    public RawMaterialAdapter(MainActivity activity, RawMaterialFragment fragment, List<RawMaterialItem> list) {
        this.activity = activity;
        this.context = fragment;
        this.rawMaterialItemList = list;
    }

    @NonNull
    @Override
    public RawMaterialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.holder_raw_material, viewGroup, false);
        return new RawMaterialAdapter.ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPicture;
        private TextView tvName;
        private TextView tvPrice;
        private Button btnAddToCart;

        ViewHolder(View v) {
            super(v);
            ivPicture = v.findViewById(R.id.iv_picture);
            tvName = v.findViewById(R.id.tv_name);
            tvPrice = v.findViewById(R.id.tv_price);
            btnAddToCart = v.findViewById(R.id.btn_add_to_cart);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final RawMaterialItem rawMaterialItem = rawMaterialItemList.get(i);
        Glide.with(context).load(getMipmapFromRawMaterialPicture(rawMaterialItem)).into(viewHolder.ivPicture);
        viewHolder.tvName.setText(rawMaterialItem.getName());
        viewHolder.tvPrice.setText(String.valueOf(rawMaterialItem.getDefaultPrice()));
        viewHolder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setRawMaterialItem(rawMaterialItem);
                activity.changePage(1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rawMaterialItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private int getMipmapFromRawMaterialPicture(RawMaterialItem item) {
        String picture = item.getPicture();
        return activity.getResources().getIdentifier(picture, "mipmap", activity.getPackageName());
    }

}
