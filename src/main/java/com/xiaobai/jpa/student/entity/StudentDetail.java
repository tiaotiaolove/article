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
    @Column(name = "id")
    private int id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "student_no")
    private int studentNo;

    @OneToOne()
    @JoinColumn(name = "stu_id", insertable = false, updatable = false)
    private Student student;

}
