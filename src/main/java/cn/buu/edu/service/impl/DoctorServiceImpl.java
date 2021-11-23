package cn.buu.edu.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.PagerUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.buu.edu.bean.Doctor;
import cn.buu.edu.bean.DoctorExample;
import cn.buu.edu.bean.DoctorExample.Criteria;
import cn.buu.edu.dao.DoctorMapper;
import cn.buu.edu.service.DoctorService;
import cn.buu.edu.utils.PageUtils;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorMapper doctorMapper;
	
	@Override
	public void save(Doctor doctor) {
		doctorMapper.insert(doctor);
	}

	@Override
	public Doctor getDotorDetailById(int did) {
//		DoctorExample doctorExample = new DoctorExample();
//		
//		Criteria criteria = doctorExample.createCriteria();
//		criteria.andDidEqualTo(did);
//		
//		List<Doctor> list = doctorMapper.selectByExample(doctorExample);
//		
//		if(list.size()>0) {
//			return list.get(0);
//		}
		
		return doctorMapper.selectByPrimaryKey(did);
	}

	@Override
	public void edit(Doctor doctor) {
		DoctorExample doctorExample = new DoctorExample();
		doctorMapper.updateByPrimaryKeySelective(doctor);
	}

	@Override
	public PageInfo<Doctor> getDotorPage(Integer pNum) {
		DoctorExample doctorExample = new DoctorExample();
		PageHelper.startPage(pNum,PageUtils.PAGE_SIZE);
		return new PageInfo<Doctor>(doctorMapper.selectByExample(doctorExample));
	}

	@Override
	public PageInfo<Doctor> getDotorPageByCond(String name, Integer department, Integer pNum) {
		DoctorExample doctorExample = new DoctorExample();
		PageHelper.startPage(pNum,PageUtils.PAGE_SIZE);
		Criteria criteria = doctorExample.createCriteria();
		if(name!=null && name.trim() != "") {
			criteria.andNameLike("%"+name+"%");
		}
		if(department != null && department != 0) {
			criteria.andDepartmentEqualTo(department);
		}
		return new PageInfo<Doctor>(doctorMapper.selectByExample(doctorExample));
	}

	@Override
	public boolean batchDelete(Integer[] ids) {
		DoctorExample doctorExample = new DoctorExample();
		
		Criteria criteria = doctorExample.createCriteria();
		criteria.andDidIn(Arrays.asList(ids));
		
		return doctorMapper.deleteByExample(doctorExample) != 0;
	}

	@Override
	public List<Doctor> getDeptDoctor(Integer deptId) {
		DoctorExample doctorExample = new DoctorExample();
		Criteria criteria = doctorExample.createCriteria();
		criteria.andDepartmentEqualTo(deptId);
		return doctorMapper.selectByExample(doctorExample);
	}

}
