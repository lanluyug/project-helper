package kk.lanluyu.projecthelper.mybatis.cursor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CursorServiceTest {

    @Test
    public void testCursor() throws InterruptedException {
        //等待10秒方便jvisualVM监控
        TimeUnit.SECONDS.sleep(10);
        long start = System.currentTimeMillis();
        try (SqlSession sqlSession = getSqlSession()) {
            TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
            try(Cursor<TestMapper.Person> cursor = testMapper.selectAll()){

                int total = 0;
                for (TestMapper.Person o : cursor) {
                    total++;
                }
                System.out.println("总数: " + total);
            } catch (IOException ignore) {
            }
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
        TimeUnit.SECONDS.sleep(10);
    }


    @Test
    public void testSelectAll() throws InterruptedException {
        //等待10秒方便jvisualVM监控
        TimeUnit.SECONDS.sleep(10);
        long start = System.currentTimeMillis();
        try (SqlSession sqlSession = getSqlSession()) {
            TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
            List<TestMapper.Person> people = testMapper.selectList();
            System.out.println(people.size());
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
        TimeUnit.SECONDS.sleep(10);
    }

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException ignored) {

        }
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

}
