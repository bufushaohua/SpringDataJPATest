package com.cyc;

import com.cyc.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.*;
import java.util.Properties;

public class MyTest {
    public static Properties getProperty() throws IOException {
        FileInputStream fis = new FileInputStream("src/main/resources/config/test.properties");
        Properties props = new Properties();
        /**
         * Properties 默认是按ISO-8859-1读取的，所以如果你想让它按照你想的格式显示就需要转换一下
         * 本来是：props.load(fis);
         */
        props.load(new InputStreamReader(fis,"utf-8"));// 将文件的全部内容读取到内存中，输入流到达结尾
        fis.close();// 加载完毕，就不再使用输入流，程序未主动关闭，需要手动关闭
        return props;
    }

    @Test
    public void test() {
        /** 创建实体管理类工厂，借助Persistence的静态方法获取
         *  其中传递的参数为持久化单元名称，需要JPA配置文件中指定
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //创建实体管理类
        EntityManager entityManager = factory.createEntityManager();
        //获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustomName("江西理工大学");
        //保存操作
        entityManager.persist(customer);
        //提交事务
        transaction.commit();
        entityManager.close();
        factory.close();
    }

    @Test
    public void testShow(){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            Customer customer = em.find(Customer.class,1L);
            System.out.println(customer);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    @Test
    public void testAdd() {
        Customer customer = new Customer();
        customer.setCustomName("梦里稻草人");
        customer.setCustomLevel("VIP客户");
        customer.setCustomSource("网络");
        customer.setCustomIndustry("IT教育");
        customer.setCustomAddress("昌平区北七家镇");
        customer.setCustomPhone("010-84389340");

        Properties p = new Properties();

        EntityManager em = null;
        EntityTransaction et = null;

        try{
            em = JPAUtil.getEntityManager();
            //获取事务对象
            et = em.getTransaction();
            //开启事务
            et.begin();
            //执行操作
            em.persist(customer);
            //提交事务
            et.commit();
        }catch (Exception e){
            //事务回滚
            et.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    @Test
    public void testAdd1() throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        String name = getProperty().getProperty("name");
        System.out.println(name);
    }
}
