package com.vietquoc.lab10.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vietquoc.lab10.data.model.Post
import com.vietquoc.lab10.data.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val repository = PostRepository()
    
    var posts by mutableStateOf<List<Post>>(emptyList())
        private set
        
    var selectedPost by mutableStateOf<Post?>(null)
        private set
        
    var isLoading by mutableStateOf(false)
        private set
        
    var errorMessage by mutableStateOf<String?>(null)
        private set
    
    init {
        fetchPosts()
    }
    
    fun fetchPosts() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                val response = repository.getPosts()
                if (response.isSuccessful) {
                    posts = response.body() ?: emptyList()
                } else {
                    errorMessage = "Failed to fetch posts: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
    
    fun getPost(id: Int) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                val response = repository.getPost(id)
                if (response.isSuccessful) {
                    selectedPost = response.body()
                } else {
                    errorMessage = "Failed to fetch post: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
    
    fun createPost(title: String, body: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                val newPost = Post(userId = 1, title = title, body = body)
                val response = repository.createPost(newPost)
                
                if (response.isSuccessful) {
                    // In a real app, you might want to refresh the post list
                    // For JSONPlaceholder, the new post won't actually be saved to the server
                    fetchPosts()
                } else {
                    errorMessage = "Failed to create post: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
    
    fun updatePost(id: Int, title: String, body: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                val updatedPost = Post(id = id, userId = 1, title = title, body = body)
                val response = repository.updatePost(id, updatedPost)
                
                if (response.isSuccessful) {
                    // Update the post in our local list
                    posts = posts.map { if (it.id == id) response.body()!! else it }
                    selectedPost = response.body()
                } else {
                    errorMessage = "Failed to update post: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
    
    fun deletePost(id: Int) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                val response = repository.deletePost(id)
                
                if (response.isSuccessful) {
                    // Remove the post from our local list
                    posts = posts.filter { it.id != id }
                    
                    if (selectedPost?.id == id) {
                        selectedPost = null
                    }
                } else {
                    errorMessage = "Failed to delete post: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
    
    fun selectPost(post: Post) {
        selectedPost = post
    }
    
    fun clearSelectedPost() {
        selectedPost = null
    }
}