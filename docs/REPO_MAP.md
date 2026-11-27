# Repository Map

## Repository Identity
- Writable authoritative path in the owner's development environment: `/home/drmemoryfish/ChatGPT/LateralLine`
- Bind-mounted source path: `/mnt/E/AI/LateralLine`
- Android Gradle root: `project/`
- Current branch after Travel Build closeout: `travel-build-v0`
- Owner-recorded LateralLine fork date: 2026-10-10
- Governance foundation instantiation date: 2026-11-24
- OwnTracks donor/inception commit: `3129fc23296c16ec5dd9f46d91d0fd94942b9f49`; Git commit date: `2026-10-05T11:37:16+01:00`
- Travel Build v0 substantive closeout: `90cba8c37bd15a1ddd5e32e8f02e4dded0c3f42e`; bookkeeping follow-up: `f2995e5c8bee3b6a43a7e8c5d140bf4adae64f2f`.

Environment paths are evidence about the owner's workstation, not portable repository requirements; agents running elsewhere must not invent or assume the bind mount exists.

## Observed Donor/Travel Android Areas
- `project/settings.gradle.kts`: Gradle settings; includes `:app` and `:location-kalman`; root name remains donor-derived.
- `project/app/`: Android app module, manifest/resources, build variants, Room schemas, main source, and tests.
- `project/location-kalman/`: donor Kalman-filter module used by location behavior.
- `project/app/src/main/java/org/owntracks/android/services/BackgroundService.kt`: foreground/background service orchestration and location request setup.
- `project/app/src/main/java/org/owntracks/android/services/LocationProcessor.kt`: current donor-derived location validation/enrichment and outbound message production. It is not yet a durable-history writer.
- `project/app/src/main/java/org/owntracks/android/services/MessageProcessor.kt`: outgoing transport queue producer/consumer, endpoint selection, retry/requeue, inbound processing, and preference-triggered queue clearing. Post-Travel audit findings are recorded in the failure/risk registers.
- `project/app/src/main/kotlin/org/owntracks/android/data/repos/RoomBackedMessageQueue.kt`: current Room-backed outgoing **transport queue**, not permanent local history.
- `project/app/src/main/kotlin/org/owntracks/android/data/repos/{AsyncDeQueue,MessageQueueDao,MessageQueueDatabase,MessageQueueEntity}.kt`: transport-queue contract/persistence.
- `project/app/src/main/kotlin/org/owntracks/android/di/MessageQueueModule.kt`: Hilt provider; Travel Build configured capacity is `5_000_000`.
- `project/app/src/main/java/org/owntracks/android/net/http/HttpMessageProcessorEndpoint.kt`: current HTTP compatibility transport.
- `project/app/src/main/java/org/owntracks/android/net/mqtt/MQTTMessageProcessorEndpoint.kt`: current MQTT compatibility transport; MQTT client persistence remains separate from the outgoing application queue.
- `project/app/src/main/java/org/owntracks/android/preferences/`: donor preference/configuration model and ordinary SharedPreferences backing.
- `project/app/src/main/java/org/owntracks/android/logging/`: donor in-memory/debug logging surfaces; rich debug output is not the future external-tooling trust boundary.
- `project/app/src/gms/`: Google Play Services location/geofencing/map flavor.
- `project/app/src/oss/`: platform/AOSP location and OpenStreetMap flavor. Long-term open/self-hosted independence makes this architectural path important even though exact future mobile dependencies remain open.
- `project/app/src/test/`, `src/androidTest/`, `src/testGms/`, `src/testOss/`: host/instrumented/flavor tests.
- `project/app/build.gradle.kts`: GMS/OSS flavors, debug/release types, current LateralLine Travel application ID, Java 21, test, lint, Room configuration.
- `project/app/schemas/`: exported donor/Travel Room schema history for existing databases; no permanent LateralLine history schema exists yet.
- `justfile`: retained local build/test/device commands.
- `.github/workflows/`: retained donor workflows; inspect actual LateralLine run/status evidence before treating them as enforced CI.

## Durable LateralLine Owner Documents
- `docs/PROJECT_BRIEF.md`: mission, product direction, security/privacy invariants, current reality.
- `docs/PLATFORM_ARCHITECTURE.md`: canonical-history, synchronization, storage lifecycle/fidelity, migration, portability/server identity, protocol and tooling trust boundaries.
- `docs/ROADMAP.md`: milestone sequencing/readiness/dependencies/evidence expectations.
- `docs/TRUTH_HIERARCHY.md`, `docs/AUTHORITY_MATRIX.md`, `docs/GOVERNANCE_NAVIGATION.md`: authority/navigation.
- `docs/TASK_PROTOCOL.md`, `PLANS.md`, `docs/QUALITY_BAR.md`: execution/planning/completion contracts.
- named registers: decisions, failures, risks, questions, progress, references.

## Retained Donor Reference Documents
- `docs/DATA_FLOW_DESIGN.md`: inherited OwnTracks technical data-flow document. Some diagrams predate the current Room queue and are stale; reference evidence only.
- `docs/PREFERENCES.md`: donor preference reference; useful evidence, not LateralLine policy.
- `CI.md`: donor CI/release reference; not LateralLine CI authority.
- `CHANGELOG.md` and upstream source/history: donor behavior/release evidence.

## Governance And Supporting Areas
- `docs/`: durable owner documents, policy, registers, architecture direction.
- `plans/ACTIVE/`: active substantial-work plans and checkpoint sidecars. Completed historical plans may remain until normal lifecycle/archive rules move them.
- `logs/`, `plans/`, `evals/`, `schemas/`: reusable templates/schemas.
- `artifacts/reports/`: generated reports/audits; `artifacts/tmp/`: disposable scratch output.
- `archive/`: retired/superseded support surfaces with successor notes.
- `skills/`: optional stable repeated workflows.

## Placement Boundary
No LateralLine backend, permanent local-history implementation area, deployment stack, independent mobile shell, or backend schema source area exists yet. A future approved implementation plan must define/map new source areas before creation. Documentation may specify those future boundaries without creating speculative scaffolding.
