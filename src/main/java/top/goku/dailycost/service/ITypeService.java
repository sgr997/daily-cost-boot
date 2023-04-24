package top.goku.dailycost.service;

import top.goku.dailycost.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZXP
 * @since 2023-04-18
 */
public interface ITypeService extends IService<Type> {

    Map<Long, Type> typeById(Collection<Long> ids);

    void init();

}
