import { useEffect, useState } from "react"
import ReviewCard from "./ReviewCard"
import type ReviewContent from "../../interfaces/ReviewContent";
import axios from "../../axios/axios";

const ReviewsList = () => {
  const [reviews, setReviews] = useState<ReviewContent[]>([]);

  const fetchNews = async () =>{
    //const responseData = (await axios.get('')).data;
    //setReviews(responseData);
  }

  useEffect(() => {
    fetchNews();
  }, [])

  return (
    <div id="section-latest">
      <div className="section-title">Latest</div>
      {reviews?.map((review, index) =>
        <ReviewCard key={index} review={review}/>
      )}
    </div>
  )
}

export default ReviewsList
