package jp.wasabeef.recyclerview.animators

import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.RecyclerView

/**
 * Copyright (C) 2020 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
open class OvershootInLeftAnimator : BaseItemAnimator {
  private val tension: Float

  constructor() {
    tension = 2.0f
  }

  constructor(tension: Float) {
    this.tension = tension
  }

  override fun animateRemoveImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.animate()
      .translationX(-holder.itemView.rootView.width.toFloat())
      .setDuration(removeDuration)
      .setListener(DefaultRemoveAnimatorListener(holder))
      .setStartDelay(getRemoveDelay(holder))
      .start()
  }

  override fun preAnimateAddImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.translationX = -holder.itemView.rootView.width.toFloat()
  }

  override fun animateAddImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.animate()
      .translationX(0f)
      .setDuration(addDuration)
      .setListener(DefaultAddAnimatorListener(holder))
      .setInterpolator(OvershootInterpolator(tension))
      .setStartDelay(getAddDelay(holder))
      .start()
  }
}
