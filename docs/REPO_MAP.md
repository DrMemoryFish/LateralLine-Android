# Repository Map

## Repository Identity
- Writable authoritative path in this environment: `/home/drmemoryfish/ChatGPT/LateralLine`
- Bind-mounted source path: `/mnt/E/AI/LateralLine`
- Android Gradle root: `project/`
- Current branch at foundation: `travel-build-v0`
- Owner-recorded LateralLine fork date: 2026-10-10
- Governance foundation instantiation date: 2026-11-24
- OwnTracks donor/inception commit: `3129fc23296c16ec5dd9f46d91d0fd94942b9f49`; Git commit date: `2026-10-05T11:37:16+01:00`
- Local repository chronology: clone from LateralLine origin at `2026-10-10T12:50:05+01:00`; `travel-build-v0` branch created at `2026-10-10T12:50:25+01:00`

## Observed Donor Android Areas
- `project/settings.gradle.kts`: Gradle settings; includes `:app` and `:location-kalman`; root name remains `owntracks-android` at foundation.
- `project/app/`: Android app module, manifest/resources, build variants, Room schemas, main source, and tests.
- `project/location-kalman/`: standalone Kalman-filter module used by location behavior.
- `project/app/src/main/java/org/owntracks/android/services/BackgroundService.kt`: foreground/background service orchestration.
- `project/app/src/main/java/org/owntracks/android/services/LocationProcessor.kt`: validates/enriches acquired locations and creates outgoing messages.
- `project/app/src/main/java/org/owntracks/android/services/MessageProcessor.kt`: outgoing queue producer/consumer, endpoint selection, retries/requeue, inbound message processing, and preference-triggered queue clearing.
- `project/app/src/main/kotlin/org/owntracks/android/data/repos/RoomBackedMessageQueue.kt`: persistent Room-backed outgoing queue implementation.
- `project/app/src/main/kotlin/org/owntracks/android/data/repos/{AsyncDeQueue,MessageQueueDao,MessageQueueDatabase,MessageQueueEntity}.kt`: queue contract and Room persistence.
- `project/app/src/main/kotlin/org/owntracks/android/di/MessageQueueModule.kt`: Hilt queue provider and observed `100_000` capacity.
- `project/app/src/main/java/org/owntracks/android/net/http/HttpMessageProcessorEndpoint.kt`: HTTP transport.
- `project/app/src/main/java/org/owntracks/android/net/mqtt/MQTTMessageProcessorEndpoint.kt`: MQTT transport; `RoomMqttClientPersistence.kt` provides MQTT client persistence separately from the outgoing message queue.
- `project/app/src/gms/`: Google Play Services location/geofencing/map flavor implementation.
- `project/app/src/oss/`: platform/AOSP location and OpenStreetMap flavor implementation.
- `project/app/src/test/`: JVM unit tests, including message processor, location, transport, preferences, and workers.
- `project/app/src/androidTest/`: instrumented tests, including `RoomBackedMessageQueueTest.kt`, retry, MQTT, migration, behavior, and UI coverage.
- `project/app/src/testGms/` and `project/app/src/testOss/`: flavor-specific tests.
- `project/app/build.gradle.kts`: GMS/OSS flavors, debug/release types, application ID/namespace, Java 21, test and Room configuration.
- `project/app/schemas/`: exported Room schema history, including message queue database versions.
- `justfile`: local build/test/device commands.
- `.github/workflows/`: retained donor CI workflows.
- `docs/PREFERENCES.md` and `docs/DATA_FLOW_DESIGN.md`: retained donor technical documentation; useful evidence, not LateralLine governance authority.

## Donor README Information Migrated Here
The pre-foundation root README identified two flavors: `gms` uses Google Play Services location and Google Maps; `oss` avoids Google Play Services and uses platform location plus OpenStreetMap. It also documented distinct Google Play, GitHub-release, and F-Droid signing keys. Those facts describe donor distribution, not a LateralLine release policy; authoritative current donor details remain available from Git history and the upstream README.

## Governance Areas
- `docs/`: durable policy, architecture direction, and append-only registers.
- `plans/ACTIVE/`: active plans and checkpoint sidecars.
- `logs/`, `plans/`, `evals/`, `schemas/`: reusable templates.
- `artifacts/reports/`: generated reports/audits; `artifacts/tmp/`: disposable scratch output.
- `archive/`: retired/superseded support surfaces with successor notes.
- `skills/`: optional stable repeated workflows.

## Placement Boundary
No LateralLine backend, new mobile shell, deployment stack, or schema source area exists yet. A future approved plan must define and map any such area before creation.
