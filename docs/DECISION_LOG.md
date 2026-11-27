# Decision Log

Append-only record of durable product, architecture, workflow, and scope decisions. Entries are oldest-to-newest and ascending by ID. Decisions do not prove implementation.

For DEC-0001 through DEC-0012, event date `2026-10-10` records when the owner supplied these directions during the earlier project session; date recorded `2026-11-24` records when this governance system formalized them. The event date is not the donor commit date or the governance-instantiation date.

## Entry Template
### YYYY-MM-DD - DEC-0000
- Date recorded (UTC):
- Event date (UTC), if different:
- Decision ID:
- Context:
- Decision:
- Reason:
- Alternatives considered:
- Tradeoffs:
- Consequences:
- Source surfaces and evidence:
- Validation state:
- Boundary:
- Follow-up actions:
- Recorded by:

## Entries
### 2026-11-24 - DEC-0001
- Date recorded (UTC): 2026-11-24
- Event date (UTC), if different: 2026-10-10
- Decision ID: DEC-0001
- Context: LateralLine is beginning from OwnTracks Android but requires an independent long-term identity.
- Decision: Treat OwnTracks Android as active donor implementation now and progressively as reference/interoperability source. Classify upstream changes ADOPT, ADAPT, SUPERSEDED, IRRELEVANT, or REVIEW rather than merging automatically.
- Reason: preserve hard-won Android knowledge without making donor architecture permanent.
- Alternatives considered: permanent fork with routine merges; immediate rewrite.
- Tradeoffs: requires deliberate upstream review and architectural judgment.
- Consequences: donor source, history, release notes, issues, PRs, tests, and docs remain evidence.
- Source surfaces and evidence: owner mission; local donor source/Git inspection.
- Validation state: `verified decision; implementation transition deferred`
- Boundary: no upstream merge or application change in this task.
- Follow-up actions: use classification in future upstream reviews.
- Recorded by: Codex

### 2026-11-24 - DEC-0002
- Date recorded (UTC): 2026-11-24
- Event date (UTC), if different: 2026-10-10
- Decision ID: DEC-0002
- Context: the donor outgoing queue currently conflates persistence and delivery.
- Decision: LateralLine's permanent model separates durable local history from transport/delivery state.
- Reason: server/network outages must not cause history loss; a finite queue is not a trustworthy master history.
- Alternatives considered: indefinitely enlarging the outgoing queue.
- Tradeoffs: future schema, migration, retention, and sync complexity.
- Consequences: Travel Build v0's 5M queue remains explicitly transitional.
- Source surfaces and evidence: owner mission; observed Room queue and MessageProcessor behavior.
- Validation state: `verified decision; not implemented`
- Boundary: no durable-history redesign in this task.
- Follow-up actions: create a dedicated architecture plan after Travel Build v0.
- Recorded by: Codex

### 2026-11-24 - DEC-0003
- Date recorded (UTC): 2026-11-24
- Event date (UTC), if different: 2026-10-10
- Decision ID: DEC-0003
- Context: large offline backlogs can delay current positions.
- Decision: Future synchronization separates high-priority live/realtime delivery from lower-priority batched, resumable, idempotent, checkpointed historical backfill.
- Reason: current locations must not wait behind historical backlog.
- Alternatives considered: one FIFO message stream for all records.
- Tradeoffs: multiple priorities/checkpoints and more server/client coordination.
- Consequences: exact batch format and protocol remain open.
- Source surfaces and evidence: owner mission.
- Validation state: `verified decision; not implemented`
- Boundary: no sync implementation in this task.
- Follow-up actions: design after durable local-history model.
- Recorded by: Codex

### 2026-11-24 - DEC-0004
- Date recorded (UTC): 2026-11-24
- Event date (UTC), if different: 2026-10-10
- Decision ID: DEC-0004
- Context: location data requires phishing-resistant human authentication.
- Decision: Use passkeys/WebAuthn as primary interactive authentication where practical; prefer passwordless-first architecture. TOTP is optional additional/recovery/step-up authentication, and SMS is not preferred MFA.
- Reason: improve phishing resistance and avoid routine master-password transmission.
- Alternatives considered: password-first authentication; mandatory TOTP after every passkey login; SMS-first MFA.
- Tradeoffs: recovery and authenticator enrollment require careful design.
- Consequences: Argon2id is relevant only if passwords are later introduced.
- Source surfaces and evidence: owner mission.
- Validation state: `verified decision; not implemented`
- Boundary: enrollment/recovery details remain open.
- Follow-up actions: threat-model identity and recovery before implementation.
- Recorded by: Codex

