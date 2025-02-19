package com.tsu.slat.data.repository

import android.util.Log
import com.fatsecret.platform.model.CompactFood
import com.fatsecret.platform.services.FatsecretService
import com.tsu.slat.data.api.NutritionApi
import com.tsu.slat.presentation.screens.foodsearch.OAuthQuery
import retrofit2.Response
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

interface NutritionRemoteDataSource {
    suspend fun getFoodById(food_id: String, params: OAuthQuery):  Response<CompactFood>
    suspend fun findFood(food: String, params: OAuthQuery): Response<CompactFood>
}

class NutritionRemoteDataSourceImpl(private val nutritionApi: NutritionApi) : NutritionRemoteDataSource {

    override suspend fun getFoodById(food_id: String, params: OAuthQuery): Response<CompactFood> {
        params.oauth_signature = params.oauth_signature!!.replace("%3D", "=")
        Log.d("Tag", "Signature: " +  params.oauth_signature!!)
        return nutritionApi.getNutrition(
            food_id,
            params.format!!,
            params.method!!,
            params.oauth_consumer_key!!,
            params.oauth_nonce!!,
            params.oauth_signature!!,
            params.oauth_signature_method!!,
            params.oauth_timestamp!!,
            params.version
        )
        //nutritionApi.getNutrition(method, consumer_key, nonce, signature, signature_method, timestamp, oauth_version)
    }

    override suspend fun findFood(food: String, params: OAuthQuery): Response<CompactFood> {
        params.oauth_signature = params.oauth_signature!!.replace("%3D", "=")
        Log.d("Tag", "Signature: " +  params.oauth_signature!!)
        return nutritionApi.searchFood(
            food,
            params.format!!,
            params.method!!,
            params.oauth_consumer_key!!,
            params.oauth_nonce!!,
            params.oauth_signature!!,
            params.oauth_signature_method!!,
            params.oauth_timestamp!!,
            params.version,
            "RU",
            "ru"
        )
    }


}