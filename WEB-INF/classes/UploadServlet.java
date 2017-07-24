import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")

@SuppressWarnings("unchecked")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "s";
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 100;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 300; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 300; // 50MB
 
    /**
     * 上传数据及保存文件
     */
    protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request)) {
		    // 如果不是则停止
		
		    System.out.println("Error: 表单必须包含 enctype=multipart/form-data");
		
		    return;
		}
 
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8"); 

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
       
         
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // 解析请求的内容提取文件数据
     
            List<FileItem> formItems = upload.parseRequest(request);
        //     String papa = request.getInputStream();
        //     System.out.println(papa);
            Map param = new HashMap(); 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                        if(item.isFormField()){
                        param.put(item.getFieldName(), item.getString("utf-8")); 
                        System.out.println(param);
                         }  
                }
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                      
                        
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        // String creatorId = request.getParameter("creatorId");
                        
                        // 保存文件到硬盘
                        item.write(storeFile);
                        PrintWriter out = response.getWriter();
                      
                        ApiSelect api = new ApiSelect();
                        List args = new ArrayList();
                        args.add(param.get("creatorId"));
                        // args.add(1);
                        args.add(fileName);
                        int imgX = Integer.parseInt(api.isImage(storeFile).get(0).toString());
                        int imgY = Integer.parseInt(api.isImage(storeFile).get(1).toString());
                       
                        if(imgX > 0 && imgY > 0){
                            args.add(1);
                            args.add(imgX);
                            args.add(imgY);
                        }else{
                            args.add(0);
                            args.add(0);
                            args.add(0);
                        }
                        
                        Map result = new HashMap();
                        //result.put("status",1);

                        //接口调用
		                result = api.createSourceFile(args);



                        String jsonString = JSON.toJSONString(result);
			out.println(jsonString);
			out.flush();
			out.close();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("error INFO: " + ex.getMessage());
        }
      
    }
}