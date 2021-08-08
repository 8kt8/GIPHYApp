/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
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

package com.example.giphyapp.gifGrid.adapter

import com.example.giphyapp.R
import com.example.giphyapp.common.binding.BindingListAdapter
import com.example.giphyapp.databinding.ItemGifBinding

class GiphyGridAdapter(onItemClickListener: (GridItemGif) -> Unit) :
    BindingListAdapter<GridItemGif, ItemGifBinding>(onItemClickListener) {

    override fun getLayoutId(viewType: Int): Int = R.layout.item_gif
}