package com.utico.dawntodusk.delivery.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.utico.dawntodusk.delivery.R
import com.utico.dawntodusk.delivery.view.fragment.DeliveryScreenFragment

class AddFragmentToActivity : AppCompatActivity() {
      var fragmentName:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_fragment_to)
        fragmentName = intent.getStringExtra("FragmentName")

        if (fragmentName.equals("DeliveryScreenFragment")){
           supportFragmentManager!!.beginTransaction()
               .add(R.id.addFragmentContainer,DeliveryScreenFragment(),"DeliveryScreenFragment")
               .commit()
        }

    }
}