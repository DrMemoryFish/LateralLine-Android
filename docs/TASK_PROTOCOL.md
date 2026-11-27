# Task Protocol

## When A Plan Is Mandatory
Use an active plan and same-stem checkpoint manifest when work spans multiple workflow stages, touches multiple concerns, changes durable behavior/architecture, creates generated evidence, revises a reusable convention, or may continue across sessions.

## Required Framing
Before substantial edits, state:
- objective and why it is the right bounded move;
- in-scope/out-of-scope areas;
- governing owner documents and primary evidence;
- branch/worktree/collision boundary;
- assumptions/constraints;
- expected durable/generated outputs;
- validation approach and deferred checks;
- registers likely to change.

## Context-Efficiency Rule
Use `docs/GOVERNANCE_NAVIGATION.md` to read the minimum authoritative context needed for the assignment. Do not spend a large context window reconstructing project intent from chat or reading every durable file when a topic owner exists. If task-local evidence contradicts an owner document, surface the conflict rather than silently choosing one.

## Owner / ChatGPT / Codex Responsibility
- Owner + ChatGPT are the primary forum for exploratory product/architecture reasoning, technology comparison, requirement negotiation, and consequential direction decisions.
- Repository owner documents are the authoritative actionable result; chat history is evidence/context, not the durable contract.
- Codex primarily inspects repository truth, executes bounded implementation/documentation assignments, tests/debugs/verifies, and reports evidence/deviations/issues.
- Codex may challenge a technically invalid premise or propose a superior alternative when evidence warrants it. Consequential product/architecture/technology changes are surfaced for owner review rather than silently introduced.
- Ordinary implementation decisions that clearly fit an approved contract remain Codex's responsibility; governance must not force escalation of routine coding choices.
- Codex never expands from one roadmap milestone into another merely because a dependency is visible. Co-design of contracts may cross milestone boundaries; implementation authorization does not.

## Collision Rule
Inspect current Git status and nearby changes. Preserve unattributed user work. Never reset, overwrite, clean, or absorb changes merely because they are outside the current task.

## Donor-Change Protocol
For OwnTracks-derived Android changes:
1. inspect relevant implementation/tests;
2. inspect history/release/upstream evidence where useful;
3. state donor behavior that must remain unchanged;
4. enumerate intentional behavior changes;
5. add focused regression tests;
6. build relevant flavors/variants;
7. use physical-device validation where lifecycle, permissions, persistence, OEM behavior, power, or real acquisition is material;
8. distinguish compatibility debt from future LateralLine architecture rather than solving both accidentally.

## Data-Integrity Protocol
For canonical-history, migration, sync, storage-lifecycle, or deletion work:
- name the authoritative copy/record identity and every mutation boundary;
- define normal and failure semantics before implementation;
- distinguish original observations from derived data;
- distinguish local presence from logical existence, sync state, offload safety, and global deletion;
- use preservation-safe migration/corruption behavior;
- prove destructive actions cannot propagate beyond their intended scope;
- use failure injection/restart/low-storage/scale evidence proportionate to risk;
- never label a transport response as durable archive/offload safety without the relevant application contract.

## Privacy / Tooling Protocol
- Secrets are never treated as ordinary diagnostics.
- Rich private diagnostics can remain available to the owner, but external tooling should consume tool-safe minimized interfaces by default.
- Synthetic/isolated datasets are preferred for scale/failure engineering.
- Broad raw access to a personal device (ADB/run-as/logcat/UI/config files) is exceptional and must be narrowly owner-authorized; procedural filtering is not a permanent technical trust boundary.
- Never print broad raw device/system output to a transcript when a bounded parser/diagnostic surface can answer the question.

## Checkpoint Manifest
Maintain: `phase_name`, `phase_status`, `commit_ready`, `intended_commit_scope`, `introduced_files`, `modified_files`, `expected_artifacts`, `expected_durable_reports`, `expected_noncommitted_generated_outputs`, `remaining_dirty_expected`, `remaining_dirty_unexpected`, `push_status`, `final_commit_hash`.

Allowed path buckets: `commit_now`, `park_for_later`, `generated_leave_untracked`, `unexpected_inspect_first`.

## Owner-Authorized Commit And Push
When the owner instructs Codex to commit a completed/reviewed repository unit, Codex owns the governed operation unless explicitly narrowed:
1. inspect status, staged/unstaged diffs, intended scope, branch/remotes;
2. reconcile active plan/checkpoint/affected durable records;
3. confirm required validation/stop conditions;
4. stage only intended files and verify staged diff;
5. create accurate commit;
6. obtain actual hash/author/committer/timestamps from Git;
7. reconcile commit/push bookkeeping from evidence;
8. push only when authorized;
9. refresh remote-tracking ref and verify local/remote equality/final dirty state;
10. report created commits and intentional uncommitted work.

Commit authorization does not imply push authorization unless owner authorizes both. Do not hand shell commands to the owner as a substitute for an authorized operation Codex can safely perform.

## Non-Recursive Commit Bookkeeping
A commit cannot contain its own final hash. Before it exists, hash fields remain null/pending. A single later bookkeeping commit may record the preceding substantive commit/push without spawning an infinite chain. Verify the follow-up's own hash/push directly from Git and report it or record it at the next natural checkpoint.

## Stop Conditions
Stop/request direction when the next action is irreversible/destructive, externally consequential, security-sensitive beyond authorization, blocked by unresolved authority conflict, or requires an owner-reserved product decision. Missing internal evidence/planning surfaces are normally self-unblocking work. A consequential unresolved choice belongs in `docs/OPEN_QUESTIONS.md`, not an invented implementation answer.

## Handoff
Update plan/sidecar, reconcile affected owner docs/registers, separate direct/deferred validation, report expected/unexpected dirty state, and name the exact next step. A handoff is not complete if the next session must reconstruct the task's architectural intent from chat.
