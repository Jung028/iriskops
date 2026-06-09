package com.alipay.riskops.rpc;

import com.alipay.sofa.rpc.context.RpcInvokeContext;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * Provider-side SOFA RPC filter that restores the caller's traceId into the
 * logging MDC for RPC calls, so logback's %X{traceId} is populated for
 * cross-service requests (the servlet TraceIdFilter does not run on the SOFA
 * REST/Netty path). Reads "traceId" from RPC baggage set by the caller.
 */
@Extension("traceIdProvider")
@AutoActive(providerSide = true)
public class TraceIdProviderFilter extends Filter {

    private static final String TRACE_ID = "traceId";

    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {
        try {
            String traceId = RpcInvokeContext.getContext().getRequestBaggage(TRACE_ID);
            if (traceId == null || traceId.isEmpty()) {
                traceId = UUID.randomUUID().toString();
            }
            MDC.put(TRACE_ID, traceId);
            return invoker.invoke(request);
        } finally {
            MDC.remove(TRACE_ID);
        }
    }
}
