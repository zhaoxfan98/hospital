package cn.buu.edu.service;

import com.github.pagehelper.PageInfo;

import cn.buu.edu.bean.Medicine;

public interface MedicineService {

	PageInfo<Medicine> getMedicinePage(Integer pNum);

	PageInfo<Medicine> getMedicinePageByCond(String name, Integer type, Integer pNum);

	Medicine getMedicineDetailById(String mid);

	void save(Medicine medicine);

	boolean batchDelete(String[] ids);

	void edit(Medicine medicine);

}
