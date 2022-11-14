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

public class OrderListRecyclerViewAdapter extends RecyclerView.Adapter<OrderListRecyclerViewAdapter.ViewHolder> {

    Context context;

    public OrderListRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OrderListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shape_order_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.customerName.setText(Utiles.orderInformations.get(position).getUserEmail());
        holder.customerPhoneNumber.setText(Utiles.orderInformations.get(position).getUserPhoneNumber());
        holder.garmentCategory.setText(Utiles.orderInformations.get(position).getGarmentCategory());
        holder.garmentQuantity.setText(Utiles.orderInformations.get(position).getGarmentQuantity());
        holder.paymentOption.setText(Utiles.orderInformations.get(position).getPaymentOption());
        holder.orderPlacement.setText(Utiles.orderInformations.get(position).getOrderPlacement());
    }

    @Override
    public int getItemCount() {
        return Utiles.orderInformations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView customerName;
        private final TextView customerPhoneNumber;
        private final TextView garmentCategory;
        private final TextView garmentQuantity;
        private final TextView orderPlacement;
        private final TextView paymentOption;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            customerName = itemView.findViewById(R.id.customer_name_id);
            customerPhoneNumber = itemView.findViewById(R.id.customer_phone_number_id);
            garmentCategory = itemView.findViewById(R.id.garment_category_id);
            garmentQuantity = itemView.findViewById(R.id.garment_quantity_id);
            orderPlacement = itemView.findViewById(R.id.order_placement_id);
            paymentOption = itemView.findViewById(R.id.payment_option_id);
        }
    }
}
