package com.exercise.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exercise.domain.Product;
import com.exercise.domain.User;
import com.exercise.service.OrderService;
import com.exercise.service.ProductService;

public class ServletSettlement extends HttpServlet {
/**���ﳵ����Ʒ���㣬��showShoppingcart.jsp�����ӹ��ﳵ��չʾҳ��õ�Ҫ�������Ʒ��pid
*����pid������ProductService��Dao���showProductDetail���õ�Ҫ�������Ʒproduct��
*�پ���OrderService��OrderDao����Ҫ�������Ʒ�������ݿ���
*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//�ж��û��Ƿ��¼����δ��¼������ת����¼ҳ�棬���е�¼������䶩����
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/loginTip.jsp");
		}else{
		 	request.setCharacterEncoding("utf-8");
			String oid=null;//Ϊÿ��������һ��������
//			String[] pids = request.getParameterValues("pid");
			//ע�⣺��������д�ġ�ѡ���name��delOne��Ӧ��дdelOne
			String[] pids = request.getParameterValues("delOne");
			String tip=null;
			if(pids!=null && !"".equals(pids)){//���������Ĳ����е�pid��Ϊ�գ�˵������Ʒ��Ҫ���㡣�����ɶ�������
				oid=UUID.randomUUID().toString();//������ɶ������
				
				Map<Product, Integer> myCart = (Map<Product, Integer>) session.getAttribute("myCart");
				ProductService service=new ProductService();
				Product product=null;//����pid�õ�Ҫ�������Ʒ
				OrderService orderService=new OrderService();
				for (String pid : pids) {
					try {
						product = service.showProductDetail(pid);//����pid�õ�Ҫ�������Ʒ
						
						for (Product p : myCart.keySet()) {//����myCart���ﳵ���ҵ�����Ʒ������
							if (p.equals(product)) {
								int count=myCart.get(p);
								orderService.addOrderService(oid,user.getUid(),p,count);
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				String buyMethod="notNow";
				session.setAttribute("buyMethod", buyMethod);
				request.getRequestDispatcher("/servletShowOrder?oid="+oid+"&uid="+user.getUid()).forward(request, response);
			}else{//��û�й�ѡҪ�������Ʒ��pidΪ�գ����Ƿ��ع��ﳵչʾ����ҳ����������ʾ
				tip="�㻹δѡ��Ҫ�������Ʒ��";
				request.setAttribute("tip",tip);
				request.getRequestDispatcher("/showShoppingcart.jsp").forward(request, response);
				//������response.sendRedirect�����ܽ�request���ݹ�ȥ����Ϊ����ͬһ�����󡣶��ǿͻ����ַ���һ�����󴫸������е�url
				//response.sendRedirect(request.getContextPath()+"/showShoppingcart.jsp");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}