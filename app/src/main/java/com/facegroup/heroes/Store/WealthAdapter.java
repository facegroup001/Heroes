package com.facegroup.heroes.Store;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facegroup.heroes.Database.Database;
import com.facegroup.heroes.R;
import com.facegroup.heroes.Wealth.Wealth;

import java.util.ArrayList;
import java.util.List;

public class WealthAdapter extends RecyclerView.Adapter<WealthAdapter.ViewHolder> {

    List<WealthModel> list;
    static Database database;
    Wealth wealth;
    String wealthType;

    public WealthAdapter(String wealthType, Activity activity, List<WealthModel> list) {
        this.list = (list == null ? new ArrayList<>() : list);
        database = new Database(activity);
        wealth = new Wealth(activity);
        this.wealthType = wealthType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_coin_store1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(wealthType, list.get(position), wealth);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_recycler_view_wealth);
        }

        public void bind(String wealthType, WealthModel wealthModel, Wealth wealth) {
            img.setImageResource(wealthModel.getImage());
            img.setOnClickListener(view -> {
                switch (wealthType) {
                    case "COIN":
                        addCoin(wealthModel.amountToAdd);
                        break;
                    case "HEART":
                        addHeart(wealthModel.amountToAdd);
                        break;
                    case "EYE":
                        addEye(wealthModel.amountToAdd);
                        break;
                    case "CARD":
                        addCard(wealthModel.amountToAdd);
                        break;
                }
                wealth.assignWealth();
            });
        }

        public void addCoin(int amountToaAdd) {
            database.updateCoinCount(database.getCoinCount() + amountToaAdd);
        }

        public void addHeart(int amountToAdd) {
            database.updateHeartCount(database.getHeartCount() + amountToAdd);
        }

        public void addEye(int amountToAdd) {
            database.updateEyeCount(database.getEyeCount() + amountToAdd);
        }

        public void addCard(int amountToAdd) {
            database.updateCardCount(database.getCardCount() + amountToAdd);
        }
    }

}
