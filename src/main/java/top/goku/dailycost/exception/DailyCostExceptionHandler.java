package top.goku.dailycost.exception;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.JWTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.goku.dailycost.vo.R;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2022/8/6 21:41
 */
@Slf4j
@RestControllerAdvice
public class DailyCostExceptionHandler {

    @ExceptionHandler(DailyCostException.class)
    public Object exception(DailyCostException exception) {
        return R.err(exception.getErrorEnum(), exception.getMessage());
    }

    @ExceptionHandler({ValidateException.class, JWTException.class})
    public Object validateException(Exception exception) {
        return R.err(ErrorEnum.TOKEN_INVALID);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Object exception(MethodArgumentNotValidException exception) {
        StringBuilder message = new StringBuilder();
        List<ObjectError> allErrors = exception.getAllErrors();
        for (ObjectError allError : allErrors) {
            if (allError instanceof FieldError) {
                message.append(((FieldError) allError).getField()).append(":");
            }
            message.append(allError.getDefaultMessage()).append(",");
        }
        return R.err(ErrorEnum.PARAMS_ERROR, message.substring(0, message.length() - 1));
    }


    @ExceptionHandler(Exception.class)
    public Object exception(Exception exception) {
        log.error("系统异常:{}", exception.getMessage(), exception);
        return R.err(ErrorEnum.SYS_ERR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object exception(HttpRequestMethodNotSupportedException exception) {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String requestURI = request.getRequestURI();
            log.error("用户请求异常:{}，requestURI:{}", exception.getMessage(), requestURI);
        } catch (Exception e) {
            log.error("用户请求异常:{}", exception.getMessage(), exception);
        }
        return R.err(ErrorEnum.SYS_ERR);
    }
}
