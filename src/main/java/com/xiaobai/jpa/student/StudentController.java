package com.xiaobai.jpa.student;

import com.xiaobai.common.base.BaseResponse;
import com.xiaobai.jpa.student.dao.StudentDetailDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生查询Controller
 *
 * @author bail
 * @date 2019/05/10
 */
@Api(tags = "StudentController", description = "学生查询API")
@RestController()
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentDetailDao studentDetailDao;

    @ApiOperation(value = "获取学生详情列表", notes = "默认按照年龄倒序排列")
    @PostMapping("/list")
    public BaseResponse list() {
        return BaseResponse.success(studentDetailDao.queryJoinTest());
    }
}
