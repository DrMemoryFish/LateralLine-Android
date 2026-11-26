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
