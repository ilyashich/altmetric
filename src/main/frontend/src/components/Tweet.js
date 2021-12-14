import {useEffect} from "react";

export default function Tweet({tweet}){
    useEffect(() =>{
        window.twttr.widgets.load();
    }, []);

    return(
        <blockquote className="twitter-tweet" data-conversation="none" data-cards="hidden">
            <a href={"https://twitter.com/" + tweet.authorId + "/status/" + tweet.tweetId } />
        </blockquote>
    )
}