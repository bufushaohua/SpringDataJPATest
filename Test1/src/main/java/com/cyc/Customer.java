package com.cyc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data //自动生成Getter、Setter方法
@AllArgsConstructor //自动生成全参构造函数
@NoArgsConstructor // 自动生成无参构造函数
@ToString  //自动生成ToString方法
@Entity //声明实体类
/**
 * 若是IDEA中JPA报错Cannot resolve table/Cannot resolve column
 * 只需要
 *  1.添加数据库信息
 *  2.关联JPA和database
  */
@Table(name = "cst_customer") // 建立实体类和表的映射关系
public class Customer {
    @Id //声明当前私有属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id") //指定和表中cust_id字段的映射关系
    public Long customId;

    @Column(name = "cust_name")
    public String customName;

    @Column(name = "cust_source")
    public String customSource;

    @Column(name = "cust_industry")
    public String customIndustry;

    @Column(name = "cust_level")
    public String customLevel;

    @Column(name = "cust_address")
    public String customAddress;

    @Column(name = "cust_phone")
    public String customPhone;
}
