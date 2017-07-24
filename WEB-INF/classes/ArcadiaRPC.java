import java.io.*;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;

@WebServlet("/ArcadiaRPC")

@SuppressWarnings("unchecked")
public class ArcadiaRPC extends HttpServlet {

	private static final long serialVersionUID = 1L;
     
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB




	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");	
        doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

		String reArgs = request.getParameter("args"); 
		String methodName = request.getParameter("methodName");
		String clientIpAddr = request.getRemoteAddr();
		//System.out.println(clientIpAddr);

		List args = JSON.parseArray(reArgs);
			args.add(clientIpAddr);	
		ApiSelect api = new ApiSelect();
		Map result = api.disTribution(methodName,args);

		if(result != null && result.size()>0){
			String jsonString = JSON.toJSONString(result);
			PrintWriter out = response.getWriter();
			out.println(jsonString);
			out.flush();
			out.close();
		}

	
	
		







	}
}