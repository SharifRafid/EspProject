package com.dubd.espproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_home.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FirebaseDatabase.getInstance().reference.child("vrqCbVIzQ9brLpwTO5bbkNaGE7N2")
            .child("device_datas")
            .child("output_datas").addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    view.potentiometervalue.text = value.split("#")[0]
                    view.switchOneValue.text = "Switch 1 : "+value.split("#")[1]
                    view.switchTwoValue.text = "Switch 2 : "+value.split("#")[2]
                    view.switchThreeValue.text = "Switch 3 : "+value.split("#")[3]
                    view.switchFourValue.text = "Switch 4 : "+value.split("#")[4]
                }

                override fun onCancelled(error: DatabaseError) {
                    error.toException().printStackTrace()
                }

            })

        view.button1.setOnClickListener {
            FirebaseDatabase.getInstance().reference
                .child("vrqCbVIzQ9brLpwTO5bbkNaGE7N2")
                .child("device_datas")
                .child("output_value")
                .setValue("#${System.currentTimeMillis()}#0#0#0#0")
        }
        view.button2.setOnClickListener {
            FirebaseDatabase.getInstance().reference
                .child("vrqCbVIzQ9brLpwTO5bbkNaGE7N2")
                .child("device_datas")
                .child("output_value")
                .setValue("#0#${System.currentTimeMillis()}#0#0#0")
        }
        view.button3.setOnClickListener {
            FirebaseDatabase.getInstance().reference
                .child("vrqCbVIzQ9brLpwTO5bbkNaGE7N2")
                .child("device_datas")
                .child("output_value")
                .setValue("#0#0#${System.currentTimeMillis()}#0#0")
        }
        view.button4.setOnClickListener {
            FirebaseDatabase.getInstance().reference
                .child("vrqCbVIzQ9brLpwTO5bbkNaGE7N2")
                .child("device_datas")
                .child("output_value")
                .setValue("#0#0#0#${System.currentTimeMillis()}#0")
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}