package com.alipay.riskops.common.service.facade.api;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBizResult;
import com.alipay.riskops.common.service.facade.item.BankAccountItem;
import com.alipay.riskops.common.service.facade.item.BankConnectionSessionItem;
import com.alipay.riskops.common.service.facade.item.RiskopsInfoItem;
import com.alipay.riskops.common.service.facade.item.RiskopsSettingsItem;
import com.alipay.riskops.common.service.facade.item.SyncBankTransactionsResult;
import com.alipay.riskops.common.service.facade.request.RiskDecisionRequest;
import com.alipay.riskops.common.service.facade.result.QueryRiskopsInfoRequest;
import com.alipay.riskops.common.service.facade.result.RiskDecisionResult;
import com.alipay.riskops.common.service.facade.result.SyncBankTransactionsRequest;
import com.alipay.riskops.common.service.facade.result.UpdateRiskopsSettingsRequest;
import com.alipay.riskops.common.service.facade.request.CreateRiskopsAccountRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author adam
 * @date 25/4/2026 6:11 PM
 */

@Path("/riskopsService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RiskopsService {

    @POST
    @Path("evaluateTransferRisk")
    RiskOpsBizResult<RiskDecisionResult> evaluateTransferRisk(RiskDecisionRequest request);
}
