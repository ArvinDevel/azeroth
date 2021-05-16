import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 测试属性注入适用于可选依赖
 */
@Service
public class ServiceA {
    @Resource
    private ServiceB service;

}
