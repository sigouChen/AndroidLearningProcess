package com.plu.huangxingli.androidlearningprocess.realMvp.presenter;

import com.plu.huangxingli.androidlearningprocess.realMvp.bean.Article;
import com.plu.huangxingli.androidlearningprocess.realMvp.interfaces.ArticleModelInterface;
import com.plu.huangxingli.androidlearningprocess.realMvp.interfaces.ArticleViewInterface;
import com.plu.huangxingli.androidlearningprocess.realMvp.model.ArticleModelImpl;

import java.lang.ref.Reference;
import java.util.List;

/**
 * Created by lenovo on 2016/3/11.
 */
public class ArticlePrensenter extends BasePresenter<ArticleViewInterface> {

    //ArticleViewInterface mArticleView;
    ArticleModelInterface mArticleModel;

    public ArticlePrensenter() {
        mArticleModel=new ArticleModelImpl();
    }

    public void fetchArticle(){

        if (isViewAttached())getView().showLoading();

        mArticleModel.fetchArticles(new ArticleModelImpl.ArticleFetchListener() {
            @Override
            public void onFetched(List<Article> articles) {
               if (isViewAttached())getView().hideLoading();

                if (isViewAttached())getView().showArticle(articles);
            }
        });

    }
}
