package servlets;


/**
 * 
 * �������ڴ���༭��ͼƬ�ϴ�����
 * �ڴ��������ĵ�url����ʱû��
 * ��������Ժ���������
 * 
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@SuppressWarnings("serial")
public class ImageUploadServlet extends HttpServlet{
	
	@SuppressWarnings("rawtypes")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//�����ļ���ʶ
		
		resp.setContentType("text/html;charset=utf-8");
		ServletContext sc=this.getServletContext();
		String tempFile="/temp";
		String filePath="/images/tiezi";
		tempFile=sc.getRealPath(tempFile);
		filePath=sc.getRealPath(filePath);
		PrintWriter out=resp.getWriter();
		try {
			DiskFileItemFactory df=new DiskFileItemFactory();
			//������Ӳ��д����ʱ���õĻ������Ĵ�С
			df.setSizeThreshold(4*1024); //4K
			//������ʱĿ¼
			df.setRepository(new File(tempFile));
			
			//����һ���ϴ���
			ServletFileUpload sf=new ServletFileUpload(df);
			sf.setSizeMax(4*1024*1024);
			List items=sf.parseRequest(req);
			Iterator iter=items.iterator();
			String filename=null;
			while(iter.hasNext()){
				FileItem item=(FileItem)iter.next();
				if(item.isFormField()){
					processFormField(item);
				}else{
					filename=processUploadFile(item,filePath);
				}
			}
		if(filename==null)return;
    	out.print("{\"url\":\"tiezi/"+filename+"\",state:\"SUCCESS\"}");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//����������ڳ�������
	@SuppressWarnings("unused")
	private void processFormField(FileItem item){
		String name=item.getFieldName();
		String value=item.getString();
	}
	
	
	//����������ڴ����ļ���
	private String processUploadFile(FileItem item,String filePath){
		String fileName=item.getName();
		System.out.println(fileName);
		int index=fileName.lastIndexOf("\\");
		fileName=fileName.substring(index+1,fileName.length());
		long fileSize=item.getSize();
		if(fileName.equals("")&&fileSize==0){
			return null;
		}
		UUID uid=UUID.randomUUID();
		String fileid=uid.toString();
		File uploadFile=new File(filePath+"/"+fileid);
		try {
			item.write(uploadFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileid;
	}
}
