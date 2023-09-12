//package com.example.demo.config;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.model.HttpStatusCodeModel;
//
//@Component
//public class ReturnEror {
//
//    public void handle(int number) throws IOException{
//    	HttpServletResponse response =new HttpServletResponse();
//    	response.setStatus(HttpStatusCodeModel.UNAUTHORIZED);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.getWriter().write("{ \"error\": \"ko co token\" }");
//    }
//}
