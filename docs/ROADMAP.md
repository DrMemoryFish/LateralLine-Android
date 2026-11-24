# Roadmap

Last reviewed: 2026-11-24  
Roadmap status: `active high-level sequencing; post-travel order remains revisable`

## Snapshot
- Current live frontier: governance foundation followed by Travel Build v0.
- Current active plan: `plans/ACTIVE/2026-11-24_travel-build-v0.md`.
- Current blockers: none known for beginning implementation; physical-device validation requires the Samsung Galaxy S24.
- Next recommended milestone: execute Travel Build v0 without expanding into long-term redesign.

## Major Completed Milestones
- [x] OwnTracks donor repository established at the recorded inception commit.
- [x] Durable governance/documentation foundation instantiated and Travel Build v0 planned (pending commit at this task closeout).

## Ordered Milestones
1. **Travel-safe donor build** — protect queued travel history and separate installation identity while preserving donor behavior.
2. **Durable local history architecture** — separate master records from transport state.
3. **High-throughput resumable synchronization** — prioritize live data; batch/checkpoint historical backfill.
4. **Authoritative backend and database** — API, PostgreSQL/PostGIS, ingestion, queries, audit, and retention.
5. **Identity, passkeys, device credentials, recovery, and authorization**.
6. **Modern LateralLine mobile shell and intentional product UX**.
7. **Device management and history exploration**.
8. **Trusted circles and granular sharing**.
9. **Security hardening and publication-quality operations**.
10. **Recorder compatibility/migration and OwnTracks dependency reduction**.
11. **Advanced sensor recording and 3D trajectory research**.
12. **Sensor fusion/higher-resolution inference and independent-platform maturity**.

## Sequencing Caveat
Milestone 1 is active. Later ordering is `PROPOSED` and should change when evidence establishes better dependencies. The roadmap does not settle backend framework, schema, batch encoding, mobile framework, iOS strategy, deployment targets, retention, or sensor design.

