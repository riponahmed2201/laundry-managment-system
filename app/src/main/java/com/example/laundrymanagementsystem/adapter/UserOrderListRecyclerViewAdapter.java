package com.example.laundrymanagementsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundrymanagementsystem.R;
import com.example.laundrymanagementsystem.utiles.Utiles;

public class UserOrderListRecyclerViewAdapter extends RecyclerView.Adapter<UserOrderListRecyclerViewAdapter.ViewHolder> {

    Context context;

    public UserOrderListRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UserOrderListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shape_user_order, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserOrderListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.garmentCategory.setText(Utiles.orderInformations.get(position).getGarmentCategory());
        holder.garmentQuantity.setText(Utiles.orderInformations.get(position).getGarmentQuantity());
        holder.paymentOption.setText(Utiles.orderInformations.get(position).getPaymentOption());
        holder.orderPlacement.setText(Utiles.orderInformations.get(position).getOrderPlacement());
    }

    @Override
    public int getItemCount() {
        return Utiles.orderInformations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView garmentCategory, garmentQuantity, paymentOption, orderPlacement;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            garmentCategory = itemView.findViewById(R.id.garment_category_id);
            garmentQuantity = itemView.findViewById(R.id.garment_quantity_id);
            paymentOption = itemView.findViewById(R.id.payment_option_id);
            orderPlacement = itemView.findViewById(R.id.order_placement_id);
        }
    }
}
