# Progress Log

Append-only work journal for continuity. Entries remain oldest-to-newest. A pending commit is updated after a later checkpoint establishes its hash.

## Entry Template
### YYYY-MM-DD
- Timestamp (UTC):
- Event date (UTC), if different:
- Session goal:
- Completed:
- Evidence or artifacts:
- Decisions referenced:
- Failures referenced:
- Open questions added or resolved:
- Entry commit:
- Push status:
- Validation state:
- Next recommended step:
- Stop reason, if any:
- Recorded by:

## Entries
### 2026-11-24
- Timestamp (UTC): 2026-11-24T21:59:41Z
- Event date (UTC), if different: 2026-11-24
- Session goal: instantiate the reusable governance template non-destructively in LateralLine and prepare Travel Build v0 without modifying application source.
- Completed: inspected template and donor repository; established authority, workflow, evidence, roadmap, decisions, risks, questions, references, repository map, reusable templates, and active Travel Build v0 plan/sidecar; migrated useful donor README information; verified temporary `PROJECT.md`, `DECISIONS.md`, and `UPSTREAM.md` were absent.
- Evidence or artifacts: governance files under repo root, `docs/`, `plans/`, `logs/`, `artifacts/`, `archive/`, `evals/`, `schemas/`, and `skills/`; Git/source/template validation commands.
- Decisions referenced: DEC-0001 through DEC-0012
- Failures referenced: none
- Open questions added or resolved: OQ-0001 through OQ-0012 added
- Entry commit: pending; governance foundation remains uncommitted by this task
- Push status: not pushed
- Validation state: verified documentation state; implementation intentionally deferred
- Next recommended step: execute `plans/ACTIVE/2026-11-24_travel-build-v0.md` as a separate implementation mission.
- Stop reason, if any: governance mission complete; application implementation explicitly out of scope.
- Recorded by: Codex

### 2026-11-24 — chronology/provenance validation
- Timestamp (UTC): 2026-11-24T22:09:25Z
- Event date (UTC), if different: 2026-11-24
- Session goal: validate historical versus current-session dates before committing the governance foundation.
- Completed: distinguished donor commit, owner-recorded fork, local clone/branch creation, prior decision-event, and current governance dates; restored supported historical dates and added direct Git timestamps where available.
- Evidence or artifacts: `git show` for donor commit; `git reflog --date=iso-strict --all`; chronology search across governance files; current system UTC clock.
- Decisions referenced: DEC-0001 through DEC-0012; decision content unchanged
- Failures referenced: none
- Open questions added or resolved: none
- Entry commit: pending; governance foundation remains uncommitted
- Push status: not pushed
- Validation state: verified against owner-supplied history and local Git evidence; remote GitHub fork timestamp not independently queried
- Next recommended step: review and commit the governance foundation, then execute the Travel Build v0 plan in a separate implementation mission.
- Stop reason, if any: chronology validation complete; commit/push explicitly excluded.
- Recorded by: Codex

### 2026-11-24 — Travel Build v0 host implementation and verification
- Timestamp (UTC): 2026-11-24T23:50:56Z
- Event date (UTC), if different: 2026-11-24
- Session goal: execute the bounded Travel Build v0 plan without beginning permanent history, backend, identity, UI, circles, or sensor architecture.
- Completed: re-anchored from repository truth; defined DEC-0013; raised injected queue capacity to 5,000,000; removed normal-enqueue and failed-requeue eviction; exposed explicit enqueue success/failure and terminal requeue failure; added small-capacity/caller regression coverage; established the co-installable `org.lateralline.android.travel.debug` / `LateralLine Travel` identity with isolated actions/providers/shortcuts; preserved donor namespace, Room, acquisition/modes, HTTP/MQTT, and `owntracks://` configuration behavior; built and inspected the GMS debug APK.
- Evidence or artifacts: full GMS debug unit suite 915/915 passed; full OSS debug unit suite 413/413 passed; focused queue/processor tests passed; full formatting passed; GMS lint task passed with the pre-existing non-gating donor backlog; Android-test APK compiled; GMS debug APK `/home/drmemoryfish/ChatGPT/LateralLine/project/app/build/outputs/apk/gms/debug/app-gms-debug.apk`, SHA-256 `6ab0b5072e9d56ab2be359640e69e5fa9af603adcecf0787367b6edd27377b99`; AAPT and APK signer inspection; final ADB device list empty.
- Decisions referenced: DEC-0001, DEC-0002, DEC-0010, DEC-0012, DEC-0013
- Failures referenced: FAIL-0001
- Open questions added or resolved: none; permanent-history and synchronization questions remain out of scope
- Entry commit: pending; implementation remains uncommitted
- Push status: not pushed
- Validation state: host-side implementation/build/tests verified; Samsung Galaxy S24 installation, lifecycle, dense capture, persistence/reconnect, endpoint, and focused Room instrumentation execution precisely deferred because no device was attached
- Next recommended step: attach the Samsung Galaxy S24 and execute the active plan's deferred device checklist before relying on the build for travel.
- Stop reason, if any: physical-device access unavailable at the final validation gate; all safe host-side work completed
- Recorded by: Codex

