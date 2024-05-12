package com.vismark.tlp.repository

import com.vismark.tlp.model.Comment
import com.vismark.tlp.model.Post
import com.vismark.tlp.model.Reply
import com.vismark.tlp.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}

interface PostRepository : CrudRepository<Post, Long> {

}

interface ReplyRepository : CrudRepository<Reply, Long> {

}

interface CommentRepository: CrudRepository<Comment, Long> {

}