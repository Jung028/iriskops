package com.alipay.riskops.web;

import com.alipay.riskops.common.service.facade.api.RiskopsService;
import com.alipay.riskops.common.service.facade.baseresult.RiskopsBizResult;
import com.alipay.riskops.common.service.facade.item.RiskopsInfoItem;
import com.alipay.riskops.common.service.facade.result.QueryRiskopsInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author adam
 * @date 25/4/2026 6:36 PM
 */

@RestController
@RequestMapping("/riskops")
public class RiskopsController {
    @Autowired
    private RiskopsService riskopsService;

    @PostMapping("/queryRiskopsInfo")
    public RiskopsBizResult<RiskopsInfoItem> queryRiskopsInfo(@RequestBody QueryRiskopsInfoRequest request) {
        return riskopsService.queryRiskopsInfo(request);
    }
}