### 2026-11-26 — Travel Build v0 interrupted-state recovery
- Timestamp (UTC): 2026-11-26T15:17:51Z
- Session goal: safely resume the bounded Travel Build v0 mission from the owner-preserved interrupted checkpoint without redoing completed implementation.
- Completed: confirmed the working tree began clean on `travel-build-v0`; confirmed local HEAD and `origin/travel-build-v0` both resolve to recovery commit `f2d98fc9e8ffca1cdf4b7309b9040e8a01c9eb2c`; compared that commit's 21-file scope with the recorded pre-interruption implementation state; confirmed the APK, Android-test APK, unit-test reports, and lint reports survived; reverified the APK SHA-256, package, label, version, and v2 debug signature; performed a fresh ADB probe; reconciled the stale checkpoint sidecar to the actual committed/pushed state; updated DEC-0012/DEC-0013 validation status; and re-reviewed R-0001/R-0004/R-0013 without changing their conclusions.
- Evidence or artifacts: Git commit/diff/status and remote-tracking ref; `/home/drmemoryfish/ChatGPT/LateralLine/project/app/build/outputs/apk/gms/debug/app-gms-debug.apk`; `/home/drmemoryfish/ChatGPT/LateralLine/project/app/build/outputs/apk/androidTest/gms/debug/app-gms-debug-androidTest.apk`; preserved Gradle reports under `project/app/build/`; `aapt dump badging`; `apksigner verify --verbose --print-certs`; `adb devices -l` with no attached devices.
- Decisions referenced: DEC-0012 and DEC-0013; unchanged
- Failures referenced: FAIL-0001; no recurrence and no new product/build failure
- Open questions added or resolved: none
- Entry commit: pushed WIP recovery checkpoint `f2d98fc9e8ffca1cdf4b7309b9040e8a01c9eb2c`; this recovery reconciliation is intentionally uncommitted pending owner direction
- Push status: implementation commit is pushed to `origin/travel-build-v0`; this recovery reconciliation is not pushed
- Validation state: host implementation and verification remain complete; a fresh 2026-11-26 probe found no attached device, so Samsung Galaxy S24 runtime validation remains precisely deferred
- Next recommended step: attach the Samsung Galaxy S24 and execute the active plan's ten-step deferred validation checklist, beginning with co-installation alongside official OwnTracks.
- Stop reason, if any: physical-device access is unavailable at the final validation gate; no implementation or governance ambiguity remains.
- Recorded by: Codex

