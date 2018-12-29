package totoro.application.xkf.totoroweather.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import totoro.application.xkf.totoroweather.R;
import totoro.application.xkf.totoroweather.application.AppCache;
import totoro.application.xkf.totoroweather.listener.OnCityChangeListener;
import totoro.application.xkf.totoroweather.model.City;
import totoro.application.xkf.totoroweather.util.AlertDialogBuilder;
import totoro.application.xkf.totoroweather.util.LogUtil;
import totoro.application.xkf.totoroweather.util.PreferenceUtil;
import totoro.application.xkf.totoroweather.util.TextUtil;

public class CityManageListAdapter extends RecyclerView.Adapter<CityManageListAdapter.ViewHolder> {
    private List<String> cityName;
    private List<String> cityId;
    private Context mContext;
    private int localPosition;
    private OnCityChangeListener listener;

    public CityManageListAdapter(OnCityChangeListener listener) {
        super();
        cityName = TextUtil.getCitys(PreferenceUtil.getAllCities());
        cityId = TextUtil.getIds(PreferenceUtil.getAllCities());
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_item, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvName.setText(cityName.get(position));
        if (cityId.get(position).equals(PreferenceUtil.getLocalCityId())) {
            holder.ivLocationIcon.setVisibility(View.VISIBLE);
            localPosition = position;
        } else {
            holder.ivLocationIcon.setVisibility(View.GONE);
        }
        if (cityId.get(position).equals(AppCache.getDataService().getCurrentCityId())) {
            holder.tvCurrentCity.setText(mContext.getString(R.string.currentCity));
        }
        holder.llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                City city = new City();
                city.setCity(cityName.get(position));
                city.setId(cityId.get(position));
                listener.onCityChange(city);
            }
        });
        holder.llContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //长按删除
                //所在地城市不允许被删除，因为每次进来都是加载所在地城市，等于白删
                //删除了正在显示的城市的话，就刷新一下
                String message = mContext.getString(R.string.deleteCityMessage).replace("@", cityName.get(position));
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PreferenceUtil.deleteCity(cityName.get(position), cityId.get(position));
                        if (cityId.get(position).equals(AppCache.getDataService().getCurrentCityId())) {
                            City city = new City();
                            city.setCity(cityName.get(localPosition));
                            city.setId(cityId.get(localPosition));
                            AppCache.getDataService().onCityChange(city);
                        }
                        cityName.remove(position);
                        cityId.remove(position);
                        notifyDataSetChanged();
                    }
                };
                if (cityId.get(position).equals(PreferenceUtil.getLocalCityId())) {
                    message = mContext.getString(R.string.cannotDelete);
                    listener = null;
                }
                AlertDialogBuilder.createDialog(mContext, mContext.getString(R.string.deleteCity),
                        message, listener);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityName.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llContent;
        private TextView tvName;
        private TextView tvCurrentCity;
        private ImageView ivLocationIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            llContent = itemView.findViewById(R.id.ll_content);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCurrentCity = itemView.findViewById(R.id.tv_current_city);
            ivLocationIcon = itemView.findViewById(R.id.iv_location_icon);
        }
    }
}
