# Open Questions

Unresolved questions must remain visible rather than being silently answered by assumption. Entries stay in ascending ID order; update status/resolution in place without deleting history.

## Entry Template
### OQ-0000: `QUESTION_TITLE`
- Date recorded (UTC):
- Last updated (UTC):
- Status: `open | resolved | deferred | obsolete`
- Question:
- Why it matters:
- Current evidence:
- Needed evidence or decision:
- Owner:
- Resolution:
- Recorded by:

## Questions
### OQ-0001: `Backend framework and service boundaries`
- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-24
- Status: open
- Question: Which language/framework and service decomposition should implement the authoritative backend?
- Why it matters: affects security, operations, maintainability, and client contracts.
- Current evidence: backend capabilities and PostgreSQL direction are decided; framework is not.
- Needed evidence or decision: requirements matrix and small architecture evaluation.
- Owner: project owner
- Resolution: pending
- Recorded by: Codex

### OQ-0002: `PostgreSQL/PostGIS schema and growth strategy`
- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-24
- Status: open
- Question: What schema, indexing, partitioning, migration, and archival model fits high-resolution multi-device history?
- Why it matters: correctness, query performance, retention, and long-term operability.
- Current evidence: database direction is decided; schema is not.
- Needed evidence or decision: workload assumptions, query requirements, prototype measurements.
- Owner: future backend architecture plan
- Resolution: pending
- Recorded by: Codex

### OQ-0003: `Encryption-at-rest and key-management boundaries`
- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-24
- Status: open
- Question: Which local, database, field, filesystem, backup, and archive layers need encryption, and how are keys rotated/recovered?
- Why it matters: location history and backups are highly sensitive.
- Current evidence: encrypted backups and rotation support are decided concerns; exact boundaries are not.
- Needed evidence or decision: threat model and operational recovery requirements.
- Owner: security architecture plan
- Resolution: pending
- Recorded by: Codex

### OQ-0004: `Batch encoding, compression, transport roles, and checkpoint protocol`
- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-27
- Status: open
- Question: Given the decided at-least-once/idempotent/durable-ACK and live-vs-backfill semantics, what wire encoding, compression, batch sizing, checkpoint representation, retry scheduling, and HTTPS/WebSocket/MQTT role split should synchronization use?
- Why it matters: throughput, mobile energy, ambiguous failure recovery, live freshness, operational complexity, and interoperability.
- Current evidence: stable record identity + at-least-once transport + idempotent server ingest + durable ACK/checkpoint direction is decided in DEC-0016. Current OwnTracks HTTP/MQTT remains compatibility evidence only.
- Needed evidence or decision: local-history schema/ordering identity, server transaction semantics, representative backlog/live benchmarks, mobile power/reconnect measurements, compatibility requirements.
- Owner: future synchronization plan
- Resolution: mechanism pending; correctness semantics already decided and must not be reopened casually.
- Recorded by: Codex; refined by ChatGPT consolidation under owner instruction

- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-24
- Status: open
- Question: What wire encoding, compression, batch sizing, idempotency key, ordering, and checkpoint design should historical sync use?
- Why it matters: throughput and failure-safe recovery.
- Current evidence: required properties are decided; mechanism is open.
- Needed evidence or decision: local-history schema and representative throughput tests.
- Owner: future synchronization plan
- Resolution: pending
- Recorded by: Codex

### OQ-0005: `Device enrollment, attestation, revocation, and recovery ceremony`
- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-24
- Status: open
- Question: How are device keys enrolled, authenticated, rotated, revoked, restored, and linked to a person/account?
- Why it matters: device independence is a security invariant.
- Current evidence: per-device identity and Android Keystore direction are decided.
- Needed evidence or decision: threat model, UX flows, platform capability assessment.
- Owner: identity/security architecture plan
- Resolution: pending
- Recorded by: Codex

### OQ-0006: `Human account recovery model`
- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-24
- Status: open
- Question: How do multiple passkeys, recovery codes, optional TOTP, lost-device recovery, and sole-admin recovery interact?
- Why it matters: recovery must not undermine phishing resistance or cause irrecoverable lockout.
- Current evidence: authentication direction is decided; ceremony is not.
- Needed evidence or decision: abuse cases and recovery threat model.
- Owner: identity/security architecture plan
- Resolution: pending
- Recorded by: Codex

### OQ-0007: `Mobile UI architecture evolution`
- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-24
- Status: open
- Question: How should the donor Android UI/framework evolve into the intentional LateralLine product shell?
- Why it matters: independence, accessibility, migration cost, and donor behavior preservation.
- Current evidence: product qualities and illustrative navigation are known; implementation approach is not.
- Needed evidence or decision: UX research, current architecture assessment, migration options.
- Owner: future mobile product plan
- Resolution: pending
- Recorded by: Codex