### 2026-11-26 — historical Git/bookkeeping audit and workflow ownership
- Timestamp (UTC): 2026-11-26T15:38:00Z
- Session goal: reconcile repository history from the donor/inception boundary, establish owner-authorized Codex commit/bookkeeping responsibility, and commit/push the reviewed recovery governance scope without rewriting history.
- Completed: fetched `origin`; audited branch/remotes, full commit metadata/topology, reflogs, the donor boundary, all LateralLine commits, durable registers, workflow authorities, the active plan, and checkpoint; confirmed the history is linear from donor commit `3129fc23296c16ec5dd9f46d91d0fd94942b9f49` through governance commit `2913c6182980c3e86e5fbe9f217faf6cdc80028a` and WIP recovery checkpoint `f2d98fc9e8ffca1cdf4b7309b9040e8a01c9eb2c`, with no other pre-audit LateralLine commits; reconciled the stale roadmap and established DEC-0014 plus the minimal commit/hash protocol in existing owner documents; Codex then created and pushed the substantive governance reconciliation commit `31a90d29aa3b7c8ff5eb4c9d7ceb53b8b0103a4b`.
- Evidence or artifacts: Git log/show/reflog/branch/remote/status/diff evidence; `2913c6182980c3e86e5fbe9f217faf6cdc80028a` author/commit timestamp 2026-11-24T23:18:17Z and remote-tracking push reflog 2026-11-24T23:18:23Z; `f2d98fc9e8ffca1cdf4b7309b9040e8a01c9eb2c` author/commit timestamp 2026-11-26T15:12:53Z and remote-tracking push reflog 2026-11-26T15:12:55Z; `31a90d29aa3b7c8ff5eb4c9d7ceb53b8b0103a4b` author/commit timestamp 2026-11-26T15:39:43Z and remote-tracking push reflog 2026-11-26T15:40:01Z.
- Decisions referenced: DEC-0012, DEC-0013, and DEC-0014
- Failures referenced: FAIL-0001 remains contained; no new failure
- Open questions added or resolved: none
- Entry commit: substantive historical-audit and recovery-governance reconciliation commit `31a90d29aa3b7c8ff5eb4c9d7ceb53b8b0103a4b`, created by Codex in this owner-authorized workflow
- Push status: `31a90d29aa3b7c8ff5eb4c9d7ceb53b8b0103a4b` pushed to `origin/travel-build-v0`; this entry and checkpoint update form the single non-recursive bookkeeping follow-up whose own hash/push are verified directly from Git under DEC-0014
- Validation state: Git proves both LateralLine commits and pushes. The 2026-11-24 progress entries correctly preserve that their work was pending/uncommitted at those original timestamps; later Git proves governance commit `2913c618...` completed that foundation. The original task explicitly excluded commit/push and Git records the owner identity as author/committer, so the audit attributes `2913c618...` to an owner-performed follow-up. The owner explicitly identifies `f2d98fc9...` as the manually created interruption-recovery checkpoint.
- Next recommended step: proceed only when the Samsung Galaxy S24 is available for the active plan's deferred validation checklist.
- Stop reason, if any: governance commit/push workflow complete after the single bookkeeping follow-up; S24 runtime validation remains deferred.
- Recorded by: Codex

### 2026-11-26 — Samsung Galaxy S24 validation resumed
- Timestamp (UTC): 2026-11-26T18:04:20Z
- Session goal: continue Travel Build v0 from the physical-device boundary without changing host implementation or official OwnTracks.
- Completed: independently verified authorized device `R3CX300ZTFR` as Samsung `SM-S921B` on Android 16/API 36; confirmed current Android user 0 and preserved official OwnTracks 2.5.9 (`org.owntracks.android`, UID 10030, Google Play installer, existing data directory); installed the existing Travel Build APK successfully as `org.lateralline.android.travel.debug` 2.6.0 (UID 10427, distinct data directory/providers/actions); ran the physical-device `RoomBackedMessageQueueTest` class with 7/7 passing and both new full-queue non-eviction cases observed; reinstalled the travel APK after normal connected-test cleanup; reconfirmed both packages; and cold-launched the travel app into `WelcomeActivity`.
- Evidence or artifacts: ADB device/package/dumpsys/activity output; APK SHA-256 `6ab0b5072e9d56ab2be359640e69e5fa9af603adcecf0787367b6edd27377b99`; `project/app/build/outputs/androidTest-results/connected/debug/flavors/gms/TEST-SM-S921B - 16-_app-gms.xml` reporting 7 tests with 0 failures/errors/skips; per-test logs for both full-queue cases.
- Decisions referenced: DEC-0012 and DEC-0013; unchanged
- Failures referenced: none; target-app removal was normal connected-test cleanup and official OwnTracks was unaffected
- Open questions added or resolved: none
- Entry commit: pending physical-validation checkpoint
- Push status: not pushed; validation remains in progress
- Validation state: automated device identity, coexistence/sandbox separation, installation, and targeted queue instrumentation passed. The phone is currently locked with `WelcomeActivity` focused behind the Samsung keyguard; permissions, visible identity, onboarding, tracking modes, real acquisition, endpoint compatibility, persistence/reconnect, shortcuts/config UI, and Samsung background lifecycle behavior remain unverified.
- Next recommended step: owner unlocks the phone and leaves LateralLine Travel visible on its welcome screen, then Codex resumes UI-state inspection and the next feasible automated/manual checks.
- Stop reason, if any: exact manual boundary reached—the Samsung lock screen prevents observing or completing onboarding.
- Recorded by: Codex

