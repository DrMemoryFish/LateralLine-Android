# Terminology

## Evidence Terms
- **OBSERVED**: directly confirmed from source, test, runtime, command output, configuration, or reviewed documentation.
- **INFERRED**: reasoned from evidence but not directly verified.
- **PROPOSED**: a possible future design; neither decided nor implemented.
- **DECIDED**: an explicit project decision; implementation status is separate.
- **UNKNOWN**: requires evidence or an owner decision.

## Governance Terms
- **owner document**: the durable file assigned a concern by `docs/AUTHORITY_MATRIX.md`.
- **active plan**: a living substantial-work document under `plans/ACTIVE/` with a same-stem checkpoint sidecar.
- **artifact**: generated evidence under `artifacts/`; non-authoritative by default.
- **checkpoint**: a coherent, reviewable, resumable set of changes and evidence.

## Product Terms
- **LateralLine**: the independent long-term location platform.
- **OwnTracks donor**: the upstream Android implementation and accumulated operational knowledge used as transitional implementation and reference.
- **OwnTracks protocol**: a useful interoperability/compatibility format, not necessarily LateralLine's permanent internal model.
- **Recorder**: OwnTracks Recorder; temporary compatibility/reference/history infrastructure, not the intended authoritative backend.
- **person / account / device / location record**: distinct identity and data concepts defined in `docs/PROJECT_BRIEF.md`.
- **trusted circle**: an explicitly invited group with granular sharing permissions.
- **durable local history**: locally persisted location records intended to survive server/network outages.
- **transport queue state**: delivery/retry state; not the permanent master-history model.
- **live channel**: high-priority current/realtime delivery.
- **historical backfill**: lower-priority resumable transfer of older durable records.
- **Travel Build v0**: the bounded travel-safety donor milestone, not a platform redesign.

## Upstream Classification
- **ADOPT**: take the upstream implementation.
- **ADAPT**: bring the concept into LateralLine architecture.
- **SUPERSEDED**: LateralLine already solves it more appropriately.
- **IRRELEVANT**: no longer applies.
- **REVIEW**: requires human architectural judgment.

