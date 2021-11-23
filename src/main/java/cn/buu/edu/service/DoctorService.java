package cn.buu.edu.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.buu.edu.bean.Doctor;

public interface DoctorService {

	public void save(Doctor doctor);

	public Doctor getDotorDetailById(int did);

	public void edit(Doctor doctor);

	public PageInfo<Doctor> getDotorPage(Integer pNum);

	public PageInfo<Doctor> getDotorPageByCond(String name, Integer department, Integer pNum);

	public boolean batchDelete(Integer[] ids);

	public List<Doctor> getDeptDoctor(Integer deptId);

}
