# Truth Hierarchy

## Authority Order
1. Explicit current owner requirements for LateralLine.
2. Primary evidence: source, tests, runtime output, Git history, configuration, and authoritative external material.
3. Source-specific analysis produced from direct evidence.
4. This file.
5. `docs/AUTHORITY_MATRIX.md` and the named topic owner.
6. `docs/TERMINOLOGY.md` when interpretation depends on wording.
7. Active plans for task-local execution details.
8. Append-only registers when they record a newer explicit decision, failure, blocker, risk, question, or progress fact.
9. Generated artifacts, implementation comments, scratch notes, remembered chat context, and unreviewed examples.

## Conflict Rules
- Observed evidence outranks inference.
- A decided direction is not proof of implementation.
- A proposal must not be reported as settled architecture.
- Donor implementation is strong evidence about existing Android behavior, not automatic authority over LateralLine product direction.
- If owner guidance conflicts with implementation, surface the discrepancy and follow controlled change rather than silently redefining the project.
- If a conflict cannot be resolved, stop only dependent work, identify the owner surface, gather the smallest resolving evidence, and log a material unresolved conflict.

## Not Authority By Itself
Chat summaries, generated reports, examples, implementation comments, current code, and upstream behavior are useful evidence but do not independently redefine project intent.

