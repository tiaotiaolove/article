package com.xiaobai.jpa.student.dao;

import com.xiaobai.jpa.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 学生表Dao
 *
 * @author bail
 * @date 2019/05/10
 */
@Repository
public interface StudentDao extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

}
