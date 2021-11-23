package cn.buu.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buu.edu.utils.ValidateCode;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/code")
public class CodeController {

    @RequestMapping(value="/getCode")
    public void getCode(@RequestParam(value = "time") String time, HttpServletRequest request, HttpServletResponse response) {
        ValidateCode code = new ValidateCode(100, 30, 4, 30, 25, "validateCode");
        code.getCode(request, response);
    }
    
    @RequestMapping(value="/checkCode",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> checkCode(@RequestParam(value = "code") String code, HttpSession session) {
    	String sCode = (String) session.getAttribute("validateCode");
    	session.removeAttribute("validateCode");
    	Map<String,Object> msg = new HashMap<String, Object>();
    	
    	if(sCode.equalsIgnoreCase(code)) {
    		msg.put("statusCode", 200);
    	}else {
    		msg.put("statusCode", 500);
    	}
		return msg;
    }
    
}
