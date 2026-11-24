# LateralLine

LateralLine is an independent, self-hostable, offline-first, privacy-focused location platform for an individual and explicitly trusted circles. It began from an OwnTracks Android donor checkout and will preserve valuable donor knowledge while becoming its own product and architecture.

The current application code is still primarily OwnTracks Android. LateralLine's permanent backend, durable-history model, synchronization architecture, passkey/device identity, circles, and redesigned mobile experience do not exist yet.

## Start Here
For substantial work:
1. read `AGENTS.md`
2. follow `docs/GOVERNANCE_NAVIGATION.md`
3. read the relevant owner documents
4. read the active plan and checkpoint sidecar

The current implementation frontier is `plans/ACTIVE/2026-11-24_travel-build-v0.md`: a bounded safety change for the donor outgoing queue and a separately installable travel build. It is not a general redesign mission.

## Donor Build Context
The retained Gradle project is under `project/` and has two observed flavors:
- `gms`: Google Play Services location and Google Maps
- `oss`: platform/AOSP location and OpenStreetMap without a Google Play Services dependency

Use the commands in `justfile` and the CI workflows as current build/test evidence. See `docs/REPO_MAP.md` for precise source/test locations and the migrated donor README details.

## Upstream Relationship
- Official upstream: https://github.com/owntracks/android
- LateralLine origin: https://github.com/DrMemoryFish/LateralLine-Android.git
- OwnTracks donor/inception commit: `3129fc23296c16ec5dd9f46d91d0fd94942b9f49`; Git commit date: `2026-10-05T11:37:16+01:00`
- Owner-recorded LateralLine fork date: 2026-10-10; the local clone and `travel-build-v0` branch creation are also evidenced by the reflog on that date

OwnTracks and OwnTracks Recorder are donor/reference/compatibility infrastructure. They are not LateralLine's permanent identity or authoritative backend design.
