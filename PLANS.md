# Execution Plans

## Purpose
An execution plan is a living handoff document, not a one-time preflight note. It must let a future session resume without reconstructing intent from chat history.

## Contract
Substantial work uses:
- one Markdown plan under `plans/ACTIVE/`
- one same-stem `.checkpoint.json` sidecar

Use `plans/TEMPLATE_EXEC_PLAN.md` and `plans/TEMPLATE_CHECKPOINT_MANIFEST.json`. Do not introduce parallel plan formats without change control.

Every active plan keeps objective, scope, truth inputs, assumptions, constraints, checkpoints, expected outputs, validation, stop conditions, progress, discoveries, decisions, and outcomes current. The sidecar is the source of truth for intended checkpoint scope and expected/unexpected dirty state; it does not itself authorize committing or pushing.

## LateralLine Planning Principle
Large coherent missions are welcome. Divide them at architectural verification boundaries rather than arbitrary code-size boundaries:

orient -> inspect -> plan -> implement -> build/test -> inspect -> checkpoint

Large scope never authorizes opportunistic unrelated refactors.

