package web;

import sun.misc.IOUtils;
import utility.DownLoadUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class My_response02 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取用户在浏览器中输入的数据*/
        String fileName = req.getParameter("fileName");
        /*获取下载文件的盘符路径*/
        String realPath = req.getRealPath("/download02"+fileName);
        /*根据浏览器版本信息避免浏览器与ider编码不符导致乱码*/
        String header = req.getHeader("user-agent");
        DownLoadUtils.getName(header, fileName);
        /*通知浏览器写回去的东西要以附件形式打开 (只用于下载)*/
        resp.setHeader("content-disposition","attachment;filename="+fileName);
        /*输入流和输出流*/
        File file = new File(realPath);/*file根据地址获取文件*/
        FileInputStream fileInputStream = new FileInputStream(file);
        ServletOutputStream outputStream = resp.getOutputStream();
        /*遍历输出的东西*/
        org.apache.commons.io.IOUtils.copy(fileInputStream, outputStream);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
