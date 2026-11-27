# Roadmap

Last reviewed: 2026-11-27
Roadmap status: `active high-level sequencing; post-travel order remains revisable`

## Snapshot
- Current live frontier: Travel Build v0 reached its bounded acceptance boundary; its final governed commit/bookkeeping is in progress.
- Current active plan: `plans/ACTIVE/2026-11-24_travel-build-v0.md`.
- Current blockers: none inside Travel Build v0; its named movement-mode limitation and security-sensitive configuration/control deferrals are recorded rather than overclaimed.
- Next recommended milestone: stop after the Travel Build v0 final commit and await explicit owner direction; do not begin permanent history or any later architecture from this roadmap alone.

## Major Completed Milestones
- [x] OwnTracks donor repository established at the recorded inception commit.
- [x] Durable governance/documentation foundation instantiated and Travel Build v0 planned; committed as `2913c6182980c3e86e5fbe9f217faf6cdc80028a` and pushed to `origin/travel-build-v0` on 2026-11-24 after the original task recorded it as pending.
- [x] Travel Build v0 implemented, host-verified, installed beside official OwnTracks, and physically accepted on Samsung Galaxy S24 with the plan's direct evidence and precise limitations recorded on 2026-11-27.

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
Milestone 1 has reached its bounded acceptance boundary. Later ordering is `PROPOSED` and requires explicit owner authorization plus a new active plan; this completion does not begin milestone 2. The roadmap does not settle backend framework, schema, batch encoding, mobile framework, iOS strategy, deployment targets, retention, or sensor design.
