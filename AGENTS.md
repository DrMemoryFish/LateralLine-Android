# LateralLine Agent Guide

## Start Here
For non-trivial work, read in order:
1. `docs/GOVERNANCE_NAVIGATION.md`
2. `docs/TRUTH_HIERARCHY.md`
3. `docs/AUTHORITY_MATRIX.md`
4. only the task-specific owner documents named by the navigation file
5. the active plan and checkpoint sidecar under `plans/ACTIVE/`, when the assignment has one

Do not read every durable document by default. Navigation and owner documents exist to keep context bounded.

## Project Role
LateralLine is an open-source, self-hostable, offline-first, privacy-focused location platform for people and explicitly trusted circles. The repository began as an OwnTracks Android fork. OwnTracks is valuable donor/reference knowledge and an interoperability source, but it is not LateralLine's permanent identity, data model, transport choice, or backend architecture.

## First Rules
- Inspect source, tests, Git state, and relevant history before changing donor-derived behavior.
- Distinguish `OBSERVED`, `INFERRED`, `PROPOSED`, `DECIDED`, and `UNKNOWN` claims.
- Do not represent an architectural direction as implemented fact.
- Preserve Android acquisition/lifecycle knowledge unless an active plan explicitly and safely changes it.
- Canonical local history and transport state are distinct; never silently convert a queue/outbox into the master archive.
- Original observations and derived interpretations are distinct; never silently rewrite originals with inference/map matching/filtering.
- Security/privacy/data-integrity boundaries are architecture constraints, not polish.
- Secrets are not ordinary diagnostics. Rich owner-private diagnostics are allowed; external tooling should consume tool-safe diagnostics through constrained interfaces where practical.
- Update an existing owner document rather than creating duplicate authority.
- Record durable decisions, failures, risks, questions, and continuity in their named registers.
- Use one active plan plus a same-stem checkpoint manifest for substantial work.
- Do not add top-level files/directories unless `docs/REPO_MAP.md` permits them or change control approves them.

## Owner / ChatGPT / Codex Division
- Owner + ChatGPT discussion is the place to explore alternatives, challenge assumptions, research technologies, and resolve consequential product/architecture questions.
- Repository owner documents are the authoritative structured result of that reasoning. Chat history itself is not a substitute for repository authority.
- Codex primarily inspects the repository, implements bounded assignments, tests/debugs/verifies them, and reports evidence/deviations/new issues against the established contracts.
- Codex should identify contradictions or technically invalid assumptions and may propose better alternatives, but it must surface consequential direction changes for owner review rather than silently introducing them.
- Ordinary implementation-level choices inside an approved contract remain Codex's responsibility; do not escalate every local coding choice.

## Current Frontier
Travel Build v0 reached its bounded acceptance boundary on 2026-11-27. Post-completion source audit identified current transport/retry/persistence/platform/privacy/release debt documented in the registers and `docs/PLATFORM_ARCHITECTURE.md`. No later implementation milestone is active.

The default next architecture frontier is Durable Local History v1, preceded/paired with a bounded current-runtime hardening/characterization gate where explicitly planned. Neither is authorized merely by reading the roadmap.

## Routing
- durable mission/product direction -> `docs/PROJECT_BRIEF.md`
- durable cross-system/data architecture -> `docs/PLATFORM_ARCHITECTURE.md`
- sequencing/readiness -> `docs/ROADMAP.md`
- active substantial work -> `plans/ACTIVE/`
- decisions/risks/failures/questions/progress -> their named registers under `docs/`
- donor implementation reference -> donor source/tests/history and retained donor docs such as `docs/DATA_FLOW_DESIGN.md`
- generated evidence -> `artifacts/`
- retired/superseded support surfaces -> `archive/`
