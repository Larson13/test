package cn.com.perf.beihe.pinter.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.perf.beihe.pinter.http.base.BaseController;
import cn.com.perf.beihe.pinter.mode.MyFile;
import cn.com.perf.beihe.pinter.mode.RestResponse;
import cn.com.perf.beihe.pinter.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/file"})
public class FileController extends BaseController {
    @Value("${cn.com.ptest.teach.uploadpath}")
    private String fileUploadPath;

    @GetMapping({"/page/upload"})
    public String toUpload(Model model) {
        model.addAttribute("desc", " <a style='color:#337ab7' href='http://www.baidu.cn'></a>@树能");
        return "upload";
    }

    @GetMapping({"/page/list"})
    public String fileList(Model model) {
        List<MyFile> myFileList = new ArrayList<>();
        if (!StringUtils.isBlank(this.fileUploadPath)) {
            File fileDir = new File(this.fileUploadPath);
            if (fileDir.exists()) {
                File[] files = fileDir.listFiles();
                for (int i = 0; i < files.length; i++) {
                    File currentFile = files[i];
                    String fileName = currentFile.getName();
                    int index = fileName.lastIndexOf(".");
                    if (index != -1)
                        try {
                            String realFileName = fileName.substring(0, index);
                            String timestamp = fileName.substring(index + 1);
                            String createTime = CommonUtils.converTime(Long.valueOf(Long.parseLong(timestamp)));
                            MyFile myFile = new MyFile();
                            myFile.setId(Integer.valueOf(i + 1));
                            myFile.setFileName(realFileName);
                            myFile.setSize(Long.valueOf(currentFile.length() / 1024L));
                            myFile.setUploadTime(createTime);
                            myFileList.add(myFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            }
            model.addAttribute("myFileList", myFileList);
            model.addAttribute("desc", "<a style='color:#337ab7' href='http://www.baidu.cn'></a>@树能");
        }
        return "fileList";
    }

    @PostMapping({"/api/upload"})
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, String remark) {
        if (file.isEmpty())
            return "上传失败，文件为空";
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(File.separator);
        if (index != -1)
            fileName = fileName.substring(index);
        if (StringUtils.isBlank(this.fileUploadPath))
            return "服务端上传路径配置错误";
        File dir = new File(this.fileUploadPath);
        if (!dir.exists())
            dir.mkdir();
        String serverFileName = fileName + "." + System.currentTimeMillis();
        File serverFile = new File(dir + File.separator + serverFileName);
        try {
            file.transferTo(serverFile);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    @PostMapping({"/api/upload2"})
    @ResponseBody
    public RestResponse<Object> upload2(HttpServletRequest request, @RequestParam("file") MultipartFile file, String remark) {
        if (file.isEmpty())
            return wrap("1", "参数为空");
                    String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(File.separator);
        if (index != -1)
            fileName = fileName.substring(index);
        if (StringUtils.isBlank(this.fileUploadPath))
            return wrap("1", "服务端上传路径配置错误");
                    File dir = new File(this.fileUploadPath);
        if (!dir.exists())
            dir.mkdir();
        String serverFileName = fileName + "." + System.currentTimeMillis();
        File serverFile = new File(dir + File.separator + serverFileName);
        try {
            file.transferTo(serverFile);
            return wrap("0", "上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return wrap("1", "上传失败");
        }
    }

    @GetMapping({"/api/download"})
    @ResponseBody
    public RestResponse<Object> download(HttpServletResponse response, Integer id) {
        ServletOutputStream servletOutputStream=null;
        if (id == null)
            return wrap("1", "参数为空");
        if (StringUtils.isBlank(this.fileUploadPath))
            return wrap("1", "服务端上传路径配置错误");
                    File dir = new File(this.fileUploadPath);
        if (!dir.exists())
            return wrap("1", "服务端上传路径不存");
                    File[] serverFiles = dir.listFiles();
        if (serverFiles.length < id.intValue())
            return wrap("1", "文件不存在 ");
                    File downloadFile = serverFiles[id.intValue() - 1];
        String fileName = downloadFile.getName().substring(0, downloadFile.getName().lastIndexOf("."));
        InputStream is = null;
        OutputStream os = null;
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            is = new FileInputStream(downloadFile);
            servletOutputStream = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) > 0)
                servletOutputStream.write(b, 0, length);
            servletOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (servletOutputStream != null)
                    servletOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wrap("0", "success");
    }
}
