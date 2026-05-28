package com.alipay.riskops.web;

import com.alipay.riskops.common.service.facade.api.RiskOpsService;
import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBizResult;
import com.alipay.riskops.common.service.facade.item.RiskOpsInfoItem;
import com.alipay.riskops.common.service.facade.result.QueryRiskOpsInfoRequest;
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
public class RiskOpsController {
    @Autowired
    private RiskOpsService riskopsService;

    @PostMapping("/queryRiskOpsInfo")
    public RiskOpsBizResult<RiskOpsInfoItem> queryRiskOpsInfo(@RequestBody QueryRiskOpsInfoRequest request) {
        return riskopsService.queryRiskOpsInfo(request);
    }
}