package com.plu.huangxingli.androidlearningprocess.realMvp.presenter;

import com.plu.huangxingli.androidlearningprocess.realMvp.bean.Article;
import com.plu.huangxingli.androidlearningprocess.realMvp.interfaces.ArticleModelInterface;
import com.plu.huangxingli.androidlearningprocess.realMvp.interfaces.ArticleViewInterface;
import com.plu.huangxingli.androidlearningprocess.realMvp.model.ArticleModelImpl;

import java.util.List;

/**
 * Created by lenovo on 2016/3/11.
 */
public class ArticlePrensenter {

    ArticleViewInterface mArticleView;
    ArticleModelInterface mArticleModel;

    public ArticlePrensenter(ArticleViewInterface mArticleView) {
        this.mArticleView = mArticleView;
        mArticleModel=new ArticleModelImpl();
    }

    public void fetchArticle(){
        mArticleView.showLoading();

        mArticleModel.fetchArticles(new ArticleModelImpl.ArticleFetchListener() {
            @Override
            public void onFetched(List<Article> articles) {
                mArticleView.hideLoading();
                mArticleView.showArticle(articles);
            }
        });

    }
}
