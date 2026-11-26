# Travel Build v0 Execution Plan

## Metadata
- Plan name: Travel Build v0
- Date started: 2026-11-24
- Owner: project owner
- Status: `implementation complete; Samsung Galaxy S24 validation deferred`

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
- [x] 2026-11-24: re-anchored at clean `travel-build-v0` commit `2913c6182980c3e86e5fbe9f217faf6cdc80028a`; inspected queue/status/notification/build/manifest/shortcut contracts, focused tests, and donor history.
- [x] 2026-11-24: defined the non-destructive queue-full and coexistence contract in DEC-0013.
- [x] 2026-11-24: implemented the 5,000,000 capacity, removed both caller-side eviction paths, made enqueue/requeue rejection explicit, and added focused caller plus small-capacity Room regression coverage.
- [x] 2026-11-24: implemented the separate `org.lateralline.android.travel.debug` identity, `LateralLine Travel` label, variant-derived actions/provider authorities/shortcut package, and retained donor namespace plus `owntracks://` interoperability.
- [x] 2026-11-24: passed focused and full GMS/OSS JVM tests, formatting, GMS lint task, Android-test compilation, GMS debug assembly, and APK signature/identity inspection; updated the plan and durable registers before sidecar reconciliation was interrupted.
- [x] 2026-11-24: confirmed no Android device was attached at the final ADB gate and precisely deferred Samsung Galaxy S24 install/runtime validation below.
- [x] 2026-11-26: resumed from pushed recovery commit `f2d98fc9e8ffca1cdf4b7309b9040e8a01c9eb2c`, confirmed HEAD and `origin/travel-build-v0` match, revalidated the preserved APK/hash/identity/signature and generated evidence, rechecked that no Android device is attached, and reconciled the stale checkpoint sidecar without redoing completed implementation.

## Surprises & Discoveries
- The Room queue already rejects full enqueue/requeue without deletion; both eviction paths are caller behavior inherited from the earlier deque implementation.
- The current debug icon is already visually distinguished with a pink background, but launcher/notification labels still say OwnTracks and shortcut targets hard-code the official release package.
- Provider authorities already derive from `${applicationId}` and application sandboxing isolates Room/preferences; the donor Kotlin namespace and instrumentation runner do not need to change for installation coexistence.
- No Android device was listed by the initial `adb devices -l` probe; physical validation remains pending until the final gate is rechecked.
- The configured SDK was missing Android platform 36 revision 2; Gradle installed it successfully before compilation.
- A combined final Gradle invocation hit a formatter worker-daemon timeout; isolated single-worker retry and the final full formatting check passed. See FAIL-0001.
- GMS lint remains configured with `abortOnError = false` and reports the donor backlog (233 errors, 338 warnings, 2 hints); no lint finding names the changed queue code, and the additive identity-resource warning was removed before the final run.

## Decision Log
- DEC-0012 governs scope.
- DEC-0013 defines the Travel Build v0 queue-full contract and minimal coexistence identity.

## Outcomes & Retrospective
- Travel Build v0 implementation and host-side verification are complete. The app still uses the donor Room queue, acquisition/tracking-mode code, and HTTP/MQTT endpoints; no permanent-history, backend, identity, circles, sensor, or UI architecture was introduced.
- Full GMS debug unit suite: 915 tests, 0 failures/errors/skips. Full OSS debug unit suite: 413 tests, 0 failures/errors/skips. Focused `MessageProcessorTest` and `MessageQueueModuleTest` also passed independently.
- `:app:ktfmtCheck`, `:app:lintGmsDebug`, `:app:assembleGmsDebug`, and `:app:assembleGmsDebugAndroidTest` completed successfully. The Room capacity/non-eviction instrumentation tests compiled but could not run without a device.
- APK: `/home/drmemoryfish/ChatGPT/LateralLine/project/app/build/outputs/apk/gms/debug/app-gms-debug.apk`
- APK identity: `org.lateralline.android.travel.debug`; label `LateralLine Travel`; version `2.6.0` (`420600000`); SHA-256 `6ab0b5072e9d56ab2be359640e69e5fa9af603adcecf0787367b6edd27377b99`; valid Android debug v2 signature.
- Direct APK inspection confirms isolated file/log provider authorities, isolated exported intent actions, variant-correct shortcut package targeting, and retained `owntracks://config` handling.
- No Samsung Galaxy S24 was attached when `/mnt/E/AI/Android/sdk/platform-tools/adb devices -l` was run at the final validation gate. Installation, permissions, OEM lifecycle, tracking-mode, real dense capture, persistence/restart, endpoint, reconnect/drain, and coexistence checks are therefore deferred rather than claimed.
- Recovery on 2026-11-26 confirmed the clean interrupted checkpoint at `f2d98fc9e8ffca1cdf4b7309b9040e8a01c9eb2c` is present on both local HEAD and `origin/travel-build-v0`. The preserved APK still has the recorded SHA-256, package, label, and valid v2 debug signature. A fresh ADB probe again listed no device, so the deferred S24 checklist remains the only incomplete acceptance boundary.

### Deferred Samsung Galaxy S24 validation
1. Attach the S24 with USB debugging enabled and confirm it appears in `/mnt/E/AI/Android/sdk/platform-tools/adb devices -l`.
2. Confirm official OwnTracks remains installed, then install the APK without uninstalling either package: `/mnt/E/AI/Android/sdk/platform-tools/adb install -r /home/drmemoryfish/ChatGPT/LateralLine/project/app/build/outputs/apk/gms/debug/app-gms-debug.apk`.
3. Verify both packages coexist (`org.owntracks.android` for official OwnTracks as applicable and `org.lateralline.android.travel.debug` for this build), and confirm the launcher/ongoing notification say `LateralLine Travel` with the distinct debug icon.
4. Launch the travel build, grant notification, precise foreground location, and background/all-the-time location permissions, and apply the Samsung battery/background settings required for uninterrupted travel capture.
5. Exercise Quiet, Manual, Significant, and Move modes; confirm manual publish and real location acquisition behave as before. Use the owner's dense Move interval, max-frequency, and accuracy preferences without treating them as new defaults.
6. Configure and smoke-test the available real HTTP and/or MQTT endpoint. Confirm OwnTracks payload/topic compatibility and that official OwnTracks remains independently configured.
7. Disconnect networking, collect a representative dense sequence, confirm queue length grows, swipe/restart and then process-restart the app, and confirm the queued count/history survives. Reconnect and verify retry/drain plus server receipt without unexpected gaps or reordering.
8. Run the safe small-capacity device regression tests rather than attempting to fill five million rows: from `project/`, run `./gradlew :app:connectedGmsDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=org.owntracks.android.data.repos.RoomBackedMessageQueueTest`. Confirm both full-enqueue and full-requeue tests reject the attempted message while preserving the two existing rows in order.
9. Verify the log and settings launcher shortcuts open this package, `owntracks://config` still offers/imports into the intended app, and external control actions use the `org.lateralline.android.travel.debug.*` names when enabled/authenticated.
10. Monitor app storage, queue length, battery behavior, and OEM kills during a representative travel window; stop using the build and preserve logs if storage pressure, queue corruption, restart loss, or background-acquisition regression appears.

## Checkpoint Manifest
- Sidecar: `plans/ACTIVE/2026-11-24_travel-build-v0.checkpoint.json`
