package com.exercise.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.exercise.dao.ProductDao;
import com.exercise.dao.UserDao;
import com.exercise.domain.Product;

public class ProductService {

	public Product showProductDetail(String pid) throws SQLException {
		ProductDao dao=new ProductDao();
		Product productDetail=dao.showProductDetail(pid);
		return productDetail;
	}
	
	//得到商品pid和对应数量的map集合
	/*public Map<String,Integer> showSCProduct(String pidSum) {
//		String[] pidSums=null;
//		pidSums = pidSum.split("-");
//		System.out.println("pid:");
//		for (String pid : pidSums) {
//			System.out.print(pid+",");
//		}
//		System.out.println("List:");
//		for(int i=0;i<sc_products.size();i++){
//			System.out.print(sc_products.get(i).getPid()+",");
//		}
//		List<Product> sc_products=new ArrayList<>();
//		ProductDao dao=new ProductDao();
//		Product p=null;
//		if(pidSums!=null){
//			for (String pid : pidSums) {
//				p=dao.showProductDetail(pid);
//				if(p!=null){
//					sc_products.add(p);
//				}
//			}
//		}
//		Map<Product,Integer> product_count = new HashMap<>();
//		for (Product product : sc_products) {
//			Integer count=product_count.get(product);
//			product_count.put(product,(count==null)?1:count+1);
//		}
//		Set<Map.Entry<Product,Integer>> set=product_count.entrySet();
//		for (Map.Entry<Product,Integer> entry:set) {
//			System.out.println(entry.getValue());
//		}
//		return product_count;
		
		String[] pidSums=null;
		pidSums = pidSum.split("-");
		List<String> pids=new ArrayList<>();
		if(pidSums!=null){
			for (String pid : pidSums) {
				if(pid!=null){
					pids.add(pid);
				}
			}
		}
		Map<String,Integer> pid_count = new HashMap<>();
		for (String s : pids) {
			Integer count=pid_count.get(s);
			pid_count.put(s,(count==null)?1:count+1);
		}
		Set<Map.Entry<String,Integer>> set=pid_count.entrySet();
		for (Map.Entry<String,Integer> entry:set) {
			System.out.println(entry.getValue());
		}
		return pid_count;
	}*/
	public Map<Product,Integer> showSCProduct(String pidSum) throws SQLException {
		String[] pidSums=null;
		pidSums = pidSum.split("-");
		List<Product> sc_products=new ArrayList<>();
		ProductDao dao=new ProductDao();
		Product p=null;
		if(pidSums!=null){
			for (String pid : pidSums) {
				p=dao.showProductDetail(pid);
				if(p!=null){
					sc_products.add(p);
				}
			}
		}
		Map<Product,Integer> product_count = new HashMap<>();
		for (Product product : sc_products) {
			Integer count=product_count.get(product);
			product_count.put(product,(count==null)?1:count+1);
		}
//		Set<Map.Entry<Product,Integer>> set=product_count.entrySet();
//		for (Map.Entry<Product,Integer> entry:set) {
//			System.out.println(entry.getValue());
//		}
		return product_count;
		
	}

	
	/*public List<Product> showSCProducts(String pidSum) throws SQLException {
		String[] pidSums=null;
		pidSums = pidSum.split("-");
		ProductDao dao=new ProductDao();
		List<Product> sc_products=new ArrayList<>();
		for (String pid : pidSums) {
			Product product=dao.showProductDetail(pid);
			sc_products.add(product);
		}
		return sc_products;
	}*/

}
