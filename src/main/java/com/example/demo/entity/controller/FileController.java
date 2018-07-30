package com.example.demo.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Files;
import com.example.demo.entity.FilesMapper;
import com.example.demo.entity.User;

@RestController
@RequestMapping
public class FileController {
	
	
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
         return new ModelAndView("files/fileslist", "filesModel", model);
     }

     /**
      * 根据name查询文件
      * @return
      */
     @GetMapping("{filesname}")
     public ModelAndView view(@PathVariable("filesname") String filesname, Model model) {
         List<Files> files = filesMapper.findByFilename(filesname);
         model.addAttribute("selectedUser", files);
         model.addAttribute("title", "查看文件");
         return new ModelAndView("files/files", "filesModel", model);
     }

     /**
      * 获取 form 表单页面
      * @return
      */
     @GetMapping("/form")
     public ModelAndView createForm(Model model) {
         model.addAttribute("files", new Files());
         model.addAttribute("title", "创建文件");
         return new ModelAndView("files/newfiles", "filesModel", model);
     }

     /**
      * 新建用户
      * @param user
      * @return
      */
     @PostMapping
     public ModelAndView create(Files newfiles) {
    	 filesMapper.insert(newfiles);
         return new ModelAndView("redirect:/fileslist");
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
         model.addAttribute("title", "删除用户");
         return new ModelAndView("files/fileslist", "filesModel", model);
     }

     /**
      * 修改用户
      */
     @GetMapping(value = "modify/{id}")
     public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
         Files files = filesMapper.findByFileid(id);
         model.addAttribute("files", files);
         model.addAttribute("title", "修改文件");
         return new ModelAndView("files/filesform", "userModel", model);
     }


	
}
