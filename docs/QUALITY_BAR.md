# Quality Bar

## General Standard
Done means evidence-backed, correctly routed, scoped, reviewable, and resumable without reconstructing chat history.

## Governance And Documentation
- One authoritative owner exists per concern; cross-references do not become duplicate policy.
- OBSERVED, INFERRED, PROPOSED, DECIDED, and UNKNOWN claims remain distinguishable.
- Current implementation and future architecture are never conflated.
- Agreed requirements include their normal/failure behavior, prohibited outcomes, ownership/boundaries, and relevant validation expectations where consequential.
- Proposals/alternatives remain proposals; unresolved consequential choices stay in `docs/OPEN_QUESTIONS.md`.
- Superseded wording is reconciled across dependent owner docs rather than leaving contradictory instructions.
- Distant milestones preserve requirements/dependencies/open questions without pretending implementation detail is settled.
- Cross-links resolve, templates remain reusable, append-only registers retain chronology/IDs.

## Data Integrity
- A future canonical location record is not called durably captured until the local-history durability contract succeeds.
- Original observations are not silently replaced by derived/inferred/map-matched/simplified data.
- Transport configuration, endpoint/protocol changes, queue clearing, network/server outages, and sync retry must not be able to destroy permanent canonical history once that architecture exists.
- Local offload, local destructive reduction, and global deletion remain distinct operations; local cleanup must never imply remote/global deletion.
- Corruption/migration failures preserve evidence/quarantine where the active architecture requires it; silent deletion of the only canonical/source copy is not acceptable.
- Destructive storage reduction requires explicit policy/authorization and fidelity/provenance reporting as defined by the platform architecture.

## Implementation Work
- Scope matches the active plan and preserves explicitly protected donor behavior/architecture invariants.
- Relevant tests/builds pass, or failures/deferred checks are recorded precisely.
- Android persistence/lifecycle changes receive focused regression coverage and appropriate physical-device validation.
- Scale/performance claims use representative measurements; configured capacity is not proof of operational capacity.
- Security/privacy changes receive threat-aware review and backend authorization validation where applicable—not UI-only checks.
- Secrets do not enter ordinary diagnostics merely for convenience. Tooling access to private personal data follows the explicit trust boundary.
- Generated outputs remain non-authoritative until reconciled.

## Completion Evidence By Risk
Use the strongest practical evidence proportionate to the claim: unit/integration/instrumentation, failure injection, synthetic scale, real-device/OEM, migration reconciliation, restore/power-loss drills, authorization tests, and direct Git/runtime inspection as applicable. A happy-path HTTP response does not prove durable archive/offload safety unless the protocol contract says it does.

## Not Done If
- completion relies on guessed facts;
- a proposal is described as implemented/decided;
- source behavior changes without tests/review proportionate to risk;
- data-loss/error paths are silently ignored;
- verification is skipped without reason;
- plan/sidecar or durable docs are stale;
- future work requires reconstructing intent from conversation history;
- a local implementation choice silently resolves an owner-level architecture/product question.
