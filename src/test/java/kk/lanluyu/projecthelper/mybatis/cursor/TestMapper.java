package kk.lanluyu.projecthelper.mybatis.cursor;

import lombok.Data;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;

public interface TestMapper {
    @Data
    class Person {
        private String name;
        private int age;
        private Integer id;
        private String address;
    }

    /**
     * 像 MySQL 这样直接用 Integer.MIN_VALUE 的不多见，
     * Type.FORWARD_ONLY 也是一些数据库的默认值，
     * 为了保险可以设置上，就目前的游标功能来看，
     * 针对不同的数据库要做对应的测试才能找到合适的参数配置。
     *
     * 由于Cursor是需要事务控制的，如果在系统中出现数据大量的修改等操作的话不建议使用Cursor
     */
    @Select("select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table")
    @Options(fetchSize = Integer.MIN_VALUE)
    Cursor<Person> selectAll();

    @Select("select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table union all " +
            "select * from test_table")
    List<Person> selectList();
}
