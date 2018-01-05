package com.exercise.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exercise.domain.Product;
import com.exercise.domain.User;
import com.exercise.service.ProductService;

public class ServletAddShoppingcart extends HttpServlet {
//����Ʒ����ҳproductDetail.jsp������빺�ﳵ��������ҳ��
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���빺�ﳵʱ�ͽ����ݴ����session��map�У�������Ʒ������
		String pid = request.getParameter("pid");
		String countString = request.getParameter("count");
		Integer count =0;
		if(countString!= null&& !"".equals(countString)){
			count = Integer.parseInt(countString);
		}
		HttpSession session = request.getSession();
		Map<Product, Integer> myCart = (Map<Product, Integer>) session.getAttribute("myCart");
		if (pid != null && !"".equals(pid)) {
			ProductService service = new ProductService();
			Product product=null;
			try {
				product = service.showProductDetail(pid);
				System.out.println("�������Ʒ��"+product.getPname());
				System.out.println("������"+count);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (myCart != null) {// �����ﳵ��Ϊ�գ���������ﳵ����ͬ����Ʒ�����������ͬ����Ʒ��Ӽ�¼
				Set<Map.Entry<Product, Integer>> set=myCart.entrySet();
				Iterator<Map.Entry<Product, Integer>> iterator=set.iterator();
				boolean hasPro = false;
				while(iterator.hasNext()){
					Map.Entry<Product, Integer> it=iterator.next();
					if(it.getKey().getPid().equals(product.getPid())){
						System.out.println("cart��ͬ��Ʒ��-"+product.getPname()+",��Ʒ��-"+(it.getValue() + count));
						it.setValue(count+it.getValue());
						hasPro=true;
//						System.out.println(it.getValue());
					}/*else{
						myCart.put(it.getKey(), count);
					}*/
				}
				if (!hasPro) {
					myCart.put(product, count);
				}
				/**
				Set<Entry<Product,Integer>> set = cartPros.entrySet();
				Iterator<Entry<Product,Integer>> ite = set.iterator();
				boolean hasPro = false;
				while (ite.hasNext()) {
					Entry<Product, Integer> entry = ite.next();
					if (entry.getKey().getPid().equals(product.getPid())) {
						int num = entry.getValue();
						entry.setValue(num+1);
						hasPro=true;
					}
				}
				if (!hasPro) {
					cartPros.put(product, 1);
				}
*/
				/*for (Product p : myCart.keySet()) {
					//�����ﳵԭ�и���Ʒ�����������
					if (p.getPid().equals(product.getPid())) {
//						System.out.println("cart��ͬ��Ʒ��-"+product.getPname());
						myCart.put(p, myCart.get(p) + count);
//						System.out.println("cart��ͬ��Ʒ��-"+(myCart.get(p) + count));
//						System.out.println(myCart.get(p));
						session.setAttribute("myCart", myCart);
					}else{//�����ﳵû�и���Ʒ������Ӽ�¼
						myCart.put(product, count);
						session.setAttribute("myCart", myCart);
					}
				}*/
			} else {// �����ﳵΪ��,��Ӽ�¼
				myCart = new HashMap<>();
				myCart.put(product, count);
				session.setAttribute("myCart", myCart);
			}
		}
//		session.setAttribute("myCart", myCart);
		//���ﳵ����Ʒ����
		int size=0;
		if(myCart!=null&&myCart.size()!=0){
			for (Product p : myCart.keySet()) {
				size+=myCart.get(p);
				System.out.println(p.getPname()+":��Ʒ��:"+(myCart.get(p) + count));
			}
		}
		session.setAttribute("size", size);
		Cookie cookie=new Cookie("JSESSIONID",session.getId() );
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		request.getRequestDispatcher("/servletProductDetail?pid="+pid).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}