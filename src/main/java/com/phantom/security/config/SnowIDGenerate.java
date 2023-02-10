package com.phantom.security.config;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * 自定义id生成策略
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/2/9 11:34
 */
public class SnowIDGenerate implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return SnowFlakeUtil.getDefaultSnowFlakeId();
    }

}
