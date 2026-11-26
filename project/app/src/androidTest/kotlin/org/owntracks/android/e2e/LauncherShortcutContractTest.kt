package org.owntracks.android.e2e

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ShortcutManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LauncherShortcutContractTest {
  @Test
  fun installedShortcutsResolveThroughExportedAliasesToPrivateActivities() {
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    val packageName = context.packageName
    assertEquals("org.lateralline.android.travel.debug", packageName)

    val expectedTargets =
        mapOf(
            "logs" to
                Pair(
                    "$SOURCE_PACKAGE.ui.shortcuts.LogViewerShortcutActivity",
                    "$SOURCE_PACKAGE.ui.status.logs.LogViewerActivity",
                ),
            "preferences" to
                Pair(
                    "$SOURCE_PACKAGE.ui.shortcuts.PreferencesShortcutActivity",
                    "$SOURCE_PACKAGE.ui.preferences.PreferencesActivity",
                ),
        )

    val shortcuts =
        context.getSystemService(ShortcutManager::class.java).manifestShortcuts.associateBy {
          it.id
        }
    assertEquals(expectedTargets.keys, shortcuts.keys)

    expectedTargets.forEach { (shortcutId, target) ->
      val shortcut = shortcuts.getValue(shortcutId)
      val shortcutIntent = shortcut.intents.orEmpty().single()
      assertEquals(Intent.ACTION_VIEW, shortcutIntent.action)
      assertNotNull(shortcutIntent.component)
      assertEquals(packageName, shortcutIntent.component!!.packageName)
      assertEquals(target.first, shortcutIntent.component!!.className)

      assertAliasContract(context, packageName, target.first, target.second)
    }
  }

  @Suppress("DEPRECATION")
  private fun assertAliasContract(
      context: Context,
      packageName: String,
      aliasName: String,
      targetActivityName: String,
  ) {
    val packageManager = context.packageManager
    val aliasInfo =
        packageManager.getActivityInfo(
            ComponentName(packageName, aliasName),
            PackageManager.MATCH_DISABLED_COMPONENTS,
        )
    assertTrue(aliasInfo.enabled)
    assertTrue(aliasInfo.exported)
    assertEquals(targetActivityName, aliasInfo.targetActivity)

    val targetInfo =
        packageManager.getActivityInfo(
            ComponentName(packageName, targetActivityName),
            PackageManager.MATCH_DISABLED_COMPONENTS,
        )
    assertFalse(targetInfo.exported)
  }

  private companion object {
    const val SOURCE_PACKAGE = "org.owntracks.android"
  }
}
