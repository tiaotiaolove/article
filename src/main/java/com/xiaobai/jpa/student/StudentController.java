package com.xiaobai.jpa.student;

import com.xiaobai.common.base.BaseResponse;
import com.xiaobai.jpa.student.dao.StudentDao;
import com.xiaobai.jpa.student.dao.StudentDetailDao;
import com.xiaobai.jpa.student.entity.Student;
import com.xiaobai.jpa.student.entity.StudentDetail;
import com.xiaobai.jpa.student.request.StudentQueryRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生查询Controller
 *
 * @author bail
 * @date 2019/05/10
 */
@Api(tags = "StudentController", description = "学生查询API")
@RestController()
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    StudentDao studentDao;

    @Autowired
    StudentDetailDao studentDetailDao;

    @ApiOperation(value = "获取学生详情列表", notes = "默认按照年龄倒序排列")
    @PostMapping("/list")
    public BaseResponse list() {
        List list = studentDao.queryMaxAgeGroupByAge();
        log.info("group by result===>", list);
        return BaseResponse.success(studentDetailDao.queryJoinTest());
    }

    @ApiOperation(value = "获取学生详情列表-多重排序", notes = "多重排序")
    @PostMapping("/list/sort")
    public BaseResponse page(@Valid @RequestBody
                                 @ApiParam(value = "学生查询Req", required = true) StudentQueryRequest studentQueryRequest) {
        studentQueryRequest.putSort("studentNo", "desc");
        studentQueryRequest.putSort("nickName", "asc");
        studentQueryRequest.putSort("student.age", "asc");
//        return BaseResponse.success(studentDetailDao.findAll(getDetailWhereCriteria()));
        return BaseResponse.success(studentDetailDao.findAll(getDetailWhereCriteria(), studentQueryRequest.getSort()));
    }


    private static Specification<StudentDetail> getDetailWhereCriteria() {
        return (stuDetail, cquery, cbuild) -> {
            List<Predicate> predicates = new ArrayList<>();
            //联查
            Join<Student, StudentDetail> student = stuDetail.join("student", JoinType.LEFT);

            predicates.add(cbuild.greaterThanOrEqualTo(student.get("age"), "20"));
            Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
            cquery.orderBy(cbuild.desc(student.get("id")), cbuild.desc(stuDetail.get("id")));
            return p.length == 0 ? null : p.length == 1 ? p[0] : cbuild.and(p);
        };
    }
}
