package com.alipay.riskops.common.service.facade.api;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseResult;
import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBizResult;
import com.alipay.riskops.common.service.facade.request.RiskDecisionRequest;
import com.alipay.riskops.common.service.facade.result.RiskDecisionResult;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author adam
 * @date 25/4/2026 6:11 PM
 */

@Path("/riskOpsService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RiskOpsService {

    @POST
    @Path("evaluateTransferRisk")
    RiskOpsBizResult<RiskDecisionResult> evaluateTransferRisk(RiskDecisionRequest request);
}
