package com.alipay.riskops.common.service.facade.auto.daointerface.custom;

import com.alipay.riskops.common.service.facade.auto.dataobject.test;
import java.util.List;

public interface TestMapperDAO {
    // Custom query selecting specific columns
    List<test> selectAgeAndPaymentId();

    // Another example: query with condition
    List<test> selectByStatus(String status);
}
