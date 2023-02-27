package com.model2.mvc.service.purchase.test;


import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.PurchaseService;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPurchase() throws Exception {
		
		Purchase purchase = new Purchase();	
		Product product = new Product();
		User user = new User();
		
		product.setProdNo(10006);
		user.setUserId("user02");
		
		//tran 코드는 자동
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("testtest");
		purchase.setReceiverPhone("8888");
		purchase.setDivyAddr("addrrrrr");
		purchase.setDivyRequest("요구사항");
		purchase.setTranCode("1");
		//구매 날짜 자동
		purchase.setDivyDate("23/02/22");
		
		System.out.println(purchase);
		
		purchaseService.addPurchase(purchase);
		
	}
	
	@Test
	public void testfindPurchase() throws Exception {
		Purchase purchase = new Purchase();
		
		System.out.println("확인용");
		purchase = purchaseService.getPurchase(1000);
		System.out.println(purchase);
		
		//Assert.assertEquals("SCOTT", purchase.getReceiverName());
	}

	
	//@Test
	public void testUpdateProduct() throws Exception {
		
		Purchase purchase = purchaseService.getPurchase(10007);
			
		System.out.println("확인용22322" + purchase);
		purchase.setPaymentOption("2");
		purchase.setReceiverName("userssss");
		purchase.setReceiverPhone("11112222");	
		purchase.setDivyAddr("44444");
		purchase.setDivyRequest("요구사하아앙");
		purchase.setDivyDate("2222-01-03");
			
		purchaseService.updatePurchase(purchase);

	}
	
	//@Test
	public void testGetPurchaseListAll() throws Exception {

		
		Search search = new Search();
		search.setPage(1);
		search.setPageSize(3);
		
		
		Map<String, Object> map = purchaseService.getPurchaseList(search);
		
		
		List<Object> list = (List<Object>) map.get("list");
		
		//Assert.assertEquals(3, list.size());

		// ==> console 확인
		System.out.println(map);
		System.out.println(list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = purchaseService.getPurchaseList(search);

		list = (List<Object>) map.get("list");
		//Assert.assertEquals(3, list.size());

		// ==> console 확인
		// System.out.println(list);

		totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);
	}
	
	//@Test
	public void testupdateTranCode() throws Exception {
		Purchase purchase = purchaseService.getPurchase(10002);
		
		purchase.setTranCode("3");
		
		purchaseService.updateTranCode(purchase);
	}

}