package com.alipay.riskops.core.model.constants;

/**
 * Key constants for the config_json column in risk_rule_config.
 * Each constant maps to a top-level (or nested) field name in the stored JSON.
 *
 * @author adam
 * @date 12/6/2026
 */
public final class RiskRuleConfigKeys {

    // ── Shared window structure ───────────────────────────────────────────────
    /** Array of sliding-window definitions: [{label, ttlSeconds}, ...] */
    public static final String WINDOWS = "windows";
    /** Seconds until a Redis key expires for this window. */
    public static final String TTL_SECONDS = "ttlSeconds";
    /** Scoring bands: [{score, severity, blockingSignal, <threshold fields>}, ...] */
    public static final String BANDS = "bands";

    // ── NEW_PAYEE_CHECK ───────────────────────────────────────────────────────
    /** Hours the new-payee counter key lives in Redis. */
    public static final String NEW_COUNT_TTL_HOURS = "newCountTtlHours";
    /** Days the known-payees set lives in Redis. */
    public static final String KNOWN_PAYEES_TTL_DAYS = "knownPayeesTtlDays";

    // ── FAILED_TXN_CHECK ─────────────────────────────────────────────────────
    /** Minutes the failed-transaction counter key lives in Redis. */
    public static final String TTL_MINUTES = "ttlMinutes";
    /** When true, a SUCCESS transaction deletes the failure counter. */
    public static final String RESET_ON_SUCCESS = "resetOnSuccess";
    /** Failed-count threshold above which the signal is treated as blocking. */
    public static final String BLOCKING_THRESHOLD = "blockingThreshold";

    // ── LARGE_AMOUNT_CHECK ───────────────────────────────────────────────────
    /** Transaction status that qualifies for accumulation (e.g. "SUCCESS"). */
    public static final String STATUS_FILTER = "statusFilter";

    // ── Shared key-building metadata ─────────────────────────────────────────
    /** Redis key prefix for this rule's counters. */
    public static final String KEY_PREFIX = "keyPrefix";
    /** Account dimension used as the Redis key segment (userId / payerAccountNo). */
    public static final String KEY_DIMENSION = "keyDimension";
    /** Transaction type this rule applies to (e.g. "TRANSFER", "TOP_UP"). */
    public static final String TXN_TYPE = "txnType";

    private RiskRuleConfigKeys() {
    }
}