### 2026-11-26 — Samsung Galaxy S24 onboarding and permission validation
- Timestamp (UTC): 2026-11-26T18:31:40Z
- Session goal: continue the physical-device checklist through onboarding and Android permission setup while preserving official OwnTracks and avoiding unverified runtime claims.
- Completed: owner unlocked the S24 and performed each directed UI action while Codex inspected the resulting screen and package state through ADB; completed the donor onboarding sequence; granted precise foreground location, background/all-the-time location, and notifications; verified all four runtime grants directly; followed `OPEN PREFERENCES` into the isolated travel app's Preferences screen; and observed the fresh MQTT Connection screen with TLS enabled and no host, username, or password configured.
- Evidence or artifacts: ADB UI hierarchies for each onboarding/permission screen; `dumpsys package org.lateralline.android.travel.debug` showing `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`, `ACCESS_BACKGROUND_LOCATION`, and `POST_NOTIFICATIONS` granted; Preferences UI showing the Connection/Reporting/Configuration management surfaces; Connection UI showing MQTT mode, default port 8883, TLS enabled, and endpoint credentials not set.
- Decisions referenced: DEC-0012 and DEC-0013; unchanged
- Failures referenced: none
- Open questions added or resolved: none; real endpoint details remain owner-held and are not recorded in governance files
- Entry commit: pending physical-validation checkpoint
- Push status: not pushed; validation remains in progress
- Validation state: onboarding and permission paths passed on the S24. Endpoint compatibility, all tracking modes, real acquisition, queue persistence/restart, reconnect/drain and server receipt, shortcut/config behavior, and representative Samsung background lifecycle remain unverified.
- Next recommended step: owner configures an available real HTTP or MQTT endpoint directly on the phone without sharing secrets in logs; Codex then verifies connection/status and continues the remaining device matrix.
- Stop reason, if any: exact manual/credential boundary reached—the repository defines no test endpoint and the fresh install has no endpoint or credentials.
- Recorded by: Codex

### 2026-11-26 — S24 endpoint/mode validation and shortcut defect resolution
- Timestamp (UTC): 2026-11-26T20:36:44Z
- Session goal: continue the bounded Travel Build v0 device matrix, resolve the Samsung launcher shortcut defect without exposing internal activities, and preserve official OwnTracks plus existing Travel app state.
- Completed: owner configured an HTTP endpoint without disclosing its value; verified a USER-triggered publish in Significant Changes mode and another in Manual mode each queued, received HTTP 200, and drained to endpoint queue 0; exercised Quiet, Manual, Significant Changes, and Move modes while retaining foreground-service/location registration evidence; confirmed Battery optimization whitelist remains `No`; confirmed both installed packages register for `owntracks:///config`; reproduced both static shortcuts failing from Samsung Launcher; identified direct non-exported activity targeting and an unresolved shortcut package resource token; replaced direct targets with two narrow exported aliases whose underlying activities remain private; added literal release/debug shortcut package overlays plus source and installed-package regression tests; rebuilt and update-installed the GMS debug APK; and physically verified from Samsung Launcher that `View logs` opens the Logs screen and `Preferences` opens the Preferences screen.
- Evidence or artifacts: focused `ShortcutContractTest`; full `:app:ktfmtCheck`; successful `:app:assembleGmsDebug` and `:app:assembleGmsDebugAndroidTest`; merged manifest/resource inspection; installed `LauncherShortcutContractTest` 1/1 passed; Samsung Launcher foreground components and allowlisted screen-title booleans; final APK `/home/drmemoryfish/ChatGPT/LateralLine/project/app/build/outputs/apk/gms/debug/app-gms-debug.apk`, SHA-256 `dc2f7ab4d9c4cb70e1f167b2fc8db8e8af7251a3deb8ed87bdbf00aafa2f3f40`.
- Decisions referenced: DEC-0012, DEC-0013, and DEC-0014; no material architecture decision changed
- Failures referenced: FAIL-0002 and FAIL-0003
- Open questions added or resolved: launcher shortcut contract resolved; representative Samsung background behavior and offline queue persistence/reconnect remain open validation work, not architecture questions
- Entry commit: substantive shortcut-resolution commit `18582d1a11d1aafeff86363259770d8e91156e80`, created by Codex in the owner-authorized workflow at 2026-11-26T20:44:09Z
- Push status: `18582d1a11d1aafeff86363259770d8e91156e80` pushed to `origin/travel-build-v0`; this entry and checkpoint update form the single non-recursive bookkeeping follow-up whose own hash/push are verified directly from Git under DEC-0014
- Validation state: both Samsung Launcher shortcuts pass; official OwnTracks remains installed; Travel package identity, existing HTTP state, queue 0, four runtime grants, Move mode, and background-service presence survived update installation. Representative movement/background lifecycle, Samsung battery configuration, offline queue growth and restart persistence, reconnect/drain, and an actual configuration import remain unverified.
- Next recommended step: resume only the remaining physical-device validation boundary in the active plan: Samsung battery/background setup, representative movement, offline restart persistence/reconnect/drain, and actual configuration import/external-control checks.
- Stop reason, if any: shortcut defect resolved at its governed commit boundary; broader Travel Build v0 device acceptance remains open.
- Recorded by: Codex

