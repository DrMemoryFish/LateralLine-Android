package org.owntracks.android

import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.w3c.dom.Element

class ShortcutContractTest {
  @Test
  fun staticShortcutsUseExportedAliasesWhileTargetActivitiesRemainPrivate() {
    val manifest = parseXml("src/main/AndroidManifest.xml")

    val expectedTargets =
        mapOf(
            "logs" to
                Pair(
                    ".ui.shortcuts.LogViewerShortcutActivity",
                    ".ui.status.logs.LogViewerActivity",
                ),
            "preferences" to
                Pair(
                    ".ui.shortcuts.PreferencesShortcutActivity",
                    ".ui.preferences.PreferencesActivity",
                ),
        )

    assertShortcutResource(
        "src/main/res/xml/shortcuts.xml",
        "org.lateralline.android.travel",
        expectedTargets,
    )
    assertShortcutResource(
        "src/debug/res/xml/shortcuts.xml",
        "org.lateralline.android.travel.debug",
        expectedTargets,
    )

    expectedTargets.values.forEach { (aliasName, targetActivityName) ->
      val alias = manifest.findComponent("activity-alias", aliasName)
      assertNotNull(alias)
      assertEquals("true", alias!!.androidAttribute("exported"))
      assertEquals(targetActivityName, alias.androidAttribute("targetActivity"))

      val targetActivity = manifest.findComponent("activity", targetActivityName)
      assertNotNull(targetActivity)
      assertEquals("false", targetActivity!!.androidAttribute("exported"))
    }
  }

  private fun assertShortcutResource(
      path: String,
      expectedPackage: String,
      expectedTargets: Map<String, Pair<String, String>>,
  ) {
    val shortcuts = parseXml(path)
    val shortcutElements = shortcuts.getElementsByTagName("shortcut")
    assertEquals(expectedTargets.size, shortcutElements.length)
    repeat(shortcutElements.length) { index ->
      val shortcut = shortcutElements.item(index) as Element
      val shortcutId = shortcut.androidAttribute("shortcutId")
      val intent = shortcut.getElementsByTagName("intent").item(0) as Element
      val expectedAlias = expectedTargets.getValue(shortcutId).first
      assertEquals("android.intent.action.VIEW", intent.androidAttribute("action"))
      assertEquals(expectedPackage, intent.androidAttribute("targetPackage"))
      assertEquals(
          "org.owntracks.android${expectedAlias}",
          intent.androidAttribute("targetClass"),
      )
    }
  }

  private fun parseXml(path: String) =
      DocumentBuilderFactory.newInstance()
          .apply { isNamespaceAware = true }
          .newDocumentBuilder()
          .parse(File(path))

  private fun org.w3c.dom.Document.findComponent(tagName: String, name: String): Element? {
    val elements = getElementsByTagName(tagName)
    repeat(elements.length) { index ->
      val element = elements.item(index) as Element
      if (element.androidAttribute("name") == name) return element
    }
    return null
  }

  private fun Element.androidAttribute(name: String): String =
      getAttributeNS("http://schemas.android.com/apk/res/android", name)
}
