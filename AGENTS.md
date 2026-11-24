# LateralLine Agent Guide

## Start Here
For non-trivial work, read in order:
1. `docs/GOVERNANCE_NAVIGATION.md`
2. `docs/TRUTH_HIERARCHY.md`
3. `docs/AUTHORITY_MATRIX.md`
4. task-specific owner documents named by the navigation file
5. the active plan and checkpoint sidecar under `plans/ACTIVE/`

## Project Role
LateralLine is a self-hostable, offline-first, privacy-focused location platform for people and explicitly trusted circles. The repository began as an OwnTracks Android fork. OwnTracks is valuable donor/reference knowledge and an interoperability source, but it is not LateralLine's permanent identity or architecture.

## First Rules
- Inspect source, tests, Git state, and relevant history before changing donor-derived behavior.
- Distinguish `OBSERVED`, `INFERRED`, `PROPOSED`, `DECIDED`, and `UNKNOWN` claims.
- Do not represent an architectural direction as implemented fact.
- Preserve Android acquisition behavior, tracking modes, persistence, transport compatibility, and lifecycle knowledge unless an active plan explicitly and safely changes them.
- Security and privacy are architecture constraints, not optional polish.
- Update an existing owner document rather than creating duplicate authority.
- Record durable decisions, failures, risks, questions, and continuity in their named registers.
- Use one active plan plus a same-stem checkpoint manifest for substantial work.
- Do not add top-level files or directories unless `docs/REPO_MAP.md` permits them or change control approves them.

## Current Frontier
The first implementation frontier is **Travel Build v0**, governed by `plans/ACTIVE/2026-11-24_travel-build-v0.md`. It is a transitional donor safety milestone, not authorization to redesign the client or implement the long-term backend, identity, synchronization, circles, UX, or sensor architecture.

## Routing
- durable guidance and registers -> `docs/`
- active substantial work -> `plans/ACTIVE/`
- reusable schemas -> `plans/`, `logs/`, `evals/`, `schemas/`
- generated evidence -> `artifacts/`
- retired/superseded support surfaces -> `archive/`
- stable repeated workflows -> `skills/`

