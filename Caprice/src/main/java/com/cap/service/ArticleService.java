package com.cap.service;

import java.util.List;

import com.cap.entity.Article;

public interface ArticleService {
	//写文章
	public void writeArticle(Article article);
	//修改文章
	public void modifyArticle(Article article);
	//删除文章
	public void delArticle(int articleId);
	//查看文章
	public Article findArticle(int articleId);
	//根据articleTag查询文章
	List<Article> list(String articleTag);

}
