# LateralLine

LateralLine is an independent, open-source, self-hostable, offline-first, privacy-focused location platform for an individual and explicitly trusted circles. It began from an OwnTracks Android donor checkout and preserves useful donor knowledge while becoming its own product, data model, synchronization system, backend, identity/security architecture, and user experience.

The current application code is still predominantly OwnTracks Android. Travel Build v0 is complete as a bounded safety fork, but LateralLine's permanent durable-history store, synchronization protocol, backend, passkey/device identity, circles, storage lifecycle/forecasting, and redesigned product shell do not exist yet.

## Start Here
For substantial work:
1. read `AGENTS.md`
2. follow `docs/GOVERNANCE_NAVIGATION.md`
3. read only the relevant owner documents
4. read the active plan/checkpoint when one exists

Key owners:
- product/current reality: `docs/PROJECT_BRIEF.md`
- cross-system/data architecture: `docs/PLATFORM_ARCHITECTURE.md`
- sequencing/readiness: `docs/ROADMAP.md`

The retained `docs/DATA_FLOW_DESIGN.md`, `docs/PREFERENCES.md`, `CI.md`, and other donor documentation are reference evidence, not permanent LateralLine architecture.

## Current Frontier
Travel Build v0 completed its intended non-eviction/coexistence/device-validation boundary. A post-completion source audit identified retry, persistence-result, dequeue-before-send, queue-clear/corruption/migration, foreground-service, diagnostic/privacy, scale, and release-readiness debt. The default next architecture frontier is Durable Local History v1, with a bounded Travel hardening/characterization readiness gate where explicitly authorized. No later implementation milestone is active merely because it appears on the roadmap.

## Donor Build Context
The retained Gradle project is under `project/` with observed flavors:
- `gms`: Google Play Services location and Google Maps
- `oss`: platform/AOSP location and OpenStreetMap without a Google Play Services dependency

The long-term independence goal requires that a non-proprietary core path remain viable; this does not mean every donor integration is already production-ready.

## Upstream Relationship
- Official upstream: https://github.com/owntracks/android
- LateralLine origin: https://github.com/DrMemoryFish/LateralLine-Android.git
- OwnTracks donor/inception commit: `3129fc23296c16ec5dd9f46d91d0fd94942b9f49`
- Owner-recorded fork date: 2026-10-10

OwnTracks and OwnTracks Recorder are donor/reference/compatibility infrastructure. They are not LateralLine's permanent identity, authoritative record model, synchronization contract, or backend design.
