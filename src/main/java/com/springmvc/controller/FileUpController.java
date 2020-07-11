package com.springmvc.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileUpController {
	
	@RequestMapping(value="index")
	public String index(){
		return "fileup";
	}
	
	@RequestMapping(value="fileup.do")
	public String fileup(MultipartFile fileup,HttpServletRequest request) throws Exception{
		//获取服务器路径
		String pathroot = request.getSession().getServletContext().getRealPath("/up");
		//判断是否有上传文件
		if (!fileup.isEmpty()) {
			//以UUID值做为文件名
			String prefix = UUID.randomUUID() + "";
			//获取文件类型
			String contentType = fileup.getContentType();
			//定义文件后缀
			//String suffix = contentType.substring(contentType.indexOf("/") + 1);



			String originalFilename= fileup.getOriginalFilename();
			String name=fileup.getName();

			String suffix = originalFilename.substring((name.lastIndexOf(",")+1);
			//拼接路径
			String path = pathroot+"\\" + prefix + "." + suffix;
			File file = new File(path);
			//判断路径是否存在，不存在就创建一个
			if(!file.exists()){
				file.getParentFile().mkdirs();
			}
			//写出文件
			//MultipartFile的transferTo(File dest)方法转存文件到指定的路径
			fileup.transferTo(file);
			return "success";
		}else{
			return "error";
		}
	}

}
