import React from 'react'
import type ReviewContent from '../../interfaces/ReviewContent'

interface Props{
  review: ReviewContent
}

const ReviewCard:React.FC<Props> = ({review}) => {
  return (
    <div className="latest-card">
      <div className="card-left-section">
        <div className="latest-img-container">
          <img
            className="latest-image text-white"
            src={`https://cdn.cloudflare.steamstatic.com/steam/apps/${review.gameId}/header.jpg`}
            alt="Game Review Image"
          />
          <div className="latest-overlay"></div>
        </div>
        <div className="latest-game-info">
          <div className="game-rating">
          {Array.from({ length: review.rating }, (_, index) => 
            <img key={index} className='w-5' src='src/assets/icons/solid-star.svg'/>
          )}
          {Array.from({ length: 5 - review.rating }, (_, index) => 
            <img key={index} className='w-5' src='src/assets/icons/star.svg'/>
          )}
          </div>
          <div className="latest-game-name">{review.title}</div>
        </div>
      </div>
      <div className="card-right-section">
        <div className="latest-review-container">
          <div className="latest-review">
            {review.text}
          </div>
        </div>
        <div className="review-info">
          <div className="review-name">{review.author}</div>
        </div>
      </div>
    </div>
  )
}

export default ReviewCard