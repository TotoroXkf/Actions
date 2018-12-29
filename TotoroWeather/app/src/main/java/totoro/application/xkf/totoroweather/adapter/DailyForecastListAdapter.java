package totoro.application.xkf.totoroweather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import totoro.application.xkf.totoroweather.R;
import totoro.application.xkf.totoroweather.listener.OnSunChangeListener;
import totoro.application.xkf.totoroweather.model.DailyForecast;
import totoro.application.xkf.totoroweather.util.ImageSelector;

public class DailyForecastListAdapter extends RecyclerView.Adapter<DailyForecastListAdapter.ViewHolder> {
    private DailyForecast mDailyForecast;
    private OnSunChangeListener mSunChangeListener;

    public DailyForecastListAdapter(DailyForecast dailyForecast) {
        super();
        mDailyForecast = dailyForecast;
    }

    public void setSunChangeListener(OnSunChangeListener listener) {
        mSunChangeListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_forecast_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //加载未来几日的天气预测
        DailyForecast.Item item = mDailyForecast.getDailyForecast().get(position);
        holder.ivDailyDetail.setImageResource(ImageSelector.selectWeatherIcon(item.getCodeDay()));
        String dailyTmp = "";
        if (position == 0) {
            dailyTmp += "今天";
            mSunChangeListener.onSunChange(item.getCodeDay(), item.getCodeNight(), item.getSunRise(), item.getSunSet());
        } else if (position == 1) {
            dailyTmp += "明天";
        } else {
            dailyTmp += "后天";
        }
        dailyTmp += "     " + item.getMinTemperature() + "°~" + item.getMaxTemperature() + "°";
        holder.tvDailyTmp.setText(dailyTmp);
        String dailyDetail = "白天" + item.getTxtDay() + "，夜晚" + item.getTxtNight() + "。"
                + item.getWindType() + "  " + item.getWindDegree() + "级。" + "日出在" + item.getSunRise() + "，"
                + "日落在" + item.getSunSet();
        holder.tvDailyDetail.setText(dailyDetail);
    }

    @Override
    public int getItemCount() {
        return mDailyForecast.getDailyForecast().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDailyTmp;
        private TextView tvDailyDetail;
        private ImageView ivDailyDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDailyTmp = itemView.findViewById(R.id.tv_daily_tmp);
            tvDailyDetail = itemView.findViewById(R.id.tv_daily_detail);
            ivDailyDetail = itemView.findViewById(R.id.iv_daily_weather_icon);
        }
    }
}
