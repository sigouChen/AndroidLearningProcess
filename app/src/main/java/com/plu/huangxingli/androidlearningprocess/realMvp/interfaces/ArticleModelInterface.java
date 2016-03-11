package com.plu.huangxingli.androidlearningprocess.realMvp.interfaces;

import com.plu.huangxingli.androidlearningprocess.realMvp.bean.Article;
import com.plu.huangxingli.androidlearningprocess.realMvp.model.ArticleModelImpl;

import java.util.List;

/**
 * Created by lenovo on 2016/3/11.
 */
public interface ArticleModelInterface {
    public List<Article> fetchArticles(ArticleModelImpl.ArticleFetchListener articleFetchListener);






}
