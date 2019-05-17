package com.xiaobai.jpa.student.dao;

import com.xiaobai.jpa.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生表Dao
 *
 * @author bail
 * @date 2019/05/10
 */
@Repository
public interface StudentDao extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
    @Query(value = "select max(s.age) from Student as s group by s.sex")
    List queryMaxAgeGroupByAge();
}