### OQ-0008: `iOS strategy`
- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-24
- Status: open
- Question: Whether, when, and how should LateralLine support iOS?
- Why it matters: multi-device platform scope and protocol design.
- Current evidence: current implementation is Android-only.
- Needed evidence or decision: user demand, platform constraints, resource/timeline assessment.
- Owner: project owner
- Resolution: pending
- Recorded by: Codex

### OQ-0009: `Deployment topology, local discovery, and portable/offline operation`
- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-27
- Status: open
- Question: Which host classes are supported first, how do clients discover/reach the same trusted deployment across LAN/public/mobile addresses, and what Cloudflare/origin/reverse-proxy topology is adopted without making public internet mandatory?
- Why it matters: PC→Pi/VPS/portable migration, LAN-only operation, TLS/passkey identity, battery/power constraints, and supportability.
- Current evidence: DEC-0019 decides open/self-hosted independence, supported-Linux portability direction, optional Cloudflare/proxy, and deployment identity independent of host/address. Portable/off-grid ARM64 is a real future validation target.
- Needed evidence or decision: initial host matrix, local discovery/TLS approach, relying-party implications, ARM64/PostgreSQL power/performance measurements, edge exposure threat model.
- Owner: future deployment/identity plans
- Resolution: pending; public endpoint is not allowed to become deployment identity.
- Recorded by: Codex; refined by ChatGPT consolidation under owner instruction

- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-24
- Status: open
- Question: Which host classes are supported first, and what Cloudflare/origin/reverse-proxy topology is adopted?
- Why it matters: portability, supportability, TLS, and exposure boundaries.
- Current evidence: portable Linux deployment and optional-proxy boundary are decided.
- Needed evidence or decision: operational requirements and threat model.
- Owner: future deployment plan
- Resolution: pending
- Recorded by: Codex

### OQ-0010: `OwnTracks/Recorder compatibility and migration guarantees`
- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-27
- Status: open
- Question: Which OwnTracks/Recorder behaviors remain supported, for how long, and what exact incremental migration/reconciliation contract safely imports the owner's existing year-plus history while recording continues?
- Why it matters: valuable long-lived history must survive transition without indefinite donor lock-in or a big-bang outage.
- Current evidence: Recorder is transitional; immutable/checksummed source archives, provenance, rerunnable/interrupted import, deterministic duplicate handling, count/time-range reconciliation, catch-up/delta/dual-run where useful, and no deletion of the only source copy are now decided requirements.
- Needed evidence or decision: current Recorder data/export inventory, source identifiers/precision semantics, import mapping, reconciliation metrics, cutover/rollback acceptance, compatibility duration.
- Owner: future compatibility/migration plan
- Resolution: mechanism pending; preservation/incremental-migration requirements decided.
- Recorded by: Codex; refined by ChatGPT consolidation under owner instruction

- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-24
- Status: open
- Question: Which protocol behaviors remain supported, for how long, and how is existing Recorder history migrated/validated?
- Why it matters: transition safety without indefinite donor lock-in.
- Current evidence: compatibility is useful and Recorder is transitional.
- Needed evidence or decision: current infrastructure inventory and migration acceptance criteria.
- Owner: future compatibility/migration plan
- Resolution: pending
- Recorded by: Codex

### OQ-0011: `Local archive representation, retention options, and physical reclamation`
- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-27
- Status: open
- Question: What storage representation, indexing/segmenting, lossless encoding, durability mode, retention controls, and reclamation strategy best support full-fidelity local history, queries, migrations, sync scanning, and future offload/reduction?
- Why it matters: full original history is preserved locally indefinitely by default within physical limits, but storage/query/recovery performance and actual byte reclamation depend on representation.
- Current evidence: default full-fidelity retention, no deletion merely because of sync, and explicit user control over destructive retention are decided. SQLite row deletion may not shrink the file; future storage lifecycle requires real reclaim behavior and bounded working space.
- Needed evidence or decision: representative volume/query/append benchmarks, failure/power-loss tests, SQLite/Room vs segmented/chunk/hybrid prototypes where justified, encryption-at-rest implications, storage forecast/reclamation tests.
- Owner: Durable Local History / future storage-lifecycle plans
- Resolution: retention principle decided; physical representation and optional policies pending.
- Recorded by: Codex; refined by ChatGPT consolidation under owner instruction

- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-24
- Status: open
- Question: What are default/per-user retention rules and the durable local/export archive format?
- Why it matters: privacy, storage growth, backup, and user control.
- Current evidence: durable history is required; retention details are not decided.
- Needed evidence or decision: product policy, legal/privacy review as applicable, volume estimates.
- Owner: project owner and future data architecture plan
- Resolution: pending
- Recorded by: Codex

