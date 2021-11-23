package cn.buu.edu.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.buu.edu.bean.Register;
import cn.buu.edu.bean.RegisterExample;
import cn.buu.edu.bean.RegisterExample.Criteria;
import cn.buu.edu.dao.DoctorMapper;
import cn.buu.edu.dao.RegisterMapper;
import cn.buu.edu.service.RegisterService;
import cn.buu.edu.utils.PageUtils;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private RegisterMapper registerMapper;
	
	@Autowired
	private DoctorMapper doctorMapper;

	@Override
	public PageInfo<Register> getRegisterPageByCond(String rid, String name, Integer department, Integer pNum) {
		RegisterExample registerExample = new RegisterExample();
		PageHelper.startPage(pNum,PageUtils.PAGE_SIZE);
		Criteria criteria = registerExample.createCriteria();
		
		if(rid!=null && rid.trim() != "") {
			criteria.andRidLike("%"+rid+"%");
		}
		if(name!=null && name.trim() != "") {
			criteria.andNameLike("%"+name+"%");
		}
		if(department != null && department != 0) {
			criteria.andDepartmentEqualTo(department);
		}
		
		List<Register> list = registerMapper.selectByExample(registerExample);
		
		for (Register register : list) {
			register.setDoctor(doctorMapper.selectByPrimaryKey(register.getDid()));
		}
		
		return new PageInfo<Register>(list);
	}

	@Override
	public Register getDotorDetailById(String rid) {
		Register r = registerMapper.selectByPrimaryKey(rid);
		r.setDoctor(doctorMapper.selectByPrimaryKey(r.getDid()));
		return r;
	}

	@Override
	public void save(Register register) {
		registerMapper.insert(register);
	}

	@Override
	public boolean batchDelete(String[] ids) {
		RegisterExample registerExample = new RegisterExample();
		
		Criteria criteria = registerExample.createCriteria();
		criteria.andRidIn(Arrays.asList(ids));
		
		return registerMapper.deleteByExample(registerExample) != 0;
	}

}
