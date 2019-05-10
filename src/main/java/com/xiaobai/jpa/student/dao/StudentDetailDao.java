package com.xiaobai.jpa.student.dao;

import com.xiaobai.jpa.student.entity.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生详情表Dao
 *
 * @author bail
 * @date 2019/05/10
 */
@Repository
public interface StudentDetailDao extends JpaRepository<StudentDetail, Integer>, JpaSpecificationExecutor<StudentDetail> {

    /**
     * 使用join fetch 避免n+1问题
     */
    @Query(value = "select t from StudentDetail t join fetch t.student s where s.age >= 20 order by s.age desc")
    List<StudentDetail> queryJoinTest();
}
