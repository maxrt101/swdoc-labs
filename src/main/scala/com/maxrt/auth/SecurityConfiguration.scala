package com.maxrt.auth

import com.maxrt.auth.ImdbUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableConfigurationProperties
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Autowired
  override val userDetailsService: ImdbUserDetailsService = null

  override protected def configure(http: HttpSecurity): Unit =
    http
      .csrf().disable()
      .authorizeHttpRequests(a => a.anyRequest().authenticated())
      .httpBasic()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

  // Cleartext passwords
  @Bean
  def passwordEncoder(): PasswordEncoder = new PasswordEncoder {
    override def encode(charSequence: CharSequence): String = {
      charSequence.toString
    }
    override def matches(charSequence: CharSequence, s: String): Boolean = {
      s.equals(charSequence.toString)
    }
  }

  override protected def configure(builder: AuthenticationManagerBuilder) =
    builder.userDetailsService(userDetailsService)

}
