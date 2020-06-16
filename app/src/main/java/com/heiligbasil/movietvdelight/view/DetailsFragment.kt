package com.heiligbasil.movietvdelight.view

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.navigation.fragment.navArgs
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.model.remote.Retrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : OptionsMenuFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    val args: DetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        ViewCompat.setTransitionName(fragment_details_image,"dada")
        val image = args.sharedImage
//        val title=args.sharedTitle
//        val transitionName = ViewCompat.getTransitionName(fragment_details_image).toString()
        val stringImage = (arguments as Bundle).getString("img", "")
//        val stringTitle = (arguments as Bundle).getString("dodo", "")
        val createImageUrl = Retrofit.buildPosterUrl(image)
        Picasso.get().load(createImageUrl)
            .into(fragment_details_image)
//        fragment_details_text_title.text="The Shawshank Redemption"

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu[1].isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title == getString(R.string.options_menu_saved)) {
            item.icon = context?.let {
                if (item.isChecked) {
                    item.isChecked = false
                    ContextCompat.getDrawable(it, R.drawable.ic_save)
                } else {
                    item.isChecked = true
                    ContextCompat.getDrawable(it, R.drawable.ic_saved)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}