### 2026-11-24 - DEC-0005
- Date recorded (UTC): 2026-11-24
- Event date (UTC), if different: 2026-10-10
- Decision ID: DEC-0005
- Context: human sessions and unattended location producers have different compromise/revocation needs.
- Decision: Each device will have its own cryptographic identity independent of human login credentials; Android private keys use Android Keystore and hardware-backed protection where practical.
- Reason: revoke a stolen device without rotating all other credentials.
- Alternatives considered: shared account secret on every device.
- Tradeoffs: enrollment, attestation expectations, key rotation, and recovery become explicit protocols.
- Consequences: short-lived access credentials and secure refresh/session design are required concerns.
- Source surfaces and evidence: owner mission.
- Validation state: `verified decision; not implemented`
- Boundary: exact ceremony and key algorithms remain open.
- Follow-up actions: design enrollment/revocation/recovery architecture.
- Recorded by: Codex

### 2026-11-24 - DEC-0006
- Date recorded (UTC): 2026-11-24
- Event date (UTC), if different: 2026-10-10
- Decision ID: DEC-0006
- Context: multi-user/multi-device sharing and authorization require clear identity boundaries.
- Decision: Keep person, account, device, and location record distinct. Sharing/circles primarily relate persons/accounts; records originate from devices.
- Reason: prevent identity collapse and support per-device provenance/revocation.
- Alternatives considered: person/device as one record.
- Tradeoffs: additional joins and domain concepts.
- Consequences: backend schemas and APIs must preserve these boundaries.
- Source surfaces and evidence: owner mission.
- Validation state: `verified decision; not implemented`
- Boundary: exact schema remains open.
- Follow-up actions: carry the model into backend and mobile designs.
- Recorded by: Codex

### 2026-11-24 - DEC-0007
- Date recorded (UTC): 2026-11-24
- Event date (UTC), if different: 2026-10-10
- Decision ID: DEC-0007
- Context: client-side visibility is not access control.
- Decision: Authorization is enforced server-side; permissions must never be implemented solely by hiding UI. Security-sensitive administration is strongly authenticated and auditable even in a one-admin deployment.
- Reason: protect highly sensitive multi-user location data.
- Alternatives considered: trusted-client/UI-only enforcement.
- Tradeoffs: backend policy and audit complexity.
- Consequences: every future API must define authenticated actor and authorization rule.
- Source surfaces and evidence: owner mission.
- Validation state: `verified decision; not implemented`
- Boundary: exact policy engine/audit schema remain open.
- Follow-up actions: include authorization cases in API acceptance tests.
- Recorded by: Codex

### 2026-11-24 - DEC-0008
- Date recorded (UTC): 2026-11-24
- Event date (UTC), if different: 2026-10-10
- Decision ID: DEC-0008
- Context: the future authoritative service needs relational and spatial capability.
- Decision: Use PostgreSQL as the backend data-layer direction, with PostGIS where spatial functionality benefits.
- Reason: fit for relational identity/permissions/history and spatial queries.
- Alternatives considered: preserve Recorder's storage model as permanent backend; unspecified database.
- Tradeoffs: operational and migration complexity; spatial use should remain justified.
- Consequences: exact schema, partitioning, retention, and framework remain open.
- Source surfaces and evidence: owner mission.
- Validation state: `verified decision; not implemented`
- Boundary: no database created in this task.
- Follow-up actions: validate schema and growth assumptions in a later plan.
- Recorded by: Codex

### 2026-11-24 - DEC-0009
- Date recorded (UTC): 2026-11-24
- Event date (UTC), if different: 2026-10-10
- Decision ID: DEC-0009
- Context: self-hosting must move among home and hosted Linux machines.
- Decision: Use Docker/Compose as the expected portability layer where appropriate, because deployment must move without application redesign.
- Reason: reduce machine-specific deployment coupling.
- Alternatives considered: host-specific manual installation as foundational deployment.
- Tradeoffs: container operations and storage/backup design still require care.
- Consequences: portability tests must cover more than one host class over time.
- Source surfaces and evidence: owner mission.
- Validation state: `verified decision; not implemented`
- Boundary: exact deployment targets and topology remain open.
- Follow-up actions: define deployment acceptance when backend work begins.
- Recorded by: Codex

