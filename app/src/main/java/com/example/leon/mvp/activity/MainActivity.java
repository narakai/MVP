package com.example.leon.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.leon.mvp.R;
import com.example.leon.mvp.entity.BookEntity;
import com.example.leon.mvp.presenter.impl.BookPresenterImpl;
import com.example.leon.mvp.ui.customwidget.LoadingDialog;
import com.example.leon.mvp.view.BookView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BookView{
    private BookPresenterImpl mBookPresenter;
    private List<BookEntity> mBookEntities;
    private LoadingDialog mLoadingDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private BookContentAdapter mBookContentAdapter;
    private int mCurrentDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void initData() {
        mBookPresenter = new BookPresenterImpl(this);
        mCurrentDir = TYPE_DIR._START;
        mLoadingDialog = new LoadingDialog(this, false);
        mBookEntities = new ArrayList<>();
        mBookContentAdapter = new BookContentAdapter();
        getIntentData();
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_load_book);
        mRecyclerView = (RecyclerView) findViewById(R.id.rlv_main_book);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
    }

    @Override
    protected void initEvent() {
        mToolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.mipmap.abc_ic_ab_back_mtrl_am_alpha));
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager m = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // get last visible item
                    int lastVisibleItem = m.findLastCompletelyVisibleItemPosition();
                    // get all item count
                    int itemCount = m.getItemCount();
                    if (lastVisibleItem == itemCount - 1) {
                        mCurrentDir = TYPE_DIR._END;
                        mLoadingDialog.show();
                        refreshData();
                    }
                }
            }
        });
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.skin_background_black)
                , (getResources().getColor(R.color.skin_colorAccent_Amber))
                , (getResources().getColor(R.color.skin_colorAccent_blue))
                , (getResources().getColor(R.color.skin_colorAccent_mred)));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurrentDir = TYPE_DIR._START;
                refreshData();
            }
        });
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        refreshData();
    }

    private void refreshData() {
        if (null != mSwipeRefreshLayout) {
            mSwipeRefreshLayout.setRefreshing(true);
            mRecyclerView.setAdapter(mBookContentAdapter);
            //dummy data
            BookEntity bookEntity1 = new BookEntity();
            bookEntity1.setBookname("test");
            bookEntity1.setLocalpath("http://120.25.175.139:8001/static/images/full/4eb65b11a77333a779defed9adfe3bb53e4d115a.jpg");
            mBookEntities.add(bookEntity1);
            mBookContentAdapter.notifyDataSetChanged();
            mBookPresenter.getBookDetail();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                //do something
            }
        }
    }


    @Override
    public void showLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void bindBookData(BookEntity bookEntity) {
        stopRefresh();
        if(null != bookEntity){
            mBookEntities.add(bookEntity);
        }
        mBookContentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String error) {
        stopRefresh();
        mLoadingDialog.dismiss();
    }

    @Override
    public void dismissLoading() {
        stopRefresh();
        mLoadingDialog.dismiss();
    }

    @Override
    public void completed() {
        stopRefresh();
        mLoadingDialog.dismiss();
    }

    private void stopRefresh(){
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    class BookContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = new BookContentVH(LayoutInflater.from(MainActivity.this)
            .inflate(R.layout.item_book_content, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            BookContentVH bookContentVH = (BookContentVH) holder;
            BookEntity bookEntity = mBookEntities.get(position);
            if(null != bookEntity){
                String bookName = bookEntity.getBookname();
                String localpath = bookEntity.getLocalpath();

                if(!TextUtils.isEmpty(bookName)){
                    bookContentVH.textView.setText(bookName);
                }
                if(!TextUtils.isEmpty(localpath)){
                    Glide.with(MainActivity.this)
                            .load(localpath)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .crossFade()
                            .placeholder(R.mipmap.bg_de)
                            .error(R.mipmap.bg_no_pic)
                            .into(bookContentVH.imageView);
                }
            }
        }

        @Override
        public int getItemCount() {
            return mBookEntities.size();
        }

        class BookContentVH extends RecyclerView.ViewHolder{
            TextView textView;
            ImageView imageView;

            public BookContentVH(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv_item_book_name);
                imageView = (ImageView) itemView.findViewById(R.id.iv_item_book_image);
            }
        }
    }

    static class TYPE_DIR {
        public static final int _START = 43;
        public static final int _END = 44;
    }
}
