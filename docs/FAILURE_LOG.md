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
