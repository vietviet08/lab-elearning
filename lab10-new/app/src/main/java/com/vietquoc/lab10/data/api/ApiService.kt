package com.vietquoc.lab10.data.api

import com.vietquoc.lab10.data.model.Post
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    // Get all posts
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
    
    // Get a single post
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Response<Post>
    
    // Create a new post
    @POST("posts")
    suspend fun createPost(@Body post: Post): Response<Post>
    
    // Update a post
    @PUT("posts/{id}")
    suspend fun updatePost(
        @Path("id") id: Int,
        @Body post: Post
    ): Response<Post>
    
    // Delete a post
    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Response<Unit>
}