### OQ-0012: `Advanced sensor logging and adaptive-capture architecture`
- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-27
- Status: deferred
- Question: Which GNSS/raw-GNSS/inertial/barometric/activity data should be captured, at what rates, and how should optional adaptive future sampling use context while preserving explicit fidelity/provenance?
- Why it matters: later trajectory inference and storage/battery optimization may benefit, but adaptive sampling changes observations that are never collected and therefore is a distinct fidelity policy.
- Current evidence: original observations and derived interpretations are separate; adaptive sampling is not an automatic-storage-cleanup loophole and requires explicit user policy. Sensors remain beyond core history/sync work.
- Needed evidence or decision: post-core sensor research plan, device battery/storage/thermal measurements, activity/sensor reliability, provenance model.
- Owner: future research/storage-lifecycle plan
- Resolution: deferred beyond core platform milestones.
- Recorded by: Codex; refined by ChatGPT consolidation under owner instruction

- Date recorded (UTC): 2026-11-24
- Last updated (UTC): 2026-11-24
- Status: deferred
- Question: Which GNSS/inertial/barometric/activity/raw-sensor data should be captured, at what rate, and under what battery/privacy constraints?
- Why it matters: later trajectory inference may benefit, but cost and accuracy limits are substantial.
- Current evidence: sensors are future research and explicitly excluded from Travel Build v0.
- Needed evidence or decision: post-core-platform research plan and device measurements.
- Owner: future research plan
- Resolution: deferred beyond core platform milestones
- Recorded by: Codex

### OQ-0013: `Canonical local-history storage engine and durability contract`
- Date recorded (UTC): 2026-11-27
- Last updated (UTC): 2026-11-27
- Status: open
- Question: Which concrete local storage engine/schema/transaction/WAL/fsync/integrity design satisfies the decided durable-capture boundary and future query/reclamation needs?
- Why it matters: “database API returned” is not automatically equivalent to survival under process death/power loss, and storage design affects migrations, scale, encryption, recovery and future storage lifecycle.
- Current evidence: DEC-0015 defines durable-capture semantics; OQ-0011 owns representation/reclamation; Travel queue is not suitable authority.
- Needed evidence or decision: small prototypes/benchmarks, Android storage behavior, failure injection, power-loss/reboot tests, migration/encryption evaluation.
- Owner: Durable Local History v1 plan
- Resolution: pending
- Recorded by: ChatGPT consolidation

### OQ-0014: `Record identity, ordering, and recording-lineage representation`
- Date recorded (UTC): 2026-11-27
- Last updated (UTC): 2026-11-27
- Status: open
- Question: What exact ID/sequence/lineage/timestamp scheme prevents collisions and ambiguous ordering across clock changes, restores, clones, reinstall, device replacement and imports?
- Why it matters: idempotent sync, provenance, reconciliation and history ordering depend on stable identity independent of timestamps.
- Current evidence: DEC-0015 requires stable unique record identity, origin device, ordering information, capture time, and a lineage/epoch distinction. Exact algorithms/fields are intentionally open.
- Needed evidence or decision: restore/clone/import scenarios, UUID/sequence design analysis, Android clock sources, migration compatibility.
- Owner: Durable Local History / sync identity co-design
- Resolution: pending
- Recorded by: ChatGPT consolidation

### OQ-0015: `Long-term communication architecture and protocol roles`
- Date recorded (UTC): 2026-11-27
- Last updated (UTC): 2026-11-27
- Status: open
- Question: Which roles should HTTPS, WebSockets, MQTT, or other protocols play for native ingestion, live viewers, history/query/management APIs, and compatibility hardware?
- Why it matters: reliability, energy, performance, privacy/security, operational complexity, and extensibility differ by role.
- Current evidence: DEC-0016 decides transport-independent records, live-vs-backfill semantics, and evidence-driven selection. HTTPS/WebSocket/MQTT roles discussed are candidates, not commitments.
- Needed evidence or decision: realistic mobile reconnect/power/load benchmarks, backend operational evaluation, OwnTracks/IoT compatibility requirements.
- Owner: synchronization/backend architecture plans
- Resolution: pending
- Recorded by: ChatGPT consolidation

