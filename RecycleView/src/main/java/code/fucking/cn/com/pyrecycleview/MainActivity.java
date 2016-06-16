package code.fucking.cn.com.pyrecycleview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import code.fucking.cn.com.pyrecycleview.adapter.HomeAdapter;
import code.fucking.cn.com.pyrecycleview.animators.SlideInRightAnimator;
import code.fucking.cn.com.pyrecycleview.divider_decoration.DividerGridDecoration;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HomeAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new HomeAdapter(this,initData());
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerGridDecoration(this));
        recyclerView.setItemAnimator(new SlideInRightAnimator());
        mAdapter.setOnItemClickListener(new HomeAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(View view, int position) {
                mAdapter.addData(position);
            }
        });
        mAdapter.setOnItemLongClickListener(new HomeAdapter.OnRecyclerItemLongClickListener() {
            @Override
            public void onRecyclerItemLongClick(View view, int position) {
                mAdapter.removeData(position);
            }
        });
    }

    protected ArrayList<String> initData()
    {
        ArrayList<String> mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
        return mDatas;
    }

}
