# Travel Build v0 Execution Plan

## Metadata
- Plan name: Travel Build v0
- Date started: 2026-11-24
- Owner: project owner
- Status: `active`

## Purpose / Big Picture
Produce an emergency-safe, separately installable GMS debug build for dense travel recording while preserving the OwnTracks donor's proven acquisition, tracking-mode, Room, HTTP, and MQTT behavior. Eliminate capacity-driven deletion without claiming that the transport queue is LateralLine's permanent history architecture.

## Scope
In scope:
- increase injected persistent queue capacity from `100_000` to `5_000_000`
- remove destructive oldest-message eviction on normal enqueue overflow
- ensure failed-message requeue cannot destroy unrelated persisted records
- produce explicit queue-full failure/warning behavior
- preserve the Room-backed queue and existing message ordering/retry behavior except where safety requires a deliberate, tested change
- preserve Quiet, Manual, Significant, and Move tracking modes and location acquisition behavior
- preserve OwnTracks protocol, HTTP, and MQTT compatibility
- establish an installation identity that can coexist with official OwnTracks
- add/update focused tests, build GMS debug APK, and verify on Samsung Galaxy S24

Out of scope:
- permanent durable local-history design
- live/backfill batch protocol
- backend/database/Recorder replacement
- identity/passkeys/device credentials
- circles/sharing, UX redesign, sensors, or unrelated donor refactors
- hard-coding the owner's approximate Move interval, max-frequency, or accuracy settings as immutable defaults

## Context and Orientation — Observed
- Branch at plan creation: `travel-build-v0`.
- Donor inception/HEAD before governance changes: `3129fc23296c16ec5dd9f46d91d0fd94942b9f49`.
- `MessageQueueModule.kt` defines `QUEUE_CAPACITY = 100_000`.
- `RoomBackedMessageQueue.enqueue` and `.requeue` return `false` at capacity and do not themselves evict rows.
- `MessageProcessor.queueMessageForSending` responds to enqueue failure by dequeuing an oldest message and retrying the new enqueue.
- `MessageProcessor.reQueueMessage` responds to requeue failure by dequeuing an oldest message before retrying the failed message.
- `dequeue()` removes a row before transport send; retry paths attempt to reinsert at the head.
- Existing instrumented queue coverage is in `RoomBackedMessageQueueTest.kt`; retry coverage includes `LocationMessageRetryTest.kt`; sender-loop unit tests exist in `MessageProcessorTest.kt`.
- The app has GMS/OSS flavors and debug/release build types. Current `applicationId`/namespace is `org.owntracks.android`, with `.debug` suffix for debug.

## Truth Inputs
- `AGENTS.md`
- `docs/PROJECT_BRIEF.md`
- `docs/TRUTH_HIERARCHY.md`
- `docs/QUALITY_BAR.md`
- `docs/REPO_MAP.md`
- `docs/DECISION_LOG.md` DEC-0001, DEC-0002, and DEC-0012
- `docs/RISK_REGISTER.md` R-0001 through R-0004 and R-0012
- relevant current donor source, tests, `CHANGELOG.md`, and Git history

## Constraints
- Development: Ubuntu 24.04.4, OpenJDK 21, Android SDK `/mnt/E/AI/Android/sdk`, Gradle cache `/mnt/E/AI/Android/gradle`.
- Physical target: Samsung Galaxy S24; official OwnTracks remains installed.
- Do not add unrelated dependencies.
- Do not report a message as durably queued if persistence failed.
- Do not delete already-persisted history merely to admit a newer/retried message.
- Do not use a 5,000,000-row test fixture; force capacity with a small injected test capacity.
- Treat existing preference-triggered queue clearing as observed donor behavior outside this milestone unless evidence proves the travel goal cannot be met safely without a separately reviewed change.

## Plan of Work
### Checkpoint 1 — Re-anchor and choose the queue-full contract
1. Re-read this plan and verify branch/status/source lines.
2. Inspect queue/message/status/notification contracts and relevant tests/history.
3. Define the smallest explicit queue-full error/warning path. Record any material decision before implementation.

### Checkpoint 2 — Implement non-destructive queue safety
1. Raise provider capacity to 5,000,000.
2. Remove both caller-side eviction paths.
3. Preserve queue ordering and retry semantics as far as safely possible.
4. Make persistence/requeue failure explicit and diagnosable.
5. Add boundary and non-eviction regression coverage using small capacities.

### Checkpoint 3 — Separate installation identity
1. Inventory `applicationId`, namespace, providers, intent actions, database/preferences isolation, instrumentation runner, shortcuts/config URI behavior, labels/icons, and scripts.
2. Choose the minimal coherent LateralLine travel-build identity without breaking protocol interoperability.
3. Add/update tests or build assertions for coexistence-sensitive identity surfaces.

### Checkpoint 4 — Build and verify
1. Run focused unit/instrumented tests and formatting checks.
2. Run relevant broader unit tests/lint in proportion to changes.
3. Build `app:assembleGmsDebug` or the verified equivalent and inspect APK package/output.
4. Install alongside official OwnTracks on S24.
5. Verify launch, permissions, tracking modes, dense capture, persistence across restart, HTTP/MQTT configuration as available, reconnect/retry, and explicit queue-full behavior using a safe test mechanism.
6. Reconcile plan, sidecar, registers, and direct/deferred validation.

## Acceptance Criteria
- Capacity is 5,000,000 in the travel build and directly verified.
- Full normal enqueue does not remove any existing queued row.
- Full failed-message requeue does not remove an unrelated queued row.
- Failure to persist/requeue is explicit; it is not logged or shown as success.
- Room persistence, donor acquisition behavior, all tracking modes, and HTTP/MQTT compatibility remain intentionally preserved.
- GMS debug APK builds with an identity that coexists with official OwnTracks.
- Focused tests prove capacity rejection/non-eviction; relevant existing tests/build checks pass.
- S24 validation evidence is recorded, or each unavailable manual check is precisely deferred with instructions.

## Risks
- Because dequeue removes before send, a failed message may be outside the database during the send attempt; implementation must reason carefully about process interruption and requeue failure.
- Package separation can affect authorities, intent actions, configuration URIs, data isolation, tests, and scripts; do not perform blind namespace replacement.
- Five million records may have storage/startup/backup/performance impacts and does not prove long-duration reliability.
- Existing queue clearing on connection preference changes remains a separate data-loss concern for the permanent model and must not be silently expanded in this milestone.

## Stop Conditions
Stop and request owner direction if:
- queue-full UX requires a material product-policy choice not resolvable from current authority
- package separation would knowingly break required compatibility/security behavior
- evidence shows the requested bounded approach cannot avoid history loss
- physical-device access or required credentials are unavailable at the final validation gate

## Idempotence and Recovery
Source/test edits and builds are rerunnable. Before resuming after interruption, inspect Git status and the checkpoint sidecar; do not rerun device installation or destructive test setup without confirming target package/data. Generated Gradle outputs remain non-authoritative and ignored.

## Progress
- [x] 2026-11-24: plan created from direct donor source, test, build, Git, and owner-requirement inspection.
- [ ] Re-anchor and define queue-full reporting contract.

## Surprises & Discoveries
- None beyond the documented donor eviction paths and separate bind-mounted workspace arrangement.

## Decision Log
- DEC-0012 governs scope; no implementation-local decision has yet been made.

## Outcomes & Retrospective
- Implementation has not started. This is the handoff artifact for the next mission.

## Checkpoint Manifest
- Sidecar: `plans/ACTIVE/2026-11-24_travel-build-v0.checkpoint.json`

