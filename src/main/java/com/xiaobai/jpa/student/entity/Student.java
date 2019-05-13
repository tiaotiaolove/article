package com.xiaobai.jpa.student.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 学生表
 *
 * @author bail
 * @date 2019/05/10
 */
@Entity
@Table(name = "x_student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private int sex;

}
