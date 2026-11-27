# Failure Log

Append-only record for failed assumptions, regressions, misleading artifacts, and workflow mistakes. Do not manufacture historical failures from risks or proposals.

## Entry Template
### YYYY-MM-DD - FAIL-0000
- Date recorded (UTC):
- Event date (UTC), if different:
- Failure ID:
- Area:
- What was expected:
- What actually happened:
- Reason:
- Evidence:
- Severity: `low | medium | high`
- Root cause hypothesis:
- Validation state:
- Containment:
- Required regression coverage:
- Follow-up owner:
- Recorded by:

## Entries
### 2026-11-24 - FAIL-0001
- Date recorded (UTC): 2026-11-24
- Failure ID: FAIL-0001
- Area: Travel Build v0 verification environment
- What was expected: the final combined formatting, lint, and APK assembly invocation would complete normally.
- What actually happened: Gradle's `ktfmtCheckAndroidTest` workers 53 and 54 failed to connect to their child worker processes within 120 seconds, failing the combined invocation after 7 minutes 56 seconds.
- Reason: Gradle reported a worker-daemon connection timeout under the combined workload; no formatting violation was reported.
- Evidence: failing `:app:ktfmtCheckAndroidTest` output followed by successful isolated `./gradlew :app:ktfmtCheckAndroidTest --no-daemon --max-workers=1` and successful final full `:app:ktfmtCheck` with the same single-worker constraint.
- Severity: low
- Root cause hypothesis: transient worker-process startup/resource pressure in the build environment.
- Validation state: contained and successfully retried; product source/tests were not implicated
- Containment: isolate formatter work and cap Gradle workers for final verification.
- Required regression coverage: none in application code; retain the successful formatter output as task evidence.
- Follow-up owner: build workflow only if the timeout recurs
- Recorded by: Codex

### 2026-11-26 - FAIL-0002
- Date recorded (UTC): 2026-11-26
- Event date (UTC), if different: 2026-11-26
- Failure ID: FAIL-0002
- Area: physical-device validation privacy handling
- What was expected: device evidence would be reduced to the minimum boolean/count/state needed for validation before entering the task transcript.
- What actually happened: one raw notification-metadata inspection emitted a reverse-geocoded location value in tool output.
- Reason: the initial ADB evidence command inspected a broad notification surface instead of filtering the device output before display.
- Evidence: owner correction during the 2026-11-26 S24 validation session; the sensitive value is intentionally not reproduced in this durable record.
- Severity: medium
- Root cause hypothesis: insufficient output minimization at the command boundary for a location-bearing Android surface.
- Validation state: contained; all subsequent device checks extract only named states, booleans, counts, safe labels, component names, and error classes.
- Containment: never print raw logcat, notification, preference, or UI records that may contain coordinates, addresses, endpoint details, network identifiers, or credentials; redirect or parse locally and emit only the allowlisted validation state.
- Required regression coverage: procedural rather than application code; retain this minimization rule for all location-device validation.
- Follow-up owner: every physical-device validation task
- Recorded by: Codex
- Recurrence update (UTC): 2026-11-27 — during continued S24 validation, a raw broad `dumpsys activity activities` payload reached the transcript and included unrelated third-party device metadata. The private values are intentionally not reproduced here. The owner reasserted command-boundary minimization: broad Android output must be redirected or parsed before transcript emission.
- Recurrence containment (UTC): 2026-11-27 — all subsequent broad UI, activity, package, location, connectivity, notification, preference, and log surfaces were either redirected to temporary files or piped through allowlist parsers; transcript output was limited to Travel component names, booleans, counts, mode names, queue counts, HTTP status codes/classes, and error classes. No further recurrence was observed through the final device boundary.
- Owner clarification (UTC): 2026-11-27 — the privacy failure is **not** that LateralLine may collect or locally expose rich private diagnostics such as precise coordinates, addresses, or useful device/network context to the owner. The failure is that broad raw personal-device data crossed into external AI/tooling output without an enforced least-privilege boundary. Authentication secrets are a separate class and should generally never enter diagnostic logs. Command-boundary allowlisting remains interim containment; the long-term requirement is a technically constrained tool-safe diagnostic interface/capability while preserving rich owner-private diagnostics.

