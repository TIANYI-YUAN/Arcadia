import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import java.sql.*;
import javax.sql.*;
import javax.naming.InitialContext;
import javax.naming.Context;

@SuppressWarnings("unchecked")
public class ConnectionPool {


    public Map postToPool(String sql,int mode){
        //String jsonString = JSON.toJSONString(group);
        try{
            Context ctx=new InitialContext();

            Connection conn=null;

            DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/arcadia");

            conn=ds.getConnection();

            Statement stmt=conn.createStatement();
            
            if(mode == 1){
            //查 只有一组数据
            ResultSet rs=stmt.executeQuery(sql);
            // List list = new ArrayList();
         
            ResultSetMetaData md = rs.getMetaData();
           
            int columnCount = md.getColumnCount();
           
            Map rowData = new HashMap();
            
            while(rs.next()){
                

                for (int i = 1; i <= columnCount; i++) {
                   
                rowData.put(md.getColumnName(i), rs.getObject(i));
                }

           
            }
            
       
            // list.add(rowData);            
                rs.close();
                stmt.close();
                conn.close();

            
                return rowData;       
            }else if(mode == 0){
                //修改数据
                int rs=stmt.executeUpdate(sql);
                
                stmt.close();
                conn.close();
                if(rs > 0 ){
                    Map rowData = new HashMap();
                    rowData.put("status",1);
                    return rowData;
                }else{
                    return null;
                }
            }else if(mode == 2){
                //查 好几行数据
                ResultSet rs=stmt.executeQuery(sql);
                List myList = new ArrayList();
                
                ResultSetMetaData md = rs.getMetaData();
             
                int columnCount = md.getColumnCount();
             
                while(rs.next()){
                    Map rowData = new HashMap();
                    for (int i = 1; i <= columnCount; i++) {
                    
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                    
                    }
                    
              
                    myList.add(rowData);     
                
                }
                
                 
                    Map cookedMap = new HashMap();
                    cookedMap.put("files",myList);

                    rs.close();
                    stmt.close();
                    conn.close();
                    return cookedMap;
            
                
            }else{
              
                stmt.close();
                conn.close();
                return null;
            }

            } catch (Exception ex) {
                    System.out.println("ConnectionPool.java Error---->" + ex.getMessage());
                    return null;
        }
    }

}
