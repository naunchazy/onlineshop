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
/**购物车的商品结算，从showShoppingcart.jsp来。从购物车的展示页面得到要结算的商品的pid
*根据pid，调用ProductService和Dao层的showProductDetail，得到要结算的商品product。
*再经过OrderService和OrderDao，将要结算的商品放入数据库中
*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//判断用户是否登录，若未登录，则跳转到登录页面，若有登录，则分配订单号
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/loginTip.jsp");
		}else{
		 	request.setCharacterEncoding("utf-8");
			String oid=null;//为每个订单配一个订单号
//			String[] pids = request.getParameterValues("pid");
			//注意：不是这样写的。选框的name是delOne，应该写delOne
			String[] pids = request.getParameterValues("delOne");
			String tip=null;
			if(pids!=null && !"".equals(pids)){//若传过来的参数中的pid不为空，说明有商品需要结算。则生成订单详情
				oid=UUID.randomUUID().toString();//随机生成订单编号
				
				Map<Product, Integer> myCart = (Map<Product, Integer>) session.getAttribute("myCart");
				ProductService service=new ProductService();
				Product product=null;//根据pid得到要结算的商品
				OrderService orderService=new OrderService();
				for (String pid : pids) {
					try {
						product = service.showProductDetail(pid);//根据pid得到要结算的商品
						
						for (Product p : myCart.keySet()) {//遍历myCart购物车，找到该商品和数量
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
			}else{//若没有勾选要结算的商品，pid为空，则还是返回购物车展示详情页，并给出提示
				tip="你还未选择要结算的商品！";
				request.setAttribute("tip",tip);
				request.getRequestDispatcher("/showShoppingcart.jsp").forward(request, response);
				//若是用response.sendRedirect，则不能将request传递过去。因为不是同一次请求。而是客户端又发了一次请求传给括号中的url
				//response.sendRedirect(request.getContextPath()+"/showShoppingcart.jsp");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}