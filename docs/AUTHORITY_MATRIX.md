# Authority Matrix

## Topic Owners
| Concern | Owner | Secondary surfaces |
|---|---|---|
| Mission, product scope/direction, security/privacy invariants, current reality | `docs/PROJECT_BRIEF.md` | `README.md`, decisions |
| Durable cross-system/data architecture, canonical-history, sync/storage/deployment/tooling boundaries | `docs/PLATFORM_ARCHITECTURE.md` | decisions, questions, risks, roadmap |
| Conflict order and claim authority | `docs/TRUTH_HIERARCHY.md` | `AGENTS.md` |
| File placement and repository map | `docs/REPO_MAP.md` | `docs/ANTI_SPRAWL_RULES.md` |
| Workflow stage order | `docs/WORKFLOW.md` | `docs/TASK_PROTOCOL.md` |
| Task execution, role boundary, handoff | `docs/TASK_PROTOCOL.md` | `AGENTS.md`, `PLANS.md`, active plans |
| Plan format/checkpoint sidecars | `PLANS.md` | templates under `plans/` |
| Quality/done definition | `docs/QUALITY_BAR.md` | active plan, tests/evidence |
| Evidence/provenance/confidence terminology | `docs/EVIDENCE_AND_CONFIDENCE.md` | all registers |
| Durable structural/convention changes | `docs/CHANGE_CONTROL.md` | decision log |
| Anti-sprawl/lifecycle | `docs/ANTI_SPRAWL_RULES.md` | repo map |
| Terminology | `docs/TERMINOLOGY.md` | project brief/platform architecture |
| Roadmap sequencing/readiness | `docs/ROADMAP.md` | platform architecture, active plan, progress log |
| Decisions | `docs/DECISION_LOG.md` | latest applicable decision |
| Failures/regressions | `docs/FAILURE_LOG.md` | direct evidence/tests |
| Progress continuity | `docs/PROGRESS_LOG.md` | active plan/sidecar |
| Open consequential questions | `docs/OPEN_QUESTIONS.md` | owner docs/active plan |
| Risks | `docs/RISK_REGISTER.md` | failures/questions |
| External/upstream sources | `docs/REFERENCES.md` | local donor source/history |
| Generated artifacts | `docs/REPO_MAP.md` | `artifacts/README.md` |
| Donor technical data-flow/preferences/CI reference | donor source + retained donor docs | `docs/REPO_MAP.md`; never overrides LateralLine owner docs |

Plans own execution detail, not durable policy. Logs preserve history. Generated artifacts and chat summaries never outrank owner documents or explicit decisions.
