package com.plu.huangxingli.androidlearningprocess.realMvp.interfaces;

import com.plu.huangxingli.androidlearningprocess.realMvp.bean.Article;

import java.util.List;

/**
 * Created by lenovo on 2016/3/11.
 */
public interface ArticleViewInterface {
    void showArticle(List<Article> articles);
    void hideLoading();
    void showLoading();
}
