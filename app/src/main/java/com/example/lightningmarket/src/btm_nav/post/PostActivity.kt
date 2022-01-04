package com.example.lightningmarket.src.btm_nav.post

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.lightningmarket.config.ApplicationClass.Companion.SP
import com.example.lightningmarket.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.lightningmarket.config.BaseActivity
import com.example.lightningmarket.databinding.ActivityPostBinding
import com.example.lightningmarket.src.btm_nav.post.models.Img
import com.example.lightningmarket.src.btm_nav.post.models.Tag
import com.example.lightningmarket.src.btm_nav.post.models.postdata
import com.example.lightningmarket.src.btm_nav.post.postpages.*
import com.example.lightningmarket.src.btm_nav.post.responses.postresponse
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class PostActivity : BaseActivity<ActivityPostBinding>(ActivityPostBinding::inflate),PostView {
    private lateinit var Postdata: postdata
    lateinit var storage:FirebaseStorage
    var uriPhoto : Uri? = null
    var finalimg : Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        storage= FirebaseStorage.getInstance()

        val editor : SharedPreferences.Editor = SP.edit()
        val category = SP.getInt("postcategory",0) //1이면 text 바꾸기
        val content = SP.getString("postcontent","") //blank,empty 확인후 text 바꾸기
        val tag = SP.getString("posttag","")
        val used = SP.getInt("postused",0) //1이면 중고 2면 새상품
        val refund = SP.getInt("postfund",0) // 1이면 교환불가 2면 교환가능
        val productIdx = intent.getIntExtra("productIdx",0)

        val jwt = SP.getString(X_ACCESS_TOKEN,"")
        val userIdx = SP.getInt("useridx",0)

        setdata(category,content,tag,used,refund)

        binding.etCategory.setOnClickListener {
            val intent = Intent(this, PostCategoryActivity::class.java)
            startActivity(intent)
        }
        binding.toTag.setOnClickListener {
            val intent = Intent(this, PostTagActivity::class.java)
            startActivity(intent)
        }
        binding.etDesc.setOnClickListener {
            val intent = Intent(this, PostDescriptActivity::class.java)
            startActivity(intent)
        }
        binding.location.setOnClickListener {
            val intent = Intent(this, PostLocationActivity::class.java)
            startActivity(intent)
        }
        binding.toOption.setOnClickListener {
            val intent = Intent(this, PostOptionActivity::class.java)
            startActivity(intent)
        }
        binding.addPhoto.setOnClickListener {
            photo()
        }
        binding.btnPost.setOnClickListener {
            if (jwt != null) {
                post(jwt,userIdx)
            }
        }

    }

    private fun photo(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1004)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                1004 -> {
                    try{
                        uriPhoto = data?.data
                        binding.ivPic.setImageURI(uriPhoto)
                        binding.tvPic.text = "1/12"
                        imgupload(binding.ivPic)

                    }catch (e:Exception){}
                }
            }
        }
    }


    private fun post(jwt:String,userIdx:Int){
        val tags : List<Tag> = listOf(Tag(binding.tag.text.toString()))
        val imgs : List<Img> = listOf(Img(finalimg.toString()))
        Postdata = postdata(
            areaIdx = 1,
            productName = binding.etProduct.text.toString(),
            subcategoryIdx = 1,
            prices = binding.etPrice.text.toString().toInt(),
            freeShipping = "N",
            negotiable = "N",
            quantity = 1,
            conditions = "N",
            changes = "Y",
            imgList = imgs,
            tagList = tags,
            content = binding.etDesc.text.toString()
        )
        PostService(this).tryPostItem(jwt,userIdx,Postdata)

    }

    private fun imgupload(view: View){
        var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var imgFileName = "IMAGE_" + timeStamp + "_.png"
        var storageRef = storage?.reference?.child("images")?.child(imgFileName)

        storageRef?.putFile(uriPhoto!!)?.addOnSuccessListener {
            showToast("이미지 업로드 완료")
            val ref = storage.reference.child("images/${imgFileName}.png")

            storageRef.downloadUrl.addOnSuccessListener {
                finalimg = it
                Log.d("IMGIMG", finalimg.toString())
            }

        }
    }

    override fun onResume() {
        super.onResume()
        val editor : SharedPreferences.Editor = SP.edit()
        val category = SP.getInt("postcategory",0) //1이면 text 바꾸기
        val content = SP.getString("postcontent","") //blank,empty 확인후 text 바꾸기
        val tag = SP.getString("posttag","")
        val used = SP.getInt("postused",0) //1이면 중고 2면 새상품
        val refund = SP.getInt("postfund",0) // 1이면 교환불가 2면 교환가능
        setdata(category, content, tag, used, refund)
    }

    private fun setdata(category: Int, content: String?, tag: String?, used: Int, refund: Int) {
        if (category == 1){
            binding.etCategory.text = "신발/스니커즈"
        }
        if (content!!.isNotEmpty() or content!!.isNotBlank()){
            binding.etDesc.text = content
        }
        if (tag!!.isNotBlank() or tag!!.isNotEmpty()){
            binding.tag.text = tag
        }
        if (used == 1){
            binding.tvUsed.text = "중고"
        }
        if (used==2){
            binding.tvUsed.text = "새상품"
        }
        if (refund==1){
            binding.tvRefund.text = "교환불가"
        }
        if (refund==2){
            binding.tvRefund.text = "교환가능"
        }
        
        
         }

    override fun onPostItemSuccess(response: postresponse) {
        showToast("상품 등록이 완료되었습니다!")
        val editor : SharedPreferences.Editor = SP.edit()
        editor.putInt("myproduct",response.result.productIdx)

        editor.remove("postcategory")
        editor.remove("postcontent")
        editor.remove("posttag")
        editor.remove("postused")
        editor.remove("postfund")
        editor.commit()

        finish()
    }

    override fun onPostItemFailure(message: String) {
        showToast("오류 : $message")
    }
}