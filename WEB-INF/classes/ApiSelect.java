import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.imageio.*;
import java.awt.Image;

import com.alibaba.fastjson.JSON;
import java.sql.*;
import javax.sql.*;
import javax.naming.InitialContext;
import javax.naming.Context;


@SuppressWarnings("unchecked")
public class ApiSelect {


    public Map disTribution (String methodName,List args){
        // List<String> temp = new ArrayList<String>();
        Map rs = new HashMap();
        switch(methodName) 
            { 
            case "checkIfAdmin": 
                rs = checkIfAdmin(args);
                return rs;
            case "createSourceFile": 
                rs = createSourceFile(args);
                return rs;
            case "checkSourceCode": 
                rs = checkSourceCode(args);
                return rs;
            case "getFileDetails": 
                rs = getFileDetails(args);
                return rs;
            case "deleteFile": 
                rs = deleteFile(args);
                return rs;
            case "moveFileToBin": 
                rs = moveFileToBin(args);
                return rs;
            // case "getPhotoDetails": 
            //     rs = getPhotoDetails(args);
            //     return rs;
            default: 
                System.out.println("Error:no such methodName"); 
                return null; 
            } 

        

    }
    //MODE 0 BOOL
    //MODE 1 MAP 
    //MODE 2 LIST
    public Map checkIfAdmin (List args) {
        String sql = "select * from admin where binary adminPw like'"+ args.get(0)+"'";
        ConnectionPool connp = new ConnectionPool();
        Map returnArgs = connp.postToPool(sql,1);
        return returnArgs;
    }

    public Map checkSourceCode(List args) {
        String sql = "select * from source_link where binary sourcecode like'"+ args.get(0)+"'";
        ConnectionPool connp = new ConnectionPool();
        Map returnArgs = connp.postToPool(sql,1);

        int ifExpire = Integer.parseInt(returnArgs.get("ifexpire").toString());
        String expireTime = returnArgs.get("expiretime").toString();
        String fileName = returnArgs.get("filename").toString();
        String checkChain = returnArgs.get("checkchain").toString();
        int cookie = Integer.parseInt(returnArgs.get("creatorId").toString());

        if(ifExpire == 0){

            return returnArgs;
        }else{
            returnArgs.clear();
            returnArgs.put("status",0);
            return returnArgs;
            
        }


    }

    public Map createSourceFile(List args) {
        // String sql = "select * from source_link where binary sourcecode like'"+ args.get(0)+"'";
        ConnectionPool connp = new ConnectionPool();
        // Map returnArgs = connp.postToPool(sql);
      
        
            String creatorid = args.get(0).toString();
            String filename = args.get(1).toString();
            String photo = args.get(2).toString();
            String photoX = args.get(3).toString();
            String photoY = args.get(4).toString();
            int num = 10000 + (int)(Math.random() * (20000-10000+1));
            String sourcecode = Integer.toString(num);
            
        // if(ifExpire == 0){
            String sql = "INSERT INTO `source_link` (`sourcecode`, `expiretime`, `filename`,`checkchain`,`creatorId`,`photo`,`width`,`height`) VALUES"
                       + " ('"+sourcecode+"', '2017-03-26 23:43:21','"+filename+"', '7dw7a1WJ27dhSH21Nss', "+ creatorid + ","+ photo +","+photoX+","+photoY+")";
            Map returnArgs = connp.postToPool(sql,0);   
            return returnArgs;
        // }
        // return returnArgs;
    }

    public Map moveFileToBin(List args) {
        ConnectionPool connp = new ConnectionPool();
        // Map returnArgs = connp.postToPool(sql);
      
        
            String sourcecode = args.get(0).toString();
          
        // if(ifExpire == 0){
            String sql = "UPDATE `source_link` SET deleted = '1' WHERE sourcecode = '"+sourcecode+"'";
            Map returnArgs = connp.postToPool(sql,0);   
            return returnArgs;
        // }
        // return returnArgs;
    }

    public Map getFileDetails(List args) {
        String sql = "select * from source_link where creatorId='"+ args.get(0)+"'";
        ConnectionPool connp = new ConnectionPool();
        Map returnArgs = connp.postToPool(sql,2);
        return returnArgs;

    }

    public Map deleteFile(List args) {
 
        String sql = "delete from source_link where sourcecode='"+ args.get(0)+"'";
        ConnectionPool connp = new ConnectionPool();
        Map returnArgs = connp.postToPool(sql,0);
        return returnArgs;

    }

    public static List isImage(File imageFile) {  
       List r = new ArrayList();
       if (!imageFile.exists()) {  
           r.add(0);
           r.add(0);
           return r;  
       }  
       Image img = null;  
       try {  
           img = ImageIO.read(imageFile);  
           if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
               r.add(0);
               r.add(0); 
               return r;  
           }  
        //    int pX = img.getWidth(null);
           r.add(img.getWidth(null));
           r.add(img.getHeight(null));
           return r;  
       } catch (Exception e) {  
           r.add(0);
           r.add(0);
           return r;  
       } finally {  
           img = null;  
       }  
   }
    // public Map getPhotoDetails(List args) {
    //     String sql = "select * from photos where creatorId='"+ args.get(0)+"'";
    //     ConnectionPool connp = new ConnectionPool();
    //     Map returnArgs = connp.postToPool(sql,2);
    //     return returnArgs;

    // }


}