package totoro.application.xkf.totoroweather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import totoro.application.xkf.totoroweather.R;
import totoro.application.xkf.totoroweather.model.HourlyForecast;

public class HourlyForecastListAdapter extends RecyclerView.Adapter<HourlyForecastListAdapter.ViewHolder> {
    private HourlyForecast mHourlyForecast;

    public HourlyForecastListAdapter(HourlyForecast hourlyForecast) {
        super();
        mHourlyForecast = hourlyForecast;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_forecast_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HourlyForecast.Item item = mHourlyForecast.getList().get(position);
        holder.tvHourlyWind.setText(item.getWindDegree()+"级");
        holder.tvHourlyDataAndTxt.setText(item.getTime() + "   " + item.getTxt());
        holder.tvHourlyTmp.setText(item.getTemperature() + "°");
    }

    @Override
    public int getItemCount() {
        return mHourlyForecast.getList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHourlyDataAndTxt;
        private TextView tvHourlyTmp;
        private TextView tvHourlyWind;

        public ViewHolder(View itemView) {
            super(itemView);
            tvHourlyDataAndTxt = itemView.findViewById(R.id.tv_hourly_data_and_txt);
            tvHourlyTmp = itemView.findViewById(R.id.tv_hourly_tmp);
            tvHourlyWind = itemView.findViewById(R.id.tv_hourly_wind);
        }
    }
}
