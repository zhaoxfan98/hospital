package cn.buu.edu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.buu.edu.bean.Doctor;
import cn.buu.edu.service.DoctorService;
import cn.buu.edu.utils.ResultEntity;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public ModelAndView getDotorPageByCond(String name,Integer department,@RequestParam(value="p",required=false,defaultValue="1") Integer pNum,HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("doctor/index");
		Doctor cond = new Doctor();
		cond.setName(name);
		cond.setDepartment(department);
		mv.addObject("doctor",cond);
		mv.addObject("requestUri", req.getRequestURI());
		mv.addObject("page", doctorService.getDotorPageByCond(name,department,pNum));
		return mv;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(Doctor doctor) {
		doctorService.save(doctor);
		return "redirect:/doctor/search";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.PUT)
	public String edit(Doctor doctor) {
		doctorService.edit(doctor);
		return "redirect:/doctor/search";
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView getDotorDetailById(@RequestParam("did")int did, @RequestParam("edit") boolean edit) {
		ModelAndView mv = new ModelAndView(edit?"doctor/edit":"doctor/look");
		mv.addObject("doctor", doctorService.getDotorDetailById(did));
		return mv;
	}
	
	@RequestMapping(value="/batchDelete",method=RequestMethod.DELETE)
	@ResponseBody
	public ResultEntity batchDelete(@RequestParam("ids[]") Integer[] ids){
		if(doctorService.batchDelete(ids)) {
			return ResultEntity.success("删除成功");
		}else {
			return ResultEntity.error("删除失败");
		}
	}
	
	@RequestMapping(value="/dept",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity catDept(@RequestParam("deptId")Integer deptId) {
		return ResultEntity.success("查询成功").put("dList", doctorService.getDeptDoctor(deptId));
	}
}
