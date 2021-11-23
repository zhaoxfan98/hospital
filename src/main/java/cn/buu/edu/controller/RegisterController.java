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
import cn.buu.edu.bean.Register;
import cn.buu.edu.service.RegisterService;
import cn.buu.edu.utils.ResultEntity;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public ModelAndView getRegisterPageByCond(String rid,String name,Integer department,@RequestParam(value="p",required=false,defaultValue="1") Integer pNum,HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("register/index");
		Register cond = new Register();
		cond.setRid(rid);
		cond.setName(name);
		cond.setDepartment(department);
		mv.addObject("r",cond);
		mv.addObject("requestUri", req.getRequestURI());
		mv.addObject("page", registerService.getRegisterPageByCond(rid,name,department,pNum));
		return mv;
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView getDotorDetailById(@RequestParam("rid")String rid, @RequestParam("edit") boolean edit) {
		ModelAndView mv = new ModelAndView(edit?"register/edit":"register/look");
		
		mv.addObject("register", registerService.getDotorDetailById(rid));
		return mv;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(Register register) {
		registerService.save(register);
		return "redirect:/register/search";
	}
	
	@RequestMapping(value="/batchDelete",method=RequestMethod.DELETE)
	@ResponseBody
	public ResultEntity batchDelete(@RequestParam("ids[]") String[] ids){
		if(registerService.batchDelete(ids)) {
			return ResultEntity.success("删除成功");
		}else {
			return ResultEntity.error("删除失败");
		}
	}

}
