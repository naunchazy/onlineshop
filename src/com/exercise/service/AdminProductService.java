package com.exercise.service;

import java.sql.SQLException;
import java.util.List;

import com.exercise.dao.AdminProductDao;
import com.exercise.domain.Product;
import com.exercise.domain.vo.Requirement;

import com.exercise.domain.vo.PageBean;

public class AdminProductService {

	public void addProductService(Product product) throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		dao.addProductDao(product);
		
	}

	public List<Product> showAllProductService() throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		List<Product> products=dao.showAllProductDao();
		return products;
	}

	public void DeleteSomeProductService(String[] delBoxes) throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		for (String pid : delBoxes) {
//			System.out.println(pid);
			dao.DeleteProductdao(pid);
		}
		
	}

	public void editorProductService(Product p) throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		dao.editorProductDao(p);
		
	}

	public Product editorShowProductService(String pid) throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		Product editProduct=dao.editorShowProductDao(pid);
		return editProduct;
	}

	public List<Product> searchProductService(Requirement requirement) throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		List<Product> products = dao.searchProductDao(requirement);
//		for (Product product : products) {
//			System.out.println(product.getPname());
//		}
		return products;
	}
	
//	public PageBean<Product> findProductsByPageBean(int currentPageInt,int productCount) throws SQLException {
//		//��ʹ��dao�����õ����ݵ�������
//		AdminProductDao dao=new AdminProductDao();
//		long productTotalCount1=(Long) dao.findProductTotalCount();
//		int productTotalCount=(int)productTotalCount1;
//		//�������ݵ��������ʹ�Servlet���ݹ�����ÿҳ��¼�������ó���ҳ��
//		int totalPage=(int) Math.ceil(productTotalCount*1.0/productCount);
//		//����ʼ�����ͼ�¼�������ݸ�dao�㣬���������ݿ����ҳ���Ҫ�����ݣ��Ա���ʾ��ҳ����
//		int intialIndex=(currentPageInt-1)*productCount;
//		AdminProductDao dao2=new AdminProductDao();
//		List<Product> pageData=dao2.showPageData(intialIndex,productCount);	
////		System.out.println(currentPageInt+"-"+productTotalCount+"-"+totalPage);
//		
//		PageBean<Product> pageBean=new PageBean<>();
//		pageBean.setCurrentPage(currentPageInt);
//		pageBean.setProductCount(productCount);
//		pageBean.setProductTotalCount(productTotalCount);
//		pageBean.setTotalPage(totalPage);
//		pageBean.setPageData(pageData);
//		return pageBean;
//	}

	public PageBean<Product> findProductsByPageBean(Requirement requirement, int currentPageInt, int productCount) throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		long productTotalCount1=(Long) dao.findProductTotalCount(requirement);
		int productTotalCount=(int)productTotalCount1;
		//�������ݵ��������ʹ�Servlet���ݹ�����ÿҳ��¼�������ó���ҳ��
		int totalPage=(int) Math.ceil(productTotalCount*1.0/productCount);
		//����ʼ�����ͼ�¼�������ݸ�dao�㣬���������ݿ����ҳ���Ҫ�����ݣ��Ա���ʾ��ҳ����
		int intialIndex=(currentPageInt-1)*productCount;
		AdminProductDao dao2=new AdminProductDao();
		List<Product> pageData=dao2.showPageData(requirement,intialIndex,productCount);	
//		System.out.println(currentPageInt+"-"+productTotalCount+"-"+totalPage);
		
		PageBean<Product> pageBean=new PageBean<>();
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setProductCount(productCount);
		pageBean.setProductTotalCount(productTotalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setPageData(pageData);
		return pageBean;
	}

}
