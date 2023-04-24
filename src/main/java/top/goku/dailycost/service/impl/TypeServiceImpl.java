package top.goku.dailycost.service.impl;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.goku.dailycost.entity.Type;
import top.goku.dailycost.mapper.TypeMapper;
import top.goku.dailycost.service.ITypeService;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ZXP
 * @since 2023-04-18
 */
@Slf4j
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @Override
    public Map<Long, Type> typeById(Collection<Long> ids) {
        List<Type> types = super.listByIds(ids);
        return Optional.ofNullable(types)
                .orElseGet(ArrayList::new)
                .stream()
                .collect(Collectors.toMap(Type::getId, Function.identity()));
    }

    @Override
    public void init() {
        Type one = super.getOne(Wrappers.lambdaQuery(Type.class).last("limit 1"));
        if (null != one) {
            return;
        }
        try (InputStream resourceAsStream = TypeServiceImpl.class.getResourceAsStream("/type.json")) {
            String typeJsonStr = IoUtil.read(resourceAsStream, StandardCharsets.UTF_8);
            List<Type> types = JSONObject.parseArray(typeJsonStr, Type.class);
            super.saveBatch(types);
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
