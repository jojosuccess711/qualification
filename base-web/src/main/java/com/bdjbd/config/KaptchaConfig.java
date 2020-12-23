package com.bdjbd.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author: dbc
 * @Date: 2018/9/6
 * @Version: 1.0
 * @Description:
 */
@Configuration
public class KaptchaConfig {

    /** 验证码宽度 */
    private static final String KAPTCHA_IMAGE_WIDTH = "kaptcha.image.width";
    /** 验证码高度 */
    private static final String KAPTCHA_IMAGE_HEIGHT = "kaptcha.image.height";
    /** 生成验证码内容范围 */
    private static final String KAPTCHA_TEXTPRODUCER_CHAR_STRING = "kaptcha.textproducer.char.string";
    /** 验证码个数 */
    private static final String KAPTCHA_TEXTPRODUCER_CHAR_LENGTH = "kaptcha.textproducer.char.length";
    /** 是否有边框 */
    private static final String KAPTCHA_BORDER = "kaptcha.border";
    /** 边框颜色 */
    private static final String KAPTCHA_BORDER_COLOR = "kaptcha.border.color";
    /** 边框厚度 */
    private static final String KAPTCHA_BORDER_THICKNESS = "kaptcha.border.thickness";
    /** 验证码字体颜色 */
    private static final String KAPTCHA_TEXTPRODUCER_FONT_COLOR = "kaptcha.textproducer.font.color";
    /** 验证码字体大小 */
    private static final String KAPTCHA_TEXTPRODUCER_FONT_SIZE = "kaptcha.textproducer.font.size";
    /** 验证码所属字体样式 */
    private static final String KAPTCHA_TEXTPRODUCER_FONT_NAMES = "kaptcha.textproducer.font.names";
    /** 干扰线颜色 */
    private static final String KAPTCHA_NOISE_COLOR = "kaptcha.noise.color";
    /** 验证码文本字符间距 */
    private static final String KAPTCHA_TEXTPRODUCER_CHAR_SPACE = "kaptcha.textproducer.char.space";
    /** 图片样式 */
    private static final String KAPTCHA_OBSCURIFICATOR_IMPL = "kaptcha.obscurificator.impl";

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put(KAPTCHA_IMAGE_WIDTH, "200");
        properties.put(KAPTCHA_IMAGE_HEIGHT, "50");
        properties.put(KAPTCHA_TEXTPRODUCER_CHAR_STRING, "345689ABCDEFGHKMNPRSTWXY");
        properties.put(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        properties.put(KAPTCHA_BORDER, "no");
        properties.put(KAPTCHA_BORDER_COLOR, "105,179,90");
        properties.put(KAPTCHA_BORDER_THICKNESS, 1);
        properties.put(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
        properties.put(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "40");
        properties.put(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "楷体");
        properties.put(KAPTCHA_NOISE_COLOR, "blue");
        properties.put(KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "4");
        properties.put(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.WaterRipple");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
