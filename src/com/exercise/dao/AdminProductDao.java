package com.exercise.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.exercise.domain.Product;
import com.exercise.domain.vo.Requirement;

import utils.C3P0Utils;

public class AdminProductDao {

	public void addProductDao(Product product) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql,product.getPid(),product.getPname(),
				product.getMarket_price(),product.getShop_price(),
				product.getPimage(),product.getPdate(),
				product.getIs_hot(),product.getPdesc(),
				product.getPflag(),product.getCid());
		
	}

	public List<Product> showAllProductDao() throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product";
		List<Product> products=qr.query(sql,new BeanListHandler<Product>(Product.class));
		return products;
	}

	public void DeleteProductdao(String pid) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="delete from product where pid=?";
		qr.update(sql,pid);
	}

	/*public void editorProductDao(String pid) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update * from product where pid=?";
		qr.update(sql,pid);
		
	}*/
	public void editorProductDao(Product p) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update product set pname=?,market_price=?,shop_price=?,"
				+ "pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
		qr.update(sql,p.getPname(),p.getMarket_price(),p.getShop_price(),p.getPimage(),p.getPdate(),p.getIs_hot(),p.getPdesc(),p.getPflag(),p.getCid(),p.getPid());	
	}

	public Product editorShowProductDao(String pid) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product where pid=?";
		Product editProduct=qr.query(sql, new BeanHandler<Product>(Product.class),pid);
		return editProduct;
	}

	public List<Product> searchProductDao(Requirement requirement) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product where 1=1";
		List<String> require=new ArrayList<>();//将搜索项中的条件放入require数组中
		//若商品名搜索项不为空，则因将该判断语句加进sql中查询，且将其作为条件放入query方法中
		if(requirement!=null&&!"".equals(requirement)){
			if(requirement.getPname()!=null&&!"".equals(requirement.getPname().trim())){
				sql+=" and pname like ?";
				require.add("%"+requirement.getPname().trim()+"%");
			}
			if(requirement.getIs_hot()!=null&&!"".equals(requirement.getIs_hot().trim())){
				sql+=" and is_hot=?";
				require.add(requirement.getIs_hot());
			}
			if(requirement.getCid()!=null&&!"".equals(requirement.getCid().trim())){
				sql+=" and cid=?";
				require.add(requirement.getCid());
			}
		}
		List<Product> products=qr.query(sql, new BeanListHandler<Product>(Product.class),require.toArray());
		return products;
	}

//	public Object findProductTotalCount() throws SQLException {
//		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
//		String sql="select count(*) from product";
//		return qr.query(sql, new ScalarHandler());
//	}
//
//	public List<Product> showPageData(int intialIndex, int productCount) throws SQLException {
//		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
//		String sql="select * from product limit ?,?";
//		return qr.query(sql, new BeanListHandler<Product>(Product.class),intialIndex,productCount);
//		
//	}
	
	public Object findProductTotalCount(Requirement requirement) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select count(*) from product where 1=1";
		List<String> require=new ArrayList<>();
		if(requirement!=null&&!"".equals(requirement)){
			if(requirement.getPname()!=null&&!"".equals(requirement.getPname().trim())){
				sql+=" and pname like ?";
				require.add("%"+requirement.getPname().trim()+"%");
			}
			if(requirement.getIs_hot()!=null&&!"".equals(requirement.getIs_hot())){
				sql+=" and is_hot=?";
				require.add(requirement.getIs_hot());
			}
			if(requirement.getCid()!=null&&!"".equals(requirement.getCid())){
				sql+=" and cid=?";
				require.add(requirement.getCid());
			}
		}
		return qr.query(sql, new ScalarHandler(),require.toArray());
		
	}
	
	public List<Product> showPageData(Requirement requirement,int intialIndex, int productCount) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product where 1=1";
		List<String> require=new ArrayList<>();
		if(requirement!=null&&!"".equals(requirement)){
			if(requirement.getPname()!=null&&!"".equals(requirement.getPname().trim())){
				sql+=" and pname like ?";
				require.add("%"+requirement.getPname()+"%");
			}
			if(requirement.getIs_hot()!=null&&!"".equals(requirement.getIs_hot())){
				sql+=" and is_hot=?";
				require.add(requirement.getIs_hot());
			}
			if(requirement.getCid()!=null&&!"".equals(requirement.getCid())){
				sql+=" and cid=?";
				require.add(requirement.getCid());
			}
		}
		sql=sql+" limit "+intialIndex+","+productCount;
		return qr.query(sql, new BeanListHandler<Product>(Product.class),require.toArray());
		
	}


}
