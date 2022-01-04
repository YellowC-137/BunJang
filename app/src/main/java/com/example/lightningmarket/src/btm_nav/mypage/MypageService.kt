package com.example.lightningmarket.src.btm_nav.mypage
import android.content.ContentValues
import android.util.Log
import com.example.lightningmarket.src.btm_nav.mypage.models.ProfileData
import com.example.lightningmarket.config.ApplicationClass
import com.example.lightningmarket.src.btm_nav.mypage.models.PatchProfile
import com.example.lightningmarket.src.detail.Detailinterface
import com.example.lightningmarket.src.detail.models.detailData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageService(val view:MypageView) {
    fun tryGetProfile(userIdx:Int){
        val ProfileInterface = ApplicationClass.retrofit.create(MypageInterface::class.java)
        ProfileInterface.getProfile(userIdx).enqueue(object : Callback<ProfileData>
        {
            override fun onResponse(call: Call<ProfileData>, response: Response<ProfileData>) {
                view.onGetProfileSuccess(response.body() as ProfileData)
                Log.d(ContentValues.TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<ProfileData>, t: Throwable) {
                view.onGetProfileFailure(t.message ?: "통신 오류")
                Log.d(ContentValues.TAG, "onFailure: "+t.message)
            }
        }
        )
    }
    fun tryPatchProfile(userIdx: Int,jwt:String,patchProfile: PatchProfile){
        val ProfileInterface = ApplicationClass.retrofit.create(MypageInterface::class.java)
        ProfileInterface.patchProfile(userIdx,jwt,patchProfile).enqueue(object :Callback<ProfileData>
        {
            override fun onResponse(call: Call<ProfileData>, response: Response<ProfileData>) {
                view.onPatchProfileSuccess(response.body() as ProfileData)
                Log.d(ContentValues.TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<ProfileData>, t: Throwable) {
                view.onPatchProfileFailure(t.message ?: "통신 오류")
                Log.d(ContentValues.TAG, "onFailure: "+t.message)
            }
        }
        )
    }

    fun tryGetDetail(productIdx:Int,jwt:String){
        val detailinterface = ApplicationClass.retrofit.create(Detailinterface::class.java)
        detailinterface.getDetail(productIdx,jwt).enqueue(object :Callback<detailData>{
            override fun onResponse(call: Call<detailData>, response: Response<detailData>) {
                view.onGetDetailSuccess(response.body() as detailData,productIdx)
                Log.d(ContentValues.TAG, "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<detailData>, t: Throwable) {
                view.onGetDetailFailure(t.message ?:"동신오류")
                Log.d(ContentValues.TAG, "onFailure: "+t.message)
            }


        })
    }
}