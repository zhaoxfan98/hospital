package cn.buu.edu.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.buu.edu.bean.Doctor;
import cn.buu.edu.bean.Medicine;
import cn.buu.edu.service.MedicineService;
import cn.buu.edu.utils.ResultEntity;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	
	@RequestMapping("/search")
	public ModelAndView getMedicinePage(String name,Integer type,@RequestParam(value="p",required=false,defaultValue="1") Integer pNum,HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("medicine/index");
		Medicine cond = new Medicine();
		cond.setName(name);
		cond.setType(type);
		mv.addObject("requestUri", req.getRequestURI());
		mv.addObject("medicine", cond);
		mv.addObject("page", medicineService.getMedicinePageByCond(name,type,pNum));
		return mv;
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView getDotorDetailById(@RequestParam("mid")String mid, @RequestParam("edit") boolean edit) {
		ModelAndView mv = new ModelAndView(edit?"medicine/edit":"medicine/look");
		mv.addObject("medicine",medicineService.getMedicineDetailById(mid));
		return mv;
	}
	
	@RequestMapping(value="/batchDelete",method=RequestMethod.DELETE)
	@ResponseBody
	public ResultEntity batchDelete(@RequestParam("ids[]") String[] ids){
		if(medicineService.batchDelete(ids)) {
			return ResultEntity.success("删除成功");
		}else {
			return ResultEntity.error("删除失败");
		}
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String add(Medicine medicine,MultipartFile photo, HttpSession session) throws IllegalStateException, IOException {
		String realPath = session.getServletContext().getRealPath("/Images");
		File directory = new File(realPath);
		if(!directory.exists()) {
			directory.mkdirs();
		}
		String originalFilename = UUID.randomUUID().toString().replaceAll("-", "") + photo.getOriginalFilename();
		
		if(photo.getSize()>0) {
			photo.transferTo(new File(directory+ "\\" + originalFilename));
		}
		medicine.setMid(UUID.randomUUID().toString().replaceAll("-", ""));
		medicine.setPicture(originalFilename);
		medicineService.save(medicine);
		
		return "redirect:/medicine/search";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(Medicine medicine,MultipartFile photo,String oldpicture, HttpSession session) throws IllegalStateException, IOException {
		if(!photo.isEmpty()) {
			String realPath = session.getServletContext().getRealPath("/Images");
			File directory = new File(realPath);
			if(!directory.exists()) {
				directory.mkdirs();
			}
			String originalFilename = UUID.randomUUID().toString().replaceAll("-", "") + photo.getOriginalFilename();
			
			if(photo.getSize()>0) {
				photo.transferTo(new File(directory+ "\\" + originalFilename));
			}
			medicine.setPicture(originalFilename);
		}else {
			medicine.setPicture(oldpicture);
		}
		
		medicineService.edit(medicine);
		
		return "redirect:/medicine/search";
	}
	
	
}
