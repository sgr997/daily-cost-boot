package top.goku.dailycost.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import top.goku.dailycost.annotation.ApiController;
import top.goku.dailycost.common.UserContext;
import top.goku.dailycost.entity.Type;
import top.goku.dailycost.service.ITypeService;
import top.goku.dailycost.vo.R;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ZXP
 * @since 2023-04-18
 */
@ApiController
@RequestMapping("/type")
public class TypeController extends BaseController<Type> {

    @Autowired
    ITypeService typeService;

    @PostMapping
    @Override
    R add(@RequestBody @Valid Type request) {
        request.setUserId(UserContext.getCurrentUser().getId());
        return super.add(request);
    }

    @ApiOperation("初始化类型")
    @RequestMapping("/init")
    R init() {
        typeService.init();
        return R.success(Boolean.TRUE);
    }
}
