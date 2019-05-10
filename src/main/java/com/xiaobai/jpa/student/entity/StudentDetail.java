package com.xiaobai.jpa.student.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 学生详情表
 *
 * @author bail
 * @date 2019/05/10
 */
@Entity
@Table(name = "x_student_detail")
@Data
public class StudentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nick_name;

    @Column
    private int student_no;

    @OneToOne()
    @JoinColumn(name = "stu_id", insertable = false, updatable = false)
    private Student student;

}
