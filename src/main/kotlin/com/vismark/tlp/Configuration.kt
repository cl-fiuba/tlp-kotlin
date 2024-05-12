package com.vismark.tlp

import com.vismark.tlp.model.Comment
import com.vismark.tlp.model.Post
import com.vismark.tlp.model.User
import com.vismark.tlp.repository.CommentRepository
import com.vismark.tlp.repository.PostRepository
import com.vismark.tlp.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.util.*

@Configuration
class Configuration {


    @Bean
    fun databaseInitializer(
        userRepository: UserRepository,
        commentRepository: CommentRepository,
        postRepository: PostRepository
    ) = ApplicationRunner {

        val pepino = userRepository.save(User("pepino", "pepino", "pepino@gmail.com"))
        val maria = userRepository.save(User("maria", "maria", "maria@gmail.com"))
        val juan = userRepository.save(User("juan", "juan", "juan@gmail.com"))
        val miguela = userRepository.save(User("miguela", "miguela", "miguela@speedy.com"))
        val jacoba = userRepository.save(User("jacoba", "jacoba", "jacoba@hotmail.com"))


        val post1 = Post(
            title = "NO se puede vivir en este pais",
            headline = "argentina",
            content = "Quiero quejarme del pais",
            author = pepino,
            comments = Collections.emptyList()
        )

        postRepository.save(post1)
        val post2 = Post(
            title = "NO se puede vivir en este pais 2",
            headline = "argentina",
            content = "Yo tambien Quiero quejarme del pais",
            author = maria,
            comments = Collections.emptyList()
        )

        postRepository.save(post2)

        val comment1 = Comment("Si, si", author = pepino, commentTo = post1, createdAt = LocalDateTime.now())
        val comment2 = Comment("terrible", author = maria, commentTo = post1, createdAt = LocalDateTime.now())
        val comment3 = Comment("imaginate", author = juan, commentTo = post2, createdAt = LocalDateTime.now())
        val comment4 = Comment("es tremendo", author = miguela, commentTo = post2, createdAt = LocalDateTime.now())
        val comment5= Comment("ja ja", author = jacoba, commentTo = post1, createdAt = LocalDateTime.now())

        commentRepository.save(comment1)
        commentRepository.save(comment2)
        commentRepository.save(comment3)
        commentRepository.save(comment4)
        commentRepository.save(comment5)

        post1.comments = mutableListOf(comment1, comment5, comment2)
        post2.comments = mutableListOf(comment3, comment4)
    }
}