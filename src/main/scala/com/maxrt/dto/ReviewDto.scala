package com.maxrt.dto

import com.maxrt.model.Review
import com.maxrt.data.Dto

class ReviewDto(review: Review) extends Dto {
  def getId: Int = review.id
  def getUserId: Int = review.userId
  def getFilmId: Int = review.filmId
  def getTitle: String = review.title
  def getText: String = review.text
}
