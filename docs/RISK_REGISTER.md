# Risk Register

## Risk Scale
- `low`: unlikely or low impact
- `medium`: plausible and meaningful
- `high`: likely, high impact, or potentially blocking

## Entry Template
### R-0000: `RISK_TITLE`
- Date recorded (UTC):
- Last reviewed (UTC):
- Status: `open | mitigated | accepted | retired`
- Severity: `low | medium | high`
- Area:
- Risk:
- Evidence:
- Validation state:
- Mitigation:
- Owner:
- Review trigger:
- Recorded by:

## Active Risks
### R-0001: `Destructive donor queue overflow`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-24
- Status: open
- Severity: high
- Area: Travel Build v0
- Risk: a full queue causes the caller to dequeue an oldest persisted item when admitting a new or failed/requeued message.
- Evidence: observed `MessageProcessor.queueMessageForSending` and `reQueueMessage`; capacity observed in `MessageQueueModule.kt`.
- Validation state: verified
- Mitigation: execute Travel Build v0 with focused regression tests before high-volume travel.
- Owner: active Travel Build v0 plan
- Review trigger: implementation/test completion
- Recorded by: Codex

### R-0002: `Temporary 5M queue becomes accidental permanent architecture`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-24
- Status: open
- Severity: high
- Area: architecture
- Risk: a larger transport queue is mistaken for durable local history and delays proper separation.
- Evidence: owner explicitly defines 5M capacity as transitional.
- Validation state: verified
- Mitigation: preserve decision/roadmap boundary and plan history architecture separately.
- Owner: project owner
- Review trigger: after Travel Build v0
- Recorded by: Codex

### R-0003: `Historical backlog drains too slowly or starves live data`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-24
- Status: open
- Severity: high
- Area: synchronization
- Risk: message-oriented FIFO delivery makes large backlog recovery impractical and delays current positions.
- Evidence: current donor message-oriented queue; owner requirements.
- Validation state: partially verified
- Mitigation: separate live priority from batched checkpointed backfill.
- Owner: future sync plan
- Review trigger: durable-history/sync design
- Recorded by: Codex

### R-0004: `Android background and lifecycle regression`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-24
- Status: open
- Severity: high
- Area: mobile donor behavior
- Risk: changes break permissions, foreground services, sleep, network transitions, reconnect, location providers, geofencing, migrations, or manufacturer-specific behavior.
- Evidence: donor source/history/tests and owner warning.
- Validation state: verified risk
- Mitigation: narrow plans, source/history inspection, regression tests, and S24 validation.
- Owner: each Android implementation plan
- Review trigger: every donor behavior change
- Recorded by: Codex

### R-0005: `Donor divergence is unmanaged`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-24
- Status: open
- Severity: medium
- Area: upstream governance
- Risk: security/behavior fixes are missed or incompatible upstream changes are imported blindly.
- Evidence: project is a long-lived fork.
- Validation state: verified risk
- Mitigation: ADOPT/ADAPT/SUPERSEDED/IRRELEVANT/REVIEW classification with evidence.
- Owner: future upstream review tasks
- Review trigger: upstream review or security advisory
- Recorded by: Codex

### R-0006: `Location-data confidentiality or authorization failure`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-24
- Status: open
- Severity: high
- Area: security/privacy
- Risk: authentication, authorization, secrets, backups, logging, or endpoint exposure leaks sensitive history.
- Evidence: data sensitivity and future multi-user scope.
- Validation state: verified risk
- Mitigation: threat modeling, backend enforcement, short-lived credentials, audit, TLS, encryption/rotation, secure backup, non-public DB.
- Owner: security architecture
- Review trigger: any backend/identity/sharing milestone
- Recorded by: Codex

### R-0007: `Single-admin concentration`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-24
- Status: open
- Severity: medium
- Area: administration
- Risk: sole-admin convenience leads to weak protection or unaudited access to other people's data.
- Evidence: foreseeable deployment has one administrator.
- Validation state: verified risk
- Mitigation: strong authentication, explicit high-impact actions, audit events, recovery planning.
- Owner: security/admin design
- Review trigger: admin feature design
- Recorded by: Codex

### R-0008: `Database growth and schema evolution`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-24
- Status: open
- Severity: medium
- Area: data platform
- Risk: high-resolution multi-device history creates storage, query, migration, backup, and restore pressure.
- Evidence: dense-capture goal and long-lived platform scope.
- Validation state: verified risk; magnitude unknown
- Mitigation: volume modeling, partition/index/retention design, migration tests, restore drills.
- Owner: future backend/data plan
- Review trigger: schema design or representative data volume
- Recorded by: Codex

### R-0009: `GNSS precision is overclaimed`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-24
- Status: open
- Severity: medium
- Area: product/research
- Risk: users or agents confuse frequent samples with lane/pavement/centimeter accuracy.
- Evidence: physical GNSS limits stated by owner.
- Validation state: verified risk
- Mitigation: preserve accuracy metadata/uncertainty; research sensor fusion without guarantees.
- Owner: product and research plans
- Review trigger: trajectory UX or sensor work
- Recorded by: Codex

### R-0010: `Duplicate sources of truth`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-24
- Status: open
- Severity: medium
- Area: governance
- Risk: temporary docs, donor docs, plans, or generated reports conflict with owner documents.
- Evidence: long-lived project and copied template structure.
- Validation state: verified risk
- Mitigation: truth hierarchy, authority matrix, anti-sprawl rules, and deprecation pointers.
- Owner: all contributors
- Review trigger: new durable document
- Recorded by: Codex

### R-0011: `Infrastructure portability or Recorder migration fails`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-24
- Status: open
- Severity: medium
- Area: deployment/migration
- Risk: host-specific assumptions or incomplete Recorder migration lose history or prevent deployment moves.
- Evidence: current infrastructure plus multi-host portability requirement.
- Validation state: verified risk
- Mitigation: portable deployment acceptance, backups, migration reconciliation, rollback/coexistence plan.
- Owner: future deployment/migration plans
- Review trigger: backend deployment or Recorder cutover
- Recorded by: Codex

### R-0012: `Offline sync correctness failure`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-24
- Status: open
- Severity: high
- Area: synchronization
- Risk: omissions, duplicates, ordering errors, checkpoint corruption, or premature acknowledgements undermine history trust.
- Evidence: offline-first requirement and planned resumable synchronization.
- Validation state: verified risk
- Mitigation: idempotent protocol, transactional ingest, acknowledgement semantics, failure-injection tests, reconciliation tools.
- Owner: future sync architecture plan
- Review trigger: sync protocol design
- Recorded by: Codex