### OQ-0016: `Intelligent storage reduction and fidelity model`
- Date recorded (UTC): 2026-11-27
- Last updated (UTC): 2026-11-27
- Status: open
- Question: What trajectory analysis, scoring, cumulative-error representation, journey closure, preview UX, and bounded-resource algorithm should power optional manual local destructive reduction?
- Why it matters: the system should recover only required bytes with minimum information loss, remain honest about fidelity, and support repeated offline reductions without treating points as independent.
- Current evidence: DEC-0017 and `PLATFORM_ARCHITECTURE.md` decide the two-pass/manual/cumulative-loss/provenance boundaries. Geometry, SED/BQS-family methods, map/activity/sensor context, hierarchies and local ML are research candidates only.
- Needed evidence or decision: benchmark corpus, information-loss metrics, calibrated score semantics, collective-reduction algorithms, resource limits, remote-original/hierarchy strategy for repeated reduction, user research.
- Owner: future Storage Lifecycle & Fidelity milestone
- Resolution: pending
- Recorded by: ChatGPT consolidation

### OQ-0017: `Technically enforced external-tool diagnostic boundary`
- Date recorded (UTC): 2026-11-27
- Last updated (UTC): 2026-11-27
- Status: open
- Question: What platform/app/tooling mechanism gives Codex/AI enough safe observability while technically preventing routine access to private personal diagnostics and secrets on the owner's device?
- Why it matters: unrestricted ADB/run-as/logcat/file/UI access defeats instruction-only privacy boundaries, while over-redaction would cripple debugging.
- Current evidence: FAIL-0002 recurrence; DEC-0018; current debug logs can expose detailed values.
- Needed evidence or decision: diagnostic API/export design, debuggable/release capability model, synthetic-device workflow, least-privilege ADB/tooling options.
- Owner: security/privacy tooling plan
- Resolution: pending
- Recorded by: ChatGPT consolidation

### OQ-0018: `Deployment identity, endpoint mobility, local discovery, TLS and key rotation`
- Date recorded (UTC): 2026-11-27
- Last updated (UTC): 2026-11-27
- Status: open
- Question: How does a client recognize the same trusted LateralLine deployment across domain/LAN/IP/host migration, local-only operation and secure key rotation without binding identity to one address or eternal key?
- Why it matters: portable/self-hosted/offline operation intersects TLS, passkeys, device trust and disaster recovery.
- Current evidence: DEC-0019 settles the identity/address separation and secure-rotation requirement, not the mechanism.
- Needed evidence or decision: threat model, local discovery options, certificate/key lineage, WebAuthn relying-party constraints, migration/recovery ceremonies.
- Owner: deployment + identity/security co-design
- Resolution: pending
- Recorded by: ChatGPT consolidation

### OQ-0019: `Circle permission composition and precedence`
- Date recorded (UTC): 2026-11-27
- Last updated (UTC): 2026-11-27
- Status: open
- Question: How do overlapping circle grants, individual grants, explicit denies, live/history scopes, temporary sharing and revocation compose when a person belongs to multiple relationships?
- Why it matters: authorization must be predictable and server-enforced without making circles owners of data.
- Current evidence: person/account/device distinction, overlapping circles, granular sharing, and server-side authorization are decided; precedence language is not.
- Needed evidence or decision: abuse cases, UX expectations, policy model comparison, authorization test matrix.
- Owner: circles/authorization plan
- Resolution: pending
- Recorded by: ChatGPT consolidation

### OQ-0020: `Ecosystem licensing and optional proprietary integration policy`
- Date recorded (UTC): 2026-11-27
- Last updated (UTC): 2026-11-27
- Status: open
- Question: What licenses and dependency policy preserve an open-source ecosystem and a fully self-hostable/non-proprietary core while respecting the EPL-derived Android client and allowing optional integrations?
- Why it matters: publication, contributor expectations, OSS flavor viability and optional cloud/map/geocoder integrations.
- Current evidence: DEC-0019 establishes open/self-hosted/no-mandatory-proprietary direction; current Android donor is EPL-1.0-derived.
- Needed evidence or decision: legal/license compatibility review, component/dependency inventory, distribution model.
- Owner: project owner / publication hardening plan
- Resolution: pending
- Recorded by: ChatGPT consolidation

### OQ-0021: `Offload-safe durability classes and backup requirements`
- Date recorded (UTC): 2026-11-27
- Last updated (UTC): 2026-11-27
- Status: open
- Question: What verified remote/archive state is sufficient before a device may automatically or manually remove its full local copy—server durable commit alone, server plus verified backup, or configurable durability classes?
- Why it matters: “synced” is not necessarily “safe to make the server the only full copy,” especially for portable/loss-prone servers.
- Current evidence: DEC-0017 separates synced from offload-safe; DEC-0019/R-0023 require backup awareness for portable deployments.
- Needed evidence or decision: backup topology, user risk tolerance/preferences, server durability/restore guarantees, offload UX.
- Owner: storage-lifecycle + backend/backup plans
- Resolution: pending
- Recorded by: ChatGPT consolidation
