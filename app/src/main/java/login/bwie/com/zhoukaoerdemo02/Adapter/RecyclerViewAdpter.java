package login.bwie.com.zhoukaoerdemo02.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import login.bwie.com.zhoukaoerdemo02.Bean.JsonBean;
import login.bwie.com.zhoukaoerdemo02.R;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/11/12 19:31
 */
public class RecyclerViewAdpter extends RecyclerView.Adapter<RecyclerViewAdpter.ImageViewHodler> {

    private  List<JsonBean.NewslistBean> list;
    private Context context;

    public RecyclerViewAdpter(Context context, List<JsonBean.NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ImageViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
        return new ImageViewHodler(view);

    }

    @Override
    public void onBindViewHolder(ImageViewHodler holder, int position) {

        ImageLoader.getInstance().displayImage(list.get(2).getPicUrl(),holder.itemimageView);
        holder.itemtextView.setText(list.get(position).getTitle());
        System.out.println("title  "+list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ImageViewHodler extends RecyclerView.ViewHolder {
        @InjectView(R.id.itemimage_view)
        ImageView itemimageView;
        @InjectView(R.id.itemtext_view)
        TextView itemtextView;
        public ImageViewHodler(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }


}
