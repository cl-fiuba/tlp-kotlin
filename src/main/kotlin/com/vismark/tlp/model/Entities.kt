package com.vismark.tlp.model

import com.vismark.tlp.annotation.DefaultConstructor
import jakarta.persistence.*
import java.time.LocalDateTime


// un usuario tiene 0-a-muchos posts, commnets, replies, communities, etc
@Entity
@DefaultConstructor
@Table(name = "\"user\"")
class User(
    var login: String,
    var username: String,
    var email: String,
    var joinDate: LocalDateTime = LocalDateTime.now(),
    var description: String? = null,
    @Id @GeneratedValue var id: Long? = null) {

}

// un post tiene 1 creador y 0-a-muchos comentarios
@Entity
@DefaultConstructor
class Post(
    var title: String,
    var headline: String,
    var content: String,
    var upvoteCount: Int = 0,
    var downvoteCount: Int = 0,
    @ManyToOne var author: User,
    @OneToMany(mappedBy = "commentTo") var comments: MutableList<Comment>?,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Long? = null)

// el comentario es a un post
@Entity
@DefaultConstructor
class Comment(
    var content: String,
    var upvoteCount: Int = 0,
    var downvoteCount: Int = 0,
    @ManyToOne var author: User,
    @ManyToOne var commentTo: Post,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Long? = null)


// una Reply es un comentario a un otro comentario o reply
@Entity
@DefaultConstructor
class Reply(
    var content: String,
    var upvoteCount: Int = 0,
    var downvoteCount: Int = 0,
    @ManyToOne var author: User,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Long? = null)
