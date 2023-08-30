package org.apache.demo.service;


import org.apache.demo.helper.DataBaseHelper;
import org.apache.demo.model.Customer;
import org.apache.demo.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 提供客户数据服务
 */
public class CustomerService {
    private static final  Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");
        try{
           Class.forName(DRIVER);

        }catch(ClassNotFoundException e){
            LOGGER.error("can not load jdbc driver",e);
        }
    }



   /* *//**
     *
     * 获取客户列表
     *//*
    public List<Customer> getCustomerList2(){
        Connection conn = null;
        List<Customer> customerList = new ArrayList<>();
        try {
            String sql = "select * from customer";
            conn = DataBaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setContact(rs.getString("contact"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setEmail(rs.getString("email"));
                customer.setRemark(rs.getString("remark"));
                customerList.add(customer);
            }
            return customerList;
        }catch (SQLException e){
            LOGGER.error("execute sql failure",e);
        }finally {
            DataBaseHelper.closeConnection(conn);
        }
        return customerList;
    }*/


    /**
     *
     * 获取客户列表
     */
    public List<Customer> getCustomerList(){
        Connection conn = null;
        //try {
            conn = DataBaseHelper.getConnection();
            String sql = "select * from customer";
            return DataBaseHelper.queryEntityList(Customer.class,sql,conn);
        /*}finally {
            DataBaseHelper.closeConnection();
        }*/
    }


    /**
     *
     * 获取客户
     */
    public Customer getCustomer(long id){
        return null;
    }


    /**
     *
     * 创建客户
     */
    public boolean createCustomer(Map<String,Object> fieldMap){

        return DataBaseHelper.insertEntity(Customer.class,fieldMap);
    }

    /**
     *
     * 更新客户
     */
    public boolean updateCustomer(long id ,Map<String,Object> fieldMap){
        return DataBaseHelper.updateEntity(Customer.class,id,fieldMap);
    }


    /**
     *
     * 删除客户
     */
    public boolean deleteCustomer(long id){
        return DataBaseHelper.deleteEntity(Customer.class,id);
    }






}