### 2026-11-24 - DEC-0010
- Date recorded (UTC): 2026-11-24
- Event date (UTC), if different: 2026-10-10
- Decision ID: DEC-0010
- Context: current infrastructure uses OwnTracks Recorder and OwnTracks protocol.
- Decision: Preserve OwnTracks protocol compatibility where useful, while treating Recorder as transitional compatibility/reference infrastructure rather than the permanent authoritative backend.
- Reason: maintain interoperability and transition safety without architecture lock-in.
- Alternatives considered: immediate compatibility removal; permanent Recorder authority.
- Tradeoffs: compatibility adapters and migration planning.
- Consequences: long-term compatibility guarantees and migration path remain open.
- Source surfaces and evidence: owner mission; retained donor source/docs.
- Validation state: `verified decision; transition not implemented`
- Boundary: no Recorder or protocol changes in this task.
- Follow-up actions: plan coexistence and migration before backend cutover.
- Recorded by: Codex

### 2026-11-24 - DEC-0011
- Date recorded (UTC): 2026-11-24
- Event date (UTC), if different: 2026-10-10
- Decision ID: DEC-0011
- Context: Cloudflare Tunnel is relevant to current self-hosting, and a reverse proxy may be useful.
- Decision: Cloudflare may provide public-edge connectivity/security/tunneling; application authentication/authorization remains an application concern. Caddy may be optional origin routing, not foundational security architecture; other proxies remain valid.
- Reason: keep security boundaries explicit and avoid proxy lock-in.
- Alternatives considered: depend conceptually on Caddy or edge controls for application authorization.
- Tradeoffs: application must own more explicit security behavior.
- Consequences: final origin topology remains open.
- Source surfaces and evidence: owner mission.
- Validation state: `verified decision; topology not implemented`
- Boundary: no infrastructure changes in this task.
- Follow-up actions: threat-model edge/origin when backend deployment begins.
- Recorded by: Codex

### 2026-11-24 - DEC-0012
- Date recorded (UTC): 2026-11-24
- Event date (UTC), if different: 2026-10-10
- Decision ID: DEC-0012
- Context: immediate travel safety is needed before permanent architecture is ready.
- Decision: Travel Build v0 raises capacity to 5,000,000, removes destructive oldest-item eviction, preserves Room/acquisition/modes/MQTT/HTTP, reports queue-full failure explicitly, separates installation identity, adds tests, builds GMS debug, and verifies on S24. It does not redesign the app or history architecture.
- Reason: protect travel history with the smallest coherent donor-safe milestone.
- Alternatives considered: full architecture rewrite before travel; leave donor behavior unchanged.
- Tradeoffs: a large queue has storage/performance limits and remains transitional.
- Consequences: implementation must follow `plans/ACTIVE/2026-11-24_travel-build-v0.md`.
- Source surfaces and evidence: owner mission; observed donor queue behavior.
- Validation state: `verified decision; implementation pending`
- Boundary: documentation task only.
- Follow-up actions: execute the active plan as the next mission.
- Validation update (UTC): 2026-11-26 — checkpoints 1–4 are implemented and host-verified in recovery commit `f2d98fc9e8ffca1cdf4b7309b9040e8a01c9eb2c`; physical Samsung Galaxy S24 validation remains deferred because no device is attached.
- Current follow-up (UTC): execute the active plan's deferred S24 checklist; this decision still does not authorize permanent history, backend, identity, synchronization, circles, UI, or sensor architecture.
- Validation update (UTC): 2026-11-27 — the bounded S24 acceptance boundary is complete. Direct evidence covers side-by-side installation, physical queue regression tests, permissions, mode selection, HTTP 2xx publishing, shortcuts, battery exemption, background/reboot survival, and offline queue persistence/reconnect/drain. The plan records the unattributed Move-to-Significant transition as a limitation and precisely defers security-gated configuration import/authenticated external control; neither is represented as a pass.
- Current follow-up (UTC): final substantive commit `90cba8c37bd15a1ddd5e32e8f02e4dded0c3f42e` is pushed and this single DEC-0014 follow-up records that state; await explicit owner direction. This decision still does not authorize permanent history, backend, identity, synchronization, circles, UI, or sensor architecture.
- Recorded by: Codex

