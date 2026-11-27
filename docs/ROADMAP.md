# Roadmap

Last reviewed: 2026-11-27
Roadmap status: `active high-level sequencing; Travel Build complete; no later implementation milestone active`

## Snapshot
- Travel Build v0 reached its bounded acceptance boundary and is committed/pushed.
- Post-Travel source audit exposed current transport/persistence/retry/platform/privacy/release debt that must be accounted for before those paths are relied upon as production-grade behavior.
- The next major architectural frontier is durable local history, but implementation is not authorized by this consolidation.
- Milestones below describe objectives, dependencies, scope boundaries, evidence expectations, and what they enable. Later ordering remains revisable where co-design reveals a better dependency.

## Cross-Milestone Rules
1. **Canonical history before transport dependence.** No later protocol/backend decision may make successful network delivery a prerequisite for durable local capture.
2. **Co-design does not mean scope collapse.** Sync, backend, device identity, authorization, deployment trust, and storage lifecycle may need joint contract design while remaining separate implementation milestones.
3. **Donor behavior is evidence, not permanent architecture.** Preserve proven Android behavior until replacement is explicitly specified/tested, but do not inherit HTTP/MQTT/Recorder/data models merely because they exist.
4. **Future technology remains open unless a decision log entry settles it.** Distant milestones preserve requirements and open questions rather than speculative implementation detail.
5. **Evidence gates matter.** Scale, lifecycle, migration, security, and power-loss claims require representative tests rather than configuration values or happy-path demos.
6. **Core autonomy.** No milestone may quietly introduce a mandatory proprietary cloud/subscription dependency for core operation.

## Readiness Gate A — Current Travel Hardening And Characterization
This is a bounded readiness gate, not a new long-term architecture milestone and not permission to redesign the donor transport stack.

### Objective
Know the real safety envelope of the Travel build and remove/contain defects that directly threaten data-integrity testing or current travel use before deeper history work depends on those paths.

### Prerequisites
- Travel Build v0 complete.
- Post-Travel audit findings reconciled into failures/risks.

### In scope when explicitly planned
- correct/define sender retry/head-of-line behavior;
- propagate queue-persistence outcome rather than reporting false publish/capture success;
- reconcile Android foreground-service type/permission with location work;
- establish a safer constrained diagnostic path for external tooling while preserving rich owner diagnostics;
- realistic synthetic scale characterization through the configured queue envelope, including storage/latency/startup/reboot/drain/failure/low-disk behavior;
- proportionate CI/static/release-check hygiene needed to trust subsequent changes.

### Explicitly out of scope
- turning the old queue into permanent history;
- implementing backend/sync/circles/passkeys;
- major UI redesign;
- optimizing the donor queue indefinitely if evidence says replacement is the right solution.

### Completion evidence
Focused regression tests for fixed defects; synthetic benchmark report with realistic datasets and multiple scales; relevant S24/OEM tests where platform lifecycle/battery is material; no overclaim that synthetic scale proves real-world acquisition endurance.

### Enables
A measured current-travel safety envelope and a cleaner foundation for Durable Local History v1.

## Milestone 1 — Travel-Safe Donor Build — COMPLETE
### Objective
Protect queued travel data from capacity-driven eviction and create a separately installable Travel identity while preserving donor acquisition/transport behavior.

### Outcome
Complete with explicit limitations; see `plans/ACTIVE/2026-11-24_travel-build-v0.md`. It is not permanent history.

## Milestone 2 — Durable Local History v1
### Objective
Make the recording device a trustworthy protocol-independent historical recorder: accepted original observations become durable locally before any transport dependency.

### Prerequisites / co-design
- Readiness Gate A findings addressed or explicitly accepted/deferred.
- Canonical observation, durable capture, identity/lineage, provenance, and local-history/transport boundaries from `docs/PLATFORM_ARCHITECTURE.md`.
- Storage/encryption choices that materially affect the schema are researched enough to avoid irreversible traps; final future compression/reduction algorithms are not required.

### In scope
- canonical original-observation model;
- stable logical IDs + ordering/lineage/provenance foundation;
- durable local commit and explicit persistence-failure semantics;
- isolated history ownership independent of transport queue/configuration;
- migrations/integrity handling and preservation-safe failure behavior;
- append and time/range access sufficient for future local timeline and sync scans;
- local storage accounting and benchmarkable schema/index behavior;
- failure injection/process death/reboot/low-disk tests;
- enough tooling to inspect/verify history without exposing private data to external tools by default.

### Explicitly out of scope
- final sync protocol or server;
- HTTP/WebSocket/MQTT role decision;
- offloading engine, trajectory reduction, adaptive sampling, or forecasting UI;
- accounts/passkeys/circles/backend implementation;
- Recorder migration execution;
- sensor fusion/ML.

