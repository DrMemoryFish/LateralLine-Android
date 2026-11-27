# Terminology

## Evidence Terms
- **OBSERVED**: directly confirmed from source, test, runtime, command output, configuration, or reviewed documentation.
- **INFERRED**: reasoned from evidence but not directly verified.
- **PROPOSED**: possible future design; neither decided nor implemented.
- **DECIDED**: explicit project decision; implementation status remains separate.
- **UNKNOWN**: requires evidence or an owner decision.

## Governance Terms
- **owner document**: durable file assigned a concern by `docs/AUTHORITY_MATRIX.md`.
- **active plan**: living substantial-work document under `plans/ACTIVE/` with same-stem checkpoint sidecar.
- **artifact**: generated evidence under `artifacts/`; non-authoritative by default.
- **checkpoint**: coherent, reviewable, resumable set of changes/evidence.
- **readiness gate**: bounded prerequisite/characterization work before a roadmap milestone; does not automatically become its own permanent architecture.

## Product / Identity Terms
- **LateralLine**: independent long-term location platform.
- **OwnTracks donor**: upstream Android implementation and accumulated operational knowledge used as transitional implementation/reference.
- **OwnTracks protocol**: interoperability/compatibility format; not necessarily LateralLine's permanent internal record model.
- **Recorder**: OwnTracks Recorder; transitional compatibility/reference/history infrastructure, not permanent LateralLine backend authority.
- **person**: human/data subject.
- **account**: human authentication/authorization identity.
- **device**: physical/logical producer with an independent eventual credential.
- **recording lineage / stream epoch**: conceptual ordering boundary for a producer lifetime/restore branch; exact name/representation remains open.
- **trusted circle**: explicitly invited group used for granular sharing authorization; a circle does not own member records.
- **ownership**: whose logical data/record it is.
- **custody**: which device/server/backup currently stores a copy; custody does not change ownership.

## History / Data Terms
- **original observation**: accepted device measurement plus available uncertainty/context at capture time; evidence of what was observed, not a perfect truth claim.
- **canonical record**: stable logical record for an accepted original observation and provenance. Derived corrections/inferences do not overwrite it.
- **derived data**: interpretation/transformation produced from original records, such as filtered/map-matched positions, activities, journeys, simplified geometry, summaries, or user annotations.
- **durable local history**: canonical local records whose successful capture boundary is the committed local-history durability contract, independent of server/transport success.
- **durable capture**: successful canonical local commit under defined durability guarantees. Launching an async write or sending to a server is not durable capture.
- **provenance**: origin/capture/import/processing lineage sufficient to distinguish native/imported/manual/derived records and processing history.
- **transport queue / outbox state**: delivery/retry state; not the permanent master-history model.
- **live channel/lane**: high-priority current/realtime scheduling semantics.
- **historical backfill**: lower-priority resumable transfer of older durable records.
- **record identity**: stable logical identity independent of capture timestamp and transport attempt.

## Synchronization Terms
- **at-least-once transport**: sender may resend after ambiguous failure; correctness depends on idempotent logical ingestion.
- **idempotent ingest**: repeated delivery of the same stable record identity does not create multiple logical records.
- **durable acknowledgement**: application-level acknowledgement/checkpoint produced only after the server's defined durable commit boundary.
- **synced**: server has durably accepted the record under the sync contract; does not automatically mean local copy may be removed.
- **offload-safe**: user's durability policy permits the full local copy to be removed because sufficient trusted copies/archive state exist.

## Storage / Fidelity Terms
- **lossless local offloading / redundant-copy eviction**: remove a full local copy while preserving a trusted full original elsewhere; never means global deletion.
- **local destructive reduction**: user-authorized lossy removal of local canonical originals to recover device storage; does not propagate deletion to server.
- **global deletion**: explicit authorized removal of logical history from the ecosystem; separate domain action.
- **fidelity state**: metadata describing whether full originals are locally present, locally reduced, remotely full/offloaded, damaged/unknown, or another future justified state; final enum remains open.
- **lossless compaction/encoding**: representation/storage optimization from which all canonical information is recoverable.
- **adaptive recording**: explicit future policy that changes what observations are captured prospectively; different from deleting already-captured originals.
- **reduction proposal**: read-only analysis artifact describing an optional local destructive reduction before any canonical mutation.
- **removal-safety / information-preservation score**: ranking/eligibility measure for a proposed destructive reduction. Do not call it probabilistic confidence unless statistically calibrated to a defined event.
- **rehydration**: restore full local detail from a trusted retained full-fidelity copy after local offloading/reduction where possible.
- **operational reserve**: device storage intentionally left available for OS/database/WAL/compaction/recovery/analysis operations rather than counted as recordable headroom.

## Diagnostics / Trust Terms
- **secret**: authentication/key material such as password, private key, auth/session/refresh token, API key, recovery secret, authorization header, device credential. Generally excluded from diagnostics entirely.
- **private diagnostic**: rich owner-visible troubleshooting data that may include precise location/address/network/payload context.
- **tool-safe diagnostic**: minimized/sanitized state intended for external development tooling: counts, booleans, timings, error classes, pseudonymous IDs, component/status states.
- **tooling trust boundary**: technical capability boundary controlling external agent access; instructions/redaction alone are insufficient when the agent has unrestricted raw device access.

## Deployment Terms
- **deployment identity**: stable logical identity/trust of a LateralLine server deployment independent of current host/IP/domain/proxy; distinct from any one eternal private key.
- **portable/off-grid deployment**: future self-hosted server operating on constrained/local hardware with intermittent/no internet and possible abrupt power loss.

## Upstream Classification
- **ADOPT**: take upstream implementation.
- **ADAPT**: bring concept into LateralLine architecture.
- **SUPERSEDED**: LateralLine already solves it more appropriately.
- **IRRELEVANT**: no longer applies.
- **REVIEW**: requires owner/human architectural judgment.

- **Travel Build v0**: completed bounded travel-safety donor milestone; not a lossless history architecture or production release.