### 2026-11-24 - DEC-0013
- Date recorded (UTC): 2026-11-24
- Decision ID: DEC-0013
- Context: Travel Build v0 needs a non-destructive queue-full contract and an installation identity that can coexist with official OwnTracks without a donor namespace rewrite.
- Decision: A full or failed Room queue write rejects the message, preserves every already-persisted row, returns an asynchronous `false` result for normal enqueue, and emits an error-level diagnostic; failed-message requeue likewise returns failure to the sender loop and is treated as a permanent send failure rather than evicting another row or claiming a retry was persisted. The travel build uses application ID `org.lateralline.android.travel` (plus the existing `.debug` suffix for debug), variant-derived app action names and shortcut package targeting, and the visible name `LateralLine Travel`. The donor Kotlin namespace and the `owntracks://` configuration scheme remain unchanged.
- Reason: this is the smallest explicit and testable failure contract that stops capacity-driven deletion, keeps current asynchronous call sites compatible, isolates Android storage/providers/intents from official OwnTracks, and preserves donor protocol/configuration interoperability.
- Alternatives considered: evict an older row; keep the failed message only in memory; overload endpoint connection state with a queue error; rename the entire Kotlin namespace; change the OwnTracks configuration URI scheme.
- Tradeoffs: the message that cannot be persisted is lost and the error is currently diagnosable through the existing logs rather than a new product UX; the retained `owntracks://` scheme can present an Android app chooser when both apps are installed.
- Consequences: focused tests must prove rejection and non-eviction at small capacity, caller paths must never call `dequeue()` to make room, rejected enqueue must balance its idling-resource count, and the built APK identity/manifest must be inspected directly.
- Source surfaces and evidence: DEC-0012; active Travel Build v0 plan; observed `RoomBackedMessageQueue`, `MessageProcessor`, build configuration, manifests, shortcuts, test contracts, and donor Git history through `b5e7332e` and `47ddbee2`.
- Validation state: `decided for Travel Build v0; implementation and build verification pending`
- Boundary: no permanent history model, backend, namespace migration, UI redesign, or protocol change is authorized.
- Follow-up actions: implement checkpoints 2–4, verify the GMS debug APK, and record direct versus deferred S24 evidence.
- Validation update (UTC): 2026-11-26 — implementation, focused/full host tests, formatting, lint task, Android-test compilation, GMS debug assembly, and direct APK identity/signature inspection are complete in recovery commit `f2d98fc9e8ffca1cdf4b7309b9040e8a01c9eb2c`; the preserved APK was rechecked after interruption. Device-only lifecycle, coexistence, endpoint, persistence/reconnect, and instrumentation execution remain deferred.
- Current follow-up (UTC): execute the active plan's deferred Samsung Galaxy S24 validation checklist; do not expand this bounded decision into permanent architecture work.
- Validation update (UTC): 2026-11-27 — the S24 ran all seven focused Room tests with no failures, retained official OwnTracks alongside the Travel identity, and passed the bounded lifecycle/offline persistence/reconnect checks recorded in the active plan. No capacity-driven deletion, queue clear, persistence error, delivery drop, or timestamp-order decrease was observed in the exercised path.
- Current follow-up (UTC): substantive closeout commit `90cba8c37bd15a1ddd5e32e8f02e4dded0c3f42e` is pushed and this one non-recursive follow-up records it; do not expand this bounded decision into permanent architecture work.
- Recorded by: Codex

### 2026-11-26 - DEC-0014
- Date recorded (UTC): 2026-11-26
- Decision ID: DEC-0014
- Context: interruption recovery temporarily required owner-executed Git operations, while the durable workflow did not explicitly assign end-to-end commit, push, and post-commit bookkeeping responsibility after the owner authorizes those actions.
- Decision: when the owner instructs Codex to commit a completed or reviewed repository unit, Codex owns scope inspection, durable-record reconciliation, validation, staging, staged-diff review, commit creation, collection of real Git evidence, non-recursive bookkeeping, authorized push, remote verification, and final status reporting. Push still requires owner authorization. A commit cannot record its own hash; a single later bookkeeping commit may record the preceding substantive commit and push without spawning another bookkeeping-only commit.
- Reason: repository continuity must not depend on the owner manually completing routine Git/bookkeeping steps, and recorded hashes/statuses must come from actual Git evidence rather than prediction.
- Alternatives considered: always hand commit commands to the owner; leave executor responsibility implicit; create recursively self-referential bookkeeping commits.
- Tradeoffs: an authorized workflow may produce one small governance follow-up commit, and its own hash/push is verified externally until the next natural checkpoint.
- Consequences: `docs/TASK_PROTOCOL.md` defines the operational sequence and `PLANS.md` defines `final_commit_hash` semantics.
- Source surfaces and evidence: explicit owner instruction on 2026-11-26; historical audit of `2913c6182980c3e86e5fbe9f217faf6cdc80028a`, `f2d98fc9e8ffca1cdf4b7309b9040e8a01c9eb2c`, reflogs, and stale durable bookkeeping.
- Validation state: `decided and applied to the current recovery reconciliation`
- Boundary: this decision governs repository commit/push execution only after owner authorization; it does not broaden implementation scope or bypass destructive/external stop conditions.
- Follow-up actions: use the governed sequence for this and future owner-authorized commits.
- Recorded by: Codex

