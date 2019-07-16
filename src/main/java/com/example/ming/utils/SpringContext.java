package com.example.ming.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by lihe on 2016/6/9.
 */
@Service
public class SpringContext implements ApplicationContextAware {
    //private static final Logger LOGGER = LoggerFactory.getLogger(SpringContext.class);

    private static ApplicationContext context;

    public static Object getBean(String beanId) {
        if (context.containsBean(beanId)) {
            return context.getBean(beanId);
        }
        return null;
    }

    public static <T> T getBeanByClass(Class<T> requiredClz) {
        return context.getBean(requiredClz);
    }

    /**
     * @return the applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.context = applicationContext;
    }


}