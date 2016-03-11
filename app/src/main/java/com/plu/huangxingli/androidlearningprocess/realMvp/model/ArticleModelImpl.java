package com.plu.huangxingli.androidlearningprocess.realMvp.model;

import android.database.Observable;

import com.plu.huangxingli.androidlearningprocess.realMvp.bean.Article;
import com.plu.huangxingli.androidlearningprocess.realMvp.interfaces.ArticleModelInterface;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by lenovo on 2016/3/11.
 */
public class ArticleModelImpl implements ArticleModelInterface{

    ArrayList<Article> articles=new ArrayList<>();
    @Override
    public List<Article> fetchArticles(final ArticleFetchListener articleFetchListener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<20;i++){
                    articles.add(new Article("article "+i));
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (articleFetchListener!=null)articleFetchListener.onFetched(articles);
            }
        }).start();


        return articles;
    }


    public interface ArticleFetchListener{
        void onFetched(List<Article> articles);
    }
}
