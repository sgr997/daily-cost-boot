package top.goku.dailycost.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.goku.dailycost.exception.DailyCostException;
import top.goku.dailycost.exception.ErrorEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2022/9/17 11:00
 */
@Slf4j
@Controller
@RequestMapping
public class IndexController {

    @RequestMapping({"/", "index", "index.html", "index.*"})
    void index(HttpServletResponse response) {
        try {
            response.sendRedirect("/s/index.html");
        } catch (IOException e) {
            log.error("重定向至首页失败", e);
            throw new DailyCostException(ErrorEnum.SYS_ERR);
        }
    }
}
