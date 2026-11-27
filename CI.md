# CI pipelines / flow — inherited OwnTracks reference

> **LateralLine authority warning:** this file is retained donor CI documentation. It describes OwnTracks Android workflows and is not proof that the current LateralLine branch is protected by equivalent required checks. During the 2026-11-27 post-Travel audit, the reviewed LateralLine commits had no attached GitHub commit-status checks. Future publication-quality CI/release policy belongs to LateralLine planning/governance, not this donor reference.

OwnTracks Android uses Github Actions to build, test and release.

## jobs

### Build, test & lint

Assembles the debug and release variants of both GMS and OSS flavours. Also compiles the test sources, then runs the unit tests (with coverage) and the Android lint.

### F-Droid scan

Runs the F-Droid server scanner against the OSS Release APK to make sure that no non-free components enter the release.

### UI tests

Runs the espresso tests. Can be parameterized over flavour, parallelism and Android API level. Uploads coverage to CodeCov in the donor project.

### Publish to Play Store

Uploads the release bundle of the GMS flavour to the Google Play Store internal testing track in the donor project.

## Workflows

The retained donor workflows include build/test/lint, release, and issue/PR maintenance automation. Inspect `.github/workflows/` and current repository runtime evidence before assuming any donor workflow is active, correctly credentialed, or enforced for LateralLine.
