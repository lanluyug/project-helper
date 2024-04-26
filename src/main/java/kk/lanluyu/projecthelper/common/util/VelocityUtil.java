package kk.lanluyu.projecthelper.common.util;

import kk.lanluyu.projecthelper.common.Constants;
import kk.lanluyu.projecthelper.function.generateclass.entity.BaseGenerateInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.Properties;
import java.util.function.Function;

/**
 * @author zzh
 * @date 2024-04-23
 */
@Slf4j
public abstract class VelocityUtil {

    private VelocityUtil(){}
    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try
        {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, Constants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


    public static VelocityContext baseContext(BaseGenerateInfo baseGenerateInfo)
    {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("functionName", baseGenerateInfo.getFunctionName());
        velocityContext.put("className", baseGenerateInfo.getClassName());
        velocityContext.put("author", baseGenerateInfo.getAuthor());
        velocityContext.put("datetime", baseGenerateInfo.getDatetime());
        velocityContext.put("packageName", baseGenerateInfo.getPackageName());
        return velocityContext;
    }

    public static <T extends BaseGenerateInfo> String render(T baseGenerateInfo,
                                Function<T, VelocityContext> contextFunction,
                                String templateResource){
        VelocityUtil.initVelocity();
        VelocityContext context = contextFunction.apply(baseGenerateInfo);
        Template tpl = Velocity.getTemplate(templateResource);
        StringWriter sw = new StringWriter();
        tpl.merge(context, sw);
        return sw.toString();
    }
}
