package com.plu.huangxingli.androidlearningprocess.realMvp.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.realMvp.bean.Article;
import com.plu.huangxingli.androidlearningprocess.realMvp.interfaces.ArticleViewInterface;
import com.plu.huangxingli.androidlearningprocess.realMvp.presenter.ArticlePrensenter;

import java.util.List;

public class ArticleListActivity extends AppCompatActivity implements ArticleViewInterface{

    private ListView listView;
    private LayoutInflater inflater;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inflater = LayoutInflater.from(ArticleListActivity.this);

        listView = (ListView) findViewById(R.id.listview);
        ArticlePrensenter articlePrensenter=new ArticlePrensenter(this);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        articlePrensenter.fetchArticle();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace dwith your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void showArticle(final List<Article> articles) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter<Article> adapter = new ArrayAdapter<Article>(ArticleListActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, articles) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        if (convertView == null) {
                            convertView = inflater.inflate(R.layout.item, null);
                        }
                        TextView textview = (TextView) convertView.findViewById(R.id.textview);
                        textview.setText(articles.get(position).getName());
                        return convertView;
                    }
                };

                listView.setAdapter(adapter);
            }
        });

    }

    @Override
    public void hideLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
