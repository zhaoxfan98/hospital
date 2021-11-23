package cn.buu.edu.service;

import com.github.pagehelper.PageInfo;

import cn.buu.edu.bean.Register;

public interface RegisterService {

	PageInfo<Register> getRegisterPageByCond(String rid, String name, Integer department, Integer pNum);

	Register getDotorDetailById(String rid);

	void save(Register register);

	boolean batchDelete(String[] ids);

}
