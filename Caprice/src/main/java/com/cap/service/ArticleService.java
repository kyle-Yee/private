package com.cap.service;

import java.util.List;

import com.cap.entity.Article;

public interface ArticleService {
	//д����
	public void writeArticle(Article article);
	//�޸�����
	public void modifyArticle(Article article);
	//ɾ������
	public void delArticle(int articleId);
	//�鿴����
	public Article findArticle(int articleId);
	//����articleTag��ѯ����
	List<Article> list(String articleTag);

}
