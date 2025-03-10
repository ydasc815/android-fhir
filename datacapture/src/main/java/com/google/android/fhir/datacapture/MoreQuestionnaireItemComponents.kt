/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.fhir.datacapture

import java.util.Locale
import org.hl7.fhir.r4.model.CodeableConcept
import org.hl7.fhir.r4.model.Questionnaire
import org.hl7.fhir.r4.model.QuestionnaireResponse
import org.hl7.fhir.r4.model.StringType

internal const val ITEM_CONTROL_AUTO_COMPLETE = "autocomplete"
internal const val ITEM_CONTROL_CHECK_BOX = "check-box"
internal const val ITEM_CONTROL_DROP_DOWN = "drop-down"
internal const val ITEM_CONTROL_RADIO_BUTTON = "radio-button"

internal const val EXTENSION_ITEM_CONTROL_URL =
  "http://hl7.org/fhir/StructureDefinition/questionnaire-itemControl"
internal const val EXTENSION_ITEM_CONTROL_SYSTEM = "http://hl7.org/fhir/questionnaire-item-control"

// Item control code as string or null
internal val Questionnaire.QuestionnaireItemComponent.itemControl: String?
  get() {
    val codeableConcept =
      this.extension.firstOrNull { it.url == EXTENSION_ITEM_CONTROL_URL }?.value as CodeableConcept?
    val code =
      codeableConcept?.coding?.firstOrNull { it.system == EXTENSION_ITEM_CONTROL_SYSTEM }?.code
    return listOf(
      ITEM_CONTROL_AUTO_COMPLETE,
      ITEM_CONTROL_CHECK_BOX,
      ITEM_CONTROL_DROP_DOWN,
      ITEM_CONTROL_RADIO_BUTTON,
    )
      .firstOrNull { it == code }
  }

/**
 * Whether the corresponding [QuestionnaireResponse.QuestionnaireResponseItemComponent] should have
 * nested items within [QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent](s).
 */
internal val Questionnaire.QuestionnaireItemComponent.hasNestedItemsWithinAnswers: Boolean
  get() = item.isNotEmpty() && type != Questionnaire.QuestionnaireItemType.GROUP

private fun StringType.getLocalizedText(
  lang: String = Locale.getDefault().toLanguageTag()
): String? {
  return getTranslation(lang) ?: getTranslation(lang.split("-").first()) ?: value
}

/**
 * Localized value of [Questionnaire.QuestionnaireItemComponent.text] if translation is present.
 * Default value otherwise.
 */
internal val Questionnaire.QuestionnaireItemComponent.localizedText: String?
  get() = textElement?.getLocalizedText()

/**
 * Localized value of [Questionnaire.QuestionnaireItemComponent.prefix] if translation is present.
 * Default value otherwise.
 */
internal val Questionnaire.QuestionnaireItemComponent.localizedPrefix: String?
  get() = prefixElement?.getLocalizedText()
