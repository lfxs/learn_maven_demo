package org.apache.demo.helper;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.demo.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * 数据库操作助手
 */
public final class DataBaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseHelper.class);

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");
        try {
            Class.forName(DRIVER);

        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
            } finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        if (conn != null) {

            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    /**
     *
     * 查询客户列表
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList;
        Connection conn = null;
        try {
            conn = getConnection();
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass));
        } catch (SQLException e) {
            LOGGER.error("query entity list failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return entityList;
    }

    /**
     *
     * 查询单个实体
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity;
        Connection conn = null;
        try {
            conn = getConnection();
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass));
        } catch (SQLException e) {
            LOGGER.error("query entity list failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return entity;
    }



}
