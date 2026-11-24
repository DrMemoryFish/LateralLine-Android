# Change Control

## Changes Requiring Explicit Control
- truth-hierarchy or authority-matrix changes
- workflow/quality gate changes
- file-routing or new top-level directory changes
- new/replaced/retired owner documents
- schemas/contracts affecting consumers
- embedded template or register-ordering convention changes
- promotion of optional support machinery into required workflow

## Required Steps
1. Identify the current owner surface and need.
2. State impacted files, workflows, consumers, and migration needs.
3. Update dependent documents in the same checkpoint or record explicit debt.
4. Name the successor when replacing a durable surface.
5. Log durable behavior changes in `docs/DECISION_LOG.md`.
6. Preserve unresolved uncertainty in `docs/OPEN_QUESTIONS.md`.

New backend, mobile, infrastructure, or schema source areas require an approved plan and same-checkpoint `docs/REPO_MAP.md` update. Do not create speculative implementation scaffolding during governance-only work.