### 2026-11-27 - DEC-0015
- Date recorded (UTC): 2026-11-27
- Decision ID: DEC-0015
- Context: Travel Build v0 proved that a large persistent transport queue reduces immediate travel risk but does not provide authoritative local history. Post-completion audit also showed that the current producer can report success without awaiting queue persistence and that the queue has dequeue-before-send and other loss windows.
- Decision: LateralLine's permanent capture model preserves accepted **original observations** as protocol-independent canonical local records before transport. Original observations include available uncertainty/quality and relevant capture metadata, including inaccurate fixes when accepted by the acquisition boundary. Corrections, quality assessments, map-matched positions, inferred activities, reconstructed journeys, simplifications, and other processed data remain separate derived objects and never silently overwrite the original. A record is considered durably captured only after the local-history durability contract successfully commits it. Each record has stable logical identity independent of wall-clock time, per-recording-stream ordering information, and provenance; device identity and recording lineage/stream epoch remain distinct to survive restore/reinstall/clone/import cases.
- Reason: history must represent what was actually observed, preserve uncertainty, survive network/server/transport changes, and remain reconcilable across device lifecycle and migration.
- Alternatives considered: use OwnTracks `MessageLocation` as permanent internal truth; treat timestamp/device pair as identity; persist only filtered/accurate points; treat async enqueue/server delivery as capture success.
- Tradeoffs: more local schema/provenance complexity, explicit migration/versioning requirements, and future derived-data storage/indexing.
- Consequences: Durable Local History v1 must place the canonical commit boundary before transport; publishing/display filters may remain separate policies; exact record-ID/timestamp/schema algorithms remain open in OQ entries.
- Source surfaces and evidence: owner/ChatGPT post-Travel architecture discussion; direct `LocationProcessor`/`MessageProcessor` source audit; DEC-0002; `docs/PLATFORM_ARCHITECTURE.md`.
- Validation state: `decided architecture; not implemented`
- Boundary: no Durable Local History implementation is authorized by this decision.
- Follow-up actions: use this contract to scope the next history design/implementation plan and resolve the remaining storage/identity open questions with evidence.
- Recorded by: ChatGPT consolidation under owner instruction

### 2026-11-27 - DEC-0016
- Date recorded (UTC): 2026-11-27
- Decision ID: DEC-0016
- Context: large historical backlog, ambiguous network failure, multiple future transports, and a future multi-user server require a synchronization contract stronger than the donor FIFO queue.
- Decision: Future synchronization uses stable record identities with **at-least-once transport and idempotent logical ingestion**, durable server acknowledgements/checkpoints only after the defined server commit boundary, and durable client sync state. Current/live delivery has independent high-priority scheduling semantics so it cannot be starved behind historical backfill; historical backfill is resumable/checkpointed and may batch/compress where evidence supports it. Wall-clock timestamps are not the sole ordering identity. HTTP/MQTT/WebSocket roles remain open and are chosen by evidence; protocols are adapters around canonical records, not the domain model.
- Reason: after a commit-but-lost-ACK event the client cannot know whether the server accepted the request. Safe resend plus idempotent storage is more robust than pretending network delivery can be exactly-once. Live visibility must remain useful during large backlog recovery.
- Alternatives considered: exactly-once transport semantics; one FIFO stream; permanent OwnTracks MQTT/HTTP as LateralLine internal architecture; HTTP 200 alone as archive/offload proof.
- Tradeoffs: requires stable IDs, server transaction semantics, checkpoints/reconciliation, and co-design with backend/device identity.
- Consequences: sync/backend/device-auth contracts require design overlap before deep implementation even if milestones stay separate. Exact batch encoding, compression, transport role split, and checkpoint representation remain open.
- Source surfaces and evidence: owner/ChatGPT discussion; DEC-0003; R-0003/R-0012; `docs/PLATFORM_ARCHITECTURE.md`.
- Validation state: `decided architecture; not implemented`
- Boundary: no sync/backend implementation or transport selection is authorized here.
- Follow-up actions: carry the contract into synchronization/backend/identity design and failure-injection acceptance tests.
- Recorded by: ChatGPT consolidation under owner instruction

