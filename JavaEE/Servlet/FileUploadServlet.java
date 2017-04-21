package web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {

		public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//step1,创建一个DiskFileItemFactory对象
		//为解析器提供解析时的缺省的配置。
		DiskFileItemFactory dfif =
			new DiskFileItemFactory();
		//step2,创建一个解析器
		ServletFileUpload sfu = 
			new ServletFileUpload(dfif);
		//step3,使用解析器解析
		try {
			//FileItem对象封装了一个表单域当中的所有的
			//数据。
			List<FileItem> items = 
				sfu.parseRequest(request);
			//遍历items
			for(int i=0;i<items.size();i++){
				FileItem item = items.get(i);
				//要判断是一个普通的表单域还是
				//上传文件域
				if(item.isFormField()){
					//是一个普通的表单域
					String fieldName = item.getFieldName();
					String value = item.getString();
					System.out.println(
							fieldName + " " + value);
				}else{
					//上传文件域,要将文件保存在服务器端
					ServletContext sctx = 
						getServletContext();
					//获得实际部署时物理路径
					String path = sctx.getRealPath("upload");
					System.out.println("path:" + path);
					//获得上传文件的名称
					String fileName = item.getName();
					File file = new File(path + "//" + fileName);
					item.write(file);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

}
