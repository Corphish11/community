package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

//	@Test
//	void contextLoads() {
//	}
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	    this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		alphaDao.find();
		AlphaDao alphaDao1 = applicationContext.getBean("Hibernate", AlphaDao.class);
		alphaDao1.find();
	}

	@Test
	public  void testApplicationInit(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);

	}

	@Test
	public void getSimpleDateFormat(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired
	@Qualifier("Hibernate")
	private AlphaDao alphaDao;

	@Test
	public void testDI(){
		System.out.println(alphaDao);
	}

}

