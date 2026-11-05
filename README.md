# ADLC Sample Backend

Java 21 / Spring Boot REST API for a deliberately small, in-memory task tracker. **Demo only; state is lost on restart.**

Endpoints: `GET /healthz`, `GET /api/tasks`, `GET /api/tasks?status=PENDING`, `POST /api/tasks`, and `POST /api/tasks/{id}/complete`.

```bash
mvn -B clean verify
mvn spring-boot:run
curl -sS http://localhost:8080/healthz
curl -sS -X POST http://localhost:8080/api/tasks -H 'Content-Type: application/json' -d '{"title":"Example","priority":"HIGH"}'
```

The worker marks pending items complete; the Next.js frontend proxies API requests. Use `adlc-sample-deploy` for the full integration demo.

This scaffold has intentionally **no authentication, durable database, or production credentials**. Do not expose it publicly.
