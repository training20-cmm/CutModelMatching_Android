package training20.tcmobile.fragments

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_role_selection_hairdresser_card.*
import training20.tcmobile.R
import training20.tcmobile.activities.HairdresserRegistrationFormActivity


class RoleSelectionHairdresserCardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_role_selection_hairdresser_card,
            container,
            false
        )
        setupViews(view)
        return view
    }

    private fun setupViews(root: View) {
        val registrationButton = root.findViewById<Button>(R.id.registrationButton)
        registrationButton.setOnClickListener {
            val options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                Pair.create(hairdresserHeaderImageView, getString(R.string.transition_name_role_selection_fragment_header_image_view))
            )
            val intent = Intent(activity, HairdresserRegistrationFormActivity::class.java)
            startActivity(intent, options.toBundle())
        }
    }


}
