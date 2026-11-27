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
- Last reviewed (UTC): 2026-11-27
- Status: mitigated
- Severity: high
- Area: Travel Build v0
- Risk: a full queue causes the caller to dequeue an oldest persisted item when admitting a new or failed/requeued message.
- Evidence: the prior `MessageProcessor.queueMessageForSending` and `reQueueMessage` eviction calls were observed and removed; focused JVM tests verify both caller paths never call `dequeue()` on rejection; and the seven-test small-capacity Room class passed on the physical S24 with both full-enqueue and full-requeue cases preserving the two existing rows in order.
- Validation state: mitigated in Travel Build v0 by source, JVM regression coverage, physical Room instrumentation, and a bounded offline persistence/reconnect exercise with no observed queue clear, persistence error, or delivery drop
- Mitigation: Travel Build v0 rejects full writes, logs the failure, balances rejected enqueue accounting, and preserves all already-persisted rows.
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
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: high
- Area: mobile donor behavior
- Risk: changes break permissions, foreground services, sleep, network transitions, reconnect, location providers, geofencing, migrations, or manufacturer-specific behavior.
- Evidence: donor source/history/tests and owner warning; Travel Build v0 GMS/OSS unit suites, Android-test compilation, lint task, and GMS APK assembly pass. Physical S24 evidence covers coexistence, all 7 targeted Room tests, four runtime grants, all four mode selections, HTTP 200 publishing, state-preserving update install, both launcher shortcuts, Samsung Unrestricted/battery exemption, process/service/location survival through UI backgrounding and reboot, and offline queue persistence plus reconnect/drain. The owner-reported 5+ hour movement window retained only its final 35.5 minutes of logs and returned in Significant after a Move baseline; attribution is unknown.
- Validation state: verified ongoing donor risk; this bounded build passed the exercised automated and physical lifecycle paths, but the long window is not a clean Move/dense-capture proof and no short validation can establish general travel reliability
- Mitigation: narrow plans, source/history inspection, regression tests, command-boundary privacy filtering, retained battery exemption, and precise limitations in the completed S24 checklist. Revalidate on every later Android behavior change.
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

### R-0013: `Travel queue storage and performance pressure`
- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: medium
- Area: Travel Build v0 / current safety envelope
- Risk: allowing up to 5,000,000 Room rows can consume substantial device storage and degrade insert/dequeue/startup/reboot/drain behavior long before the configured ceiling; a capacity constant is not an operational guarantee.
- Evidence: Travel Build raises injected capacity from 100,000 to 5,000,000; bounded S24 exercise retained/drained only a small backlog (at least 55 records). No representative multi-million-row benchmark has been run.
- Validation state: configuration and small physical persistence/drain verified; million-scale storage/performance/battery/recovery magnitude unknown.
- Mitigation: characterize the current Travel safety envelope with realistic synthetic trajectories at multiple scales through the 5,000,000 and capacity+1 boundaries. Measure DB/WAL/file size, insert/dequeue tail latency, memory, initialization/startup/reboot, drain throughput, persistent failure/retry, network flapping, process death, low/full disk, ordering, and battery/thermal behavior where material. Use smaller representative real-device endurance tests alongside synthetic scale. Do not spend disproportionate effort optimizing this transitional queue if durable local history is the better architectural fix.
- Owner: post-Travel readiness gate and future durable-history plan
- Review trigger: benchmark results, low-storage warning, next travel reliance, or permanent-history design
- Recorded by: Codex; 2026-11-27 review expanded by ChatGPT consolidation under owner instruction

- Date recorded (UTC): 2026-11-24
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: medium
- Area: Travel Build v0
- Risk: allowing up to 5,000,000 Room rows can consume substantial device storage and may affect queue startup, backup, query, and drain performance before the permanent history architecture exists.
- Evidence: Travel Build v0 deliberately raises the injected capacity from 100,000 to 5,000,000; recovery reconfirmed source/APK/build evidence, and the 2026-11-27 S24 exercise retained and drained a bounded backlog that reached at least 55 records without observed corruption or persistence error. No representative multi-million-row benchmark has been run.
- Validation state: capacity, build, bounded physical persistence, and drain directly verified; storage/startup/drain performance at scale remains unknown
- Mitigation: treat the build as transitional, monitor queue length/storage during travel, preserve free space, and design/benchmark the permanent durable-history model separately.
- Owner: project owner and future durable-history plan
- Review trigger: large backlog evidence, low-storage warning, or permanent history design
- Recorded by: Codex

