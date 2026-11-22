# MUTLAQO HECH NIMA

**An Android app designed to do absolutely nothing. And it does it perfectly.**

An intentionally empty black screen, random Uzbek exit messages, and occasional opt-in notifications. The app is open-source and built with Kotlin + Jetpack Compose.

## Current prototype
- Full-screen black screen with no visible UI
- Back navigation opens a random exit dialog
- 100 Uzbek exit messages
- Notification permission prompt (Android 13+)
- Random Uzbek notifications scheduled periodically (WorkManager, about every 6 hours)
- GitHub Actions workflow builds a debug APK

## Build on GitHub Actions
1. Push this repository to GitHub (`main` or `master`).
2. Open **Actions** → **Android APK Build**.
3. Run the workflow, or push a commit to trigger it.
4. Download the APK from the workflow run's **Artifacts** section.

## Current limitations
- PRO animation and billing are not implemented yet.
- The first-50 PRO claim is not yet enforced globally; it needs a small server-side claim service to avoid duplicate claims.
- Notifications are periodic, not individually randomized in timing yet; Android may delay them to conserve battery.
- No data collection or account system is included.

## Tech stack
- Kotlin
- Jetpack Compose / Material 3
- WorkManager
- Android Gradle Plugin 8.7.3, Kotlin 2.0.21

## License
Apache-2.0 (add a license file before public release if you want this license to apply).
