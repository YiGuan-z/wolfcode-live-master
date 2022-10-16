package cn.wolfcode.core.processor;

import cn.wolfcode.core.utils.BarrageMsgBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import cn.wolfcode.core.anno.BarrageAnnotation;

/**
 * @Desc 自定义消息注解处理类
 **/
@Component
@Slf4j
public class BarrageAnnotationPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        BarrageAnnotation annotation = AnnotationUtils.findAnnotation(bean.getClass(), BarrageAnnotation.class);
        if (null == annotation) {
            return bean;
        }
        String msgType = annotation.msgType();
        if (BarrageMsgBeanUtils.exist(msgType)) {
            throw new RuntimeException(msgType + "相同类型的Bean已经存在，请重新调整");
        }
        BarrageMsgBeanUtils.addMsgType(msgType, beanName);
        log.info("[BarrageAnnotationPostProcessor]-[postProcessAfterInitialization]-[添加消息类型到缓存中]-[msgType:{}]", msgType);
        return bean;
    }
}
