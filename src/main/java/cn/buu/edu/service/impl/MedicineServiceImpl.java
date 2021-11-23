package cn.buu.edu.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.buu.edu.bean.Medicine;
import cn.buu.edu.bean.MedicineExample;
import cn.buu.edu.bean.MedicineExample.Criteria;
import cn.buu.edu.dao.MedicineMapper;
import cn.buu.edu.service.MedicineService;
import cn.buu.edu.utils.PageUtils;

@Service
public class MedicineServiceImpl implements MedicineService {
	
	@Autowired
	private MedicineMapper medicineMapper;

	@Override
	public PageInfo<Medicine> getMedicinePage(Integer pNum) {
		MedicineExample example = new MedicineExample();
		PageHelper.startPage(pNum, PageUtils.PAGE_SIZE);
		return new PageInfo<Medicine>(medicineMapper.selectByExample(example));
	}

	@Override
	public PageInfo<Medicine> getMedicinePageByCond(String name, Integer type, Integer pNum) {
		MedicineExample example = new MedicineExample();
		PageHelper.startPage(pNum, PageUtils.PAGE_SIZE);
		
		Criteria criteria = example.createCriteria();
		
		if(name != null && name.trim() != "") {
			criteria.andNameLike("%"+name+"%");
		}
		
		if(type!=null && type>0) {
			criteria.andTypeEqualTo(type);
		}
		
		return new PageInfo<Medicine>(medicineMapper.selectByExample(example));
	}


	@Override
	public void save(Medicine medicine) {
		medicineMapper.insert(medicine);
	}

	@Override
	public Medicine getMedicineDetailById(String mid) {
		return medicineMapper.selectByPrimaryKey(mid);
	}

	@Override
	public boolean batchDelete(String[] ids) {
		MedicineExample example = new MedicineExample();
		Criteria criteria = example.createCriteria();
		criteria.andMidIn(Arrays.asList(ids));
		return medicineMapper.deleteByExample(example) != 0;
	}

	@Override
	public void edit(Medicine medicine) {
		medicineMapper.updateByPrimaryKeySelective(medicine);
	}

}