### 2026-11-27 - DEC-0017
- Date recorded (UTC): 2026-11-27
- Decision ID: DEC-0017
- Context: finite mobile storage must be managed without turning local cleanup into permanent history loss or server-side retention policy. The owner wants continued recording during difficult offline travel while preserving maximum original information.
- Decision: Full-fidelity canonical originals are retained locally by default within physical limits, and full-fidelity originals durably ingested by the server are retained there by default. Successful synchronization does not automatically delete the local copy. Three operations are separate: (1) lossless local offloading/redundant-copy eviction of an offload-safe full copy; (2) explicit user-authorized **local** destructive fidelity reduction; and (3) explicit ecosystem-wide/global deletion. Local cleanup/reduction never emits an implicit server deletion. `synced` and `offload-safe` are distinct states. Automatic storage management may reclaim nonessential data, losslessly optimize, perform user-policy-controlled lossless offloading, forecast/warn, and prepare reduction proposals, but it does not automatically destructively simplify canonical history.
- Reason: local storage pressure and global retention are different concerns; a trusted remote copy can make local offload lossless ecosystem-wide, while device-only originals require explicit tradeoff before loss.
- Alternatives considered: delete local originals immediately after sync; use one synced/deleted boolean; automatic lossy simplification at critical storage; propagate local deletion as sync tombstone.
- Tradeoffs: richer copy/fidelity/archive state, more explicit UX/policy, and future rehydration/storage-accounting complexity.
- Consequences: future storage lifecycle supports fidelity state, protected/pinned ranges, lightweight local derived representations after offload, and rehydration where full originals remain elsewhere. Exact offload-safe durability classes remain open.
- Source surfaces and evidence: owner/ChatGPT storage-lifecycle discussion; `docs/PLATFORM_ARCHITECTURE.md` §§4–5.
- Validation state: `decided architecture; not implemented`
- Boundary: no storage reduction/offloading/forecasting implementation is authorized; none is required for Durable Local History v1 beyond preserving extensibility/state separation.
- Follow-up actions: dedicated later storage-lifecycle milestone after canonical history and server archive state exist.
- Recorded by: ChatGPT consolidation under owner instruction

### 2026-11-27 - DEC-0018
- Date recorded (UTC): 2026-11-27
- Decision ID: DEC-0018
- Context: physical-device validation exposed private location/network data to external AI tooling. The owner clarified that rich private local diagnostics are desirable; the confidentiality concern is external tooling access, while authentication secrets require stricter treatment than ordinary personal diagnostics.
- Decision: Distinguish **secrets**, **private diagnostics/personal data**, and **tool-safe diagnostics**. Rich local diagnostics may include coordinates, addresses, network/location context, and other useful owner-visible information. Secrets such as passwords, private keys, auth/session/refresh tokens, API keys, recovery secrets, authorization headers, and device credentials should generally never be duplicated into diagnostic logs. External AI/development tooling receives minimized tool-safe diagnostics by default through technically enforced capability boundaries where practical. Unrestricted ADB/run-as/raw log/filesystem/UI access is not considered a real privacy boundary merely because instructions say not to inspect private data. Synthetic isolated environments are preferred for scale/failure engineering.
- Reason: protect the owner and credentials without crippling legitimate local diagnostics or relying on prompt/procedural discipline as the sole control.
- Alternatives considered: globally remove detailed diagnostics; allow arbitrary raw AI access with instruction-only redaction; treat credentials as ordinary PII.
- Tradeoffs: requires a dedicated safe diagnostic surface/capability model and may restrict some automated inspection on personal devices.
- Consequences: FAIL-0002 containment remains interim; future tooling/privacy design must enforce least privilege. Current debug logging/SharedPreferences behavior remains technical debt rather than accepted architecture.
- Source surfaces and evidence: FAIL-0002; owner clarification; current logging/preferences source; `docs/PLATFORM_ARCHITECTURE.md` §8.
- Validation state: `decided architecture/process direction; not implemented`
- Boundary: this does not forbid owner-visible private diagnostics and does not authorize secrets to be logged.
- Follow-up actions: define the technical diagnostic/tooling boundary in a future hardening plan.
- Recorded by: ChatGPT consolidation under owner instruction

