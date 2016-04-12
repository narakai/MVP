package com.example.leon.mvp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leon.mvp.R;
import com.example.leon.mvp.entity.BookEntity;
import com.example.leon.mvp.presenter.impl.BookPresenterImpl;
import com.example.leon.mvp.view.BookView;

import java.util.List;

public class MainActivity extends BaseActivity implements BookView{
    private TextView textView;
    private BookPresenterImpl bookPresenter;
    private List<BookEntity> bookEntityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void initData() {
        bookPresenter = new BookPresenterImpl(this);
    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.myText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (null != fab) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Book name is " + bookEntityList.get(0).getBookname(), Toast.LENGTH_SHORT).show();
                    textView.setText(bookEntityList.get(1).getBookname());
                }
            });
        }
    }

    @Override
    protected void initEvent() {
        bookPresenter.getBookDetail();
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

    @Override
    public void bindBookEntityDetail(List<BookEntity> bookEntityDetail) {
        bookEntityList = bookEntityDetail;
    }
}
