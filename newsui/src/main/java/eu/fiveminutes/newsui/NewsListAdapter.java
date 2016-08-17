package eu.fiveminutes.newsui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tinoba on 17.8.2016..
 */
public class NewsListAdapter extends ArrayAdapter<String>{
    public final ArrayList<String> textList;
    public NewsListAdapter(Context context, ArrayList<String> textList){
        super(context,R.layout.newsitem,textList);
        this.textList = textList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.newsitem, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.txtParagraph.setText(textList.get(position));

        return view;
    }
    static class ViewHolder {
        @BindView(R.id.txtParagraph)
        TextView txtParagraph;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
