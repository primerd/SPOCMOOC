package cn.edu.tit.iservice.iserviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tit.bean.Admin;
import cn.edu.tit.common.ReadExcel;
import cn.edu.tit.idao.IAdminDao;
import cn.edu.tit.iservice.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	/**
	 * 申明Dao层
	 * */
	@Autowired
	private IAdminDao iAdminDao;
	
	
	@Override
	public String readExcelFile(MultipartFile file) {
		//创建处理EXCEL的类
        ReadExcel readExcel=new ReadExcel();
        //解析excel，获取上传的事件单
        List<Admin> adminList = null;
        int insertResult = 0;
		String insertMsg = "";
		try {
			adminList = readExcel.getExcelInfo(file);	
			for(Admin s :adminList) {
				iAdminDao.addAdmin(s);
				insertResult++;
				System.out.println(s.toString());
			}
			if(insertResult ==0) {
				insertMsg = "载入数据库失败";
			}else if(insertResult == adminList.size()){
				insertMsg = "全部载入数据库";
			}else {
				insertMsg = "部分载入数据库";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("接受excel表格中的数据失败！！！");
		}
		for(Admin s : adminList) {
        	System.out.println("打印excel中的数据"+s.toString());
        }
		return insertMsg;
	}

	@Override
	public void setCategory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyCategory() {
		// TODO Auto-generated method stub

	}

	/**
	 * 添加管理员信息
	 * */
	@Override
	public void addAdmin(Admin admin) {
		iAdminDao.addAdmin(admin);
	}

	@Override
	public void addUserInfo() {
		// TODO Auto-generated method stub

	}

}
