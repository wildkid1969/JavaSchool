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
		//step1,����һ��DiskFileItemFactory����
		//Ϊ�������ṩ����ʱ��ȱʡ�����á�
		DiskFileItemFactory dfif =
			new DiskFileItemFactory();
		//step2,����һ��������
		ServletFileUpload sfu = 
			new ServletFileUpload(dfif);
		//step3,ʹ�ý���������
		try {
			//FileItem�����װ��һ�������е����е�
			//���ݡ�
			List<FileItem> items = 
				sfu.parseRequest(request);
			//����items
			for(int i=0;i<items.size();i++){
				FileItem item = items.get(i);
				//Ҫ�ж���һ����ͨ�ı�����
				//�ϴ��ļ���
				if(item.isFormField()){
					//��һ����ͨ�ı���
					String fieldName = item.getFieldName();
					String value = item.getString();
					System.out.println(
							fieldName + " " + value);
				}else{
					//�ϴ��ļ���,Ҫ���ļ������ڷ�������
					ServletContext sctx = 
						getServletContext();
					//���ʵ�ʲ���ʱ����·��
					String path = sctx.getRealPath("upload");
					System.out.println("path:" + path);
					//����ϴ��ļ�������
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