### 2026-11-27 - DEC-0019
- Date recorded (UTC): 2026-11-27
- Decision ID: DEC-0019
- Context: LateralLine is intended to move from the owner's PC to home/portable Linux hardware and remain functional without mandatory public-cloud infrastructure, including possible battery/power-bank/solar-assisted server operation.
- Decision: The core LateralLine ecosystem is intended to remain open-source, self-hostable, and operable without mandatory project/SaaS subscription fees or proprietary cloud services. Optional proprietary integrations may exist but are not core dependencies. Client/server local operation should work over a reachable LAN without requiring Cloudflare/public DNS. A deployment's logical identity/trust is independent of its current hardware, IP, domain, reverse proxy, or hosting provider and may survive secure key rotation/migration. Portable/off-grid Linux/ARM64-class operation is a real future validation target; no promise is made to support arbitrary OS/hardware without evidence.
- Reason: preserve autonomy, portability, incremental migration, and usable local operation under intermittent/no internet.
- Alternatives considered: cloud/SaaS-dependent architecture; binding server identity to URL/host/private key; host-specific manual architecture.
- Tradeoffs: local discovery/TLS/passkey relying-party design, backup/recovery, abrupt-power resilience, ARM/resource testing, and optional integration boundaries require explicit work.
- Consequences: Docker/Compose remains a portability tool rather than a security dependency; PostgreSQL/PostGIS performance on constrained hardware must be measured; the OSS/non-Google client path cannot be allowed to become irrelevant if needed for core independence.
- Source surfaces and evidence: owner/ChatGPT deployment discussion; DEC-0008/0009/0011; `docs/PLATFORM_ARCHITECTURE.md` §7.
- Validation state: `decided direction; not implemented`
- Boundary: exact deployment topology, local discovery/TLS, licensing, backup topology, and hardware floor remain open.
- Follow-up actions: resolve deployment/trust/licensing questions before publication-grade deployment architecture is frozen.
- Recorded by: ChatGPT consolidation under owner instruction

### 2026-11-27 - DEC-0020
- Date recorded (UTC): 2026-11-27
- Decision ID: DEC-0020
- Context: the project has accumulated substantial owner/ChatGPT reasoning. Requiring Codex to repeatedly reconstruct that reasoning wastes context and creates avoidable architectural drift. The retained donor `DATA_FLOW_DESIGN.md` is not a suitable LateralLine architecture authority.
- Decision: Owner/ChatGPT discussion is the primary exploratory/design forum; durable repository owner documents are the authoritative actionable result; Codex primarily performs bounded repository inspection/implementation/testing/debugging/verification/reporting against those documents. Codex may challenge invalid assumptions and propose alternatives, but consequential direction changes are surfaced rather than silently introduced. Create `docs/PLATFORM_ARCHITECTURE.md` as the owner for durable cross-system/data architecture contracts; retain donor technical documents as evidence only. Navigation must direct each assignment to the minimum relevant owner context rather than requiring full knowledge-base reads.
- Reason: improve context efficiency, continuity, architectural consistency, and owner leverage without reducing Codex to blind execution or over-governing routine coding decisions.
- Alternatives considered: keep architecture only in PROJECT_BRIEF/chat; promote donor DATA_FLOW_DESIGN to LateralLine authority; require every task to read all docs; let Codex independently reconstruct product direction each time.
- Tradeoffs: architecture owner documents must be maintained as decisions evolve; navigation/authority changes must stay synchronized.
- Consequences: `AGENTS.md`, governance navigation, authority matrix, repo map, task protocol, quality bar, roadmap, and registers route through the new owner surface. Plans still own task-local execution details, not durable policy.
- Source surfaces and evidence: owner consolidation instruction; existing anti-sprawl/change-control/truth-hierarchy rules; retained donor document staleness.
- Validation state: `decided governance structure; documentation consolidation only`
- Boundary: no runtime implementation or future milestone activation is authorized.
- Follow-up actions: use the new navigation model in subsequent planning and keep consequential unresolved choices in `docs/OPEN_QUESTIONS.md`.
- Recorded by: ChatGPT consolidation under owner instruction
