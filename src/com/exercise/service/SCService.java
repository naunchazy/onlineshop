//package com.exercise.service;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import com.exercise.dao.SCDao;
//import com.exercise.dao.UserDao;
//import com.exercise.domain.Shoppingcart;
//
//public class SCService {
//	//��¼�û����빺�ﳵʱ���ж��ǸüӼ�¼���Ǽ�����
//	public void addDBSCService(int uid, String pid) throws SQLException {
//		SCDao dao=new SCDao();
//		Shoppingcart scRecord=dao.searchDBSCDao(uid,pid);
//		if(scRecord!=null){
//			dao.updateDBSCDao(uid,pid,scRecord.getCount());
//		}else{
//			dao.addDBSCDao(uid,pid);
//		}
//	}
//	//��¼�û��鿴���ﳵ��Ҫ�õ��û�ID����Ʒ������
//	public ShoppingcartRedord showDBSCService(int uid) throws SQLException {
//		
//		//�������ݿ���û���������Ʒ����Ʒ��������֪�û�ID
//		//�õ���Ʒ
//		List<String> userPids
//		for (String string : userPids) {
//			
//		}
//		return null;
//	}
//}
