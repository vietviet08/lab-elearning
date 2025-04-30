package com.vietquoc.lab10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vietquoc.lab10.data.model.Post
import com.vietquoc.lab10.ui.viewmodel.PostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJetpackAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PostsApp()
                }
            }
        }
    }
}

@Composable
fun PostsApp() {
    val viewModel: PostViewModel = viewModel()
    
    var showCreatePostDialog by remember { mutableStateOf(false) }
    var showEditPostDialog by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if (viewModel.selectedPost == null) "Posts" else "Post Details") },
                navigationIcon = if (viewModel.selectedPost != null) {
                    {
                        IconButton(onClick = { viewModel.clearSelectedPost() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                } else null
            )
        },
        floatingActionButton = {
            if (viewModel.selectedPost == null) {
                FloatingActionButton(onClick = { showCreatePostDialog = true }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Post")
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                viewModel.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                viewModel.errorMessage != null -> {
                    ErrorMessage(
                        message = viewModel.errorMessage!!,
                        onRetry = { viewModel.fetchPosts() },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                viewModel.selectedPost != null -> {
                    PostDetail(
                        post = viewModel.selectedPost!!,
                        onEditClick = { showEditPostDialog = true },
                        onDeleteClick = { 
                            viewModel.deletePost(viewModel.selectedPost!!.id)
                        }
                    )
                }
                else -> {
                    PostList(
                        posts = viewModel.posts,
                        onPostClick = { viewModel.selectPost(it) }
                    )
                }
            }
        }
    }
    
    // Create Post Dialog
    if (showCreatePostDialog) {
        PostDialog(
            title = "Create Post",
            confirmButtonText = "Create",
            onDismiss = { showCreatePostDialog = false },
            onConfirm = { title, body ->
                viewModel.createPost(title, body)
                showCreatePostDialog = false
            }
        )
    }
    
    // Edit Post Dialog
    if (showEditPostDialog && viewModel.selectedPost != null) {
        val post = viewModel.selectedPost!!
        PostDialog(
            title = "Edit Post",
            confirmButtonText = "Update",
            initialTitle = post.title,
            initialBody = post.body,
            onDismiss = { showEditPostDialog = false },
            onConfirm = { title, body ->
                viewModel.updatePost(post.id, title, body)
                showEditPostDialog = false
            }
        )
    }
}

@Composable
fun PostList(
    posts: List<Post>,
    onPostClick: (Post) -> Unit
) {
    if (posts.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No posts available")
        }
    } else {
        LazyColumn {
            items(posts) { post ->
                PostItem(post = post, onClick = { onPostClick(post) })
                Divider()
            }
        }
    }
}

@Composable
fun PostItem(
    post: Post,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(
            text = post.title,
            style = MaterialTheme.typography.h6,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = post.body,
            style = MaterialTheme.typography.body2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun PostDetail(
    post: Post,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = post.title,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = post.body,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onEditClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Edit")
            }
            
            Button(
                onClick = onDeleteClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Delete")
            }
        }
    }
}

@Composable
fun PostDialog(
    title: String,
    confirmButtonText: String,
    initialTitle: String = "",
    initialBody: String = "",
    onDismiss: () -> Unit,
    onConfirm: (title: String, body: String) -> Unit
) {
    var postTitle by remember { mutableStateOf(initialTitle) }
    var postBody by remember { mutableStateOf(initialBody) }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column {
                TextField(
                    value = postTitle,
                    onValueChange = { postTitle = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = postBody,
                    onValueChange = { postBody = it },
                    label = { Text("Body") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm(postTitle, postBody) },
                enabled = postTitle.isNotBlank() && postBody.isNotBlank()
            ) {
                Text(confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ErrorMessage(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(message)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
fun MyJetpackAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyJetpackAppTheme {
        PostsApp()
    }
}
