# iriskops — Developer Notes

## Observability Stack

### How traces reach Tracely (`http://localhost:3000/api/trace/{traceId}`)

Tracely does **not** read from OTLP/Tempo/Jaeger directly. It reads from its own
Prisma `span` table, which is populated on demand by calling:

```
POST http://localhost:3000/api/trace/{traceId}/ingest
```

That endpoint queries **Loki** for log lines containing `traceId=<id>`, builds
spans from them, and stores the result. So the full pipeline is:

```
iriskops logs (traceId=... in every line)
  → promtail scrapes /IdeaProjects/iriskops/logs/*.log
  → pushes to Loki (itracer-loki, port 3100)
  → POST /api/trace/{id}/ingest pulls from Loki
  → spans stored in Tracely Prisma DB
  → GET  /api/trace/{id} returns spans
```

### Prerequisites before a trace appears in Tracely

1. **itracer stack running** — start from `/Users/adam/VSCodeProjects/itracer`:
   ```bash
   docker compose up -d
   ```
   Key containers: `itracer-loki` (3100), `itracer-tempo` (4317/4318), `itracer-promtail`.

2. **Loki healthy** — promtail pushes to Loki. If Loki is crashing (ring/WAL
   issues), delete stale volume data and restart:
   ```bash
   docker compose stop loki promtail
   docker volume rm itracer_loki_data itracer_promtail_positions
   docker compose up -d loki promtail
   ```
   Verify: `curl http://localhost:3100/loki/api/v1/labels`

3. **iriskops logging to file** — logback must write to
   `/IdeaProjects/iriskops/logs/usercenter-web.log`. Promtail scrapes that path.

4. **Wait ~20s** for promtail to ship the logs to Loki after a new request.

5. **Trigger ingest**:
   ```bash
   curl -X POST http://localhost:3000/api/trace/{traceId}/ingest
   ```

### Loki config (`/Users/adam/VSCodeProjects/itracer/monitoring/loki-config.yaml`)

Key settings required to keep Loki stable on macOS Docker:
- `query_scheduler.use_scheduler_ring: false` — prevents interface-discovery crash
- `ingester.lifecycler.address: 127.0.0.1` and `join_after: 0s` — stops the
  ingester from staying in "empty ring" / "no tokens" state at startup
- `limits_config.per_stream_rate_limit: 32MB` — prevents 429s when promtail
  replays a large backlog of historical logs

### Trace publishing to Jaeger (for RCA / `find_jaeger_url`)

iriskops is configured to send OTLP directly to **Jaeger** via the VM parameter
in the IntelliJ run config (already set in `.idea/workspace.xml`):

```
-javaagent:$PROJECT_DIR$/../opentelemetry-javaagent.jar
-Dotel.traces.exporter=otlp
-Dotel.exporter.otlp.endpoint=http://localhost:4319
```

Port 4319 maps to Jaeger's OTLP HTTP port (configured in
`/IdeaProjects/iwallet/kafka-docker/docker-compose.yml`). Ports 4317/4318 are
occupied by `itracer-tempo` and cannot be used for Jaeger.

`application.properties` default:
```properties
otel.traces.exporter=${OTEL_TRACES_EXPORTER:none}   # off unless run config sets it
otel.exporter.otlp.endpoint=${OTEL_EXPORTER_OTLP_ENDPOINT:http://localhost:4319}
```

### Rule config cache (Redis)

`RiskRuleConfigProvider` caches rule config in Redis for 30 minutes under key
`risk:rule_config:{ruleCode}`. After a DB config change, evict the cache:

```bash
redis-cli DEL risk:rule_config:TRANSFER_VELOCITY_CHECK
```
