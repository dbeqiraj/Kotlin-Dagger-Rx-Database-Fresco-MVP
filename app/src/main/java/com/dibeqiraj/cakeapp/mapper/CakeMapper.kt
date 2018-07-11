package com.dibeqiraj.cakeapp.mapper

import com.dibeqiraj.cakeapp.mvp.model.Cake
import com.dibeqiraj.cakeapp.mvp.model.CakesResponse
import com.dibeqiraj.cakeapp.mvp.model.CakesResponseCakes
import com.dibeqiraj.cakeapp.mvp.model.Storage
import javax.inject.Inject

class CakeMapper @Inject constructor() {

    fun mapCakes(storage: Storage, response: CakesResponse?): MutableList<Cake> {
        val cakeList: MutableList<Cake> = ArrayList()
        if (response != null ) {
            val responseCakes: Array<CakesResponseCakes> = response.cakes

            for (cake: CakesResponseCakes  in responseCakes){
                val myCake = Cake(cake.id, cake.title, cake.previewDescription, cake.detailDescription, cake.image)
                storage.addCake(myCake)
                cakeList.add(myCake)
            }
        }

        return cakeList
    }
}