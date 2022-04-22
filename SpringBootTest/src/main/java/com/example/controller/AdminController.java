package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.bean.admin;
import com.example.bean.role;
import com.example.common.Result;
import com.example.service.AdminService;
import com.example.service.RoleService;
import com.example.utils.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired(required = false)
    private RoleService roleService;

    //分页查询人员信息
    @GetMapping("/pageUsers")
    public Result pageUsers(@RequestParam int currentPage, @RequestParam int pageSize,
                            @RequestParam String adminName, @RequestParam String adminEmail,
                            @RequestParam String adminAddress){

        return Result.success(adminService.getProductPage(currentPage, pageSize, adminName, adminAddress, adminEmail));
    }

    //通过id删除人员
    @GetMapping("/deleteById")
    public Result deleteById(@RequestParam String adminId){
        if(adminService.deleteById(adminId)==1)
            return Result.success();
        return Result.error();
    }

//    添加人员
    @PostMapping("/insertAdmin")
    public Result insertAdmin(@RequestBody admin dto){
        if(adminService.insertAdmin(dto)==1){
            return Result.success();
        }
        return Result.error();
    }

//    编辑人员
    @PostMapping("/updAdmin")
    public Result updAdmin(@RequestBody admin dto){
        if(adminService.updateAdmin(dto)==1){
            return Result.success();
        }
        return Result.error();
    }

//    角色列表
    @GetMapping("/selectRole")
    public Result selectRole(){
        return Result.success(roleService.selectRole());
    }

//    角色count
    @GetMapping("/selectRoleCount")
    public Result selectRoleCount(){
        return Result.success(roleService.selectRoleCount());
    }

//    角色上传
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        //        List<User> list = reader.readAll(User.class);

        // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<role> users = CollUtil.newArrayList();
        for (List<Object> row : list) {
        role user = new role();
        user.setRoleName(row.get(0).toString());
        user.setRoleFunction(row.get(1).toString());
        user.setRolePower(Integer.parseInt(row.get(2).toString()));
        users.add(user);
        }
        roleService.saveBatch(users);
        System.out.println(users.toString());
        return true;
    }

//    角色导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<role> list = roleService.selectRole();
        // 通过工具类创建writer 写出到磁盘路径
    //        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("roleName", "角色名");
        writer.addHeaderAlias("roleFunction", "描述");
        writer.addHeaderAlias("rolePower", "职能");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

        }
}
