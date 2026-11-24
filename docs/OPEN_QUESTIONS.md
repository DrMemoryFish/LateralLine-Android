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

### OQ-0004: `Batch encoding, compression, and checkpoint protocol`
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

### OQ-0009: `Initial deployment targets and final origin topology`
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
- Last updated (UTC): 2026-11-24
- Status: open
- Question: Which protocol behaviors remain supported, for how long, and how is existing Recorder history migrated/validated?
- Why it matters: transition safety without indefinite donor lock-in.
- Current evidence: compatibility is useful and Recorder is transitional.
- Needed evidence or decision: current infrastructure inventory and migration acceptance criteria.
- Owner: future compatibility/migration plan
- Resolution: pending
- Recorded by: Codex

### OQ-0011: `Retention and local archive format`
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

### OQ-0012: `Advanced sensor logging architecture`
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