### 2026-11-27 — Travel Build v0 final S24 lifecycle and offline acceptance
- Timestamp (UTC): 2026-11-27T19:11:08Z
- Event date (UTC), if different: physical background baseline began 2026-11-26T22:34:47Z; owner returned the device on 2026-11-27 after approximately 5+ hours of movement.
- Session goal: complete the remaining bounded Samsung battery/background, representative movement, offline persistence/reconnect, and safe configuration/external-control validation without revisiting completed host work or changing official OwnTracks.
- Completed: set only LateralLine Travel to Samsung Unrestricted and verified Android battery-optimization exemption; retained a Move/background baseline; completed the owner-reported physical movement period; restored Move after an unattributed return in Significant; disabled both originally enabled network transports without altering the endpoint; grew the queue from 0; verified retained queue counts of 35, 40, 46, and 51 across active use, UI backgrounding, Travel force-stop/relaunch, and full S24 reboot, followed by 55 while reconnect awaited a new report; verified Move, battery exemption, process, foreground service, and location registration after reboot; restored both network transports; triggered reconnect and observed drain to 0; inspected the configuration/control security switches without reading secrets; and verified final package, lifecycle, network, mode, queue, and HTTP state. Official OwnTracks remained installed and untouched.
- Evidence or artifacts: owner report of approximately 5+ movement hours; process elapsed time exceeding 20 hours across the surrounding validation period; final retained 35.5-minute pre-reboot Travel log slice with 2 background acquisitions, 2 successful sends, 2 HTTP 2xx responses, and zero exact donor drop/requeue/persistence errors; post-reboot queue state 51; final queue 0 and HTTP 200; post-reconnect Travel-process log counts of 94 successful sends/HTTP 2xx, 0 non-2xx, 0 drop/clear/deserialization/persistence errors, and 90/90 timestamped location successes in nondecreasing order. Broad device output remained outside the transcript after FAIL-0002 containment was reasserted.
- Decisions referenced: DEC-0012, DEC-0013, and DEC-0014; no architecture or product behavior decision changed.
- Failures referenced: FAIL-0002 recurrence and containment; FAIL-0003 remained resolved with no shortcut regression.
- Open questions added or resolved: no architecture question added. The Move-to-Significant transition during the long window remains unattributed because the owner is unsure whether the mode action was used; later force-stop and device-reboot checks preserved Move, so the event is recorded as a validation limitation rather than a reproduced defect.
- Entry commit: pending final Travel Build v0 acceptance commit; `final_commit_hash` remains null until that commit exists.
- Push status: current acceptance reconciliation is uncommitted and unpushed; existing HEAD `996dd4cab9868afeeb214e44dc90a26d83626298` matches `origin/travel-build-v0` before this final workflow.
- Validation state: Travel Build v0's bounded acceptance criteria are satisfied with direct S24 evidence or precise deferral. The 5+ hour period is not claimed as a clean long-window Move/dense-capture pass, and actual configuration import/authenticated external control are not claimed because external configuration and intent control are disabled and no safe owner-provided authenticated fixture exists.
- Next recommended step: run governance validation, create and push the final substantive commit, record its real hash in one non-recursive bookkeeping follow-up, verify remote equality and a clean worktree, then await explicit owner direction.
- Stop reason, if any: no remaining in-scope implementation or device blocker; only the governed final commit/bookkeeping boundary remains.
- Recorded by: Codex
