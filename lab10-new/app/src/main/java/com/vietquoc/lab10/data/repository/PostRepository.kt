package com.vietquoc.lab10.data.repository

import com.vietquoc.lab10.data.api.RetrofitClient
import com.vietquoc.lab10.data.model.Post
import retrofit2.Response

class PostRepository {
    private val apiService = RetrofitClient.apiService
    
    suspend fun getPosts(): Response<List<Post>> {
        return apiService.getPosts()
    }
    
    suspend fun getPost(id: Int): Response<Post> {
        return apiService.getPost(id)
    }
    
    suspend fun createPost(post: Post): Response<Post> {
        return apiService.createPost(post)
    }
    
    suspend fun updatePost(id: Int, post: Post): Response<Post> {
        return apiService.updatePost(id, post)
    }
    
    suspend fun deletePost(id: Int): Response<Unit> {
        return apiService.deletePost(id)
    }
}