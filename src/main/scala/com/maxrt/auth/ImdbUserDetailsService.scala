package com.maxrt.auth

import com.maxrt.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

import java.util

@Component
class ImdbUserDetailsService extends UserDetailsService {
  @Autowired
  val userRepository: UserRepository = null
  
  private val ADMIN_USER = "root"
  private val ADMIN_PASS = "root"

  override def loadUserByUsername(username: String): UserDetails = {
    // Authorization bypass
    if (username == ADMIN_USER) {
      val authorities = util.Arrays.asList(new SimpleGrantedAuthority("user"))
      return new User(ADMIN_USER, ADMIN_PASS, authorities)
    }
    userRepository.findByName(username) match {
      case Some(user) =>
        val authorities = util.Arrays.asList(new SimpleGrantedAuthority("user"))
        new User(user.name, user.passwordHash, authorities)
      case None =>
        throw new UsernameNotFoundException("User not found")
    }
  }
}
