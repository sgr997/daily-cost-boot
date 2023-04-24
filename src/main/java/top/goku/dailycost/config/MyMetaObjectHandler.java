package top.goku.dailycost.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

/**
 * 自动填充字段
 */
@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        long now = System.currentTimeMillis();
        if (metaObject.getValue("createTime") == null) {
            metaObject.setValue("createTime", now);
        }
        if (metaObject.getValue("updateTime") == null) {
            metaObject.setValue("updateTime", now);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        long now = System.currentTimeMillis();
        if (metaObject.getValue("updateTime") == null) {
            metaObject.setValue("updateTime", now);
        }
    }
}