### 2026-11-26 - FAIL-0003
- Date recorded (UTC): 2026-11-26
- Event date (UTC), if different: 2026-11-26
- Failure ID: FAIL-0003
- Area: Travel Build v0 Samsung launcher shortcuts
- What was expected: the visible `View logs` and `Preferences` static shortcuts would launch their intended LateralLine Travel screens.
- What actually happened: both shortcuts initially left Samsung Launcher in the foreground. Direct component evidence showed the shortcut targets were non-exported, and the first alias correction exposed a second defect: Android retained the `@string/application_id` shortcut package as a resource-token string rather than resolving it to the installed debug package.
- Reason: an external launcher cannot start the private activities directly, and static shortcut intent package parsing did not resolve the resource reference used for the variant package.
- Evidence: bounded external-start `SecurityException`; failed installed-device shortcut contract test showing a resource-token package; final host contract test, installed-device contract test, merged manifest/resource inspection, and Samsung Launcher launches of both intended screens.
- Severity: medium
- Root cause hypothesis: the donor shortcut contract assumed launcher access to internal activities and resource resolution behavior that the physical Samsung/Android 16 path does not provide.
- Validation state: resolved and physically verified on the S24
- Containment: keep the internal activities non-exported; expose only two enabled launcher aliases; give release/debug shortcut resource overlays literal matching package identities; cover source and installed-package contracts.
- Required regression coverage: `ShortcutContractTest` for source/variant/manifest invariants and `LauncherShortcutContractTest` for installed `ShortcutManager`/`PackageManager` resolution.
- Follow-up owner: Travel Build v0 and future Android identity changes
- Recorded by: Codex

### 2026-11-27 - FAIL-0004
- Date recorded (UTC): 2026-11-27
- Failure ID: FAIL-0004
- Area: donor/Travel outgoing retry state
- What was expected: retryable send failures decrement a finite retry budget coherently or otherwise follow a deliberate non-destructive retry policy without permanently starving newer queued work.
- What actually happened: direct source audit found that `sendAvailableMessages()` stores `retriesToGo - 1` inside `LastMessageStatus.RetryableFailure`, but the next iteration reads only `nextTimeWaitFor` and never assigns the stored retry count back to the live `retriesToGo` variable. The failed message is requeued into the head slot, so a persistently failing message can be selected repeatedly and newer regular messages can be starved. The branch that logs “Ran out of retries…Dropping” also requeues before making that decision, which is semantically inconsistent with an actual drop.
- Reason: retry budget/state is split between a mutable local variable and a status object without a complete state transition.
- Evidence: direct review of current `MessageProcessor.kt` on `travel-build-v0` after Travel Build v0 closeout.
- Severity: high
- Root cause hypothesis: inherited donor retry logic evolved around requeue/backoff without a single authoritative retry-attempt state; Travel Build non-eviction changes preserved that structure.
- Validation state: source-confirmed; persistent-failure runtime reproduction/regression test not yet performed in this consolidation.
- Containment: do not claim finite retry/drop semantics from current code; treat persistent-failure/head-of-line behavior as a readiness-gate defect.
- Required regression coverage: deterministic endpoint failures proving retry/backoff/count behavior, queue order/live-data progress, restart behavior, and no unintended data loss.
- Follow-up owner: bounded post-Travel hardening/readiness plan.
- Recorded by: ChatGPT consolidation under owner instruction

### 2026-11-27 - FAIL-0005
- Date recorded (UTC): 2026-11-27
- Failure ID: FAIL-0005
- Area: Travel queue persistence result propagation
- What was expected: if a location message cannot be persisted to the outgoing queue, the publishing pipeline does not represent that operation as successful/published.
- What actually happened: `MessageProcessor.queueMessageForSending()` returns `Deferred<Boolean>` and correctly returns `false` on persistence rejection/failure, but `LocationProcessor.publishLocationMessage()` calls it without awaiting/observing that result and immediately returns `Result.success(Unit)`. `onLocationChanged()` can then set `currentPublishedLocation` based on that success even though the queue write later fails.
- Reason: Travel Build added an explicit queue-layer result while preserving asynchronous donor call sites; the producer contract was not upgraded end-to-end within the bounded milestone.
- Evidence: direct current source review of `LocationProcessor.kt` and `MessageProcessor.kt` after Travel Build closeout.
- Severity: high
- Root cause hypothesis: scope deliberately stopped at non-destructive queue admission, leaving callers fire-and-forget.
- Validation state: source-confirmed; no runtime failure injection performed in this documentation mission.
- Containment: interpret Travel Build “explicit persistence failure” as a queue-layer signal, not proof of end-to-end publish/capture failure propagation. Future canonical-history capture must not report success until its durable commit completes.
- Required regression coverage: force queue/full/I/O failure through the producing location path and assert no published/captured success state is emitted.
- Follow-up owner: bounded post-Travel hardening/readiness plan and Durable Local History v1 contract.
- Recorded by: ChatGPT consolidation under owner instruction