### R-0014: `Transport queue still contains loss windows outside full-capacity eviction`
- Date recorded (UTC): 2026-11-27
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: high
- Area: current Travel/donor persistence
- Risk: Travel Build fixed capacity-driven eviction but the outgoing queue can still lose the only queued copy through dequeue-before-send process/power loss, requeue failure after dequeue, preference-triggered queue clear, deserialization/corruption deletion, or partial legacy migration cleanup.
- Evidence: direct current source review of `MessageProcessor.kt` and `RoomBackedMessageQueue.kt`; the completed Travel plan already noted dequeue-before-send and configuration queue-clear concerns.
- Validation state: source-confirmed mechanisms; not all failure windows have been physically reproduced.
- Mitigation: never promote the queue to master history; preserve canonical history before transport; use preservation-safe quarantine/migration behavior; add focused failure tests to readiness/history work.
- Owner: post-Travel hardening and Durable Local History plans
- Review trigger: any queue/history/migration change or before relying on Travel as lossless recorder
- Recorded by: ChatGPT consolidation

### R-0015: `Persistent transport failure can starve newer data`
- Date recorded (UTC): 2026-11-27
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: high
- Area: current sender/retry
- Risk: broken retry-budget state plus head-slot requeue can repeatedly select one failing message and block newer regular records, undermining live progress and making logged retry/drop semantics unreliable.
- Evidence: FAIL-0004 direct source audit.
- Validation state: source-confirmed; runtime regression pending.
- Mitigation: bounded retry-state hardening and deterministic failure tests; long-term live/history scheduling independent of a single FIFO/head-retry path.
- Owner: post-Travel readiness gate
- Review trigger: before next travel reliance or synchronization work
- Recorded by: ChatGPT consolidation

### R-0016: `Capture/persistence success state can be false`
- Date recorded (UTC): 2026-11-27
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: high
- Area: current location pipeline
- Risk: a queue write may fail while `LocationProcessor` reports publish success and updates published-location state, hiding data loss from higher layers/user health reporting.
- Evidence: FAIL-0005 direct source audit.
- Validation state: source-confirmed.
- Mitigation: propagate persistence outcome in current hardening where appropriate; permanent architecture defines capture success only at durable local-history commit.
- Owner: post-Travel readiness gate / Durable Local History
- Review trigger: next persistence/capture change
- Recorded by: ChatGPT consolidation

### R-0017: `External tooling can cross the private-diagnostic trust boundary`
- Date recorded (UTC): 2026-11-27
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: high
- Area: privacy/development tooling
- Risk: an AI/tool with broad ADB, debuggable `run-as`, raw logcat/UI/filesystem access can retrieve private personal diagnostics despite procedural redaction rules; current debug logs can contain detailed location/configuration values.
- Evidence: FAIL-0002 and recurrence; direct logging/preferences source review; owner clarification.
- Validation state: verified current development risk.
- Mitigation: rich owner-private diagnostics remain permitted; secrets excluded from logs; develop a technically constrained tool-safe diagnostic surface/capability; use synthetic environments for broad automation; raw personal-device access exceptional/minimum-scope.
- Owner: security/privacy tooling design and Android hardening plans
- Review trigger: any physical-device automation, debug/log redesign, or external-agent integration
- Recorded by: ChatGPT consolidation

### R-0018: `Android foreground-service declaration may not match location work`
- Date recorded (UTC): 2026-11-27
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: high
- Area: Android platform/lifecycle
- Risk: the current service is declared/runtime-started as `connectedDevice` while its core long-running behavior includes location tracking/sharing; Android 14+ foreground-service contracts require appropriate declared types/permissions and location work has a dedicated `location` type.
- Evidence: current manifest/service source; current Android Developers foreground-service documentation. Physical S24 tracking worked, so this is a platform-contract risk rather than proof of a current runtime failure.
- Validation state: source/platform mismatch requiring targeted resolution/testing.
- Mitigation: reconcile manifest/runtime service type/permissions with current Android rules, then repeat lifecycle/background tests.
- Owner: post-Travel Android readiness gate
- Review trigger: before production/release target or any service/lifecycle change
- Recorded by: ChatGPT consolidation

