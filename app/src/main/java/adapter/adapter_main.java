package adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cryptowatcher.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.coinModel;

public class adapter_main extends RecyclerView.Adapter<adapter_main.MyViewHolder> {


    private Context context;
    private List<coinModel> coinModel;

    public adapter_main(Context context, List<coinModel> coinModel) {
        this.context = context;
        this.coinModel = coinModel;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.coin_list_item_rc_main, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.txt_name_coin.setText(coinModel.get(position).getName());
        holder.txt_price_btc.setText(coinModel.get(position).getPrice_btc());
        holder.txt_price_usd.setText(coinModel.get(position).getPrice_usd());
        holder.txt_change1h.setText(coinModel.get(position).getPercent_change_1h());
        holder.txt_change24h.setText(coinModel.get(position).getPercent_change_24h());
        holder.txt_change7d.setText(coinModel.get(position).getPercent_change_7d());

        String oneh = coinModel.get(position).getPercent_change_1h();
        String sevend = coinModel.get(position).getPercent_change_7d();
        String tfh = coinModel.get(position).getPercent_change_24h();
        double v1 = Double.parseDouble(oneh);
        double v24 = Double.parseDouble(tfh);
        double v7d = Double.parseDouble(sevend);
        if (v1 < 0) {
            //red
            holder.txt_change1h.setBackgroundColor(Color.parseColor("#FFCE3C4B"));
        } else {
            if (v1 > 0) {
                //green
                holder.txt_change1h.setBackgroundColor(Color.parseColor("#FF64C864"));
            } else if (v1 == 0.00) {
                //ddd
                holder.txt_change1h.setBackgroundColor(Color.parseColor("#B5C04D78"));
            }
        }
        if (v7d < 0) {
            holder.txt_change7d.setBackgroundColor(Color.parseColor("#FFCE3C4B"));
        } else {
            if (v7d > 0) {
                holder.txt_change7d.setBackgroundColor(Color.parseColor("#FF64C864"));

            } else if (v7d == 0) {
                holder.txt_change7d.setBackgroundColor(Color.parseColor("#B5C04D78"));

            }
        }
        if (v24 < 0) {
            holder.txt_change24h.setBackgroundColor(Color.parseColor("#FFCE3C4B"));
        } else {
            if (v24 > 0) {
                holder.txt_change24h.setBackgroundColor(Color.parseColor("#FF64C864"));
            } else if (v24 == 0) {
                holder.txt_change24h.setBackgroundColor(Color.parseColor("#B5C04D78"));
            }
        }

        Picasso.with(context).load(new StringBuilder("https://res.cloudinary.com/dxi90ksom/image/upload/")
                .append(coinModel.get((position)).getSymbol().toLowerCase()).append(".png").toString()).into(holder.img_icon);
    }

    @Override
    public int getItemCount() {
        return coinModel.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView txt_name_coin;
        private TextView txt_price_usd;
        private TextView txt_price_btc;
        private TextView txt_change1h;
        private TextView txt_change24h;
        private TextView txt_change7d;
        private ImageView img_icon;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name_coin = itemView.findViewById(R.id.txt_name_coin);
            txt_price_btc = itemView.findViewById(R.id.txt_price_btc);
            txt_price_usd = itemView.findViewById(R.id.txt_price_usd);
            txt_change1h = itemView.findViewById(R.id.txt_change1h);
            txt_change24h = itemView.findViewById(R.id.txt_change24h);
            txt_change7d = itemView.findViewById(R.id.txt_change7d);
            img_icon = itemView.findViewById(R.id.img_icon);


        }

    }
}
