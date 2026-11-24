# Governance Navigation

## Start Sequence
1. `AGENTS.md`
2. `docs/TRUTH_HIERARCHY.md`
3. `docs/AUTHORITY_MATRIX.md`
4. the minimum task-specific documents below
5. the active plan and checkpoint sidecar, when applicable

`docs/ROADMAP.md` sequences work; it does not override evidence or owner documents.

## Minimum Reading By Task
| Task | Read first | Then read |
|---|---|---|
| Product scope or architecture | `docs/PROJECT_BRIEF.md` | `docs/DECISION_LOG.md`, `docs/OPEN_QUESTIONS.md`, `docs/ROADMAP.md` |
| Donor Android change | active plan | `docs/REPO_MAP.md`, `docs/QUALITY_BAR.md`, relevant donor source/tests/history |
| Security or privacy | `docs/PROJECT_BRIEF.md` | `docs/DECISION_LOG.md`, `docs/RISK_REGISTER.md`, `docs/EVIDENCE_AND_CONFIDENCE.md` |
| Upstream review | `docs/REFERENCES.md` | `docs/DECISION_LOG.md`, source/tests/history |
| New durable file or directory | `docs/REPO_MAP.md` | `docs/ANTI_SPRAWL_RULES.md`, `docs/CHANGE_CONTROL.md` |
| Substantial execution or handoff | `docs/TASK_PROTOCOL.md` | `PLANS.md`, active plan/sidecar |
| Completion/review | `docs/QUALITY_BAR.md` | task plan, tests, direct evidence |
| Resume after interruption | active plan/sidecar | newest relevant progress, decision, failure, risk, and question entries |

## Durable Docs Sync Gate
Before final reporting on material work, reconcile the owner documents and registers whose truth changed. If a relevant durable surface remains unchanged, say why.

