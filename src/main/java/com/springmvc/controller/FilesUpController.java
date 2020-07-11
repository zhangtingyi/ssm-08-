package com.springmvc.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FilesUpController {
	
	@RequestMapping(value="indexs")
	public String indexs(){
		return "filesup";
	}
	
	@RequestMapping("filesup.do")
	public String filesup(@RequestParam MultipartFile[] files,HttpServletRequest request) throws Exception{

		for (MultipartFile mul : files) {
		//获取服务器路径
		String pathroot = request.getSession().getServletContext().getRealPath("/upload");
		//定义文件路径
		String path = null;
		File file = null;
		//判断是否有上传文件
		if (!mul.isEmpty()) {
			//以UUID做为文件名
			String prefix = UUID.randomUUID() + "";
			//获取文件类型
			String contentType = mul.getContentType();
			//定义文件后缀
			String suffix = contentType.substring(contentType.indexOf("/") + 1);
			//拼接路径
			path = pathroot+"\\" + prefix + "." + suffix;
			System.out.println(path);
			file = new File(path);
			//判断路径是否存在，不存在就创建一个
			if(!file.exists()){
				file.getParentFile().mkdirs();
			}
			//写出文件
			mul.transferTo(file);
			}else{
				return "error";
			}
		}
		return "success";
	}


}
