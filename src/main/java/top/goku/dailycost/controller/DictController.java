package top.goku.dailycost.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import top.goku.dailycost.annotation.ApiController;
import top.goku.dailycost.entity.Dict;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ZXP
 * @since 2023-04-17
 */
@ApiController
@RequestMapping("/dict")
public class DictController extends BaseController<Dict> {

}
