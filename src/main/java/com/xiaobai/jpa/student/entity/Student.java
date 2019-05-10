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
    private int id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private int sex;

}
