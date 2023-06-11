package com.szh.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.beans.Transient;
import java.util.Map;

/**
 * Created by szh on 2023-06-11
 *
 * @author szh
 */

@Service
public class SimpleDemo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        String sql = "replace into money (id, name, money) values (120, '初始化', 200)," + "(130, '初始化', 200)," + "(140, '初始化', 200)," + "(150, '初始化', 200)";
        jdbcTemplate.execute(sql);
    }

    private boolean updateName(int id) {
        String sql = "update money set `name`  = '更新' where id = " + id;
        jdbcTemplate.execute(sql);
        return true;
    }

    public void query(String tag, int id) {
        String sql = "select * from money where id=" + id;
        Map map = jdbcTemplate.queryForMap(sql);
        System.out.println(tag + " >>>> " + map);
    }

    private boolean updateMoney(int id) {
        String sql = "update money set `money`= `money` + 10 where id=" + id;
        jdbcTemplate.execute(sql);
        return false;
    }

    /**
     * 运行异常导致回滚
     */
    @Transactional
    public boolean testRuntimeExceptionTrans(int id) {
        if (this.updateName(id)) {
            this.query("after updateMoney name", id);
            if (this.updateMoney(id)) {
                return true;
            }
        }
        throw new RuntimeException("更新失败，回滚!");
    }

    /**
     * 注解 @Transactional 默认只针对运行时异常生效，如下面这种 case，虽然是抛出了异常，但是并不会生效
     */
    @Transactional
    public boolean testNormalException(int id) throws Exception {
        if (this.updateName(id)) {
            this.query("after updateMoney name", id);
            if (this.updateMoney(id)) {
                return true;
            }
        }

        throw new Exception("声明异常");
    }

    /**
     * 可以借助 rollbackFor 属性来指明，触发回滚的异常类型
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean testSpecialException(int id) throws Exception {
        if (this.updateName(id)) {
            this.query("after updateMoney name", id);
            if (this.updateMoney(id)) {
                return true;
            }
        }
        throw new IllegalArgumentException("参数异常");
    }
}