### Completion evidence
Durable commit contract proven across process/restart/reboot/failure cases; endpoint/protocol/transport queue changes cannot destroy canonical history; representative scale/query/storage benchmarks; schema migration/integrity tests; explicit unresolved storage/encryption decisions preserved.

### Enables
Safe synchronization design, local history UX work, migration/import targets, and future storage-lifecycle features.

## Milestone 3 — High-Throughput Resumable Synchronization
### Objective
Synchronize canonical records safely and efficiently while current location remains high priority during large backlogs.

### Prerequisites / co-design
- Stable local record identity/order/provenance from Milestone 2.
- Server transaction/acknowledgement semantics co-designed with Milestone 4.
- Device authentication/upload identity contract co-designed with Milestone 5 before production authentication is frozen.

### In scope
- at-least-once transmission + idempotent logical ingest contract;
- durable checkpoints/ack state;
- separate live-priority and historical-backfill scheduling semantics;
- batching/compression experiments and recovery from interrupted/ambiguous delivery;
- duplicate/omission/order/reconciliation tests;
- mock/prototype endpoint acceptable before full backend if contract is explicit.

### Out of scope
- full account/circle product;
- final backend administration/UI;
- assuming MQTT/HTTP/WebSocket roles without evaluation.

### Completion evidence
Failure-injection proves safe resend after lost ACK; no duplicate logical records; current live state not starved by backlog; restart/resume/checkpoint correctness; reconciliation detects inconsistency.

### Enables
Efficient multi-device/server history movement and backend ingestion at realistic scale.

## Milestone 4 — Authoritative Shared Backend And Database
### Objective
Implement the portable self-hosted service and PostgreSQL/PostGIS data layer for durable ingest, history queries, shared state, audit, retention controls, and administration APIs.

### Prerequisites / co-design
- Sync transaction/ACK contract from Milestone 3.
- Identity/authorization/device contracts co-designed with Milestone 5 before externally exposed APIs stabilize.
- Deployment identity/host mobility requirements understood.

### In scope
- PostgreSQL/PostGIS schema/index/partition/growth design based on workload evidence;
- durable idempotent ingestion and history query APIs;
- audit/security event foundation;
- backup/restore and migration procedures;
- local-LAN operation without mandatory public internet;
- Linux amd64/arm64 portability measurements in representative environments.

### Out of scope
- treating current host/domain as server identity;
- mandatory Cloudflare/proprietary managed services;
- circles UI or full product shell.

### Completion evidence
Transactional ingest/ACK semantics, restore drills, migration tests, representative volume/query benchmarks, abrupt-power/process tests where applicable, portable deployment on at least two meaningful host classes over time.

### Enables
Shared cross-device history, remote access, robust migrations, and identity/circle product layers.

## Milestone 5 — Identity, Passkeys, Device Credentials, Recovery, Authorization
### Objective
Establish human and device trust independently, with phishing-resistant login, per-device credentials, revocation, recovery, and server-enforced authorization.

### Prerequisites / co-design
- Backend API/security boundaries from Milestone 4.
- Upload provenance/ack contract from Milestone 3.
- Portable/offline deployment identity and TLS/relying-party implications researched.

### In scope
Passkeys/WebAuthn primary auth, multiple authenticators/recovery, optional TOTP/step-up, per-device key enrollment/rotation/revocation, short-lived sessions/tokens, authorization/audit rules, server-deployment trust/key-rotation design.

### Out of scope
Circles product policy beyond authorization primitives; SMS-first MFA; shared master device secret.

### Completion evidence
Threat model, enrollment/revocation/recovery tests, server-side authorization tests, lost-device/admin recovery drills, no UI-only authorization.

### Enables
Safe multi-user/circle features and trustworthy device provenance.

## Milestone 6 — Modern LateralLine Mobile Shell And Intentional UX
### Objective
Evolve the donor UI into a LateralLine product shell without regressing proven acquisition/history behavior.

### Prerequisites
Stable history/service/state contracts from earlier milestones; UI architecture decision OQ-0007 sufficiently resolved.

### In scope
Navigation/shell, accessibility, clear security/sync/storage/fidelity health, map/history/device/account surfaces at an appropriate foundation level.

### Out of scope
Rewriting validated acquisition/storage merely for UI purity; copying Life360; hiding authorization as a security control.

### Evidence
Accessibility/UX tests, state/error handling, migration from donor screens without lifecycle regressions.

### Enables
Higher-level history/device/circle experiences.

## Milestone 7 — Device Management And History Exploration
### Objective
Expose trustworthy local/server history, device provenance/status, timeline/range exploration, precise date/time access, route/journey views, export and integrity/fidelity state.

