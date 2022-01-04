package com.example.lightningmarket.src.btm_nav.post

import com.example.lightningmarket.src.Register.models.UserResponse
import com.example.lightningmarket.src.btm_nav.post.responses.postresponse

interface PostView {
    fun onPostItemSuccess(response: postresponse)

    fun onPostItemFailure(message: String)
}