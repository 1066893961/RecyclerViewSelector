package jxd.com.recyclerviewselecteddemo.Adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jxd.com.recyclerviewselecteddemo.MainActivity;
import jxd.com.recyclerviewselecteddemo.Mode.RecyclerViewMode;
import jxd.com.recyclerviewselecteddemo.R;

/**
 * Created by 46123 on 2018/2/1.
 * RecyclerViewAdapter
 */

public class RecyclerViewAdapter extends BaseQuickAdapter<RecyclerViewMode, BaseViewHolder> {

    MainActivity mainActivity;

    public RecyclerViewAdapter(MainActivity mainActivity, @Nullable List<RecyclerViewMode> data) {
        super(R.layout.listitem_recyclerview, data);
        this.mainActivity = mainActivity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final RecyclerViewMode item) {
        helper.setText(R.id.textView_name, item.name);
        Glide.with(helper.getView(R.id.imageView_pic).getContext()).load(item.picUrl).into((ImageView) helper.getView(R.id.imageView_pic));
        helper.getView(R.id.imageView_selected).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.isCheck) {
                    item.isCheck = false;
                    helper.setImageResource(R.id.imageView_selected, R.mipmap.icon_circle_gray);
                } else {
                    item.isCheck = true;
                    helper.setImageResource(R.id.imageView_selected, R.mipmap.icon_circle_yellow);
                }
//                Observable<RecyclerViewMode> observable = Observable.create(new Observable.OnSubscribe<RecyclerViewMode>() {
//                    @Override
//                    public void call(Subscriber<? super RecyclerViewMode> subscriber) {
//                        subscriber.onNext(item);
//                        subscriber.onCompleted();
//                    }
//                });
//                observable.subscribe(mainActivity.observer);
                mListener.onSelectorLister(item);
            }
        });
        if (item.isCheck) {
            helper.setImageResource(R.id.imageView_selected, R.mipmap.icon_circle_yellow);
        } else {
            helper.setImageResource(R.id.imageView_selected, R.mipmap.icon_circle_gray);
        }
    }
    private SelectedListener mListener;
    public void setSelectedListener(SelectedListener mListener){
        this.mListener = mListener;
    }
    public interface SelectedListener{
        void onSelectorLister(RecyclerViewMode mode);
    }
}
