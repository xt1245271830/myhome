package web;

import org.apache.commons.io.IOUtils;
import utility.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class My_response extends HttpServlet{
    /* 下载：从服务器将资源下载到本地（io流）
     *  上传：将本地的资源上传到服务器 （io流）
     *
     *   下载步骤分析：
     *       1 在自己的项目中准备资源 供用户去下载
     *       2 为用户提供一个下载页面 download.html
     *       3 只要用户点击下载要到servlet完成下载功能
     *                 核心
     *                 1 明确用户要下载的是那些资源
     *                 2 获取到用户要下载的资源和服务器准备的资源匹配
     *                 3 最后要传输的数据要以附件下载的形式打开   content-disposition
     *                 4 输入流和输出流的对拷
     *
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fileName");/*创建对象获取用户输入的fileName数据*/
        /*1 明确用户要下载的是那些资源*/

        ServletContext servletContext = getServletContext();/*让全局管理者获取对象*/
        String realPath = servletContext.getRealPath("download/" + fileName);/*获取下载文件的盘符位置*/
        /*String realPath = req.getRealPath("download/" + fileName);获取下载文件的盘符位置*/
        /*2 获取到用户要下载的资源和服务器准备的资源匹配*/

        String value = req.getHeader("user-agent");/*可以获取用户的浏览器版本信息*/
        String encode= DownLoadUtils.getName(value,fileName);/*根据浏览器版本信息避免浏览器与idea编码不一致导致的乱码*/

        resp.setHeader("content-disposition","attachment;filename="+fileName);/*设置响应头下载框*/
        /*3 最后要传输的数据要以附件下载的形式打开   content-disposition*/

        File file = new File(realPath);/*file：根据realPath地址获取文本内容*/
        InputStream inputStream = new FileInputStream(file);/*输入流*/
        ServletOutputStream outputStream = resp.getOutputStream();/*输出流*/
        org.apache.commons.io.IOUtils.copy(inputStream, outputStream);/*导的一个commons包中的方法，代替以前的边读边写*/
        /*4 输入流和输出流的对拷*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
