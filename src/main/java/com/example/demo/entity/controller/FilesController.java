package com.example.demo.entity.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Files;
import com.example.demo.entity.FilesMapper;
@RequestMapping("/files")
@RestController
public class FilesController {
	
	
	private  String UPLOAD_FILES_LOCATION="UPLOAD_FILES\\";
	
	@Autowired
	private FilesMapper filesMapper;
	
    /**
     * 从 用户存储库 获取用户列表
     * @return
     */
	private List<Files> getAllFileslist() {
		return filesMapper.listAllFiles();
	}
	
	
    /**
     * 查询所有文件
     * @return
     */
	@GetMapping
	public ModelAndView list(Model model) {
        model.addAttribute("allFileList", getAllFileslist());
        model.addAttribute("title", "全部文件列表");
		return new ModelAndView("fileslist", "filesModel", model);
		
	}
	
	
	/**
     * 根据name查询文件
     * @return
     */
    @GetMapping("filesname/{filesname}")
    public ModelAndView view(@PathVariable("filesname") String filesname, Model model) {
        List<Files> files = filesMapper.findByFilename(filesname);
        model.addAttribute("files", files);
        model.addAttribute("title", "查看文件");
        return new ModelAndView("filesview", "filesModel", model);
    }
    
    
    
	/**
     * 根据id查询文件
     * @return
     */
    @GetMapping("filesid/{filesid}")
    public ModelAndView view(@PathVariable("filesid") Long filesid, Model model) {
        Files files = filesMapper.findByFileid(filesid);
        model.addAttribute("files", files);
        model.addAttribute("title", "查看文件");
        return new ModelAndView("filesview", "filesModel", model);
    }

    /**
     * 获取 form 表单页面
     * @return
     */
    @GetMapping("/filesform")
    public ModelAndView createForm(Model model) {
        model.addAttribute("files", new Files());
        model.addAttribute("title", "创建文件");
        return new ModelAndView("filesform", "filesModel", model);
    }

    /**
     * 新建用户
     * @param user
     * @return
     */
    @PostMapping
    public ModelAndView create(Files newfiles) {
   	 filesMapper.insert(newfiles);
        return new ModelAndView("redirect:/files");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, Model model) {
        filesMapper.deleteByPrimaryKey(id);

        model.addAttribute("allFileList", getAllFileslist());
        model.addAttribute("title", "删除文件");
        return new ModelAndView("fileslist", "filesModel", model);
    }

    /**
     * 修改用户
     */
    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        Files files = filesMapper.findByFileid(id);
        model.addAttribute("files", files);
        model.addAttribute("title", "修改文件");
        return new ModelAndView("filesform", "filesModel", model);
    }
    
    
    @GetMapping("/upload")
    public ModelAndView index() {
        return new ModelAndView("upload");
    }
    
    @PostMapping("/upload")
    public ModelAndView singleFileUpload(@RequestParam("file") MultipartFile file,
            Model model) {
    	if (file.isEmpty()) {
            model.addAttribute("message", "文件为空，请选择文件再上传（这里可以前台控制）");
            return new ModelAndView("uploadStatus","uploadmodel",model);
        }
    	try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String dateStr = dateformat.format(System.currentTimeMillis());
            Path path = Paths.get(UPLOAD_FILES_LOCATION+dateStr+" "+file.getOriginalFilename());
            java.nio.file.Files.write(path, bytes);

            model.addAttribute("message","成功上传 '" + path.getFileName() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView("uploadStatus","uploadmodel",model);
		
	}
    
    @GetMapping("/uploadStatus")
    private ModelAndView uploadStatus() {
    	return new ModelAndView("uploadStatus");
    }
	
	
	
	
}
