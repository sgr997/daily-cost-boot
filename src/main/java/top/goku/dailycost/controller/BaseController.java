package top.goku.dailycost.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.goku.dailycost.vo.PageRequest;
import top.goku.dailycost.vo.R;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2023/4/1 10:01
 */
@SuppressWarnings("rawtypes")
public class BaseController<Entity> {

    @Autowired
    IService<Entity> service;

    @ApiOperation("添加")
    @PostMapping
    R add(@RequestBody @Valid Entity request) {
        service.save(request);
        return R.success(request);
    }

    @ApiOperation("修改")
    @PutMapping
    R modify(@RequestBody @Valid Entity request) {
        service.updateById(request);
        return R.success(request);
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    R<IPage> page(@Valid PageRequest request, Entity entity) {
        QueryWrapper<Entity> queryWrapper = Wrappers.query(entity).orderByDesc("id");
        Page<Entity> res = service.page(new Page<>(request.getPageIndex(), request.getPageSize()), queryWrapper);
        return R.success(res);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    R<List> list(Entity entity) {
        return R.success(service.list());
    }

    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    R getById(@PathVariable("id") Long id) {
        return R.success(service.getById(id));
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    R<Boolean> delete(@PathVariable String id) {
        return R.success(service.removeById(id));
    }

}
