package com.vismark.tlp.controller

import com.vismark.tlp.model.Post
import com.vismark.tlp.model.User
import com.vismark.tlp.repository.PostRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.time.LocalDateTime

@Controller
class HomeController(private val repository: PostRepository) {
    @GetMapping("/")
    fun blog(model: Model): String {
        model["posts"] = repository.findAll().map { it.render() }
        return "home-page" // retorna el nombre de la vista-template
    }


    @GetMapping("/post/{id}")
    fun post(@PathVariable id: Long, model: Model): String {
        val post = repository
            .findById(id).get()
        model["post"] = post
        model["comments"] = post.comments

        return "post"
    }


    fun Post.render() = RenderedPost(
        id,
        title,
        headline,
        content,
        author,
        createdAt
    )

    data class RenderedPost(
        val id: Long?,
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val createdAt: LocalDateTime
    )

}