### R-0019: `Compatibility-era security and configuration surfaces exceed final LateralLine posture`
- Date recorded (UTC): 2026-11-27
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: medium
- Area: security/privacy/compatibility
- Risk: donor-era ordinary SharedPreferences hold endpoint credentials, DEBUG logging can emit preference/payload values, network config globally permits cleartext/user CAs, `content://` configuration import bypasses the explicit URI/file enable check before user Apply, and exported shortcut aliases add a small external-launch surface. Treating these as permanent architecture could weaken security/privacy.
- Evidence: direct current source/manifest/resource review; shortcut behavior/tests.
- Validation state: source-confirmed surfaces; exploitability/required compatibility varies by item.
- Mitigation: preserve current compatibility only where needed; secrets excluded from diagnostics; threat-model and narrow final network/config/exported-component posture; keep compatibility adapters separate from canonical architecture.
- Owner: security hardening / Android product plans
- Review trigger: release hardening, configuration redesign, credential/device-identity implementation
- Recorded by: ChatGPT consolidation

### R-0020: `Release/CI/static-analysis evidence is weaker than a publication-quality gate`
- Date recorded (UTC): 2026-11-27
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: medium
- Area: engineering/release
- Risk: the current artifact is debug-signed, lint uses `abortOnError=false` with a large donor backlog, and reviewed LateralLine commits exposed no GitHub commit-status checks. Local successful Gradle/device evidence could be overread as an enforced production release pipeline.
- Evidence: build configuration, Travel plan lint counts, GitHub commit-status inspection.
- Validation state: verified current process gap.
- Mitigation: keep local evidence precise now; establish required CI/release-signing/static-analysis policy before publication-quality distribution.
- Owner: readiness/release hardening milestones
- Review trigger: before broader release or when CI is revised
- Recorded by: ChatGPT consolidation

### R-0021: `Storage pressure/reclamation can fail when free space is already low`
- Date recorded (UTC): 2026-11-27
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: high
- Area: durable local history/storage lifecycle
- Risk: physical storage is finite; row deletion may not return filesystem bytes, compaction/rewrite can require substantial working space, and an emergency algorithm that needs more free disk/RAM than remains cannot preserve continued recording.
- Evidence: owner storage requirements; SQLite documentation on freelist/auto-vacuum/VACUUM; current queue has no permanent-history storage-pressure architecture.
- Validation state: architecture risk; implementation open.
- Mitigation: proactive forecasting, operational reserve, storage accounting, lossless reclamation first, storage representation with real reclaim behavior, bounded-resource emergency algorithms, low/full-disk tests.
- Owner: Durable Local History / future Storage Lifecycle milestone
- Review trigger: local-history storage-engine selection and storage-reduction design
- Recorded by: ChatGPT consolidation

### R-0022: `Local cleanup could be confused with global deletion`
- Date recorded (UTC): 2026-11-27
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: high
- Area: storage lifecycle/synchronization
- Risk: if local presence, sync state, offload safety, fidelity, and logical existence are collapsed, a storage cleanup could accidentally propagate a server deletion or a server ACK could trigger unintended local destruction.
- Evidence: owner requirements; DEC-0017.
- Validation state: architectural risk before implementation.
- Mitigation: separate local offload, local destructive reduction, and global deletion APIs/events/state; explicit authorization/audit for global deletion; server full-fidelity retention by default.
- Owner: history/sync/storage-lifecycle designs
- Review trigger: any local offloading, tombstone, retention, or delete API design
- Recorded by: ChatGPT consolidation

### R-0023: `Portable/off-grid deployment can become a fragile single copy`
- Date recorded (UTC): 2026-11-27
- Last reviewed (UTC): 2026-11-27
- Status: open
- Severity: high
- Area: deployment/backup
- Risk: a battery-powered portable server is exposed to abrupt power loss, theft, damage and storage failure; autonomy can be mistaken for durability if it becomes the only remaining full copy.
- Evidence: owner portable-server requirement; existing backup/security direction.
- Validation state: future operational risk.
- Mitigation: transactional durability/power-loss testing, verified backups/restore drills, durability/offload-safe policy, portable storage quality guidance, server identity independent of host.
- Owner: backend/deployment/storage-lifecycle plans
- Review trigger: portable-host deployment or local offload policy design
- Recorded by: ChatGPT consolidation
