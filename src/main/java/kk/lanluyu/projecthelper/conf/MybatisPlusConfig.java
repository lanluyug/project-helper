package kk.lanluyu.projecthelper.conf;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zzh
 * @date 2024-04-15
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MySqlInjector sqlInjector() {
        return new MySqlInjector();
    }

    public static class MySqlInjector extends DefaultSqlInjector {

        @Override
        public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
            List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
            methodList.add(new InsertBatchSomeColumn(i -> i.getFieldFill() != FieldFill.UPDATE));
            return methodList;
        }
    }

}