### Prerequisites
Durable history, server/query APIs, identity/device model, mobile shell.

### In scope
Local-first access to locally stored history even when server unreachable; cross-device/server views when connected; provenance/fidelity visibility.

### Evidence
Large-history query/performance tests, offline local access, device provenance correctness, export/reimport validation.

### Enables
Mature personal-history product and storage lifecycle UX.

## Milestone 8 — Trusted Circles And Granular Sharing
### Objective
Allow multiple overlapping circles and individual grants for live/history/other shared information while data ownership remains with the person/device domain.

### Prerequisites
Identity/authz, backend, mobile shell, history model. OQ on permission composition/precedence resolved.

### In scope
Membership/invitation/revocation, granular scopes, overlapping circles, live and historical authorization, audit-sensitive actions.

### Out of scope
Circle ownership of records; client/UI-only authorization.

### Evidence
Authorization matrix/abuse-case tests, revoke/change propagation, overlap/precedence tests, audit evidence.

### Enables
Family/friend/multi-user product use.

## Milestone 9 — Security Hardening And Publication-Quality Operations
### Objective
Move from private/development deployment to publication-quality operational security/release discipline.

### In scope
Threat-model closure, release signing/update pipeline, CI required checks, secret handling, backup encryption/rotation, logging/tooling boundaries, rate limits, incident/admin operations, dependency/supply-chain review, licensing/open-source packaging.

### Evidence
Security tests/review, restore/recovery/rotation drills, release reproducibility/signing/CI gates, documented operational runbooks.

### Enables
Responsible wider deployment/distribution.

## Milestone 10 — Recorder Compatibility/Migration And OwnTracks Dependency Reduction
### Objective
Safely migrate the owner's existing long-term OwnTracks/Recorder history and reduce donor/backend dependency without disrupting ongoing recording.

### Prerequisites
Canonical import model, mature backend/history, reconciliation tools, stable ongoing capture/sync.

### In scope
Immutable/checksummed source archives, import manifests/provenance, deterministic duplicate handling, count/time-range reconciliation, rerunnable/interrupted imports, delta/catch-up/dual-run/cutover, compatibility adapters where useful.

### Out of scope
Big-bang source deletion; deleting the only source archive after normalization.

### Evidence
Migration dry runs, reconciliation reports, rollback/cutover tests, no recording blind spot.

### Enables
Independent LateralLine backend/history while retaining useful ecosystem interoperability.

## Milestone 11 — Storage Lifecycle, Fidelity, Forecasting, And Emergency Recovery
### Objective
Manage finite device storage intelligently without confusing local copy management with global retention.

### Prerequisites
Mature durable history + server archive/ack state + local history UX. Exact storage representation must support real byte reclamation.

### In scope
Proactive forecasting/headroom; lossless compaction; configurable lossless local offload; pinned/protected ranges; lightweight local derived representations; rehydration; manual two-pass destructive reduction; cumulative fidelity/provenance; optional adaptive future-sampling policy research.

### Out of scope
Automatic destructive simplification; server-side lossy retention as a consequence of local pressure; uncalibrated “confidence” claims.

### Evidence
Synthetic/real trajectory benchmarks, actual bytes recovered vs temporary working space, repeated-reduction cumulative error tests, uncertainty preservation, low-storage emergency tests, server copies unaffected by local operations.

### Enables
Long offline trips with controlled storage degradation rather than abrupt recording loss.

## Milestone 12 — Advanced Sensor Recording And 3D Trajectory Research
### Objective
Capture and evaluate GNSS/raw-GNSS, altitude/vertical accuracy, barometer, inertial, activity and other sensor signals that may improve interpretation.

### Prerequisites
Stable canonical/provenance model and storage/battery measurement framework.

### Evidence
Battery/storage/thermal measurements, sensor timing/calibration/provenance, clear distinction between observation and inference.

### Enables
Richer trajectory inference and later fusion research.

## Milestone 13 — Sensor Fusion, Higher-Resolution Inference, And Independent-Platform Maturity
### Objective
Research map matching, pedestrian dead reckoning, sensor fusion, higher-resolution trajectory inference and broader platform maturity without overclaiming physical precision.

### Prerequisites
Sensor/history/provenance foundation and benchmark corpus.

### Evidence
Ground-truthed accuracy/uncertainty evaluation, reproducible model versions, failure/ambiguity analysis, platform-specific lifecycle validation.

## Sequencing Caveat
Only Travel Build v0 is complete. Readiness Gate A and Milestones 2+ require explicit owner authorization and active plans. Numbers express a default dependency direction, not permission to implement later work automatically. Contract design may overlap where necessary, but implementation scopes should remain bounded.
