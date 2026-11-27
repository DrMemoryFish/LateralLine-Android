# Governance Navigation

## Start Sequence
1. `AGENTS.md`
2. `docs/TRUTH_HIERARCHY.md`
3. `docs/AUTHORITY_MATRIX.md`
4. the minimum task-specific documents below
5. the active plan and checkpoint sidecar, when applicable

`docs/ROADMAP.md` sequences work; it does not override evidence, explicit owner requirements, or topic owners. Do not load the entire documentation set for ordinary tasks.

## Minimum Reading By Task
| Task | Read first | Then read |
|---|---|---|
| Product mission/scope/current reality | `docs/PROJECT_BRIEF.md` | relevant decisions, `docs/ROADMAP.md` |
| Cross-system/data architecture | `docs/PLATFORM_ARCHITECTURE.md` | relevant decisions/questions/risks, roadmap milestone |
| Durable local history | `docs/PLATFORM_ARCHITECTURE.md` | `docs/ROADMAP.md` Milestone 2, relevant OQ/RISK/FAIL entries, active plan |
| Synchronization/backend/identity | relevant roadmap milestone + `docs/PLATFORM_ARCHITECTURE.md` | relevant OQ/decisions/security risks; co-design contracts before deep implementation |
| Storage lifecycle/fidelity/forecasting | `docs/PLATFORM_ARCHITECTURE.md` §4–5 | roadmap Milestone 11, relevant OQ/risks |
| Migration/OwnTracks compatibility | `docs/PLATFORM_ARCHITECTURE.md` §6 | OQ-0010, roadmap Milestone 10, donor/Recorder evidence |
| Deployment/offline portability | `docs/PROJECT_BRIEF.md`, `docs/PLATFORM_ARCHITECTURE.md` §7 | OQ deployment/identity questions, relevant risks |
| Security/privacy/diagnostic tooling | `docs/PROJECT_BRIEF.md`, `docs/PLATFORM_ARCHITECTURE.md` §8 | decisions/risks/failures/evidence |
| Donor Android change | active plan | `docs/REPO_MAP.md`, `docs/QUALITY_BAR.md`, relevant donor source/tests/history |
| Upstream review | `docs/REFERENCES.md` | decisions, source/tests/history |
| New owner document or durable structure | `docs/CHANGE_CONTROL.md` | `docs/REPO_MAP.md`, `docs/ANTI_SPRAWL_RULES.md`, authority/navigation updates |
| Substantial execution/handoff | `docs/TASK_PROTOCOL.md` | `PLANS.md`, active plan/sidecar |
| Completion/review | `docs/QUALITY_BAR.md` | task plan, direct evidence, affected owner docs/registers |
| Resume after interruption | active plan/sidecar | newest relevant progress/decision/failure/risk/question entries |

## Donor Documentation Boundary
`docs/DATA_FLOW_DESIGN.md`, `docs/PREFERENCES.md`, `CI.md`, and other retained OwnTracks documents are reference evidence unless explicitly promoted by an owner document. They may be stale relative to current Travel code and do not define LateralLine architecture.

## Durable Docs Sync Gate
Before final reporting on material work, reconcile every owner document/register whose truth changed. If a relevant durable surface remains unchanged, state why. Do not leave a consequential decision only in chat, scratch output, implementation comments, or a generated report.
