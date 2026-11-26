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
- Recorded by: Codex
