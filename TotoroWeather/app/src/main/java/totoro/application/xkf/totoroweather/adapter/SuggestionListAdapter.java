package totoro.application.xkf.totoroweather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

import totoro.application.xkf.totoroweather.R;
import totoro.application.xkf.totoroweather.model.Suggestion;
import totoro.application.xkf.totoroweather.util.ImageSelector;
import totoro.application.xkf.totoroweather.util.LogUtil;

public class SuggestionListAdapter extends RecyclerView.Adapter<SuggestionListAdapter.ViewHolder> {
    private Suggestion mSuggestion;

    public SuggestionListAdapter(Suggestion suggestion) {
        super();
        mSuggestion = suggestion;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, String> map = mSuggestion.getMap();
        String[] types = mSuggestion.getTypes();
        holder.ivSuggestionIcon.setImageResource(ImageSelector.selectSuggestionIcon(position));
        holder.tvSuggestionType.setText(types[position]);
        holder.tvSuggestionDetail.setText(map.get(types[position]).split("&")[1]);
        holder.tvSuggestionAbout.setText(map.get(types[position]).split("&")[0]);
    }

    @Override
    public int getItemCount() {
        return mSuggestion.getTypes().length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivSuggestionIcon;
        private TextView tvSuggestionAbout;
        private TextView tvSuggestionDetail;
        private TextView tvSuggestionType;

        public ViewHolder(View itemView) {
            super(itemView);
            ivSuggestionIcon = itemView.findViewById(R.id.iv_suggestion_icon);
            tvSuggestionAbout = itemView.findViewById(R.id.tv_suggestion_about);
            tvSuggestionDetail = itemView.findViewById(R.id.tv_suggestion_detail);
            tvSuggestionType = itemView.findViewById(R.id.tv_suggestion_type);
        }
    }
}
