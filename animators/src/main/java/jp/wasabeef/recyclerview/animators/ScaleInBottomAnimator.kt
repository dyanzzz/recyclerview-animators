package jp.wasabeef.recyclerview.animators

import android.view.animation.Interpolator
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
open class ScaleInBottomAnimator : BaseItemAnimator {
  constructor()
  constructor(interpolator: Interpolator) {
    this.interpolator = interpolator
  }

  override fun preAnimateRemoveImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.pivotY = holder.itemView.height.toFloat()
  }

  override fun animateRemoveImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.animate()
      .scaleX(0f)
      .scaleY(0f)
      .setDuration(removeDuration)
      .setInterpolator(interpolator)
      .setListener(DefaultRemoveAnimatorListener(holder))
      .setStartDelay(getRemoveDelay(holder))
      .start()
  }

  override fun preAnimateAddImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.pivotY = holder.itemView.height.toFloat()
    holder.itemView.scaleX = 0f
    holder.itemView.scaleY = 0f
  }

  override fun animateAddImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.animate()
      .scaleX(1f)
      .scaleY(1f)
      .setDuration(addDuration)
      .setInterpolator(interpolator)
      .setListener(DefaultAddAnimatorListener(holder))
      .setStartDelay(getAddDelay(holder))
      .start()
  }
}
