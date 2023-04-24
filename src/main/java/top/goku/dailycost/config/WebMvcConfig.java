package top.goku.dailycost.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.goku.dailycost.annotation.ApiController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2022/8/7 14:20
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login", "/api/user/register");
//                .excludePathPatterns("/index.html", "/swagger-resources", "/favicon.ico", "/v2/api-docs", "/webjars/**", "/doc.html", "/static/**");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
                .addPathPrefix("/api", clazz -> clazz.isAnnotationPresent(ApiController.class));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .maxAge(1800L)
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedOriginPatterns("*");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        /**
         * 1.需要定义一个convert转换消息的对象
         * 2.创建配置信息，加入配置信息：比如是否需要格式化返回的json
         * 3.converter中添加配置信息
         * 4.convert添加到converters当中
         */
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
//        supportedMediaTypes.add(MediaType.parseMediaType(MediaType.TEXT_HTML_VALUE + ";charset=utf-8"));
        fastJsonConverter.setSupportedMediaTypes(supportedMediaTypes);

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setSerializerFeatures(
                //结果是否格式化,默认为false
                SerializerFeature.PrettyFormat,
                //List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty,
                //是否输出值为null的字段,默认为false
				SerializerFeature.WriteMapNullValue,
                //字符类型字段如果为null,输出为"",而非null
				SerializerFeature.WriteNullStringAsEmpty,
                //数值字段如果为null,输出为0,而非null
//				SerializerFeature.WriteNullNumberAsZero,
                //Boolean字段如果为null,输出为false,而非null
                SerializerFeature.WriteNullBooleanAsFalse,
                //消除对同一对象循环引用的问题，默认为false
                SerializerFeature.DisableCircularReferenceDetect,
                //全局修改日期格式yyyy-MM-dd HH:mm:ss,默认为false
                SerializerFeature.WriteDateUseDateFormat,
//				//解决Long转json精度丢失的问题
                SerializerFeature.BrowserCompatible
        );
//		SerializeConfig serializeConfig = SerializeConfig.globalInstance;
//        serializeConfig.put(Long.class , ToStringSerializer.instance);
//        serializeConfig.put(Long.TYPE , ToStringSerializer.instance);
//        fastJsonConfig.setSerializeConfig(serializeConfig);

        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(0, fastJsonConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 开放static,templates,public 目录 但是请求时候需要加上对应的前缀,比如我访问static下的资源/static/xxxx/xx.js
        registry.addResourceHandler("/s/**").addResourceLocations("classpath:/static/dist/");
    }
}
