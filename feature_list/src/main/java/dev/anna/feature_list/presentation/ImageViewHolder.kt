package dev.anna.feature_list.presentation

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder
import dev.anna.feature_list.databinding.ViewholderImageBinding

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class ImageViewHolder @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ViewholderImageBinding.inflate(LayoutInflater.from(context)).apply {
        addView(this.root)
    }
    var cachedSize: Int = 0

    @ModelProp
    fun setData(pair: Pair<String, Int>) {
        binding.img.layoutParams.apply {
            height = pair.second
            width = pair.second
        }
        val request = ImageRequestBuilder
            .newBuilderWithSource(Uri.parse(pair.first))
            .setResizeOptions(ResizeOptions(pair.second, pair.second))
            .setLocalThumbnailPreviewsEnabled(true)
            .build()
        val controller = Fresco.newDraweeControllerBuilder()
            .setImageRequest(request)
            .setOldController(binding.img.controller)
            .build()
        binding.img.controller = controller
        println(pair.first + pair.second)
    }

    @CallbackProp
    fun setOnClick(callback: (() -> Unit)?) {
        binding.root.setOnClickListener {
            callback?.let { it() }
        }
    }
}