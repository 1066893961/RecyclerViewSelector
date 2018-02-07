package jxd.com.recyclerviewselecteddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxd.com.recyclerviewselecteddemo.Adapter.RecyclerViewAdapter;
import jxd.com.recyclerviewselecteddemo.Mode.RecyclerViewMode;
import rx.Observer;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView imageView;

    private List<RecyclerViewMode> recyclerViewModeList = new ArrayList<>();
    private RecyclerViewAdapter recyclerViewAdapter;

    private boolean isSelected = false;
    private Map<String, RecyclerViewMode> recyclerViewMap = new HashMap<>();
    public Observer<RecyclerViewMode> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        imageView = (ImageView) findViewById(R.id.imageView_selected);
        initData();
        initRecyclerView();
        initRecyclerViewAdapter();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected) {
                    isSelected = false;
                    imageView.setImageResource(R.mipmap.icon_circle_gray);
                    for (int i = 0; i < recyclerViewAdapter.getData().size(); i++) {
                        if (recyclerViewAdapter.getData().get(i).isCheck) {
                            recyclerViewAdapter.getData().get(i).isCheck = false;
                        }
                        recyclerViewMap.remove(recyclerViewAdapter.getData().get(i).No);
                    }
                } else {
                    isSelected = true;
                    imageView.setImageResource(R.mipmap.icon_circle_yellow);
                    for (int i = 0; i < recyclerViewAdapter.getData().size(); i++) {
                        if (!recyclerViewAdapter.getData().get(i).isCheck) {
                            recyclerViewAdapter.getData().get(i).isCheck = true;
                        }
                        recyclerViewMap.put(recyclerViewAdapter.getData().get(i).No, recyclerViewAdapter.getData().get(i));
                    }
                }
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });

        observer = new Observer<RecyclerViewMode>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RecyclerViewMode recyclerViewMode) {
                for (int i = 0; i < recyclerViewAdapter.getData().size(); i++) {
                    if (recyclerViewAdapter.getData().get(i).isCheck) {
                        recyclerViewMap.put(recyclerViewAdapter.getData().get(i).No, recyclerViewAdapter.getData().get(i));
                    } else {
                        recyclerViewMap.remove(recyclerViewAdapter.getData().get(i).No);
                    }
                }
                if (recyclerViewMap.size() == recyclerViewAdapter.getData().size()) {
                    isSelected = true;
                    imageView.setImageResource(R.mipmap.icon_circle_yellow);
                } else {
                    isSelected = false;
                    imageView.setImageResource(R.mipmap.icon_circle_gray);
                }
            }
        };
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * 初始化Adapter
     */
    private void initRecyclerViewAdapter() {
        recyclerViewAdapter = new RecyclerViewAdapter(this, recyclerViewModeList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 1; i <= 16; i++) {
            RecyclerViewMode recyclerViewMode = new RecyclerViewMode();
            recyclerViewMode.No = String.valueOf(i);
            recyclerViewMode.name = i + "只绵羊";
            recyclerViewMode.picUrl = "https://imgsa.baidu.com/forum/w%3D580%3B/sign=ca6fd4239a2397ddd679980c69b9b3b7/fcfaaf51f3deb48f23034c1afb1f3a292cf57888.jpg";
            recyclerViewModeList.add(recyclerViewMode);
        }
    }
}
