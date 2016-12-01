package org.meruvian.workshop.jaxrs.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.meruvian.workshop.jaxrs.entity.News;
import org.meruvian.workshop.jaxrs.repository.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RestNewsService implements NewsService {
	@Inject
	private NewsRepository newsRepository;
	
	@Override
	public News getNewsById(long id) {
		return newsRepository.findById(id);
	}

	@Override
	public List<News> findNewsByTitle(String title) {
		return newsRepository.findByTitle(title);
	}

	@Override
	@Transactional
	public News saveNews(News news) {
		news.setId(0);
		news.setCreateDate(new Date());
		
		return newsRepository.save(news);
	}

	@Override
	@Transactional
	public News updateNews(long id, News news) {
		News ori = getNewsById(news.getId());
		if (ori != null) {
			ori.setTitle(news.getTitle());
			ori.setContent(news.getContent());
			ori.setCategory(news.getCategory());
		}
		
		return ori;
	}

	@Override
	@Transactional
	public void deleteNews(long id) {
		newsRepository.delete(id);
	}

}
